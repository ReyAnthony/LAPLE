package fr.laple.model.exercises.answers;

import fr.laple.controller.exercises.AbstractExerciseController;
import fr.laple.model.exercises.IExerciseMode;
import fr.laple.model.exercises.IExerciseSolver;
import fr.laple.view.exercises.AbstractExerciseView;

/**
 * Created by anthonyrey on 04/06/2015.
 */
public abstract class AbstractAnswerMode {

    public abstract IExerciseSolver getSolver();
    public abstract AbstractExerciseView getCorrespondingView();
    public abstract boolean testIfAnswerAndQuestionAreCompatible(IExerciseMode mode);
    public abstract AbstractExerciseController getAssociatedActionListener();

    public String toString(){
        return "Please redefine this method";
    }


}