package fr.laple;

import fr.laple.controller.startup.LoginPromptController;
import fr.laple.view.startup.SplashScreen;

import javax.swing.*;

/**
 * LAPLE code entry point, it calls the Login Prompt
 *
 * @author anthonyrey
 * @see fr.laple.view.startup.LoginPrompt
 */
public class Main {

    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName() );
        } catch (Exception e) {
            e.printStackTrace();
        }
        new SplashScreen();
        new LoginPromptController();

    }
}
