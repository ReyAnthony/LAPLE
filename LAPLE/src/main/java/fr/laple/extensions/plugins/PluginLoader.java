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

    public PluginLoader(ConfigFileParser cfp) throws PluginLoadingException {

        dummies = new ArrayList<>();

         this.cfp = cfp;

        for(IPlugin p : cfp.getDummies())
        {
            loadDummies(p);
        }
    }

    private void loadDummies(IPlugin chosen) throws PluginLoadingException
    {
        dummies.add(chosen);
    }

    private IPlugin loadRealPlugins(IPlugin chosen)
    {

        IPlugin ip = chosen;

        //if not internal
        try {

            ip = cfp.getRealPlugin(chosen);

        } catch (Exception e) {

            //If there is an error when loading, we create a dummy plugin, so we can always review it later
            JOptionPane.showMessageDialog(null, "There was an error loading feature \" " + chosen.getName() + " \" ",
                    "Error", JOptionPane.WARNING_MESSAGE);

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
