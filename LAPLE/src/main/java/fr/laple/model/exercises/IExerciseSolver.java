package fr.laple.model.exercises;

import fr.laple.model.language.Symbol;


/**
 * This interface defines methods needed for IExerciseSolver implementations
 *
 * @author anthonyrey
 */
public interface IExerciseSolver {

    /**
     * This method is solving the exercice
     * You need to use the mode to be sure that you are comparing the right parameter as answer will be null at every
     * parameter except the one corresponding to mode.getAnswer.
     *
     * We will likely have a Standard implementation only comparing the String values and another using neuroph
     * to check if the drawing is equal to the wanted symbol.
     *
     * @see fr.laple.model.exercises.IExerciseMode
     *
     * @param answer The symbol containing the user answer, please be aware that only the field corresponding to answer
     *               mode is populated, so be careful and use IExercise mode implementation given in param
     * @param wanted The symbol we actually want to learn, compare it to the anwser
     * @param mode   An implementation of IExerciseMode
     * @return       True if the symbol are the same, false otherwize
     */
    public boolean solveExercise(Symbol answer, Symbol wanted, IExerciseMode mode);

    /**
     * This method is checking if the current solver is compatible with
     * the selected mode.
     *
     * @param mode The selected mode
     * @return False if they are not compatible, true if they are
     */
    public boolean testIfModeAndSolverAreCompatible(IExerciseMode mode);


}
