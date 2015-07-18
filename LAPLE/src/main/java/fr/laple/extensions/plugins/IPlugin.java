package fr.laple.extensions.plugins;

import java.io.File;

/**
 * Created by anthonyrey on 15/07/2015.
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
