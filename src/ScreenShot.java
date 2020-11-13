package src;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.util.*;
import java.awt.*;
import javax.swing.*;


public class ScreenShot {

    /** Gets screen width and height from main
     * @param w
     * @param h
     * @return
     * @throws Exception
     */
    public Image SCapture(int w, int h) throws Exception {
        Robot robot = new Robot();
        BufferedImage screenCapture = robot.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
        Image image = getScaledImage(screenCapture, w, h);
        return image;
    }

    /** Gets screen width and height from main
     * @param srcImg
     * @param w
     * @param h
     * @return
     */
    private static Image getScaledImage(Image srcImg, int w, int h) {
        BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();

        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg, 0, 0, w, h, null);
        g2.dispose();
        return resizedImg;
    }
}
