package fr.laple.Exercises;

import fr.laple.language.Symbol;

/**
 * Created by anthonyrey on 23/04/2015.
 */
public class StandardExerciseSolver implements IExerciseSolver {
    @Override
    public boolean solveExercise(Symbol answer, Symbol wanted, IExerciseMode mode) {

        //get the right answer member from symbol according to the set mode
        return mode.getAnswers(answer).equals(mode.getAnswers(wanted));
    }
}
