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
 * This class is the controller for LanguageSelectionView
 * It manage plugin instanciation, filling LapleDataModel, language selection etc..
 *
 * @see fr.laple.extensions.plugins.IPlugin
 * @see fr.laple.extensions.plugins.languages.ILanguagePlugin
 * @see fr.laple.extensions.plugins.features.IFeaturePlugin
 * @see fr.laple.model.datamodel.LapleDataModel
 *
 * @author anthonyrey
 */
public class LanguageSelectionController implements ActionListener {

    private LanguageSelectionView view;
    private PluginLoader langLoader;
    private List<IPlugin> allLanguagePlugins;

    /**
     * This is the controller for the class, it loads dummy language plugin and add them in
     * a list to be displated in the comboBox
     *
     * It calls loadConfig then populate the languageList
     */
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

    /**
     * Instanciate a PluginLoader with an instance of LanguagePluginConfigFileParser
     *
     * @see fr.laple.extensions.plugins.PluginLoader
     * @see fr.laple.extensions.plugins.languages.LanguagePluginConfigFileParser
     *
     */
    public void loadConfig()
    {

        try {
           langLoader = new PluginLoader(new LanguagePluginConfigFileParser());
        } catch (PluginLoadingFatalException e) {
            Plugins.pluginError(e.getMessage());
        }
    }

    /**
     * Return the language list, but without the data or else it can take a lot of memory if
     * there is lot of plugins
     *
     * @return A list of IPlugins (ILanguagePlugins without data to be precise (dummies))
     */
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

    /**
     * When you press Ok , the selected language plugin is really loaded (with all the data).
     * Then, instanciate a PluginLoader with an instance of FeaturePluginConfigFileParser
     * Which allows to get the feature plugins
     *
     * All of these data are then stored in the LapleDataModel
     *
     * Depending on the error, the program will show an error / quit
     *
     * @see fr.laple.model.datamodel.LapleDataModel
     * @see fr.laple.extensions.plugins.PluginLoader
     * @see fr.laple.extensions.plugins.features.FeaturePluginConfigFileParser
     *
     * @param e An ActionEvent Object
     */
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
                    feature.instanciateExerciseTypes(dataModel);
                }
                new LapleGUIController(dataModel);

        } catch (PluginLoadingFatalException e1) {
           Plugins.pluginError(e1.getMessage());
        }

        view.dispose();
    }

}
