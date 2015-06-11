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
 * Created by anthonyrey on 01/06/2015.
 */
public class LoginPromptController implements ActionListener {

    private LoginPrompt loginPrompt;


    public LoginPromptController()
    {
        this.loginPrompt = new LoginPrompt();
        loginPrompt.getValidationButton().addActionListener(this);
        loginPrompt.getNoAccountButton().addActionListener(this);
    }

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
