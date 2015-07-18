package fr.laple.extensions.plugins;

/**
 * Created by anthonyrey on 02/06/2015.
 */
public class PluginLoadingException extends Exception{

    public PluginLoadingException(String plugin, String error)
    {
        super("Error with the following plugin : " + plugin+"\n"+error );
    }
}
