package fr.laple.extensions.plugins;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by anthonyrey on 14/07/2015.
 */
public class PluginLoader {

    private List<IPlugin> dummies;
    private ConfigFileParser cfp;

    public PluginLoader(ConfigFileParser cfp) throws PluginLoadingFatalException {

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

    private IPlugin loadRealPlugins(IPlugin chosen)
    {

        IPlugin ip = chosen;

        try {

            ip = cfp.getRealPlugin(chosen, true);

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

    /**
     * Dummies are classes without any data but those needed by an IPlugin
     * So you can put them in a list without taking all the heap space ..
     * @return
     */
    public List<IPlugin> getDummies()
    {
        return dummies;
    }


    public List<IPlugin> getLoadedPlugins()
    {
        List<IPlugin> plugins = new ArrayList<>();

        for(IPlugin ip : dummies)
        {
            plugins.add(loadRealPlugins(ip));
        }

        return plugins;
    }

    public IPlugin getLoadedPlugins(IPlugin plugin)
    {
        for(IPlugin ip : dummies)
        {
            //TODO might not be enough testing
            if(plugin.getName().equals(ip.getName()))
                return loadRealPlugins(ip);
        }

        //if it does not work, we return the dummy
        return plugin;
    }



}
