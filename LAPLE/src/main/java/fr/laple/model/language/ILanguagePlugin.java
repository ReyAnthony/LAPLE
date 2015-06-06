package fr.laple.model.language;

import fr.laple.model.exercises.IExerciseMode;
import fr.laple.model.exercises.answers.AbstractAnswerMode;
import fr.laple.model.lessons.ILessonContainer;

import java.util.ArrayList;

/**
 * This interface is to be implemented by a LapleLanguagePlugin class. It will ensure that the software can have the
 * needed informations from the language plugin.
 * It will also take care of loadingLessons and populating SymbolContainers.
 *
 * @author anthonyrey
 *
 */
public interface ILanguagePlugin {

    public String getLanguageName();
    public ArrayList<SymbolContainer> getSymbolContainer();
    public String getVersion();
    public ArrayList<IExerciseMode> getExercisesModes() ;
    public ArrayList<AbstractAnswerMode> getExercisesSolvingModes();
    public ArrayList<ILessonContainer> getLessonContainers();

}
