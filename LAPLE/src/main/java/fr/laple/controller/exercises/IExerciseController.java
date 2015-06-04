package fr.laple.controller.exercises;

import fr.laple.model.exercises.Exercise;
import fr.laple.model.language.SymbolContainer;
import fr.laple.view.exercises.AbstractExerciseView;

import java.awt.event.ActionListener;

/**
 * Created by anthonyrey on 04/06/2015.
 */
public interface IExerciseController extends ActionListener {

    public void setView(AbstractExerciseView panel);
    public void setExercise(Exercise e);
    public void setSymbolContainer(SymbolContainer sc);


}
