package fr.laple.view.exercises;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * Created by anthonyrey on 04/06/2015.
 */
public abstract class AbstractExerciseView extends JPanel {

    public abstract void addActionListener(ActionListener al);

    public abstract JLabel getSymbol();

    public abstract void setSymbol(JLabel symbol);

    public abstract JTextField getAnswer();
}
