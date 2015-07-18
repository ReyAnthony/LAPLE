package fr.laple.model.exercises.answers;

import fr.laple.controller.exercises.AbstractExerciseController;
import fr.laple.model.exercises.exercisemode.IExerciseMode;
import fr.laple.model.exercises.solver.IExerciseSolver;
import fr.laple.view.exercises.AbstractExerciseView;

/**
 * Created by anthonyrey on 04/06/2015.
 */
public class DrawingMode extends AbstractAnswerMode {

    //TODO
    @Override
    public IExerciseSolver getSolver() {
        return null;
    }

    @Override
    public AbstractExerciseView getCorrespondingView() {
        return null;
    }

    @Override
    public boolean testIfAnswerAndQuestionAreCompatible(IExerciseMode mode) {

        return false;
        //TOOD
        //return mode instanceof ExModeUserLangTranscriptLang;

    }
    @Override
    public AbstractExerciseController getAssociatedActionListener() {
        //TODO drawing mode
        return null;
    }


    public String toString()
    {
        return "Drawing mode";
    }
}
