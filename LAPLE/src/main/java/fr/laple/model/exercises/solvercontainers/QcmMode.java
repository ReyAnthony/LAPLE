package fr.laple.model.exercises.solvercontainers;

import fr.laple.model.exercises.IExerciseSolver;
import fr.laple.model.exercises.StandardExerciseSolver;
import fr.laple.view.exercises.ExerciseView;

import javax.swing.*;

/**
 * Created by anthonyrey on 04/06/2015.
 */
public class QcmMode extends SolverContainer {

    //TODO add right view
    private IExerciseSolver solver;
    private JPanel view;

    public QcmMode()
    {
        this.solver = new StandardExerciseSolver();
        this.view = new ExerciseView();

    }

    @Override
    public IExerciseSolver getSolver() {
        return solver;
    }

    @Override
    public JPanel getCorrespondingView() {
        return view;
    }

    public String toString()
    {
        return "QCM mode";

    }
}
