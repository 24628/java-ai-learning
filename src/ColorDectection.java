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
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

public class ColorDectection
{
    private static int[][] yellowArray;

    public void detectColorInImage(Image img, int w, int h) {
        BufferedImage conImg = convertImage(img);
        int[][] pixels = new int[w][h];

        for( int i = 0; i < w; i++ )
            for( int j = 0; j < h; j++ )
                selectColor(conImg, i,j);
    }

    private void selectColor(BufferedImage img, int pixelW, int pixelH){
        int rgb = img.getRGB(pixelW,pixelH);

        float hsb[] = new float[3];
        int r = (rgb >> 16) & 0xFF;
        int g = (rgb >>  8) & 0xFF;
        int b = (rgb      ) & 0xFF;
        Color.RGBtoHSB(r, g, b, hsb);

        if      (hsb[1] < 0.1 && hsb[2] > 0.9) whiteFound();
        else if (hsb[2] < 0.1) blackFound();
        else {
            float deg = hsb[0]*360;
            if (deg >=  30 && deg <  90) yellowFound(pixelW, pixelY);
            else if (deg >=  90 && deg < 150) greenFound();
            else if (deg >= 150 && deg < 210) cyanFound();
            else if (deg >= 210 && deg < 270) blueFound();
            else if (deg >= 270 && deg < 330) magentaFound();
            else redFound();
        }
    }

    private void blackFound(){
          //
    }

    private void whiteFound(){
          //
    }

    private void yellowFound(int pixelX, int pixelY){
      // Step 1: Check if there is an item in the array
      // Step 2: Save the position of the pixel
      // Step 3: Then when ever the same pixel is found Look for the distance between ?
      // Step 4: if the distance is below certain amount look at the third up coming pixel locations
      // Step 5: if step 4 fails look at the last found pixel and repeat step 2
      // Step 6: if its a row of mutiple pixels in a row save the locations and start looking at the same X but different y to see if its true (But should also work for circles ;-;)
    }

    private void greenFound(){
          //
    }

    private void cyanFound(){
          //
    }

    private void blueFound(){
          //
    }

    private void magentaFound(){
          //
    }

    private void redFound(){
          //
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
