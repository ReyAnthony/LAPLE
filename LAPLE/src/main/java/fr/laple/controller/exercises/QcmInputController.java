package fr.laple.controller.exercises;

import fr.laple.model.language.Symbol;
import fr.laple.view.exercises.QCMExerciseView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by anthonyrey on 04/06/2015.
 */
public class QcmInputController extends AbstractExerciseController {

    @Override
    public void actionPerformed(ActionEvent e) {

        QCMExerciseView view = (QCMExerciseView) getView();

        if (getExercise().solveExercice(((JButton) e.getSource()).getText()))
            addBlinker(new Blinker(((JButton) e.getSource()), true));
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

        //TODO fix color bug
        view.getNextButton().setVisible(true);

    }


    public void setTheView()
    {
        super.setTheView();
        QCMExerciseView view = (QCMExerciseView) getView();

        view.getSymbol().setText(getExercise().getQuestion());
        ArrayList<JButton> buttons = view.getQcmButtons();

        //TODO BDD check
        //TODO fix doublon bug
        //TODO check for lenght of exercise list

        Collections.shuffle(buttons);
        buttons.get(0).setText(getExercise().getAnwser());

        ArrayList<Symbol> sym = new ArrayList<>(getSymbolContainer().getSymbolMap().values());
        Collections.shuffle(sym);

        buttons.get(1).setText(getExercise().getInAnswerFormat(sym.get(0)));
        buttons.get(2).setText(getExercise().getInAnswerFormat(sym.get(1)));
        buttons.get(3).setText(getExercise().getInAnswerFormat(sym.get(2)));


    }

}
