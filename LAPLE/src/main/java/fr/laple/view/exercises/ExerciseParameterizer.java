package fr.laple.view.exercises;

import fr.laple.controller.exercises.ExerciseParameterizerController;
import fr.laple.model.exercises.IExerciseMode;
import fr.laple.model.exercises.answers.AbstractAnswerMode;
import fr.laple.model.language.ILanguagePlugin;
import fr.laple.model.language.SymbolContainer;

import javax.swing.*;
import java.awt.*;

/**
 * Created by anthonyrey on 02/06/2015.
 */
public class ExerciseParameterizer extends JPanel{

    private JLabel questionModeMessage;
    private JLabel symbolModeMessage;
    private JComboBox<SymbolContainer> symbolMode;
    private JButton okButton;
    private JComboBox<IExerciseMode> questionMode;
    private JComboBox<AbstractAnswerMode> answerMode;
    private JLabel answerModeMessage;

    public ExerciseParameterizer(ILanguagePlugin model) {

        GridLayout layout = new GridLayout(4,1);
        this.setLayout(layout);

        this.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JPanel symbolModePanel = new JPanel();
        symbolModeMessage = new JLabel("Select a symbol mode :");
        symbolModeMessage.setHorizontalAlignment(JLabel.CENTER);
        symbolModeMessage.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        symbolMode = new JComboBox<>();

        symbolModePanel.add(symbolModeMessage);
        symbolModePanel.add(symbolMode);

        questionModeMessage = new JLabel("Select a question mode :");
        questionModeMessage.setHorizontalAlignment(JLabel.CENTER);
        questionModeMessage.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        questionMode = new JComboBox<>();
        okButton = new JButton("OK");

        JPanel panel = new JPanel();
        answerModeMessage = new JLabel("Select an answer mode :");
        answerMode = new JComboBox<>();

        panel.add(answerModeMessage);
        panel.add(answerMode);
        panel.add(okButton);

        this.add(symbolModePanel, BorderLayout.PAGE_START);
        this.add(questionModeMessage, BorderLayout.PAGE_START);
        this.add(questionMode, BorderLayout.CENTER);
        this.add(panel, BorderLayout.PAGE_END);

        ExerciseParameterizerController controller = new ExerciseParameterizerController(model, this);
        okButton.addActionListener(controller);
        questionMode.addItemListener(controller);

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

}
