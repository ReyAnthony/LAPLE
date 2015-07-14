package fr.laple.view.exercises;

import fr.laple.controller.exercises.ExerciseParameterizerController;
import fr.laple.model.exercises.exercisemode.IExerciseMode;
import fr.laple.model.exercises.answers.AbstractAnswerMode;
import fr.laple.extensions.languages.plugins.ILanguagePlugin;
import fr.laple.model.language.SymbolContainer;
import fr.laple.model.listable.IListable;
import fr.laple.model.listable.RootData;

import javax.swing.*;
import java.awt.*;

/**
 * Created by anthonyrey on 02/06/2015.
 */
public class ExerciseParameterizer extends JPanel implements IListable{

    private JLabel questionModeMessage;
    private JLabel countMessage;
    private JLabel symbolModeMessage;
    private JComboBox<SymbolContainer> symbolMode;
    private JButton okButton;
    private JButton backButton;
    private JComboBox<IExerciseMode> questionMode;
    private JComboBox<AbstractAnswerMode> answerMode;
    private JLabel answerModeMessage;
    private JSpinner exerciseCountSelector;

    public ExerciseParameterizer(ILanguagePlugin model) {

        GridBagLayout layout = new GridBagLayout();
        this.setLayout(layout);
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(10,10,10,10);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;

        symbolModeMessage = new JLabel("Select a symbol mode :");
        symbolModeMessage.setHorizontalAlignment(JLabel.CENTER);
        this.add(symbolModeMessage, gbc);

        gbc.gridy = 2;
        symbolMode = new JComboBox<>();
        this.add(symbolMode, gbc);

        gbc.gridy = 4;
        questionModeMessage = new JLabel("Select a question mode :");
        questionModeMessage.setHorizontalAlignment(JLabel.CENTER);
        this.add(questionModeMessage, gbc);

        gbc.gridy = 6;
        questionMode = new JComboBox<>();
        this.add(questionMode, gbc);

        gbc.gridy = 8;
        answerModeMessage = new JLabel("Select an answer mode :");
        answerModeMessage.setHorizontalAlignment(JLabel.CENTER);
        this.add(answerModeMessage, gbc);

        gbc.gridy = 10;
        answerMode = new JComboBox<>();
        this.add(answerMode, gbc);

        gbc.gridy = 12;
        countMessage = new JLabel("Number of exercises :");
        countMessage.setHorizontalAlignment(JLabel.CENTER);
        this.add(countMessage, gbc);

        gbc.gridy = 14;
        exerciseCountSelector = new JSpinner();
        this.add(exerciseCountSelector, gbc);

        gbc.gridy = 16;
        okButton = new JButton("OK");
        this.add(okButton, gbc);

        gbc.gridy = 18;
        backButton = new JButton("Back");
        this.add(backButton, gbc);

        ExerciseParameterizerController controller = new ExerciseParameterizerController(model, this);
        okButton.addActionListener(controller);
        questionMode.addItemListener(controller);
        symbolMode.addItemListener(controller);

    }

    public JComboBox<SymbolContainer> getSymbolMode() {
        return symbolMode;
    }

    public JComboBox<IExerciseMode> getQuestionMode() {
        return questionMode;
    }

    public JComboBox<AbstractAnswerMode> getAnswerMode() {
        return answerMode;
    }

    public int getExerciseCount() {
        return (Integer) exerciseCountSelector.getValue();
    }

    public JSpinner getExerciseCountSelector() {
        return exerciseCountSelector;
    }

    public JButton getOkButton()
    {
        return okButton;
    }

    public JButton getBackButton()
    {
        return backButton;
    }

    @Override
    public void expectedBehavior(JTabbedPane tabbedPane, ILanguagePlugin model, RootData rootData) {

        int selected = tabbedPane.getSelectedIndex();
        tabbedPane.remove(selected);
        tabbedPane.insertTab("Exercises", null, this, null, selected);
        tabbedPane.setSelectedIndex(selected);
    }

    @Override
    public void oneInListPreAction()
    {
        getBackButton().setVisible(false);
    }

    public String toString()
    {
        return "Standard exercises";
    }
}
