package fr.laple.LapleUI;

import fr.laple.user.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by anthonyrey on 12/04/2015.
 */
public class LoginPrompt extends JFrame implements ActionListener{

    private JLabel message;
    private JButton validationButton;
    private JButton offlineButton;
    private JButton noAccountButton;
    private JTextField loginField;
    private JPasswordField pwdField;
    private JPanel panel;

    public LoginPrompt()
    {
        this.setSize(200,200);
        this.setTitle("LAPLE - Login");
        this.setResizable(false);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        message = new JLabel("Login to your LAPLE account :");
        validationButton = new JButton("Log-in");
        offlineButton = new JButton("No thanks");
        noAccountButton = new JButton("Create an account");
        loginField = new JTextField("Login");
        pwdField = new JPasswordField("Password");
        panel = new JPanel();

        validationButton.addActionListener(this);
        offlineButton.addActionListener(this);
        noAccountButton.addActionListener(this);

        this.setContentPane(panel);

        panel.add(message);
        panel.add(loginField);
        panel.add(pwdField);
        panel.add(validationButton);
        panel.add(offlineButton);
        panel.add(noAccountButton);

        this.setVisible(true);

    }

    private void goToWebsite()
    {
        try {
            java.awt.Desktop.getDesktop().browse(
                    new URI("https://github.com/ReyAnthony/LAPLE"));
        } catch (IOException | URISyntaxException e1) {

            JOptionPane.showMessageDialog(this,
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

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource().equals(validationButton))
        {
            JOptionPane.showMessageDialog(this, "Not implemented yet");
        }
        else if (e.getSource().equals(offlineButton)){

            this.dispose();
            new LanguageSelection();

        }
        else if(e.getSource().equals(noAccountButton))
        {
            try {
                java.awt.Desktop.getDesktop().browse(
                        new URI("https://github.com/ReyAnthony/LAPLE"));
            } catch (IOException | URISyntaxException e1) {

                JOptionPane.showMessageDialog(this,
                        "Seems like there is a browser related error.. hum...");
            }

        }

    }
}
