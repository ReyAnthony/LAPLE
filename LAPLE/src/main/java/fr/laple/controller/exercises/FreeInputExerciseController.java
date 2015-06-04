package fr.laple.controller.exercises;

import fr.laple.model.exercises.Exercise;
import fr.laple.model.language.SymbolContainer;
import fr.laple.view.exercises.AbstractExerciseView;

import java.awt.event.ActionEvent;

/**
 * Created by anthonyrey on 02/06/2015.
 */
public class FreeInputExerciseController implements IExerciseController{

    private AbstractExerciseView view;
    private Exercise exercise;
    private SymbolContainer sc;


    @Override
    public void actionPerformed(ActionEvent e) {

        if(exercise.solveExercice(view.getAnswer().getText()))
        {
            System.out.println("solved");

        }


    }

    @Override
    public void setView(AbstractExerciseView panel) {
        this.view = panel;
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

    }
}
