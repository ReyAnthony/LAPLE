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


    public void updateTheView()
    {
        super.updateTheView();
        QCMExerciseView view = (QCMExerciseView) getView();

        view.getSymbol().setText(getExercise().getQuestion());
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



    }

}
