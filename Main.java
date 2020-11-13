import java.awt.*;
import javax.swing.*;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.swing.border.*;
import javax.accessibility.*;
import java.awt.event.*;

import src.ScreenShot;
import src.ColorDectection;
import src.KeyInputs;
import src.helper.GarbishColleter;
import src.helper.ConvertImage;


public class Main {

    private static ScreenShot ScreenShot = new ScreenShot();
    private static ColorDectection colorDec = new ColorDectection();
    private static KeyInputs keyInputs = new KeyInputs();
    private static GarbishColleter garbishColleter = new GarbishColleter();
    private static ConvertImage cvImg = new ConvertImage();

    private static JFrame frame = new JFrame("Image Rendering");
    private static JLabel imageLabel = new JLabel();

    private static int ScreenHeight = 864;
    private static int ScreenWidth = 1152;

    private static boolean loop = true;

    private static void initWindow() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(ScreenWidth, ScreenHeight));
        frame.setLocationRelativeTo(null);
        frame.add(imageLabel);
        frame.pack();
        frame.setVisible(true);
    }

    private static Image drawOnImageTest(Image img)  {

        BufferedImage bufferedImage = cvImg.convertImage(img);
        Graphics g = bufferedImage.getGraphics();

        g.setColor(Color.RED);
        g.fillRect(50,50,50,50);
        g.dispose();

        return bufferedImage;
    }

    public static void main(String[] args) {
        initWindow();

        while (loop) {
            try {
                long startTime = System.currentTimeMillis();
                Image img = ScreenShot.SCapture(ScreenWidth, ScreenHeight);
                Color[][] colors = colorDec.makeArrayOfColorToPixelGrid(img, ScreenWidth, ScreenHeight);

                Image img2 = drawOnImageTest(img);
                imageLabel.setIcon(new ImageIcon(img2));

                long endTime = System.currentTimeMillis();
                System.out.println("loop took this amount of miliseconds: " + (endTime - startTime));

                garbishColleter.cleanUp();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}


