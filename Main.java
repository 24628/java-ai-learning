import java.awt.*;
import javax.swing.*;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.image.BufferedImage;
import java.lang.ref.WeakReference;

import src.ScreenShot;
import src.ColorDectection;
import src.KeyInputs;

public class Main {

    private static JFrame frame = new JFrame("Image Rendering");
    private static JLabel label = new JLabel();

    private static int ScreenHeight = 864;
    private static int ScreenWidth = 1152;

    private static boolean loop = true;

    private static void initWindow() {
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
        KeyInputs keyInputs = new KeyInputs();

        while (loop) {
            try {
                long startTime = System.currentTimeMillis();
                Image img = ScreenShot.SCapture(ScreenWidth, ScreenHeight);
                label.setIcon(new ImageIcon(img));
                colorDec.detectColorInImage(img, ScreenWidth, ScreenHeight);
                long endTime = System.currentTimeMillis();
                System.out.println("loop took this amount of miliseconds: " + (endTime - startTime));

                gc();
                // keyInputs.test();
                // loop = false;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * This method guarantees that garbage collection is
     * done unlike <code>{@link System#gc()}</code>
     * https://stackoverflow.com/questions/1481178/how-to-force-garbage-collection-in-java
     * GenerateList(int pixelX, int pixelY) will cause memory leak if this is not here
     */
    public static void gc() {
        Object obj = new Object();
        WeakReference ref = new WeakReference<Object>(obj);
        obj = null;
        while (ref.get() != null) {
            System.gc();
        }
    }
}
