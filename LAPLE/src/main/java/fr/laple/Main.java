package fr.laple;

import fr.laple.controller.startup.LanguageSelectionController;
import fr.laple.view.startup.SplashScreen;

/**
 * LAPLE code entry point, it calls the Login Prompt
 *
 * @author anthonyrey
 * @see fr.laple.view.startup.LoginPrompt
 */
public class Main {

    public static void main(String[] args) {

        Runnable runnable = () -> {

            new SplashScreen();
            new LanguageSelectionController();
        };
        runnable.run();
    }
}
