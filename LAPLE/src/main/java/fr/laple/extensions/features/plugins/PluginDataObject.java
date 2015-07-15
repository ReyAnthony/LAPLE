package fr.laple.extensions.features.plugins;

import java.io.File;

/**
 * Created by anthonyrey on 15/07/2015.
 */
public class PluginDataObject {

    private File path;
    private String name;

    public PluginDataObject(File path, String name)
    {
        this.path = path;
        this.name = name;
    }


    public File getPath() {
        return path;
    }

    public String getPathString() {
        return  path.getPath();
    }

    public String getName() {
        return name;
    }
}
