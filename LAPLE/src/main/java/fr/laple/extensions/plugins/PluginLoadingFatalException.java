package fr.laple.extensions.plugins;

/**
 * Created by anthonyrey on 02/06/2015.
 */
public class PluginLoadingFatalException extends Exception{

    public PluginLoadingFatalException(String plugin, String error)
    {
        super("Fatal error while loading "+plugin +", check software parameters or reinstall the software\n"+error);
    }
}
