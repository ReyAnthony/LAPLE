package fr.laple;

import fr.laple.LapleUI.LoginPrompt;
import fr.laple.LapleUI.Testing.ExerciseUITest;

/**
 * LAPLE code entry point, it calls the Login Prompt
 *
 * @author anthonyrey
 * @see fr.laple.LapleUI.LoginPrompt
 */
public class Main {

    public static void main(String[] args) {
        new ExerciseUITest();
        new LoginPrompt();
    }
}
