package com.cheney.pdftest;

import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.alibaba.fastjson.JSON;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;
import org.apache.pdfbox.text.TextPosition;


import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;

/**
 * Desc: 验证pdfbox的可行性
 *
 * @author admin
 * @date since 2023/8/8 18:44
 */

public class PdfDemo {
    //要匹配的位置内容点
    private  static final String[] target= {"计量点编号", "电量信息"};
    public static void main(String[] args) {
        ExcelWriter excelWriter= ExcelUtil.getWriter("/Users/linchuangwei/Documents/work/rj/jtt/开发/24-07-09/电费账单10月.xls");
        String folderPath = "/Users/linchuangwei/Documents/work/rj/jtt/开发/24-07-09";
        File folder = new File(folderPath);
        if (folder.exists() && folder.isDirectory()) {
            List<Map<String,Object>>  mps =  listPdfFiles(folder);
            excelWriter.write(mps, true);
        } else {
            System.out.println("Invalid folder path.");
        }
        excelWriter.close();
    }
    /**
     * 获取pdf文件列表
     *
     * @param folder 文件夹
     * @return {@code List<Map<String,Object>>}
     */
    private static  List<Map<String,Object>>  listPdfFiles(File folder) {
        List<Map<String,Object>> mps = new ArrayList<>();
        File[] files = folder.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    listPdfFiles(file); // 递归调用，处理子文件夹
                } else {
                    String fileName = file.getName();
                    if (fileName.toLowerCase().endsWith(".pdf")) {
                        mps.add(getLineData(file));
                    }
                }
            }
        }
        return mps;
    }
    /**
     * 行数据
     *
     * @param file 文件
     * @return {@code Map<String,Object>}
     */
    public static Map<String,Object> getLineData(File file){
        Map<String,Object> lineData = new HashMap<>(target.length+2);
        List<Point> pointList =  getPoint(file);
        String[]  arr=  getPointValue(file, pointList.stream().map(s -> new Rectangle(s.getX(), s.getY(), 260, 10)).toArray(Rectangle[]::new));
        if(arr.length>=target.length) {
            for(int i=0;i<target.length;i++)
            {
                lineData.put(target[i], arr[i]);
            }
            lineData.put("fileName", file.getName().toLowerCase().replace(".pdf", ""));
        }
        return lineData;
    }
    /**
     * 获得PDF指定坐标点文本值
     *
     * @param file       文件
     * @param rectangles 矩形坐标
     * @return {@code String[]}
     */
    public  static String[] getPointValue( File file,Rectangle... rectangles){
        String[] textArr = new String[rectangles.length];
        // String text="";
        try {
            PDDocument document = Loader.loadPDF(file);
            PDFTextStripperByArea  textStripper = new PDFTextStripperByArea ();

            for(int i = 0; i < rectangles.length;i++   ) {
                Rectangle rectangle =rectangles[i];
                String regionName = "regionName"+rectangle.getX()+rectangle.getY();
                textStripper.addRegion(regionName, rectangle);
                PDPage page = document.getPage(0);
                textStripper.extractRegions(page);
                // 获取区域的text
                String text = textStripper.getTextForRegion(regionName);
                text = text.replace("\u0000","-").replace(" ","");
                System.out.println(">>text"+text);
                textArr[i]=text;
            }

            textStripper.setSortByPosition(true);
            textStripper.setStartPage(1);
            textStripper.setEndPage(document.getNumberOfPages());

            textStripper.getText(document);

            document.close();
        }catch (IOException e) {
            e.printStackTrace();
        }

        return  textArr;
    }

    public  static List<Point> getPoint(File file){
        List<Point> pointList=new ArrayList<>();
        try {
            PDDocument document =  Loader.loadPDF(file);
            PDFTextStripper textStripper = new PDFTextStripper() {
                @Override
                protected void writeString(String text, List<TextPosition> textPositions) throws IOException {
                    for(String target:target){
                        if (text.contains(target)) {
                            Point point = new Point();
                            TextPosition textPositionEnd = textPositions.get(textPositions.size() - 1);
                            point.setX((int) textPositionEnd.getEndX());
                            point.setY((int) textPositionEnd.getY());
                            pointList.add(point);
                        }
                    }
                }
            };

            textStripper.setSortByPosition(true);
            textStripper.setStartPage(1);
            textStripper.setEndPage(document.getNumberOfPages());
            textStripper.getText(document);
            document.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(">>>>>pointList" + JSON.toJSONString(pointList));
        return pointList;
    }
}


