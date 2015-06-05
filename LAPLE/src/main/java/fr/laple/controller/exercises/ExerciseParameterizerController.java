package fr.laple.controller.exercises;

import fr.laple.model.exercises.Exercise;
import fr.laple.model.exercises.IExerciseMode;
import fr.laple.model.exercises.IExerciseSolver;
import fr.laple.model.exercises.answers.AbstractAnswerMode;
import fr.laple.model.language.ILanguagePlugin;
import fr.laple.model.language.Symbol;
import fr.laple.model.language.SymbolContainer;
import fr.laple.view.exercises.AbstractExerciseView;
import fr.laple.view.exercises.ExerciseParameterizer;

import javax.swing.*;
import java.awt.*;
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

    private ILanguagePlugin model;
    private ExerciseParameterizer parameterizer;

    public ExerciseParameterizerController(ILanguagePlugin model, ExerciseParameterizer parameterizer) {

        this.model = model;
        this.parameterizer = parameterizer;
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

    @Override
    public void actionPerformed(ActionEvent e) {

        SymbolContainer sc = (SymbolContainer) parameterizer.getSymbolMode().getSelectedItem();
        IExerciseMode mode = (IExerciseMode) parameterizer.getQuestionMode().getSelectedItem();
        AbstractAnswerMode answerMode = (AbstractAnswerMode) parameterizer.getAnswerMode().getSelectedItem();
        IExerciseSolver solver =  answerMode.getSolver();
        AbstractExerciseView exView = answerMode.getCorrespondingView();

        //TODO BDD check

        //Random test

        ArrayList<Symbol> sym = new ArrayList<>(sc.getSymbolMap().values());
        Collections.shuffle(sym);
        LinkedList<Exercise> exercises = new LinkedList<>();

        //TODO selector for ex count
        for(int i = 0; i < parameterizer.getExerciseCount(); i++)
        {
            Exercise ex = new Exercise( sym.get(i) , mode, solver, sc);
            exercises.push(ex);
        }


        AbstractExerciseController listener = answerMode.getAssociatedActionListener();

        parameterizer.invalidate();
        parameterizer.removeAll();
        parameterizer.setLayout(new BorderLayout());
        parameterizer.add(exView);
        exView.addActionListener(listener);
        parameterizer.revalidate();
        parameterizer.repaint();

        listener.addExercises(exercises);
        listener.setSymbolContainer(sc);
        listener.setView(exView);
        listener.addModel(model);


    }

    @Override
    public void itemStateChanged(ItemEvent e) {

        //we set the comboBoxes again;
        setSolverCombo();

    }


}
