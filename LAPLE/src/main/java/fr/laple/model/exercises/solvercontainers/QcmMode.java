package fr.laple.model.exercises.solvercontainers;

import fr.laple.model.exercises.IExerciseMode;
import fr.laple.model.exercises.IExerciseSolver;
import fr.laple.model.exercises.StandardExerciseSolver;
import fr.laple.view.exercises.AbstractExerciseView;
import fr.laple.view.exercises.FreeInputExcerciseView;

/**
 * Created by anthonyrey on 04/06/2015.
 */
public class QcmMode extends AbstractAnswerMode {

    //TODO add right view
    private IExerciseSolver solver;
    private AbstractExerciseView view;

    public QcmMode()
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

        return true;
    }

    public String toString()
    {
        return "QCM mode";

    }
}
