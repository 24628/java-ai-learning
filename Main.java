import java.awt.*;
import javax.swing.*;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.image.BufferedImage;

import src.ScreenShot;
import src.ColorDectection;

public class Main {

  private static JFrame frame = new JFrame("Simple GUI");

  private static JLabel label = new JLabel();

  private static int ScreenHeight = 864;

  private static int ScreenWidth = 1152;

  private static void initWindow()
  {
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setPreferredSize(new Dimension(ScreenWidth, ScreenHeight));
      frame.setLocationRelativeTo(null);
      frame.pack();
      frame.getContentPane().add(label);
      frame.setVisible(true);
  }

  public static void main(String[] args) {
      initWindow();

        ScreenShot ScreenShot = new ScreenShot();
        ColorDectection colorDec = new ColorDectection();

        while(true) {
            try {
              long startTime = System.currentTimeMillis();
              Image img = ScreenShot.SCapture(ScreenWidth, ScreenHeight);
              label.setIcon(new ImageIcon(img));
              colorDec.detectColorInImage(img, ScreenWidth, ScreenHeight);
              long endTime = System.currentTimeMillis();
              System.out.println("loop took this amount of miliseconds: " + (endTime - startTime));
            } catch (Exception ex) {
              ex.printStackTrace();
            }
        }
    }
}
