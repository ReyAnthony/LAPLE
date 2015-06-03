package fr.laple.controller.exercises;

import fr.laple.model.exercises.Exercise;
import fr.laple.model.exercises.IExerciseMode;
import fr.laple.model.exercises.IExerciseSolver;
import fr.laple.model.language.ILanguagePlugin;
import fr.laple.model.language.SymbolContainer;
import fr.laple.view.exercises.ExerciseView;
import fr.laple.view.exercises.GenericExerciseParameterizer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * Created by anthonyrey on 03/06/2015.
 */
public class ExerciseParameterizerController implements ActionListener, ItemListener{

    private ILanguagePlugin model;
    private GenericExerciseParameterizer parameterizer;

    public ExerciseParameterizerController(ILanguagePlugin model, GenericExerciseParameterizer parameterizer)
    {
        this.model = model;
        this.parameterizer = parameterizer;
        setComboBoxes();
    }

    private void setComboBoxes()
    {
        ComboBoxModel<SymbolContainer> comboModel =
                new DefaultComboBoxModel<>(model.getSymbolContainer().toArray(new SymbolContainer[]{}));
        parameterizer.getSymbolMode().setModel(comboModel);

        ComboBoxModel<IExerciseMode> comboModelBis =
                new DefaultComboBoxModel<>(model.getExercisesModes().toArray(new IExerciseMode[]{}));
        parameterizer.getQuestionMode().setModel(comboModelBis);

        ComboBoxModel<IExerciseSolver> comboModelTer = new DefaultComboBoxModel<>
                (model.getExercisesSolvingModes().toArray(new IExerciseSolver[]{}));
        parameterizer.getAnswerMode().setModel(comboModelTer);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        SymbolContainer sc = (SymbolContainer) parameterizer.getSymbolMode().getSelectedItem();
        IExerciseMode mode = (IExerciseMode) parameterizer.getQuestionMode().getSelectedItem();
        IExerciseSolver solver = (IExerciseSolver) parameterizer.getAnswerMode().getSelectedItem();

        //TODO random and BDD check

        Exercise ex = null;
        try {
            ex = new Exercise( sc.getSymbol("a"), mode, solver, sc);
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        parameterizer.removeAll();
        parameterizer.add(new ExerciseView(ex));
        parameterizer.revalidate();
        parameterizer.repaint();

    }

    @Override
    public void itemStateChanged(ItemEvent e) {

    }
}
