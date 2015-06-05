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
            setBlinker(new Blinker(getView(), true));
        else
             setBlinker(new Blinker(getView(), false));

        for (JButton button : view.getQcmButtons())
            button.setEnabled(false);

        view.getNextButton().setVisible(true);

    }


    public void setTheView()
    {
        super.setTheView();
        QCMExerciseView view = (QCMExerciseView) getView();

        view.getSymbol().setText(getExercise().getQuestion());
        ArrayList<JButton> buttons = view.getQcmButtons();

        //TODO BDD check

        buttons.get(0).setText(getExercise().getAnwser());


        ArrayList<Symbol> sym = new ArrayList<>(getSymbolContainer().getSymbolMap().values());
        Collections.shuffle(sym);

        buttons.get(1).setText(getExercise().getInAnswerFormat(sym.get(0)));
        buttons.get(2).setText(getExercise().getInAnswerFormat(sym.get(1)));
        buttons.get(3).setText(getExercise().getInAnswerFormat(sym.get(2)));

        Collections.shuffle(buttons);
    }

}
