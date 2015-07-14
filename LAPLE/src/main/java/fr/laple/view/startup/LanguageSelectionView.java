package fr.laple.view.startup;

import fr.laple.ztools.ScreenTools;

import javax.swing.*;
import java.awt.*;

/**
 * This class is a prompt for selecting avalaible languages. It uses entry points from language plugins to determine the language list
 * @see fr.laple.extensions.languages.plugins.ILanguagePlugin
 * @author anthonyrey
 *
 */
public class LanguageSelectionView extends JFrame{

    private JLabel message;
    private JComboBox choices;
    private JButton validationButton;
    private JPanel panel;


    public LanguageSelectionView()
    {
        this.setLayout(new BorderLayout());
        this.setSize(250,100);
        this.setLocation(ScreenTools.getCenteredPoint(this.getWidth(), this.getHeight()));
        this.setTitle("LAPLE - Language selection");
        this.setResizable(false);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        
        message = new JLabel("Select a language :");
        validationButton = new JButton("OK");
        choices = new JComboBox();
        panel = new JPanel();

        panel.add(message);
        panel.add(choices);
        panel.add(validationButton);

        this.setContentPane(panel);

        this.setVisible(true);

    }


    public JComboBox getChoices() {
        return choices;
    }

    public JButton getValidationButton() {
        return validationButton;
    }
}
