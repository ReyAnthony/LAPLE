package fr.laple.lapleUI;

import fr.laple.language.ILanguagePlugin;
import fr.laple.tools.ScreenTools;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is a prompt for selecting avalaible languages. It uses entry points from language plugins to determine the language list
 * @see fr.laple.language.ILanguagePlugin
 * @author anthonyrey
 *
 */
public class LanguageSelection extends JFrame implements ActionListener{

    private JLabel message;
    private JComboBox choices;
    private JButton validationButton;
    private JPanel panel;
    private ILanguagePlugin llp;

    public LanguageSelection()
    {
        this.setSize(300,100);
        this.setLocation(ScreenTools.getCenteredPoint(this.getWidth(), this.getHeight()));
        this.setTitle("LAPLE - Language selection");
        this.setResizable(false);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        
        message = new JLabel("Select a language :");
        validationButton = new JButton("OK");
        choices = new JComboBox();
        panel = new JPanel();

        List<String> languageList = getLanguageList();

        for(String s : languageList)
        {
            choices.addItem(s);
        }

        panel.add(message);
        panel.add(choices);
        panel.add(validationButton);
        validationButton.addActionListener(this);

        this.setContentPane(panel);

        this.setVisible(true);

    }

    //TODO this is a stub
    private List<String> getLanguageList()
    {

        List<String> languageList = new ArrayList<>();

        try {
            llp = (ILanguagePlugin) getClass().getClassLoader().loadClass("fr.laple.extensions.languages.japanese.LapleLanguagePlugin").newInstance();

        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }

        languageList.add(llp.getLanguageName());


        return languageList;
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource().equals(validationButton)){
            //We need a path here, but for testing purposes ...
            new LapleGUI(llp);
            this.dispose();
        }

    }
}
