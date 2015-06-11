package fr.laple.view.exercises;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * Created by anthonyrey on 04/06/2015.
 */
public abstract class AbstractExerciseView extends JPanel {

    private JButton nextButton;
    private JLabel remainingCount;
    private JLabel succesCount;

    public AbstractExerciseView()
    {
        nextButton = new JButton("Next Exercise");
        nextButton.setEnabled(false);
        remainingCount = new JLabel("Exercises left :");
        succesCount = new JLabel("Suceeded : N/N");
    }

    public abstract void addActionListener(ActionListener al);

    public abstract JLabel getSymbol();

    public abstract void setSymbol(JLabel symbol);

    public abstract JTextField getAnswer();

    public JButton getNextButton() {
        return nextButton;
    }

    public abstract void resetTheView();

    public JLabel getRemainingCount() {
        return remainingCount;
    }

    public JLabel getSuccesCount() {
        return succesCount;
    }





}
