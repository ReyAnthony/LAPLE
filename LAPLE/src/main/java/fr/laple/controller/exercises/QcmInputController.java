package fr.laple.controller.exercises;

import fr.laple.model.language.Symbol;
import fr.laple.view.exercises.QCMExerciseView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Collections;

/**
 * This class defines the controller for a FreeInputExercise
 *
 * @see fr.laple.controller.exercises.AbstractExerciseController
 * @see fr.laple.view.exercises.AbstractExerciseView
 * @see fr.laple.view.exercises.FreeInputExerciseView
 * @see fr.laple.controller.exercises.FreeInputExerciseController
 *
 * @author anthonyrey
 */
public class QcmInputController extends AbstractExerciseController {

    /**
     * Action performed when qcm Button is clicked
     * Add blinkers accordingly
     *
     * @see fr.laple.controller.exercises.Blinker
     *
     * @param e An ActionEvent Object
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        super.actionPerformed(e);

        QCMExerciseView view = (QCMExerciseView) getView();

        if (getExercise().solveExercice(((JButton) e.getSource()).getText()))
        {
            addBlinker(new Blinker(((JButton) e.getSource()), true));
            incrementSucesses();
        }
        else
        {
            addBlinker(new Blinker(((JButton) e.getSource()), false));

            for (JButton button : view.getQcmButtons())
            {
                if(button.getText().equals(getExercise().getAnwser()))
                    addBlinker(new Blinker(button, true));
            }

        }

        for (JButton button : view.getQcmButtons())
        {
            button.setEnabled(false);
            button.setOpaque(true);
        }

        //TODO fix color background / transparency bug
        view.getNextButton().setEnabled(true);
    }

    /**
     * Update the view, see the parent class as the behavior is shared
     * Also add the new buttons and shuffle them
     *
     * @see fr.laple.controller.exercises.AbstractExerciseController
     */
    public void updateTheView()
    {
        super.updateTheView();
        QCMExerciseView view = (QCMExerciseView) getView();


        ArrayList<JButton> buttons = view.getQcmButtons();

        //TODO BDD check

        Collections.shuffle(buttons);

        String answer = getExercise().getAnwser();
        buttons.get(0).setText(answer);

        ArrayList<Symbol> sym = new ArrayList<>(getSymbolContainer().getSymbolMap().values());
        Collections.shuffle(sym);

        int i = 1;
        for(Symbol s : sym)
        {
            if(i == 4)
                break;

            if(getExercise().getInAnswerFormat(s).equals(answer))
                continue;

            buttons.get(i).setText(getExercise().getInAnswerFormat(s));
            i++;


        }

        setFontSize();



    }

}
