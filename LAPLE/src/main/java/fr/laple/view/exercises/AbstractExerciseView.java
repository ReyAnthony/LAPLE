package fr.laple.view.exercises;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * Abstract view for the exercises, the others are using this one as a base
 * @author anthonyrey
 */
public abstract class AbstractExerciseView extends JPanel {

    private JButton nextButton;
    private JLabel remainingCount;
    private JLabel succesCount;
    private JButton backButton;

    public AbstractExerciseView()
    {
        nextButton = new JButton("Next Exercise");
        nextButton.setEnabled(false);

        backButton = new JButton("Back");
        remainingCount = new JLabel("Exercises left :");
        succesCount = new JLabel("Suceeded : N/N");
    }

    public JButton getBackButton()
    {
        return backButton;
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
