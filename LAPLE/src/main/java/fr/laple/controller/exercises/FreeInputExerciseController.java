package fr.laple.controller.exercises;

import fr.laple.view.exercises.FreeInputExerciseView;

import java.awt.event.ActionEvent;

/**
 * Created by anthonyrey on 02/06/2015.
 */
public class FreeInputExerciseController extends AbstractExerciseController {

    @Override
    public void actionPerformed(ActionEvent e) {

        FreeInputExerciseView view = (FreeInputExerciseView) getView();

        if (getExercise().solveExercice(getView().getAnswer().getText()))
            setBlinker(new Blinker(getView(), true));
        else
            setBlinker(new Blinker(getView(), false));

        view.getAnswer().setEnabled(false);
        view.getValidationButton().setEnabled(false);
        view.getNextButton().setVisible(true);

    }

    public void setTheView()
    {
        getView().getSymbol().setText(getExercise().getQuestion());
    }
}
