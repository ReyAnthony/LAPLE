package fr.laple.controller.exercises;

import fr.laple.model.exercises.Exercise;
import fr.laple.model.language.Symbol;
import fr.laple.model.language.SymbolContainer;
import fr.laple.view.exercises.AbstractExerciseView;
import fr.laple.view.exercises.QCMExerciseView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by anthonyrey on 04/06/2015.
 */
public class QcmInputController implements IExerciseController {

    private QCMExerciseView view;
    private Exercise exercise;
    private SymbolContainer sc;

    @Override
    public void actionPerformed(ActionEvent e) {


        if(exercise.solveExercice(((JButton) e.getSource()).getText()))
        {
            new Blinker(this.view, true);
        }
        else
        {
            new Blinker(this.view, false);
        }

        for(JButton button : view.getQcmButtons())
        {
            button.setEnabled(false);
        }


    }

    @Override
    public void setView(AbstractExerciseView panel) {
        this.view = (QCMExerciseView) panel;
        setTheView();
    }

    @Override
    public void setExercise(Exercise e) {
        this.exercise = e;
    }

    @Override
    public void setSymbolContainer(SymbolContainer sc) {
        this.sc = sc;
    }

    private void setTheView()
    {
        view.getSymbol().setText(exercise.getQuestion());
        ArrayList<JButton> buttons = view.getQcmButtons();

        //TODO BDD check

        buttons.get(0).setText(exercise.getAnwser());


        ArrayList<Symbol> sym = new ArrayList<>(sc.getSymbolMap().values());
        Collections.shuffle(sym);

        buttons.get(1).setText(exercise.getInAnswerFormat(sym.get(0)));
        buttons.get(2).setText(exercise.getInAnswerFormat(sym.get(1)));
        buttons.get(3).setText(exercise.getInAnswerFormat(sym.get(2)));

        Collections.shuffle(buttons);
    }

}
