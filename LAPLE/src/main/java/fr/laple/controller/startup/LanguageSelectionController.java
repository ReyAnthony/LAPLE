package fr.laple.controller.startup;

import fr.laple.controller.LapleGUIController;
import fr.laple.extensions.features.plugins.FeaturePluginLoadingException;
import fr.laple.extensions.languages.plugins.LangPluginLoadingException;
import fr.laple.extensions.features.plugins.FeaturePluginLoader;
import fr.laple.extensions.features.plugins.IFeaturePlugin;
import fr.laple.extensions.languages.japanese.ParserException;
import fr.laple.extensions.languages.plugins.ILanguagePlugin;
import fr.laple.extensions.languages.plugins.LanguageConfigFileParser;
import fr.laple.extensions.languages.plugins.PluginConfigObject;
import fr.laple.model.datamodel.LapleDataModel;
import fr.laple.view.startup.LanguageSelectionView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by anthonyrey on 01/06/2015.
 */
//TODO test the Load of plugins outside the classpath
//TODO refactor
public class LanguageSelectionController implements ActionListener {

    private LanguageSelectionView view;
    private LanguageConfigFileParser lcfp;

    public LanguageSelectionController()
    {
        this.view = new LanguageSelectionView();
        this.view.getValidationButton().addActionListener(this);
        loadConfig();

        List<String> languageList = getLanguageList();

        for(String s : languageList)
        {
            view.getChoices().addItem(s);
        }

    }

    public void loadConfig()
    {

        try {
            lcfp = new LanguageConfigFileParser();
        } catch (LangPluginLoadingException e) {

            pluginLoadingError(e.getMessage());
        }
    }

    private List<String> getLanguageList()
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

        String selected = ((String) view.getChoices().getSelectedItem());

        try {
            for(PluginConfigObject pco : lcfp.getLanguagePluginsList())
            {
                if(pco.getName().equals(selected)) {
                    ILanguagePlugin plugin = (ILanguagePlugin) pco.getPlugin().newInstance();
                    //TODO LOAD plugins according to file

                    /*
                        The code here is a bit tricky :
                        First we create list of IFeaturePlugin
                        The we load all plugins from the feature plugin loader
                        If there is an error, we print a message
                            then we iter over the feature (so we actually dont)
                        else
                            we really iter over them and thus they are loaded
                     */

                    List<IFeaturePlugin> features = new ArrayList<>();

                    try {

                        FeaturePluginLoader fpl = new FeaturePluginLoader();
                        features = fpl.getLoadedPlugin();
                    } catch (FeaturePluginLoadingException e1) {

                        pluginLoadingWarning(e1.getMessage());
                    } finally {

                        LapleDataModel dataModel = new LapleDataModel(plugin, features);

                        for(IFeaturePlugin feature : features)
                        {
                            feature.instanciateExerciseModes(dataModel);
                        }
                        new LapleGUIController(dataModel);
                    }

                    //just in case ?
                    break;
                }
            }

        } catch (InstantiationException | IllegalAccessException | ParserException  e1) {
            pluginLoadingError(e1.getMessage());
        }

        view.dispose();
    }

    private void pluginLoadingError(String errorMessage)
    {
        JOptionPane.showMessageDialog(view, errorMessage, "Fatal Error", JOptionPane.ERROR_MESSAGE);
        System.exit(0);
    }

    private void pluginLoadingWarning(String errorMessage)
    {
        JOptionPane.showMessageDialog(view, errorMessage, "Warning", JOptionPane.WARNING_MESSAGE);
    }
}
