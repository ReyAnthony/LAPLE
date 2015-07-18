package fr.laple.extensions.plugins;

/**
 * This class is a pluginLoading Exception
 * It is a non fatal exception
 *
 * @author anthonyrey
 */
public class PluginLoadingException extends Exception{

    public PluginLoadingException(String plugin, String error)
    {
        super("Error with the following plugin : " + plugin+"\n"+error );
    }
}
