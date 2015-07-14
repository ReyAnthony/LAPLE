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
import fr.laple.view.exercises.ExerciseParameterizer;
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
 * Created by anthonyrey on 03/06/2015.
 */
public class ExerciseParameterizerController implements ActionListener, ItemListener {

    private LapleDataModel model;
    private ExerciseParameterizer parameterizer;
    private RootData rootData;

    public ExerciseParameterizerController(LapleDataModel model, ExerciseParameterizer parameterizer, RootData rootData) {

        this.model = model;
        this.parameterizer = parameterizer;
        this.rootData = rootData;
        setComboBoxes();
    }

    private void setComboBoxes() {

        ComboBoxModel<SymbolContainer> comboModel =
                new DefaultComboBoxModel<>(model.getSymbolContainer().toArray(new SymbolContainer[]{}));
        parameterizer.getSymbolMode().setModel(comboModel);

        ComboBoxModel<IExerciseMode> comboModelBis =
                new DefaultComboBoxModel<>(model.getExercisesModes().toArray(new IExerciseMode[]{}));
        parameterizer.getQuestionMode().setModel(comboModelBis);

        setSolverCombo();
        setSolverMaxValue();

    }

    private void setSolverCombo()
    {

        ArrayList<AbstractAnswerMode> solvers = new ArrayList<>();

        //selecting solver that are compatible with the current questionMode
        for(AbstractAnswerMode solver :  model.getExercisesSolvingModes())
        {
            IExerciseMode selectedExerciseMode = (IExerciseMode) parameterizer.getQuestionMode().getSelectedItem();

            if(solver.testIfAnswerAndQuestionAreCompatible(selectedExerciseMode))
            {
                solvers.add(solver);
            }

        }

        ComboBoxModel<AbstractAnswerMode> comboModelTer = new DefaultComboBoxModel<>
                (solvers.toArray(new AbstractAnswerMode[]{}));
        parameterizer.getAnswerMode().setModel(comboModelTer);

    }

    private void setSolverMaxValue()
    {
        SymbolContainer sc = (SymbolContainer) parameterizer.getSymbolMode().getSelectedItem();
        JSpinner countSelector = parameterizer.getExerciseCountSelector();
        countSelector.setModel(new SpinnerNumberModel(1, 1, sc.getSize(), 1));
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource().equals(this.parameterizer.getOkButton()))
        {
            SymbolContainer sc = (SymbolContainer) parameterizer.getSymbolMode().getSelectedItem();
            IExerciseMode mode = (IExerciseMode) parameterizer.getQuestionMode().getSelectedItem();
            AbstractAnswerMode answerMode = (AbstractAnswerMode) parameterizer.getAnswerMode().getSelectedItem();
            IExerciseSolver solver =  answerMode.getSolver();
            AbstractExerciseView exView = answerMode.getCorrespondingView();

            //TODO BDD check
            //TODO replace by bdd given data

            //Random test

            ArrayList<Symbol> sym = new ArrayList<>(sc.getSymbolMap().values());
            Collections.shuffle(sym);
            LinkedList<Exercise> exercises = new LinkedList<>();

            for(int i = 0; i < parameterizer.getExerciseCount(); i++)
            {
                Exercise ex = new Exercise( sym.get(i) , mode, solver, sc);
                exercises.push(ex);
            }

            AbstractExerciseController listener = answerMode.getAssociatedActionListener();

            //TODO must be generalized
            //TODO maybe an helper class to work with tabs  ?
            JTabbedPane tabbedPane = (JTabbedPane) parameterizer.getParent();

            TabTools.swapTab(tabbedPane, exView);
            exView.addActionListener(listener);

            listener.addExercises(exercises);
            listener.setSymbolContainer(sc);
            listener.init(exView, rootData);
            listener.addModel(model);
        }
        else if(e.getSource().equals(parameterizer.getBackButton()))
        {
            JTabbedPane tabbedPane = (JTabbedPane) parameterizer.getParent();
            TabTools.swapTab(tabbedPane, new ListView(model, rootData.getRootModel(), false,
                    rootData));

        }

    }

    @Override
    public void itemStateChanged(ItemEvent e) {

        if(e.getSource().equals(parameterizer.getSymbolMode()))
        {
            setSolverMaxValue();
        }
        else if(e.getSource().equals(parameterizer.getQuestionMode()))
        {
            //we set the comboBoxes again;
            setSolverCombo();
        }

    }


}
