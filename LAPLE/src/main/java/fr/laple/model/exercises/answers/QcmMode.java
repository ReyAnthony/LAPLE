package fr.laple.model.exercises.answers;

import fr.laple.controller.exercises.AbstractExerciseController;
import fr.laple.controller.exercises.QcmInputController;
import fr.laple.model.exercises.IExerciseMode;
import fr.laple.model.exercises.IExerciseSolver;
import fr.laple.model.exercises.StandardExerciseSolver;
import fr.laple.view.exercises.AbstractExerciseView;
import fr.laple.view.exercises.QCMExerciseView;

/**
 * Created by anthonyrey on 04/06/2015.
 */
public class QcmMode extends AbstractAnswerMode {


    @Override
    public IExerciseSolver getSolver() {
        return new StandardExerciseSolver();
    }

    @Override
    public AbstractExerciseView getCorrespondingView() {
        return new QCMExerciseView();
    }

    @Override
    public boolean testIfAnswerAndQuestionAreCompatible(IExerciseMode mode) {

        return true;
    }

    @Override
    public AbstractExerciseController getAssociatedActionListener() {
        return new QcmInputController();
    }

    public String toString()
    {
        return "QCM mode";

    }
}
