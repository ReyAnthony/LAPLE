package fr.laple.LapleUI;

import fr.laple.lang.jp.LapleLanguagePlugin;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by anthonyrey on 11/04/2015.
 */
public class LanguageSelection extends JFrame implements ActionListener{

    private JLabel message;
    private JComboBox choices;
    private JButton validationButton;
    private JPanel panel;

    public LanguageSelection()
    {
        this.setSize(300,100);
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

        ClassLoader loader = LanguageSelection.class.getClassLoader();
        List<String> languageList = new ArrayList<>();
        LapleLanguagePlugin llp = null;

        try {
            llp = (LapleLanguagePlugin) loader.loadClass("fr.laple.lang.jp.LapleLanguagePlugin").newInstance();

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
            new LapleGUI((String) choices.getSelectedItem());
            this.dispose();
        }

    }
}