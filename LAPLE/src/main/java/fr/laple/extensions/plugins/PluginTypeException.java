package fr.laple.extensions.plugins;


/**
 * This class is a pluginType Exception
 *
 * @author anthonyrey
 */
public class PluginTypeException extends Exception {

    public PluginTypeException(String plugin)
    {
        super("Error with the following plugin : " + plugin+"\n"+"Wrong type" );

    }
}
