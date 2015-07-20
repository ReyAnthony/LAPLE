package fr.laple.model.exercises.answers;

import fr.laple.controller.exercises.AbstractExerciseController;
import fr.laple.model.exercises.exercisemode.IExerciseMode;
import fr.laple.model.exercises.solver.IExerciseSolver;
import fr.laple.view.exercises.AbstractExerciseView;

/**
 * This class defines an answer mode
 *
 * @author anthonyrey
 */
public abstract class AbstractAnswerMode {

    /**
     * @see fr.laple.model.exercises.solver.IExerciseSolver
     * @see fr.laple.model.exercises.solver.StandardExerciseSolver
     *
     * @return An instance of IExercise Solver
     */
    public abstract IExerciseSolver getSolver();

    /**
     * @see fr.laple.view.exercises.AbstractExerciseView
     * @return An Instance of an ExercisView
     */
    public abstract AbstractExerciseView getCorrespondingView();

    /**
     * This tests if the given ExerciseMode is compatible with this answerMode
     *
     * @see fr.laple.model.exercises.exercisemode.IExerciseMode
     * @see fr.laple.model.exercises.exercisemode.ExModeTranscriptLangUserLang
     * @see fr.laple.model.exercises.exercisemode.ExModeUserLangTranscriptLang
     *
     * @param mode An IExerciseMode
     * @return true if right else false
     */
    public abstract boolean testIfAnswerAndQuestionAreCompatible(IExerciseMode mode);

    /**
     * This method retrieve the action listener for the mode
     *
     * @see fr.laple.controller.exercises.AbstractExerciseController
     * @see fr.laple.controller.exercises.FreeInputExerciseController
     * @see fr.laple.controller.exercises.QcmInputController
     *
     * @return An AbstractExerciseController (or an instance of its child to be more precise)
     */
    public abstract AbstractExerciseController getAssociatedActionListener();

    /**
     * @return "Please redefine this method"
     */
    //Done before knowing about default methods
    public String toString(){
        return "Please redefine this method";
    }


}
