package fr.laple.controller.exercises;

import fr.laple.model.exercises.Exercise;
import fr.laple.model.language.ILanguagePlugin;
import fr.laple.model.language.SymbolContainer;
import fr.laple.view.exercises.AbstractExerciseView;
import fr.laple.view.exercises.ExerciseParameterizer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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
    private ArrayList<Blinker> blinkers = new ArrayList<>();
    private ILanguagePlugin model;
    private int startingExerciseCount;
    private int sucesses;

    public void init(AbstractExerciseView panel) {

        this.view = panel;

        view.getBackButton().addActionListener(this);
        view.getParent().setEnabled(false);

        startingExerciseCount = exerciseQueue.size() +1;
        updateTheView();
        updateMessages();

        view.getNextButton().addActionListener(e -> {

            try{

                setExercise(getExercises().pop());
                updateTheView();
                updateMessages();

                for(Blinker b : blinkers)
                {
                    b.stop();
                }
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

    public void updateTheView()
    {
        view.getNextButton().setEnabled(false);
        view.getSymbol().setText(getExercise().getQuestion());
    }

    public void updateMessages()
    {
        view.getRemainingCount().setText("Exercises Left : "+(getExerciseCount())+"/"+ startingExerciseCount);
        view.getSuccesCount().setText("Suceeded : " + sucesses + "/" + startingExerciseCount);

    }

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

    public void addBlinker(Blinker blinker) {
        blinkers.add(blinker);
    }

    public void addModel(ILanguagePlugin model)
    {
        this.model = model;
    }

    private void finished()
    {
        JTabbedPane tabbedPane = (JTabbedPane) view.getParent();
        tabbedPane.setEnabled(true);
        int selected = tabbedPane.getSelectedIndex();
        tabbedPane.remove(selected);

        ExerciseParameterizer newView = new ExerciseParameterizer(model);

        tabbedPane.insertTab("Exercises", null, newView, null, selected);
        tabbedPane.setSelectedIndex(selected);


    }

    public void incrementSucesses()
    {
        sucesses++;
    }

    public int getExerciseCount()
    {
        return exerciseQueue.size() +1;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource().equals(view.getBackButton()))
        {
            finished();
        }
    }

    public void setFontSize()
    {
        JLabel symbol = view.getSymbol();
        String txt = symbol.getText();
        Font f = symbol.getFont().deriveFont((float) 200 / txt.length() );
        symbol.setFont(f);
        symbol.setText(txt);
        symbol.repaint();


    }


}
