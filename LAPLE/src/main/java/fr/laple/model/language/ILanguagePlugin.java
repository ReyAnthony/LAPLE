package fr.laple.model.language;

import fr.laple.model.exercises.IExerciseMode;
import fr.laple.model.exercises.solvercontainers.SolverContainer;

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
    //public LessonContainer getLessonContainer();
    public ArrayList<SymbolContainer> getSymbolContainer();
    public String getVersion();
    public ArrayList<IExerciseMode> getExercisesModes();
    public ArrayList<SolverContainer> getExercisesSolvingModes();


}
