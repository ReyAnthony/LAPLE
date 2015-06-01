package fr.laple;

import fr.laple.view.LoginPrompt;
import fr.laple.view.SplashScreen;
import fr.laple.view.testing.ExerciseUITest;

/**
 * LAPLE code entry point, it calls the Login Prompt
 *
 * @author anthonyrey
 * @see fr.laple.view.LoginPrompt
 */
public class Main {

    public static void main(String[] args) {

        new SplashScreen();
        new ExerciseUITest();
        new LoginPrompt();
    }
}
