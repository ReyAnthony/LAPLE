package fr.laple.view;

import fr.laple.tools.ScreenTools;
import fr.laple.model.user.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * This class allows an user to Login to the application. It will set the informations in the User singleton the call the Language selection prompt
 *
 * @see fr.laple.view.LanguageSelection
 * @see fr.laple.model.user.User
 * @author anthonyrey
 */
public class LoginPrompt extends JFrame implements ActionListener{

    private JLabel message;
    private JButton validationButton;
    private JButton noAccountButton;
    private JTextField loginField;
    private JPasswordField pwdField;
    private JPanel panel;

    public LoginPrompt()
    {
        this.setSize(400,200);
        this.setTitle("LAPLE - Login");
        this.setResizable(false);
        this.setLocation(ScreenTools.getCenteredPoint(this.getWidth(), this.getHeight()));
        this.setLayout(new BorderLayout());

        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        message = new JLabel("Login to your LAPLE account :");
        validationButton = new JButton("Log-in");
        noAccountButton = new JButton("Create an account");
        loginField = new JTextField("Login");
        pwdField = new JPasswordField("Password");
        panel = new JPanel();

        validationButton.addActionListener(this);
        noAccountButton.addActionListener(this);

        this.setContentPane(panel);

        panel.add(message);
        panel.add(loginField);
        panel.add(pwdField);
        panel.add(validationButton);
        panel.add(noAccountButton);

        this.setVisible(true);
        this.setAlwaysOnTop(true);
        this.setAlwaysOnTop(false);

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
            new LanguageSelection();
            this.dispose();
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
