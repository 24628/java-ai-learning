package src;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.awt.*;
import javax.swing.*;

public class ColorDectection
{
    public void detectColorInImage(Image img) {
        BufferedImage conImg = convertImage(img);
        int rgb = conImg.getRGB(500,500);

        float hsb[] = new float[3];
        int r = (rgb >> 16) & 0xFF;
        int g = (rgb >>  8) & 0xFF;
        int b = (rgb      ) & 0xFF;
        Color.RGBtoHSB(r, g, b, hsb);

        if      (hsb[1] < 0.1 && hsb[2] > 0.9) System.out.println("white");
        else if (hsb[2] < 0.1) System.out.println("black");
        else {
            float deg = hsb[0]*360;
            if      (deg >=   0 && deg <  30) System.out.println("red");
            else if (deg >=  30 && deg <  90) System.out.println("yellow");
            else if (deg >=  90 && deg < 150) System.out.println("green");
            else if (deg >= 150 && deg < 210) System.out.println("cyan");
            else if (deg >= 210 && deg < 270) System.out.println("blue");
            else if (deg >= 270 && deg < 330) System.out.println("magenta");
            else System.out.println("red");
        }
    }

    private static BufferedImage convertImage(Image img) {
        if (img instanceof BufferedImage) return (BufferedImage) img;
        BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();
        return bimage;
    }
}
