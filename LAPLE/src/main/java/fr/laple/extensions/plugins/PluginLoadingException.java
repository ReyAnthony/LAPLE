package fr.laple.extensions.plugins;

/**
 * Created by anthonyrey on 02/06/2015.
 */
public class PluginLoadingException extends Exception{

    public PluginLoadingException()
    {
        super("Error with the feature plugin configuration file, " +
                "check feature_plugins.json, software parameters or reinstall the software");
    }
}
