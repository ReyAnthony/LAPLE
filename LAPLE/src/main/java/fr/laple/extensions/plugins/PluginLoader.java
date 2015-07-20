package fr.laple.extensions.plugins;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is a wrapper for the PluginConfigFileParser
 *
 * @see fr.laple.extensions.plugins.PluginConfigFileParser
 *
 * @author anthonyrey
 */
public class PluginLoader {

    private List<IPlugin> dummies;
    private PluginConfigFileParser cfp;

    /**
     * Constructor to the Plugin Loader class
     *
     * @see fr.laple.extensions.plugins.features.FeaturePluginConfigFileParser
     * @see fr.laple.extensions.plugins.languages.LanguagePluginConfigFileParser
     *
     * @param cfp The needed PluginConfigFileParser
     * @throws PluginLoadingFatalException if there is any error while loading the dummies
     */
    public PluginLoader(PluginConfigFileParser cfp) throws PluginLoadingFatalException {

        dummies = new ArrayList<>();
        this.cfp = cfp;
        cfp.getDummies().forEach(this::loadDummies);
    }

    //dummies are not really used for IFeaturePlugins

    /**
     * Add the dummies to the dummies list
     *
     * @param chosen The dummy to add
     */
    private void loadDummies(IPlugin chosen)
    {
        dummies.add(chosen);
    }

    /**
     * Load a real plugin from a dummy (with or without data)
     *
     * @param chosen The dummy plugin (as an IPlugin)
     * @param withData With data ?
     * @return The Plugin
     */
    private IPlugin loadRealPlugins(IPlugin chosen, boolean withData) {

        //a bit hard to understand due to the flow of erros
        /*
            if there is a non fatal error, we give the dummy plugin untouched, so that he can be fixed anyway
            They will not be loaded as features because they lack there own data (dummies are returning empty lists)

         */
        IPlugin ip = chosen;
        try {
            ip = cfp.getPlugin(chosen, withData);
        } catch (PluginLoadingException e) {
            Plugins.pluginMessage(e.getMessage());
        } catch (PluginLoadingFatalException | PluginTypeException e) {
            Plugins.pluginError(e.getMessage());
        }
        return ip;
    }

    /**
     * Get the loaded plugins (generated from the dummies)
     *
     * @param withData with data ?
     * @return A List of IPlugin
     */
    public List<IPlugin> getLoadedPlugins(boolean withData)  {
        List<IPlugin> plugins = new ArrayList<>();

        for(IPlugin ip : dummies)
        {
            //if cannot get plugin from dummy, it is skipped and still is a dummy
            plugins.add(loadRealPlugins(ip, withData));
        }

        return plugins;
    }

    /**
     * Get a specific loaded plugin
     *
     * @param plugin The Dummy to get
     * @param withData with data ?
     * @return The plugin
     */
    public IPlugin getLoadedPlugins(IPlugin plugin, boolean withData) {

        for(IPlugin ip : dummies)
        {
            //TODO might not be enough testing
            if(plugin.getName().equals(ip.getName()))
                return loadRealPlugins(ip, withData);
        }

        //if it does not work, we return the dummy
        return plugin;
    }



}
