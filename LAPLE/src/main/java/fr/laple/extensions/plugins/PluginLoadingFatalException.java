package fr.laple.extensions.plugins;

/**
 * This class is a pluginFatalLoadingException
 * It is a fatal exception
 *
 * @author anthonyrey
 */
public class PluginLoadingFatalException extends Exception{

    public PluginLoadingFatalException(String plugin, String error)
    {
        super("Fatal error while loading "+plugin +", check software parameters or reinstall the software\n"+error);
    }
}
