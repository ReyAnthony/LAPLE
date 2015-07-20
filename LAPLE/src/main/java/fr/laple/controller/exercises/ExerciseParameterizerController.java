package fr.laple.controller.exercises;

import fr.laple.model.datamodel.LapleDataModel;
import fr.laple.model.exercises.Exercise;
import fr.laple.model.exercises.answers.AbstractAnswerMode;
import fr.laple.model.exercises.exercisemode.IExerciseMode;
import fr.laple.model.exercises.solver.IExerciseSolver;
import fr.laple.model.language.Symbol;
import fr.laple.model.language.SymbolContainer;
import fr.laple.model.listable.RootData;
import fr.laple.view.ListView;
import fr.laple.view.exercises.AbstractExerciseView;
import fr.laple.view.exercises.ExerciseParameterizerView;
import fr.laple.ztools.tabTools.TabTools;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

/**
 * This class is the controller for the ExerciseParamaterizerView
 *
 * @see fr.laple.view.exercises.ExerciseParameterizerView
 *
 * @author anthonyrey
 */
public class ExerciseParameterizerController implements ActionListener, ItemListener {

    private LapleDataModel model;
    private ExerciseParameterizerView view;
    private RootData rootData;

    /**
     * Controller to the class
     *
     * @param model The LapleDataModel
     * @param view  An ExerciseParameterizerView
     * @param rootData  A RootDataObject
     */
    public ExerciseParameterizerController(LapleDataModel model, ExerciseParameterizerView view, RootData rootData) {

        this.model = model;
        this.view = view;
        this.rootData = rootData;
        setComboBoxes();
    }

    /**
     * Set the comboBoxes to the values of the symbol container modes (hiragana etc..)
     * Then set the ExercisesModes and finally call setSolverCombo and setSolverMaxValue
     *
     */
    private void setComboBoxes() {

        ComboBoxModel<SymbolContainer> comboModel =
                new DefaultComboBoxModel<>(model.getSymbolContainer().toArray(new SymbolContainer[]{}));
        view.getSymbolMode().setModel(comboModel);

        ComboBoxModel<IExerciseMode> comboModelBis =
                new DefaultComboBoxModel<>(model.getExercisesModes().toArray(new IExerciseMode[]{}));
        view.getQuestionMode().setModel(comboModelBis);

        setSolverCombo();
        setSolverMaxValue();

    }

    /**
     * Remove the incompatible answers modes according to the set question mode
     */
    private void setSolverCombo()
    {

        ArrayList<AbstractAnswerMode> solvers = new ArrayList<>();

        //selecting solver that are compatible with the current questionMode
        for(AbstractAnswerMode solver :  model.getExercisesSolvingModes())
        {
            IExerciseMode selectedExerciseMode = (IExerciseMode) view.getQuestionMode().getSelectedItem();

            if(solver.testIfAnswerAndQuestionAreCompatible(selectedExerciseMode))
            {
                solvers.add(solver);
            }

        }

        ComboBoxModel<AbstractAnswerMode> comboModelTer = new DefaultComboBoxModel<>
                (solvers.toArray(new AbstractAnswerMode[]{}));
        view.getAnswerMode().setModel(comboModelTer);

    }

    /**
     * Set the upper limit for the exercise count
     */
    private void setSolverMaxValue()
    {
        SymbolContainer sc = (SymbolContainer) view.getSymbolMode().getSelectedItem();
        JSpinner countSelector = view.getExerciseCountSelector();
        countSelector.setModel(new SpinnerNumberModel(1, 1, sc.getSize(), 1));
    }

    /**
     * Here two cases :
     *
     * - Back : get back to the root view defined in RooData Object (Will do DB stuff)
     * - Ok : Create an exercise according to DB data (the symbols you know)
     *
     * @see fr.laple.model.listable.RootData
     *
     * @param e An ActionEvent Object
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource().equals(this.view.getOkButton()))
        {
            SymbolContainer sc = (SymbolContainer) view.getSymbolMode().getSelectedItem();
            IExerciseMode mode = (IExerciseMode) view.getQuestionMode().getSelectedItem();
            AbstractAnswerMode answerMode = (AbstractAnswerMode) view.getAnswerMode().getSelectedItem();
            IExerciseSolver solver =  answerMode.getSolver();
            AbstractExerciseView exView = answerMode.getCorrespondingView();

            //TODO BDD check
            //TODO replace by bdd given data

            //Random test

            ArrayList<Symbol> sym = new ArrayList<>(sc.getSymbolMap().values());
            Collections.shuffle(sym);
            LinkedList<Exercise> exercises = new LinkedList<>();

            for(int i = 0; i < view.getExerciseCount(); i++)
            {
                Exercise ex = new Exercise( sym.get(i) , mode, solver, sc);
                exercises.push(ex);
            }

            AbstractExerciseController listener = answerMode.getAssociatedActionListener();
            JTabbedPane tabbedPane = (JTabbedPane) view.getParent();

            TabTools.swapTab(tabbedPane, exView);
            exView.addActionListener(listener);

            listener.addExercises(exercises);
            listener.setSymbolContainer(sc);
            listener.init(exView, rootData);
            listener.addModel(model);
        }
        else if(e.getSource().equals(view.getBackButton()))
        {
            JTabbedPane tabbedPane = (JTabbedPane) view.getParent();
            TabTools.swapTab(tabbedPane, new ListView(model, rootData.getRootModel(), false,
                    rootData));

        }

    }

    /**
     * If you change the value of the symbol mode combo or the question mode, we re update the values
     *
     * @param e An ItemEvent Object
     */
    @Override
    public void itemStateChanged(ItemEvent e) {

        if(e.getSource().equals(view.getSymbolMode()))
        {
            setSolverMaxValue();
        }
        else if(e.getSource().equals(view.getQuestionMode()))
        {
            //we set the comboBoxes again;
            setSolverCombo();
        }

    }


}
