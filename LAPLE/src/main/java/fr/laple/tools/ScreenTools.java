package fr.laple.tools;

import java.awt.*;

/**
 * class with static methods used as shortcuts for widely used methods
 *
 * @author anthonyrey
 */
public class ScreenTools {

    /**
     *
     * Is used to determine the position of a frame for it
     * to be centered
     *
     * @param width The width of the frame
     * @param height The height of the frame
     * @return a Point object to use with setLocation method
     */
    public static Point getCenteredPoint(int width, int height)
    {
        Toolkit tk =Toolkit.getDefaultToolkit();
        double hMax = tk.getScreenSize().getHeight();
        double wMax = tk.getScreenSize().getWidth();

        return new Point((int) (wMax/2) - width/2, (int) (hMax/2) - height/2);
    }

}
