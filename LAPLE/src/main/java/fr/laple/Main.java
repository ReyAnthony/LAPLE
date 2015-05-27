package fr.laple;

import fr.laple.lapleUI.LoginPrompt;
import fr.laple.lapleUI.SplashScreen;
import fr.laple.lapleUI.testing.ExerciseUITest;

/**
 * LAPLE code entry point, it calls the Login Prompt
 *
 * @author anthonyrey
 * @see fr.laple.lapleUI.LoginPrompt
 */
public class Main {

    public static void main(String[] args) {

        new SplashScreen();
        new ExerciseUITest();
        new LoginPrompt();
    }
}
