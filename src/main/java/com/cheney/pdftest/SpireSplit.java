package com.cheney.pdftest;

import com.spire.pdf.PdfDocument;
import com.spire.pdf.utilities.PdfTableExtractor;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author cheney
 * 日期 2024/7/11
 */
public class SpireSplit {

    public static List<PdfDocument> splitDoc(String fileName) {
        List<PdfDocument> docs = new ArrayList<>();
        PdfDocument pdf = new PdfDocument();
        pdf.loadFromFile(fileName);
        int num = pdf.getPages().getCount();
        int copies = num / 10;//份数
        int remainder = num % 10;//取余
        if (remainder > 0) {
            copies++;//取余大于0，份数加1
        }
        for (int j = 0; j < copies; j++) {
            PdfDocument doc = new PdfDocument();
            int start = j * 10;//拆分读取的起始页
            int end = (start + 9) > num - 1 ? num - 1 : start + 9;//结尾页
            doc.insertPageRange(pdf, start, end);//将读取到的数据初始话到新的PdfDocument 里面
            docs.add(doc);
        }
//        pdf.dispose();
        return docs;
    }

}
