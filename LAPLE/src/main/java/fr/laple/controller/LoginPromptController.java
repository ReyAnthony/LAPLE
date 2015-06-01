package fr.laple.controller;

import fr.laple.model.user.User;
import fr.laple.view.startup.LanguageSelection;
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


    public LoginPromptController(LoginPrompt loginPrompt)
    {
        this.loginPrompt = loginPrompt;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource().equals(loginPrompt.getValidationButton()))
        {
            JOptionPane.showMessageDialog(null, "Not implemented yet");
            new LanguageSelection();
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
                    new URI("https://github.com/ReyAnthony/LAPLE"));
        } catch (IOException | URISyntaxException e1) {

            JOptionPane.showMessageDialog(loginPrompt,
                    "Seems like there is a browser related error.. hum...");
        }

    }

    private void setUserSettings(boolean online)
    {
        User.getInstance().setOnline(online);

        //Add infos of user if online
        if(online)
        {



        }

    }


}
