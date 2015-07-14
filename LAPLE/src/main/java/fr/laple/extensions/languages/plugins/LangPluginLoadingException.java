package fr.laple.extensions.languages.plugins;

/**
 * Created by anthonyrey on 02/06/2015.
 */
public class LangPluginLoadingException extends Exception{

    public LangPluginLoadingException()
    {
        super("Error with the plugin configuration file, " +
                "check language_plugins.json or reinstall the software");
    }
}
