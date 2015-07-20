package fr.laple.controller.startup;

import fr.laple.model.user.User;
import fr.laple.view.startup.LoginPrompt;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Controller for the LoginPromptView
 *
 * @see fr.laple.view.startup.LoginPrompt
 *
 * @author anthonyrey
 */
public class LoginPromptController implements ActionListener {

    private LoginPrompt loginPrompt;

    /**
     * Constructor for the class
     */
    public LoginPromptController()
    {
        this.loginPrompt = new LoginPrompt();
        loginPrompt.getValidationButton().addActionListener(this);
        loginPrompt.getNoAccountButton().addActionListener(this);
    }

    /**
     * Two cases :
     *
     * - Validation : check your credential then connect
     * - No account : gotToWebsite method
     *
     * @param e An ActionEvent Object
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource().equals(loginPrompt.getValidationButton()))
        {
            JOptionPane.showMessageDialog(null, "Not implemented yet");
            new LanguageSelectionController();
            loginPrompt.dispose();
        }
        else if(e.getSource().equals(loginPrompt.getNoAccountButton()))
        {
            goToWebsite();
        }

    }

    /**
     * Go to the laple website
     */
    private void goToWebsite()
    {
        try {
            java.awt.Desktop.getDesktop().browse(
                    new URI("http://www.laple.fr/sign_up.php"));
        } catch (IOException | URISyntaxException e1) {

            JOptionPane.showMessageDialog(loginPrompt,
                    "Seems like there is a browser related error.. hum...");
        }

    }

    /**
     * Fill the User singleton
     *
     * @param online Is the user online ?
     */
    private void setUserSettings(boolean online)
    {
        User.getInstance().setOnline(online);

        //Add infos of user if online
        /*
        if(online)
        {
            //TODO BDD part
        }
        */

    }


}
