package fr.laple.view.exercises;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * Created by anthonyrey on 04/06/2015.
 */
public abstract class AbstractExerciseView extends JPanel {

    private JButton nextButton;
    private JLabel exerciseCount;

    public AbstractExerciseView()
    {
        nextButton = new JButton("Next Exercise");
        nextButton.setVisible(false);
        exerciseCount = new JLabel("Exercises left :");
    }

    public abstract void addActionListener(ActionListener al);

    public abstract JLabel getSymbol();

    public abstract void setSymbol(JLabel symbol);

    public abstract JTextField getAnswer();

    public JButton getNextButton() {
        return nextButton;
    }

    public abstract void resetTheView();

    public JLabel getCounterLabel() {
        return exerciseCount;
    }

    public void setCounterLabel(JLabel exerciseCount) {
        this.exerciseCount = exerciseCount;
    }
}
