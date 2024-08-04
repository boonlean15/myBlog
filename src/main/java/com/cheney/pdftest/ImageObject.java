package com.cheney.pdftest;

import lombok.Data;

import java.awt.image.BufferedImage;

/**
 * @author cheney
 * 日期 2024/7/9
 */
@Data
public class ImageObject {
    private int index;
    private String suffix;
    private BufferedImage image;
}
