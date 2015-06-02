package fr.laple.view.startup;

import fr.laple.controller.LanguageSelectionController;
import fr.laple.tools.ScreenTools;

import javax.swing.*;
import java.util.List;

/**
 * This class is a prompt for selecting avalaible languages. It uses entry points from language plugins to determine the language list
 * @see fr.laple.model.language.ILanguagePlugin
 * @author anthonyrey
 *
 */
public class LanguageSelection extends JFrame{

    private JLabel message;
    private JComboBox choices;
    private JButton validationButton;
    private JPanel panel;


    public LanguageSelection()
    {
        LanguageSelectionController controller = new LanguageSelectionController(this);

        this.setSize(300,100);
        this.setLocation(ScreenTools.getCenteredPoint(this.getWidth(), this.getHeight()));
        this.setTitle("LAPLE - Language selection");
        this.setResizable(false);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        
        message = new JLabel("Select a language :");
        validationButton = new JButton("OK");
        choices = new JComboBox();
        panel = new JPanel();

        List<String> languageList = controller.getLanguageList();

        for(String s : languageList)
        {
            choices.addItem(s);
        }

        panel.add(message);
        panel.add(choices);
        panel.add(validationButton);
        validationButton.addActionListener(controller);

        this.setContentPane(panel);

        this.setVisible(true);

    }


    public JComboBox getChoices() {
        return choices;
    }
}
