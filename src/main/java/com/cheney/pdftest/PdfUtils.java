package com.cheney.pdftest;

import com.google.common.collect.Lists;
import org.apache.commons.io.FileUtils;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.io.RandomAccessRead;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDResources;
import org.apache.pdfbox.pdmodel.common.PDStream;
import org.apache.pdfbox.pdmodel.graphics.PDXObject;
import org.apache.pdfbox.pdmodel.graphics.image.PDImage;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.text.PDFTextStripper;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.List;

/**
 * PDF处理
 *
 */
public class PdfUtils {
    public static String text(byte[] data) throws Exception {
        return PdfUtils.text(data, true);
    }
    public static String text(byte[] data, boolean sortByPosition) throws Exception {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(data);
        return PdfUtils.text(inputStream, sortByPosition);
    }
    /**
     * 使用pdfbox提取PDF文本（解析正常，可使用）
     *
     * @param file
     * @return
     * @throws IOException
     */
    public static String text(File file, boolean sortByPosition) throws Exception {
        InputStream inputStream = new FileInputStream(file);
        return PdfUtils.text(inputStream, sortByPosition);
    }
    public static String text(File file) throws Exception {
        return PdfUtils.text(file, true);
    }
    public static String text(InputStream inputStream) throws Exception {
        return text(inputStream, true);
    }
    /**
     * 使用pdfbox提取PDF文本（解析正常，可使用）
     *
     * @param inputStream
     * @return
     * @throws IOException
     */
    public static String text(InputStream inputStream, boolean sortByPosition) throws Exception {
        PDDocument document = null;
        try {
//            document = PDDocument.load(inputStream);
            document = Loader.loadPDF((RandomAccessRead) inputStream);
            PDFTextStripper textStripper = new PDFTextStripper();
            // Get total page count of the PDF document
            int numberOfPages = document.getNumberOfPages();
            //set the first page to be extracted
            textStripper.setStartPage(1);
            // set the last page to be extracted
            textStripper.setEndPage(numberOfPages);
            // 获取文本内容
            textStripper.setSortByPosition(sortByPosition);
            textStripper.setShouldSeparateByBeads(true);
            return StrUtils.removeReturnChar(textStripper.getText(document));
        } finally {
            CloseUtils.closeQuietly(document, inputStream);
        }
    }

    /**
     * 使用pdfbox提取PDF文本（解析正常，可使用）
     *
     * @param file
     * @return
     * @throws IOException
     */
    public static List<ImageObject> images(File file) throws IOException {
        InputStream inputStream = new FileInputStream(file);
        return PdfUtils.images(inputStream);
    }

    public static List<ImageObject> images(byte[] data) throws IOException {
        ByteArrayInputStream inputStream = null;
        try {
            inputStream = new ByteArrayInputStream(data);
            return PdfUtils.images(inputStream);
        } finally {
            CloseUtils.closeQuietly(inputStream);
        }
    }

    /**
     * 使用pdfbox提取PDF图片列表
     *
     * @param inputStream
     * @return
     * @throws IOException
     */
    public static List<ImageObject> images(InputStream inputStream) throws IOException {
        List<ImageObject> imageList = Lists.newArrayList();
        PDDocument document = null;
        try {
//            document = PDDocument.load(inputStream);
            document = Loader.loadPDF((RandomAccessRead) inputStream);
            // get resources for a page
            PDResources pdResources = document.getPage(0).getResources();
            int i = 0;
            for (COSName csName : pdResources.getXObjectNames()) {
//                System.out.println(i+":"+csName);
                PDXObject pdxObject = pdResources.getXObject(csName);
                if (pdxObject instanceof PDImageXObject) {
//                    i++;
                    PDStream pdStream = pdxObject.getStream();
                    PDImageXObject image = new PDImageXObject(pdStream, pdResources);
                    String imageSuffix = imageSuffix(image);
                    // image storage location and image name
                    BufferedImage bufferedImage = image.getImage();
                    ImageObject object = new ImageObject();
                    object.setIndex(i++);
                    object.setImage(bufferedImage);
                    object.setSuffix(imageSuffix);
                    imageList.add(object);
                }
            }
        } finally {
            CloseUtils.closeQuietly(document, inputStream);
        }
        return imageList;
    }

    /**
     * 获取图片后缀
     *
     * @param pdImage
     * @return
     * @throws IOException
     */
    private static String imageSuffix(PDImageXObject pdImage) throws IOException {
        String suffix = pdImage.getSuffix();
        if (suffix == null || "jb2".equals(suffix)) {
            suffix = "png";
        } else if ("jpx".equals(suffix)) {
            // use jp2 suffix for file because jpx not known by windows
            suffix = "jp2";
        }

        if (hasMasks(pdImage)) {
            // TIKA-3040, PDFBOX-4771: can't save ARGB as JPEG
            suffix = "png";
        }
        return suffix;
    }

    private static boolean hasMasks(PDImage pdImage) throws IOException {
        if (pdImage instanceof PDImageXObject) {
            PDImageXObject ximg = (PDImageXObject) pdImage;
            return ximg.getMask() != null || ximg.getSoftMask() != null;
        }
        return false;
    }

    /**
     * 保存图片到指定文件夹
     *
     * @param imageList
     * @param dir
     * @param prefixName
     * @throws IOException
     */
    public static void saveImage(List<ImageObject> imageList, String dir, String prefixName) throws IOException {
        File imgDir = new File(dir);
        FileUtils.forceMkdir(imgDir);
        for(ImageObject image:imageList){
            File imgFile = new File(dir, prefixName+"_"+image.getIndex()+"."+image.getSuffix());
            ImageIO.write(image.getImage(), image.getSuffix(), imgFile);
        }
    }
}

