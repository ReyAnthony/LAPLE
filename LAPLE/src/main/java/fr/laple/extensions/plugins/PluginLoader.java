package fr.laple.extensions.plugins;

import javax.swing.*;
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

        for(IPlugin p : cfp.getDummies())
        {
            loadDummies(p);
        }
    }

    //dummies are not really used for IFeaturePlugins
    private void loadDummies(IPlugin chosen)
    {
        dummies.add(chosen);
    }

    private IPlugin loadRealPlugins(IPlugin chosen, boolean withData)
    {

        IPlugin ip = chosen;

        try {

            ip = cfp.getRealPlugin(chosen, withData);

        } catch (PluginLoadingException e) {

            //If there is an error when loading, we create a dummy plugin, so we can always review it later
            JOptionPane.showMessageDialog(null, e.getMessage(),
                    "Error", JOptionPane.WARNING_MESSAGE);

        } catch (PluginLoadingFatalException e) {
            //if fatal
            JOptionPane.showMessageDialog(null, e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }

        return ip;
    }

    public List<IPlugin> getLoadedPlugins(boolean withData)
    {
        List<IPlugin> plugins = new ArrayList<>();

        for(IPlugin ip : dummies)
        {
            plugins.add(loadRealPlugins(ip, withData));
        }

        return plugins;
    }

    public IPlugin getLoadedPlugins(IPlugin plugin, boolean withData)
    {
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
