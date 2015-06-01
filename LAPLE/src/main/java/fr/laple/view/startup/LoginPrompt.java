package fr.laple.view.startup;

import fr.laple.controller.LoginPromptController;
import fr.laple.tools.ScreenTools;

import javax.swing.*;
import java.awt.*;

/**
 * This class allows an user to Login to the application. It will set the informations in the User singleton the call the Language selection prompt
 *
 * @see LanguageSelection
 * @see fr.laple.model.user.User
 * @author anthonyrey
 */
public class LoginPrompt extends JFrame{

    private JLabel message;
    private JButton validationButton;
    private JButton noAccountButton;
    private JTextField loginField;
    private JPasswordField pwdField;

    public LoginPrompt()
    {
        LoginPromptController controller = new LoginPromptController(this);
        BorderLayout layout = new BorderLayout();
        JPanel container = new JPanel();
        container.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        this.setContentPane(container);

        this.setSize(400, 200);
        this.setTitle("LAPLE");
        this.setResizable(false);
        this.setLocation(ScreenTools.getCenteredPoint(this.getWidth(), this.getHeight()));
        this.setLayout(layout);

        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        message = new JLabel("Login to your LAPLE account :");
        message.setHorizontalAlignment(JLabel.CENTER);
        this.add(message, BorderLayout.PAGE_START);

        JPanel fields = new JPanel();
        fields.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        fields.setPreferredSize(new Dimension(400, 80));
        loginField = new JTextField("Login");
        loginField.setPreferredSize(new Dimension(300,30));
        pwdField = new JPasswordField("Password");
        pwdField.setPreferredSize(new Dimension(300, 30));
        fields.add(loginField);
        fields.add(pwdField);
        add(fields, BorderLayout.CENTER);

        JPanel buttons = new JPanel();
        validationButton = new JButton("Log-in");
        noAccountButton = new JButton("I do not have an account");
        validationButton.addActionListener(controller);
        noAccountButton.addActionListener(controller);
        buttons.add(validationButton);
        buttons.add(noAccountButton);
        add(buttons, BorderLayout.PAGE_END);

        this.pack();
        this.setVisible(true);
        this.setAlwaysOnTop(true);
        this.setAlwaysOnTop(false);


    }

    public JButton getValidationButton() {
        return validationButton;
    }


    public JButton getNoAccountButton() {
        return noAccountButton;
    }
}
