package fr.laple.controller.startup;

import fr.laple.controller.LapleGUIController;
import fr.laple.extensions.plugins.Plugins;
import fr.laple.extensions.plugins.*;
import fr.laple.extensions.plugins.features.FeaturePluginConfigFileParser;
import fr.laple.extensions.plugins.features.IFeaturePlugin;
import fr.laple.extensions.plugins.languages.ILanguagePlugin;
import fr.laple.extensions.plugins.languages.LanguagePluginConfigFileParser;
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
    private List<IPlugin> allLanguagePlugins;

    public LanguageSelectionController()
    {
        this.view = new LanguageSelectionView();
        this.view.getValidationButton().addActionListener(this);
        allLanguagePlugins = new ArrayList<>();
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
           langLoader = new PluginLoader(new LanguagePluginConfigFileParser());
        } catch (PluginLoadingFatalException e) {
            Plugins.pluginError(e.getMessage());
        }
    }

    private List<IPlugin> getLanguageList()
    {

        DefaultComboBoxModel<IPlugin> model = new DefaultComboBoxModel<>();

        List<IPlugin> languageList = new ArrayList<>();

            for(IPlugin plugin : langLoader.getLoadedPlugins(false))
            {
                //if there is obvisouly an issue with the language plugin
                //If there is a big problem we can't go over there anyway
                if(plugin.getPath().exists() || plugin.isInternal())
                    languageList.add(plugin);

                allLanguagePlugins.add(plugin);
            }

        return languageList;
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        try {
                ILanguagePlugin plugin = (ILanguagePlugin)
                        langLoader.getLoadedPlugins((IPlugin) view.getChoices().getSelectedItem(), true);

                List<IFeaturePlugin> features = new ArrayList<>();

                PluginLoader fpl = new PluginLoader(new FeaturePluginConfigFileParser());

                for(IPlugin p : fpl.getLoadedPlugins(true))
                {
                    features.add((IFeaturePlugin) p);
                }

                LapleDataModel dataModel = new LapleDataModel(plugin, features, allLanguagePlugins);

                for(IFeaturePlugin feature : features)
                {
                    feature.instanciateExerciseModes(dataModel);
                }
                new LapleGUIController(dataModel);

        } catch (PluginLoadingFatalException e1) {
           Plugins.pluginError(e1.getMessage());
        }

        view.dispose();
    }

}
