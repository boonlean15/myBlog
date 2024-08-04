package com.cheney.pdftest;

import com.spire.pdf.*;
import com.spire.pdf.texts.*;
import com.spire.pdf.utilities.PdfTable;
import com.spire.pdf.utilities.PdfTableExtractor;
import lombok.extern.slf4j.Slf4j;

import java.io.FileWriter;
import java.util.EnumSet;
import java.util.List;

/**
 * @author cheney
 * 日期 2024/7/9
 */
@Slf4j
public class SpirePdfTest {
//    public static void main(String[] args) {
//        // 加载pdf文件
//        PdfDocument pdf = new PdfDocument();
//        pdf.loadFromFile("/Users/linchuangwei/Documents/work/rj/jtt/开发/24-07-09/电费账单10月.pdf");
//
//        //保存为Excel文档
//        pdf.saveToFile("/Users/linchuangwei/Documents/work/rj/jtt/开发/24-07-09/电费账单10月.xlsx", FileFormat.XLSX);
//        pdf.dispose();
//    }

    // SpirePdf
    public static void main(String[] args) {
        String fileName = "/Users/linchuangwei/Documents/work/rj/jtt/开发/24-07-09/电费账单10月的副本.pdf";
        List<PdfDocument> pdfDocuments = SpireSplit.splitDoc(fileName);
        for(PdfDocument pdf : pdfDocuments){
            PdfTableExtractor extractor = new PdfTableExtractor(pdf);
            PdfTable[] tableLists ;
            for (int page = 0; page < pdf.getPages().getCount(); page++) {
                tableLists = extractor.extractTable(page);
                if (tableLists != null && tableLists.length > 0) {
                    for (PdfTable table : tableLists){
                        int row = table.getRowCount();
                        int column = table.getColumnCount();
                        for (int i = 0; i < row; i++)
                        {
                            for (int j = 0; j < column; j++)
                            {
                                String text = table.getText(i, j);
                                log.info("page --- {}, row --- {}, column --- {}  text --- {}",page,row,column,text);
                                log.info("i ---- {} j ------ {}", i,j);
                            }
                        }
                    }
                }
            }
            pdf.dispose();
        }
    }

//    public static void main(String[] args) throws Exception {
//        String outputFile="/Users/linchuangwei/Documents/work/rj/jtt/开发/24-07-09/电费账单10月.txt";
//        PdfDocument pdfDocument = new PdfDocument();
//        pdfDocument.loadFromFile("/Users/linchuangwei/Documents/work/rj/jtt/开发/24-07-09/电费账单10月.pdf");
//        StringBuilder builder = new StringBuilder();
//        PdfTableExtractor extractor = new PdfTableExtractor(pdfDocument);
//        PdfTable[] tableLists;
//        for (int pageIndex = 0; pageIndex < pdfDocument.getPages().getCount(); pageIndex++) {
//            tableLists = extractor.extractTable(pageIndex);
//            if (tableLists != null && tableLists.length > 0) {
//                for (PdfTable table : tableLists) {
//                    int row = table.getRowCount();
//                    int column = table.getColumnCount();
//                    for (int i = 0; i < row; i++) {
//                        for (int j = 0; j < column; j++) {
//                            String text = table.getText(i, j);
//                            builder.append(text + "  ");
//                        }
//                        builder.append("\r\n");
//                    }
//                }
//            }
//        }
//        FileWriter fileWriter = new FileWriter(outputFile);
//        fileWriter.write(builder.toString());
//        fileWriter.flush();
//        fileWriter.close();
//    }

}
