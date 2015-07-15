package fr.laple.extensions;

/**
 * Created by anthonyrey on 15/07/2015.
 */
public interface IPlugin {

    public String getName();
    public String getDescription();
    public String getDeveloper();
    public String getVersion();
    public String otherCredits();
    public String getPath();
    public void setPath(String path);
    public default boolean isRemovable(){return true;}
}
