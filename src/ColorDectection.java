package src;

import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.util.List;
import java.util.Arrays;

import src.helper.ConvertImage;

public class ColorDectection {

    private static ConvertImage cvImg = new ConvertImage();
    /**
     * @param img
     * @param w
     * @param h
     * @return
     */
    public static Color[][] makeArrayOfColorToPixelGrid(Image img, int w, int h) {
        BufferedImage conImg = cvImg.convertImage(img);
        Color[][] colors = new Color[w][h];

        for (int x = 0; x < w; x++) {
            for (int y = 0; y < h; y++) {
                colors[x][y] = new Color(conImg.getRGB(x, y));
            }
        }

        return colors;
    }
}
