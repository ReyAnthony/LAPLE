package fr.laple.extensions.plugins;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anthonyrey on 14/07/2015.
 */
public class PluginLoader {

    private List<IPlugin> dummies;
    private PluginConfigFileParser cfp;

    public PluginLoader(PluginConfigFileParser cfp) throws PluginLoadingFatalException {

        dummies = new ArrayList<>();
        this.cfp = cfp;
        cfp.getDummies().forEach(this::loadDummies);
    }

    //dummies are not really used for IFeaturePlugins
    private void loadDummies(IPlugin chosen)
    {
        dummies.add(chosen);
    }

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

    public List<IPlugin> getLoadedPlugins(boolean withData)  {
        List<IPlugin> plugins = new ArrayList<>();

        for(IPlugin ip : dummies)
        {
            //if cannot get plugin from dummy, it is skipped and still is a dummy
            plugins.add(loadRealPlugins(ip, withData));
        }

        return plugins;
    }

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
