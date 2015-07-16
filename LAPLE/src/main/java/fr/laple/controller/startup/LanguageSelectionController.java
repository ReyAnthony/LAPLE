package fr.laple.controller.startup;

import fr.laple.controller.LapleGUIController;
import fr.laple.extensions.plugins.*;
import fr.laple.extensions.plugins.features.FeatureConfigFileParser;
import fr.laple.extensions.plugins.features.IFeaturePlugin;
import fr.laple.extensions.plugins.languages.ILanguagePlugin;
import fr.laple.extensions.plugins.languages.LanguageConfigFileParser;
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
public class LanguageSelectionController implements ActionListener {

    private LanguageSelectionView view;
    private PluginLoader langLoader;

    public LanguageSelectionController()
    {
        this.view = new LanguageSelectionView();
        this.view.getValidationButton().addActionListener(this);
        loadConfig();

        List<IPlugin> languageList = getLanguageList();

        for(IPlugin s : languageList)
        {
            view.getChoices().addItem(s);
        }

    }

    public void loadConfig()
    {

        try {
           langLoader = new PluginLoader(new LanguageConfigFileParser());
        } catch (PluginLoadingFatalException e) {
            pluginLoadingError(e.getMessage());
        }
    }

    private List<IPlugin> getLanguageList()
    {

        List<IPlugin> languageList = new ArrayList<>();

        for(IPlugin plugin : langLoader.getDummies())
        {
            languageList.add(plugin);
        }

        return languageList;
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        try {
                ILanguagePlugin plugin = (ILanguagePlugin)
                        langLoader.getLoadedPlugins((IPlugin) view.getChoices().getSelectedItem());

                List<IFeaturePlugin> features = new ArrayList<>();

                PluginLoader fpl = new PluginLoader(new FeatureConfigFileParser());

                for(IPlugin p : fpl.getLoadedPlugins())
                {
                    features.add((IFeaturePlugin) p);
                }

                LapleDataModel dataModel = new LapleDataModel(plugin, features);

                for(IFeaturePlugin feature : features)
                {
                    feature.instanciateExerciseModes(dataModel);
                }
                new LapleGUIController(dataModel);

        } catch (PluginLoadingFatalException e1) {
           pluginLoadingError(e1.getMessage());
        }

        view.dispose();
    }

    private void pluginLoadingError(String errorMessage)
    {
        JOptionPane.showMessageDialog(view, errorMessage, "Fatal Error", JOptionPane.ERROR_MESSAGE);
        System.exit(0);
    }

}
