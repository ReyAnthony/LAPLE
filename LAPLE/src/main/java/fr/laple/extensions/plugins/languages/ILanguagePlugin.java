package fr.laple.extensions.plugins.languages;

import fr.laple.extensions.plugins.IPlugin;
import fr.laple.model.exercises.answers.AbstractAnswerMode;
import fr.laple.model.exercises.exercisemode.IExerciseMode;
import fr.laple.model.language.SymbolContainer;
import fr.laple.model.lessons.AbstractLessonContainer;

import java.util.ArrayList;

/**
 * This interface is to be implemented by a LapleLanguagePlugin class. It will ensure that the software can have the
 * needed informations from the language plugin.
 * It will also take care of loadingLessons and populating SymbolContainers.
 *
 * @author anthonyrey
 *
 */
public interface ILanguagePlugin extends IPlugin{

    public ArrayList<SymbolContainer> getSymbolContainer();
    public ArrayList<IExerciseMode> getExercisesModes() ;
    public ArrayList<AbstractAnswerMode> getExercisesSolvingModes();
    public ArrayList<AbstractLessonContainer> getLessonContainers();

}
