package fr.laple.extensions.plugins;

import java.io.File;

/**
 * This interface defines an IPlugin
 * ILanguagePlugin and IFeaturePlugin extends this inteface
 *
 * @see fr.laple.extensions.plugins.languages.ILanguagePlugin
 * @see fr.laple.extensions.plugins.features.IFeaturePlugin
 *
 * @author anthonyrey
 */
public interface IPlugin {

    public String getName();
    public String getDescription();
    public String getDeveloper();
    public String getVersion();
    public String otherCredits();
    public File getPath();
    public void setPath(File path);
    public boolean isInternal();
}
