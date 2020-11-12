package src.helper;

import java.util.ArrayList;
import java.util.List;

public class GenerateColorListPerPixel
{

    private static int pixelIn1RowFilter = 50;
    private static ArrayList<ArrayList<Integer>> pixelYList = new ArrayList<ArrayList<Integer>>();
    private static ArrayList<Integer> tmpList = new ArrayList<Integer>();
    private static ArrayList<Integer> pixelXList = new ArrayList<Integer>();
    private static int tmpPixelX = -1;

    //@todo optimize if possible if we run in performance isseus
    public List<Object> ReturnPixelArray() {
        int[] pixelXArray = pixelXList.stream().mapToInt(Integer::intValue).toArray();
        int[][] pixelYArray = pixelYList.stream().map(u->u.stream().mapToInt(i->i).toArray()).toArray(int[][]::new);

        List<Object> FinalList = new ArrayList<Object>();

        for(int i=0;i < pixelYArray.length;i++){
            List<SubHelperGenerateColorListPerPixel> list = new ArrayList<>();
            SubHelperGenerateColorListPerPixel s = new SubHelperGenerateColorListPerPixel();
            s.pixel_x = pixelXArray[i];
            for(int j=0;j < pixelYArray[i].length; j++)
                s.pixel_y.add(pixelYArray[i][j]);
            list.add(s);
            FinalList.add(list);
        }

        return FinalList;
    }

    /*
    @todo optimize if possible if we run in performance isseus
    @params int pixelX
    @params int pixelY

    if is this is NOT the first time in the loop && of the pixelX and tmp pixel are not the same
    then set tmpPixelX to pixelX
    1. add Pixel y to the list!
    2. and if the tempArray list is smaller then 20 dont add them to the current list cuz its prob alone pixel!
    3. add the new pixelY!
    4. create new arraylist cuz TempPixel and PixelX were not the same so we must have been on a new row!

    else if Check if its the first iteration
    1. just add set tmpPixelX to pixelX
    2. add pixelY to the tmpList

    else just add pixelY to tmpList

    if color is found give pixel X and Y to the corresponding function

    */
    public void GenerateList(int pixelX, int pixelY){
        tmpList.add(pixelY);
        if (tmpPixelX != pixelX && tmpPixelX != -1){
            tmpPixelX = pixelX;
            pixelXList.add(pixelX);
            if(tmpList.size() > pixelIn1RowFilter) pixelYList.add(tmpList);
            tmpList = new ArrayList<Integer>();
        } else if (tmpPixelX != pixelX && tmpPixelX == -1) {
            pixelXList.add(pixelX);
            tmpPixelX = pixelX;
        }
    }
}

class SubHelperGenerateColorListPerPixel {
    public int pixel_x;
    public List<Integer> pixel_y;

    public SubHelperGenerateColorListPerPixel() {
        pixel_x = 0;
        pixel_y = new ArrayList<>();
    }

    @Override
    public String toString() {
        return pixel_x+ " "+pixel_y;
    }
}
