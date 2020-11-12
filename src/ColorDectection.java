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
    private static ArrayList<ArrayList<Integer>> yellowList = new ArrayList<ArrayList<Integer>>();
    private static ArrayList<Integer> tmpYellowList = new ArrayList<Integer>();
    private static int tmpYellowPixelX = -1;

    private static GenerateColorListPerPixel FoundYellow = new GenerateColorListPerPixel();
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

        handleYellowList();
        FoundYellow.ReturnPixelArray();
    }

    /*
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

        if      (hsb[1] < 0.1 && hsb[2] > 0.9) whiteFound();
        else if (hsb[2] < 0.1) blackFound();
        else {
            float deg = hsb[0]*360;
            if (deg >=  30 && deg <  90) FoundYellow.GenerateList(pixelX, pixelY);
            else if (deg >=  90 && deg < 150) greenFound();
            else if (deg >= 150 && deg < 210) cyanFound();
            else if (deg >= 210 && deg < 270) blueFound();
            else if (deg >= 270 && deg < 330) magentaFound();
            else redFound();
        }
    }

    private void handleYellowList(){

      // System.out.println(yellowList);
      // Step 1: Check if there is an item in the array
      // Step 2: Save the position of the pixel
      // Step 3: Then when ever the same pixel is found Look for the distance between ?
      // Step 4: if the distance is below certain amount look at the third up coming pixel locations
      // Step 5: if step 4 fails look at the last found pixel and repeat step 2
      // Step 6: if its a row of mutiple pixels in a row save the locations and start looking at the same X but different y to see if its true (But should also work for circles ;-;)
    }

    private void blackFound(){
          //
    }

    private void whiteFound(){
          //
    }

    /*
    @params int pixelX
    @params int pixelY

    if is this is NOT the first time in the loop && of the pixelX and tmp pixel are not the same
    then set tmpYellowPixelX to pixelX
    1. add Pixel y to the list!
    2. and if the tempArray list is smaller then 20 dont add them to the current list cuz its prob alone pixel!
    3. add the new pixelY!
    4. create new arraylist cuz TempPixel and PixelX were not the same so we must have been on a new row!

    else if Check if its the first iteration
    1. just add set tmpYellowPixelX to pixelX
    2. add pixelY to the tmpYellowList

    else just add pixelY to tmpYellowList

    if color is found give pixel X and Y to the corresponding function

    */
    private void yellowFound(int pixelX, int pixelY){
        // if (tmpYellowPixelX != pixelX && tmpYellowPixelX != -1){
        //     tmpYellowPixelX = pixelX;
        //     tmpYellowList.add(pixelY);
        //     if(tmpYellowList.size() > 50) {
        //         yellowList.add(tmpYellowList);
        //         System.out.println(pixelX);
        //         System.out.println(tmpYellowList.size());
        //     }
        //     tmpYellowList = new ArrayList<Integer>();
        // } else if (tmpYellowPixelX != pixelX && tmpYellowPixelX == -1 ) {
        //     tmpYellowPixelX = pixelX;
        //     tmpYellowList.add(pixelY);
        // } else {
        //     tmpYellowList.add(pixelY);
        // }
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
