package fr.laple.exercises;

import fr.laple.language.Symbol;

/**
 * This class is an implementation of IExercicesSolver, it solves by comparing answer and wanted only
 *
 * @author anthonyrey
 */
public class StandardExerciseSolver implements IExerciseSolver {
    @Override
    public boolean solveExercise(Symbol answer, Symbol wanted, IExerciseMode mode) {

        //get the right answer member from symbol according to the set mode
        return mode.getAnswers(answer).equals(mode.getAnswers(wanted));
    }
}
