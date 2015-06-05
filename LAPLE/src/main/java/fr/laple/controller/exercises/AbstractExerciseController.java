package fr.laple.controller.exercises;

import fr.laple.model.exercises.Exercise;
import fr.laple.model.language.ILanguagePlugin;
import fr.laple.model.language.SymbolContainer;
import fr.laple.view.exercises.AbstractExerciseView;
import fr.laple.view.exercises.ExerciseParameterizer;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * Created by anthonyrey on 04/06/2015.
 */
public abstract class AbstractExerciseController implements ActionListener {

    private AbstractExerciseView view;
    private Exercise exercise;
    private SymbolContainer sc;
    private LinkedList<Exercise> exerciseQueue;
    private Blinker blinker;
    private ILanguagePlugin model;

    public void setView(AbstractExerciseView panel) {
        this.view = panel;
        setTheView();

            view.getNextButton().addActionListener(e -> {

                try{

                    setExercise(getExercises().pop());
                    setTheView();
                    getBlinker().stop();
                    getView().resetTheView();
                }
                catch (NoSuchElementException exc)
                {
                    finished();
                }

            });

    }

    public AbstractExerciseView getView()
    {
        return view;
    }

    public void addExercises(LinkedList<Exercise> e) {
        this.exerciseQueue = e;
        exercise = exerciseQueue.pop();
    }


    public void setSymbolContainer(SymbolContainer sc) {
        this.sc = sc;
    }

    public abstract void setTheView();

    public Exercise getExercise() {
        return exercise;
    }

    public LinkedList<Exercise> getExercises() {
        return exerciseQueue;
    }

    public SymbolContainer getSymbolContainer() {
        return sc;
    }

    public void setExercise(Exercise ex)
    {
        exercise = ex;
    }

    public Blinker getBlinker() {
        return blinker;
    }

    public void setBlinker(Blinker blinker) {
        this.blinker = blinker;
    }

    public void addModel(ILanguagePlugin model)
    {
        this.model = model;
    }

    private void finished()
    {
        view.invalidate();
        view.removeAll();
        view.setLayout(new BorderLayout());
        view.add(new ExerciseParameterizer(model));
        view.revalidate();
        view.repaint();

    }
}
