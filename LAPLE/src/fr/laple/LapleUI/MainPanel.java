package fr.laple.LapleUI;

import fr.laple.Exercises.ExModeUlangTlang;
import fr.laple.Exercises.Exercise;
import fr.laple.Exercises.StandardExerciseSolver;
import fr.laple.language.Symbol;

import javax.swing.*;

/**
 * This class is the main LAPLE panel. It contains the software's manual
 *
 * @author anthonyrey
 */
public class MainPanel extends JPanel {

    private JTextArea manual;

    public MainPanel(){

        manual = new JTextArea();
        manual.setEnabled(false);

        createUI();

    }

    private void createUI()
    {
        manual.setText("Manual");
        this.add(manual);

    }

}
