package fr.laple;

import fr.laple.controller.startup.LoginPromptController;
import fr.laple.view.startup.LoginPrompt;
import fr.laple.view.startup.SplashScreen;

/**
 * LAPLE code entry point, it calls the Login Prompt
 *
 * @author anthonyrey
 * @see fr.laple.view.startup.LoginPrompt
 */
public class Main {

    public static void main(String[] args) {

        new SplashScreen();
        new LoginPromptController();

    }
}
