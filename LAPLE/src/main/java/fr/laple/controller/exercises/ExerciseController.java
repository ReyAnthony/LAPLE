package fr.laple.controller.exercises;

import fr.laple.model.exercises.Exercise;
import fr.laple.view.exercises.ExerciseView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by anthonyrey on 02/06/2015.
 */
public class ExerciseController implements ActionListener {

    private ExerciseView view;
    private Exercise exercise;

    public ExerciseController(ExerciseView view, Exercise exercise)
    {
        this.view = view;
        this.exercise = exercise;
        setSetTheView();

    }

    private void setSetTheView()
    {
        view.getSymbol().setText(exercise.getQuestion());

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(exercise.solveExercice(view.getAnswer().getText()))
        {
            System.out.println("solved");

        }


    }
}
