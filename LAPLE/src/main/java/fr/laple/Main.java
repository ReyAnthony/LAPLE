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

    //TODO Creer et affecter la vue à l'onglet, puis creer le controlleur qui prendra une instance de la vue
    //TODO la vue qui crée le controlleur, c'est pas extra

    public static void main(String[] args) {

        try {

            UIManager.setLookAndFeel(
                    UIManager.getCrossPlatformLookAndFeelClassName());
        }
        catch (UnsupportedLookAndFeelException e) {
            //TODO exception
        }
        catch (ClassNotFoundException e) {
            //TODO exception
        }
        catch (InstantiationException e) {
            //TODO exception
        }
        catch (IllegalAccessException e) {
            //TODO exception
        }

        Runnable runnable = () -> {

            new SplashScreen();
            new LoginPromptController();
        };
        runnable.run();
    }
}
