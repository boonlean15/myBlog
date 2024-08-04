package com.cheney.pdftest;

import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;
import org.apache.pdfbox.text.TextPosition;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author cheney
 * 日期 2024/7/9
 */
@Slf4j
public class PdfTest {


//    public static void main(String[] args) throws IOException {
//        PDDocument document = Loader.loadPDF(new File("/Users/linchuangwei/Documents/work/rj/jtt/开发/24-07-09/电费账单10月.pdf"));
//        PDFTextStripper stripper = new PDFTextStripper();
//        String text = stripper.getText(document);
//        System.out.println("开始输出");
//        System.out.println(text);
//        document.close();
//    }

    /**
     * 获取文档坐标
     * @param  file PDF文件对象
     * @param sourceTex 匹配的字符
     * @return 坐标
     */
    public static Point getPoint(File file,String sourceTex) {
        Point point = new Point();
        //获取文档坐标
        try {
            PDDocument document =  Loader.loadPDF(file);
            PDFTextStripper textStripper = new PDFTextStripper(){
                @Override
                protected void writeString(String text, List<TextPosition> textPositions) throws IOException {
                    if (text.contains("计量点编号")) {
                        this.output.write(text);
                    }
//                    if (text.contains("表计资产编号")) {
//                        TextPosition textPositionStart = textPositions.get(0);
//                        TextPosition textPositionEnd = textPositions.get(textPositions.size()-1);
//                        log.info("text ---- {}",text);
//                        log.info("textPositionStart ---- {}",textPositionStart);
//                        log.info("textPositionStart ---- x:" + textPositionStart.getX() + "  --- y:"
//                                + textPositionStart.getY() + "---- endX:" + textPositionStart.getEndX()
//                                + " ---- endY:" + textPositionStart.getEndY() + " ---- height:"
//                                + textPositionStart.getHeight() + " --- width:" + textPositionStart.getWidth());
//                        log.info("textPositionEnd ---- {}",textPositionEnd);
//                        log.info("textPositionEnd ---- x:" + textPositionEnd.getX() + "  --- y:"
//                                + textPositionEnd.getY() + "---- endX:" + textPositionEnd.getEndX()
//                                + " ---- endY:" + textPositionEnd.getEndY() + " ---- height:"
//                                + textPositionEnd.getHeight() + " --- width:" + textPositionEnd.getWidth());
//                    }
                }
            };
//            PDFTextStripper textStripper = new PDFTextStripper();


            int numberOfPages = document.getNumberOfPages();
            for (int page = 1; page <= numberOfPages; ++page)
            {
                textStripper.setSortByPosition(true);
                textStripper.setStartPage(page);
                textStripper.setEndPage(page);
                String text = textStripper.getText(document);
                log.info("text  ---- {}",text);
            }

//            textStripper.setSortByPosition(true);
//            textStripper.setStartPage(1);
//            textStripper.setEndPage(document.getNumberOfPages());
//
//            textStripper.getText(document);

            document.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return point;
    }


    public static void main(String[] args) {
        File file = new File("/Users/linchuangwei/Documents/work/rj/jtt/开发/24-07-09/电费账单10月.pdf");
//        Point jldNum = getPoint(file, "计量点编号");
//
        Point dlNum = getPoint(file, "千瓦时");

//        Point dlxxNum = getPoint(file, "电量信息 Electricity Consumption Details");
//
//        Point dfNum = getPoint(file,"电费信息 Electricity Bill Information");

        log.info("电量信息位置：{}", dlNum);//电量信息位置：Point(x=4, y=255)

    }
//    public static void main(String[] args) {
//        String filePath = "/Users/linchuangwei/Documents/work/rj/jtt/开发/24-07-09/电费账单10月.pdf";
//        try {
//            File file = new File(filePath);
//            PDDocument document = Loader.loadPDF(file);
//            PDFTextStripperByArea  textStripper = new PDFTextStripperByArea ();
//            Rectangle rectangle = new Rectangle(4,255, 250,10);
//            String regionName = "regionName";
//            textStripper.addRegion(regionName, rectangle);
//            PDPage page = document.getPage(0);
//            textStripper.extractRegions(page);
//            String text = textStripper.getTextForRegion(regionName);
//
//            log.info("text ----- {}", text);
//
//            textStripper.setSortByPosition(true);
//            textStripper.setStartPage(1);
//            textStripper.setEndPage(document.getNumberOfPages());
//            textStripper.getText(document);
//            document.close();
//        }catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }




}
