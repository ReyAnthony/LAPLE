package fr.laple.controller.exercises;

import fr.laple.model.exercises.Exercise;
import fr.laple.model.exercises.IExerciseMode;
import fr.laple.model.exercises.IExerciseSolver;
import fr.laple.model.exercises.solvercontainers.SolverContainer;
import fr.laple.model.language.ILanguagePlugin;
import fr.laple.model.language.Symbol;
import fr.laple.model.language.SymbolContainer;
import fr.laple.view.exercises.ExerciseView;
import fr.laple.view.exercises.GenericExerciseParameterizer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by anthonyrey on 03/06/2015.
 */
public class ExerciseParameterizerController implements ActionListener, ItemListener{

    private ILanguagePlugin model;
    private GenericExerciseParameterizer parameterizer;

    public ExerciseParameterizerController(ILanguagePlugin model, GenericExerciseParameterizer parameterizer) {

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

        ArrayList<SolverContainer> solvers = new ArrayList<>();

        //selecting solver that are compatible with the current questionMode
        for(SolverContainer solver :  model.getExercisesSolvingModes())
        {
            IExerciseMode selectedExerciseMode = (IExerciseMode) parameterizer.getQuestionMode().getSelectedItem();

            if(solver.getSolver().testIfModeAndSolverAreCompatible(selectedExerciseMode))
            {
                solvers.add(solver);
            }

        }

        ComboBoxModel<SolverContainer> comboModelTer = new DefaultComboBoxModel<>
                (solvers.toArray(new SolverContainer[]{}));
        parameterizer.getAnswerMode().setModel(comboModelTer);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        SymbolContainer sc = (SymbolContainer) parameterizer.getSymbolMode().getSelectedItem();
        IExerciseMode mode = (IExerciseMode) parameterizer.getQuestionMode().getSelectedItem();
        SolverContainer solverContainer = (SolverContainer) parameterizer.getAnswerMode().getSelectedItem();
        IExerciseSolver solver =  solverContainer.getSolver();
        ExerciseView exView =(ExerciseView) solverContainer.getCorrespondingView();

        //TODO BDD check

        //Random test

        ArrayList<Symbol> sym = new ArrayList<>(sc.getSymbolMap().values());
        Collections.shuffle(sym);

        Exercise ex = null;
        try {
            ex = new Exercise( sym.get(0) , mode, solver, sc);
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        ExerciseController exerciseController = new ExerciseController(exView, ex);

        parameterizer.invalidate();
        parameterizer.removeAll();
        parameterizer.setLayout(new BorderLayout());

        parameterizer.add(exView);
        exView.addActionListener(exerciseController);
        
        parameterizer.revalidate();
        parameterizer.repaint();

    }

    @Override
    public void itemStateChanged(ItemEvent e) {

        //we set the comboBoxes again;
        setSolverCombo();

    }
}
