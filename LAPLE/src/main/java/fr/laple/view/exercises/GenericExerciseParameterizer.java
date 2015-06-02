package fr.laple.view.exercises;

import javax.swing.*;
import java.awt.*;

/**
 * Created by anthonyrey on 02/06/2015.
 */
public class GenericExerciseParameterizer extends JPanel{

    private JLabel questionModeMessage;
    private JList<String> questions;
    private JButton okButton;
    private JComboBox<String> answers;
    private JLabel answerModeMessage;

    public GenericExerciseParameterizer()
    {
        BorderLayout layout = new BorderLayout();
        this.setLayout(layout);

        this.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        questionModeMessage = new JLabel("Select a question mode :");
        questionModeMessage.setHorizontalAlignment(JLabel.CENTER);
        questionModeMessage.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        questions = new JList<>();

        DefaultListModel<String> model = new DefaultListModel<>();
        model.add(0, "test");
        model.add(1, "test2");

        questions.setModel(model);
        questions.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JPanel panel = new JPanel();
        answerModeMessage = new JLabel("Select an answer mode :");
        answers = new JComboBox<>();
        answers.addItem("test");
        okButton = new JButton("OK");

        panel.add(answerModeMessage);
        panel.add(answers);
        panel.add(okButton);



        this.add(questionModeMessage, BorderLayout.PAGE_START);
        this.add(questions, BorderLayout.CENTER);
        this.add(panel, BorderLayout.PAGE_END);

    }

}
