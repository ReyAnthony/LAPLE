package fr.laple.model.exercises.answers;

import fr.laple.controller.exercises.AbstractExerciseController;
import fr.laple.controller.exercises.FreeInputExerciseController;
import fr.laple.model.exercises.exercisemode.ExModeTranscriptLangUserLang;
import fr.laple.model.exercises.exercisemode.IExerciseMode;
import fr.laple.model.exercises.solver.IExerciseSolver;
import fr.laple.model.exercises.solver.StandardExerciseSolver;
import fr.laple.view.exercises.AbstractExerciseView;
import fr.laple.view.exercises.FreeInputExerciseView;

/**
 * Created by anthonyrey on 04/06/2015.
 */
public class FreeInputMode extends AbstractAnswerMode {

    @Override
    public IExerciseSolver getSolver() {
        return new StandardExerciseSolver();
    }

    @Override
    public AbstractExerciseView getCorrespondingView() {
        return new FreeInputExerciseView();
    }

    @Override
    public boolean testIfAnswerAndQuestionAreCompatible(IExerciseMode mode) {

        return mode instanceof ExModeTranscriptLangUserLang;
    }

    @Override
    public AbstractExerciseController getAssociatedActionListener() {
        return new FreeInputExerciseController();
    }


    public String toString()
    {
        return "Free input";
    }
}
