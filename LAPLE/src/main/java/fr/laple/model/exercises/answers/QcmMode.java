package fr.laple.model.exercises.answers;

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

    //TODO add right view
    private IExerciseSolver solver;
    private AbstractExerciseView view;
    private Class listener;

    public QcmMode()
    {
        this.solver = new StandardExerciseSolver();
        this.view = new QCMExerciseView();
        this.listener = QcmInputController.class;

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

        return true;
    }

    @Override
    public Class getAssociatedActionListener() {
        return listener;
    }

    public String toString()
    {
        return "QCM mode";

    }
}
