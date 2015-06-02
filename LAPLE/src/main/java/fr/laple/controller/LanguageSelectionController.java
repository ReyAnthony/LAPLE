package fr.laple.controller;

import fr.laple.extensions.languages.PluginConfigObject;
import fr.laple.extensions.languages.PluginLoadingException;
import fr.laple.model.language.ILanguagePlugin;
import fr.laple.view.LapleGUI;
import fr.laple.view.startup.LanguageSelection;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by anthonyrey on 01/06/2015.
 */
//TODO test the Load of plugins outside the classpath
public class LanguageSelectionController implements ActionListener {

    private LanguageSelection langSelect;
    private LanguageConfigFileParser lcfp;

    public LanguageSelectionController(LanguageSelection langSelect)
    {
        this.langSelect = langSelect;
        loadConfig();

    }

    public void loadConfig()
    {

        try {
            lcfp = new LanguageConfigFileParser();
        } catch (PluginLoadingException e) {

            pluginLoadingError(e.getMessage());
        }
    }

    //TODO Read a config file to know where are the files
    public List<String> getLanguageList()
    {

        List<String> languageList = new ArrayList<>();

        for(PluginConfigObject pco : lcfp.getLanguagePluginsList())
        {
            languageList.add(pco.getName());
        }

        return languageList;
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        String selected = ((String) langSelect.getChoices().getSelectedItem());

        try {
            for(PluginConfigObject pco : lcfp.getLanguagePluginsList())
            {
                if(pco.getName().equals(selected))
                {
                    ILanguagePlugin plugin = (ILanguagePlugin) pco.getPlugin().newInstance();
                    new LapleGUI(plugin);
                    break;
                }
            }

            //Normally we shouln't have any problems here, but more is better than less
        } catch (InstantiationException | IllegalAccessException e1) {
            pluginLoadingError(e1.getMessage());
        }

        langSelect.dispose();
    }

    private void pluginLoadingError(String errorMessage)
    {
        JOptionPane.showMessageDialog(langSelect, errorMessage, "Fatal Error", JOptionPane.ERROR_MESSAGE);
        System.exit(0);
    }
}
