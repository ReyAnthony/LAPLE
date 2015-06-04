package fr.laple.model.exercises.solvercontainers;

import fr.laple.model.exercises.ExModeUserLangTranscriptLang;
import fr.laple.model.exercises.IExerciseMode;
import fr.laple.model.exercises.IExerciseSolver;
import fr.laple.model.exercises.StandardExerciseSolver;
import fr.laple.view.exercises.AbstractExerciseView;
import fr.laple.view.exercises.FreeInputExcerciseView;

/**
 * Created by anthonyrey on 04/06/2015.
 */
public class FreeInputMode extends AbstractAnswerMode {

    private IExerciseSolver solver;
    private AbstractExerciseView view;

    public FreeInputMode()
    {
        this.solver = new StandardExerciseSolver();
        this.view = new FreeInputExcerciseView();

    }
    @Override
    public IExerciseSolver getSolver() {
        return solver;
    }

    @Override
    public AbstractExerciseView getCorrespondingView() {
        return view;
    }

    @Override
    public boolean testIfAnswerAndQuestionAreCompatible(IExerciseMode mode) {

        if (mode instanceof ExModeUserLangTranscriptLang)
            return false;
        else
            return true;
    }

    public String toString()
    {
        return "Free input";
    }
}
