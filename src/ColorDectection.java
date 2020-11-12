package src;

import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.*;
import java.awt.*;
import javax.swing.*;;
import java.util.List;
import java.util.Arrays;

import src.helper.GenerateColorListPerPixel;

public class ColorDectection
{
    private static GenerateColorListPerPixel WhiteFound = new GenerateColorListPerPixel();
    private static GenerateColorListPerPixel BlackFound = new GenerateColorListPerPixel();
    private static GenerateColorListPerPixel YellowFound = new GenerateColorListPerPixel();
    private static GenerateColorListPerPixel GreenFound = new GenerateColorListPerPixel();
    private static GenerateColorListPerPixel CyanFound = new GenerateColorListPerPixel();
    private static GenerateColorListPerPixel BlueFound = new GenerateColorListPerPixel();
    private static GenerateColorListPerPixel MagentaFound = new GenerateColorListPerPixel();
    private static GenerateColorListPerPixel RedFound = new GenerateColorListPerPixel();
    /*
    @params Image img
    @params int w
    @params int h

    Confert notmal image to BufferedImage
    Then set the width and height to image pixels cause image is always full screen
    Loop all the pixels

    */
    public void detectColorInImage(Image img, int w, int h) {
        BufferedImage conImg = convertImage(img);
        int[][] pixels = new int[w][h];

        for( int i = 0; i < w; i++ )
            for( int j = 0; j < h; j++ )
                selectColor(conImg, i,j);

        // System.out.println(YellowFound.ReturnPixelArray());
    }

    /*
    @todo optimize if possible if we run in performance isseus
    @params Image img
    @params int pixelX
    @params int pixelY

    get int rgb of pixel on location
    transfer RGB to HSF
    if color is found give pixel X and Y to the corresponding function

    */
    private void selectColor(BufferedImage img, int pixelX, int pixelY){
        int rgb = img.getRGB(pixelX,pixelY);

        float hsb[] = new float[3];
        int r = (rgb >> 16) & 0xFF;
        int g = (rgb >>  8) & 0xFF;
        int b = (rgb      ) & 0xFF;
        Color.RGBtoHSB(r, g, b, hsb);

        if      (hsb[1] < 0.1 && hsb[2] > 0.9) WhiteFound.GenerateList(pixelX, pixelY);
        else if (hsb[2] < 0.1) BlackFound.GenerateList(pixelX, pixelY);
        else {
            float deg = hsb[0]*360;
            if (deg >=  30 && deg <  90) YellowFound.GenerateList(pixelX, pixelY);
            else if (deg >=  90 && deg < 150) GreenFound.GenerateList(pixelX, pixelY);
            else if (deg >= 150 && deg < 210) CyanFound.GenerateList(pixelX, pixelY);
            else if (deg >= 210 && deg < 270) BlueFound.GenerateList(pixelX, pixelY);
            else if (deg >= 270 && deg < 330) MagentaFound.GenerateList(pixelX, pixelY);
            else RedFound.GenerateList(pixelX, pixelY);
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
