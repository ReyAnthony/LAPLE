package fr.laple.controller.exercises;

import fr.laple.model.datamodel.LapleDataModel;
import fr.laple.model.exercises.Exercise;
import fr.laple.model.language.SymbolContainer;
import fr.laple.model.listable.RootData;
import fr.laple.view.ListView;
import fr.laple.view.exercises.AbstractExerciseView;
import fr.laple.ztools.tabTools.TabTools;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * An abstract class to generalize exercises controllers
 *
 * @see fr.laple.view.exercises.AbstractExerciseView
 * @see fr.laple.view.exercises.FreeInputExerciseView
 * @see fr.laple.controller.exercises.FreeInputExerciseController
 * @see fr.laple.controller.exercises.QcmInputController
 *
 * @author anthonyrey
 */
public abstract class AbstractExerciseController implements ActionListener {


    private AbstractExerciseView view;
    private Exercise exercise;
    private SymbolContainer sc;
    private LinkedList<Exercise> exerciseQueue;
    private ArrayList<Blinker> blinkers = new ArrayList<>();
    private LapleDataModel model;
    private int startingExerciseCount;
    private int sucesses;
    private RootData rootData;

    /**
     * This method is to initialize the exercises, which are initialized after being created
     * The referenced classes may help to understand the work flow of this class
     *
     * @see fr.laple.controller.exercises.ExerciseParameterizerController
     * @see fr.laple.model.exercises.answers.AbstractAnswerMode
     *
     * @param panel
     * @param rootData
     */
    public void init(AbstractExerciseView panel, RootData rootData) {

        this.view = panel;
        this.rootData = rootData;

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

    /**
     * Accessor method
     *
     * @return An AbstractExerciseView Object
     */
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

    /**
     * Update the view or prepare the view for a next exercise to be precise
     */
    public void updateTheView()
    {
        view.getNextButton().setEnabled(false);
        view.getSymbol().setText(getExercise().getQuestion());
    }

    /**
     * Update the messages (exercise left and success count
     */
    public void updateMessages()
    {
        view.getRemainingCount().setText("Exercises Left : "+(getExerciseCount())+"/"+ startingExerciseCount);
        view.getSuccesCount().setText("Suceeded : " + sucesses + "/" + startingExerciseCount);

    }

    /**
     * If BackButton is pressed, it calls finished
     * Other behavior are defined by the child classes
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource().equals(view.getBackButton()))
        {
            finished();
        }
    }

    /**
     * Swap the view, do some cleaning etc..
     */
    private void finished()
    {

        JTabbedPane tabbedPane = (JTabbedPane) view.getParent();
        TabTools.swapTab(tabbedPane,  new ListView(model, rootData.getRootModel(), false,
                rootData));
        tabbedPane.setEnabled(true);

    }

    /**
     * We use a font to display the symbol, when it is a word, they may be too big
     * so this method scale the symbol to a displayable size
     *
     */
    protected void setFontSize()
    {
        JLabel symbol = view.getSymbol();
        String txt = symbol.getText();
        Font f = symbol.getFont().deriveFont((float) 200 / txt.length() );
        symbol.setFont(f);
        symbol.setText(txt);
        symbol.repaint();

    }

    /**
     * Add a blinker to the blinkers list
     *
     * @see fr.laple.controller.exercises.Blinker
     *
     * @param blinker
     */
    protected void addBlinker(Blinker blinker) {
        blinkers.add(blinker);
    }

    /**
     * Increments the success variable
     */
    protected void incrementSucesses()
    {
        sucesses++;
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


    public void addModel(LapleDataModel model)
    {
        this.model = model;
    }

    public int getExerciseCount()
    {
        return exerciseQueue.size() +1;
    }

}
