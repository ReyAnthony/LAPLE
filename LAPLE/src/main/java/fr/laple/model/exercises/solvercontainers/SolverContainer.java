package fr.laple.model.exercises.solvercontainers;

import fr.laple.model.exercises.IExerciseSolver;

import javax.swing.*;

/**
 * Created by anthonyrey on 04/06/2015.
 */
public abstract class SolverContainer {

    public abstract IExerciseSolver getSolver();
    public abstract JPanel getCorrespondingView();

    public String toString(){
        return "Please redefine this method";
    }


}
