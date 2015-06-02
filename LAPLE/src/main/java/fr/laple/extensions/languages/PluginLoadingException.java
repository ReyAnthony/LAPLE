package fr.laple.extensions.languages;

/**
 * Created by anthonyrey on 02/06/2015.
 */
public class PluginLoadingException extends Exception{

    public PluginLoadingException()
    {
        super("Error with the plugin configuration file," +
                "check language_plugins.json or reinstall the software");
    }
}
