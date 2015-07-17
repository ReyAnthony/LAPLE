package fr.laple.extensions.plugins;

/**
 * Created by anthonyrey on 17/07/2015.
 */
public class PluginTypeException extends Exception {

    public PluginTypeException(String plugin)
    {
        super("Error with the following plugin : " + plugin+"\n"+"Wrong type" );

    }
}
