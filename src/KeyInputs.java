package src;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.AWTException;

public class KeyInputs {
    private static final Robot robot = initRobot();

    private static Robot initRobot() {
        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        return robot;
    }

    public void pressKey(KeyEvent key, Boolean caps) {
        if (caps) robot.keyPress(KeyEvent.VK_CAPS_LOCK);
        robot.keyPress(key.getKeyCode());
    }

    public void pressKeyWithCtrl(KeyEvent key) {
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(key.getKeyCode());
    }

    public void releaseKey(KeyEvent key) {
        robot.keyRelease(KeyEvent.VK_CAPS_LOCK);
        robot.keyRelease(key.getKeyCode());
    }

    public void releaseKeyWithCtrl(KeyEvent key) {
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyRelease(key.getKeyCode());
    }

    public static KeyEvent VK_H; // h To select townhall

    public static KeyEvent VK_PERIOD; // . To select villager

    public static KeyEvent VK_COMMA; // , To find idle millitary
}
