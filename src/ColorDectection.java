package src;

import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.*;
import java.awt.*;
import javax.swing.*;;
import java.util.List;
import java.util.Arrays;

public class ColorDectection {

    /**
     * @param img
     * @param w
     * @param h
     * @return
     */
    public static Color[][] loadPixelsFromImage(Image img, int w, int h) {
        BufferedImage conImg = convertImage(img);
        Color[][] colors = new Color[w][h];

        for (int x = 0; x < w; x++) {
            for (int y = 0; y < h; y++) {
                colors[x][y] = new Color(conImg.getRGB(x, y));
            }
        }

        return colors;
    }

    /**
     * Convert image to buffered image to use
     *
     * @param img
     * @return
     */
    private static BufferedImage convertImage(Image img) {
        if (img instanceof BufferedImage) return (BufferedImage) img;
        BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();
        return bimage;
    }
}
