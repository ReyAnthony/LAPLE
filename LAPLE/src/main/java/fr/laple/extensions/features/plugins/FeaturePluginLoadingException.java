package fr.laple.extensions.features.plugins;

/**
 * Created by anthonyrey on 02/06/2015.
 */
public class FeaturePluginLoadingException extends Exception{

    public FeaturePluginLoadingException()
    {
        super("Error with the feature plugin configuration file, " +
                "check feature_plugins.json, software parameters or reinstall the software");
    }
}
