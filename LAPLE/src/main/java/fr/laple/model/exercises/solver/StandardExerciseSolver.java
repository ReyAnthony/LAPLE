package fr.laple.model.exercises.solver;

import fr.laple.model.exercises.exercisemode.IExerciseMode;
import fr.laple.model.language.Symbol;

/**
 * This class is an implementation of IExercicesSolver, it solves by comparing answer and wanted only
 *
 * @author anthonyrey
 */
public class StandardExerciseSolver implements IExerciseSolver {
    @Override
    public boolean solveExercise(Symbol answer, Symbol wanted, IExerciseMode mode) {

        //get the right answer member from symbol according to the set mode
        return mode.getAnswer(answer).equals(mode.getAnswer(wanted));
    }

    public String toString()
    {
        return "Standard Exercise solver";
    }
}
