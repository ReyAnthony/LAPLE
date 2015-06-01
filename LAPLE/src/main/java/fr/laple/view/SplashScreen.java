package fr.laple.view;

import fr.laple.tools.ScreenTools;

import javax.swing.*;

/**
 * Splash Screen
 * Nothing more than a JWindow at the center of the screen
 *
 * @author anthonyrey
 */
public class SplashScreen extends JWindow {

    public SplashScreen()
    {

        ImageIcon image  = new ImageIcon(getClass().getResource("/images/splash.png"));

        int wImage = image.getIconWidth();
        int hImage = image.getIconHeight();

        this.setSize(wImage,hImage);
        this.setLocation(ScreenTools.getCenteredPoint(wImage, hImage));

        JLabel icon = new JLabel(image);
        this.add(icon);
        this.setAlwaysOnTop(true);

        this.setVisible(true);

        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        this.dispose();


    }

}
