package fr.laple.model.exercises.answers;

import fr.laple.controller.exercises.AbstractExerciseController;
import fr.laple.extensions.languages.japanese.neural.NeuralExerciseSolver;
import fr.laple.model.exercises.ExModeUserLangTranscriptLang;
import fr.laple.model.exercises.IExerciseMode;
import fr.laple.model.exercises.IExerciseSolver;
import fr.laple.view.exercises.AbstractExerciseView;

/**
 * Created by anthonyrey on 04/06/2015.
 */
public class DrawingMode extends AbstractAnswerMode {

    @Override
    public IExerciseSolver getSolver() {
        return new NeuralExerciseSolver();
    }

    @Override
    public AbstractExerciseView getCorrespondingView() {
        return null;
    }

    @Override
    public boolean testIfAnswerAndQuestionAreCompatible(IExerciseMode mode) {

        return mode instanceof ExModeUserLangTranscriptLang;

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
