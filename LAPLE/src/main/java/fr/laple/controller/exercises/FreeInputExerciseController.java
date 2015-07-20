package fr.laple.controller.exercises;

import fr.laple.view.exercises.FreeInputExerciseView;

import java.awt.event.ActionEvent;

/**
 * This class defines the controller for a FreeInputExercise
 *
 * @see fr.laple.controller.exercises.AbstractExerciseController
 * @see fr.laple.view.exercises.AbstractExerciseView
 * @see fr.laple.view.exercises.FreeInputExerciseView
 * @see fr.laple.controller.exercises.QcmInputController
 *
 * @author anthonyrey
 */
public class FreeInputExerciseController extends AbstractExerciseController {

    /**
     * Action performed on validation
     * Add blinkers accordingly
     *
     * @see fr.laple.controller.exercises.Blinker
     *
     * @param e An ActionEvent Object
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        super.actionPerformed(e);

        FreeInputExerciseView view = (FreeInputExerciseView) getView();

        if (getExercise().solveExercice(getView().getAnswer().getText()))
        {
            addBlinker(new Blinker(getView().getAnswer(), true));
            incrementSucesses();
        }
        else
            addBlinker(new Blinker(getView().getAnswer(), false));

        view.getAnswer().setText(getExercise().getAnwser());
        view.getAnswer().setEnabled(false);
        view.getValidationButton().setEnabled(false);
        view.getNextButton().setEnabled(true);

    }

    /**
     * Update the view, see the parent class as the behavior is shared
     * @see fr.laple.controller.exercises.AbstractExerciseController
     */
    public void updateTheView()
    {
        super.updateTheView();
        setFontSize();

    }
}
