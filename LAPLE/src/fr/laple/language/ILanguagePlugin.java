package fr.laple.language;

import java.io.File;
import java.util.ArrayList;

/**
 * This interface is to be implemented by a LapleLanguagePlugin class. It will ensure that the software can have the needed informations from the language plugin
 *
 * @author anthonyrey
 *
 */
public interface ILanguagePlugin {

    public String getLanguageName();
    public void loadSymbolContainers(File json);
    public void loadLessons(File json);
    //public LessonContainer getLessonContainer();
    public ArrayList<SymbolContainer> getSymbolContainer();
    public String getVersion();


}
