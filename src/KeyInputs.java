package src;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.AWTException;

public class KeyInputs
{
    private static Robot robot = new Robot();

    public void pressKey(KeyEvent key, Boolean caps){
        try {
              if(caps) robot.keyPress(KeyEvent.VK_CAPS_LOCK);
              robot.keyPress(KeyEvent.key);
              releaseKey();
        } catch (AWTException e) {
              e.printStackTrace();
        }
    }

    public void pressKeyWithCtrl(KeyEvent key){
        try {
              robot.keyPress(KeyEvent.VK_CONTROL);
              robot.keyPress(KeyEvent.key);
              releaseKeyWithCtrl();
        } catch (AWTException e) {
              e.printStackTrace();
        }
    }

    public void releaseKey(KeyEvent key){
        try {
              robot.keyRelease(KeyEvent.VK_CAPS_LOCK);
              robot.keyRelease(KeyEvent.key);
        } catch (AWTException e) {
              e.printStackTrace();
        }
    }

    public void releaseKeyWithCtrl(KeyEvent key){
        try {
              robot.keyRelease(KeyEvent.VK_CONTROL);
              robot.keyRelease(KeyEvent.key);
        } catch (AWTException e) {
              e.printStackTrace();
        }
    }

    public static KeyEvent VK_H; // h To select townhall

    public static KeyEvent VK_PERIOD; // . To select villager

    public static KeyEvent VK_COMMA // , To find idle millitary
}
