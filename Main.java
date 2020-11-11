import java.awt.*;
import javax.swing.*;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.image.BufferedImage;

import src.ScreenShot;

public class Main {

  public static JFrame frame = new JFrame("Simple GUI");

  public static JLabel label = new JLabel();

  private static int ScreenHeight = 300;

  private static int ScreenWidth = 400;

  private static void createWindow()
  {
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setPreferredSize(new Dimension(ScreenWidth, ScreenHeight));
      frame.setLocationRelativeTo(null);
      frame.pack();
      frame.getContentPane().add(label);
      frame.setVisible(true);
  }

  public static void main(String[] args) {
      createWindow();

      ScreenShot ScreenShot = new ScreenShot();
      try {
        Image img = ScreenShot.SCapture(ScreenWidth, ScreenHeight);
        label.setIcon(new ImageIcon(img));
      } catch (Exception ex) {
        ex.printStackTrace();
      }
  }
}
