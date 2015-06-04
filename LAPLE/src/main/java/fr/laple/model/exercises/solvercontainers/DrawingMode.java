package fr.laple.model.exercises.solvercontainers;

import fr.laple.extensions.languages.japanese.neural.NeuralExerciseSolver;
import fr.laple.model.exercises.IExerciseSolver;
import fr.laple.view.exercises.ExerciseView;

import javax.swing.*;

/**
 * Created by anthonyrey on 04/06/2015.
 */
public class DrawingMode extends SolverContainer{

    //todo add right view
    //TODO need less coupling with neural Solver (specific japaanese)
    //put drawing mode in japanese folder ?
    private IExerciseSolver solver;
    private JPanel view;

    public DrawingMode()
    {
        this.solver = new NeuralExerciseSolver();
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
        return "Drawing mode";
    }
}
