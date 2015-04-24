package fr.laple.LapleUI.Testing;

import fr.laple.Exercises.ExModeTlangUlang;
import fr.laple.Exercises.Exercise;
import fr.laple.Exercises.StandardExerciseSolver;
import fr.laple.language.Symbol;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by anthonyrey on 23/04/2015.
 */
public class ExerciseUITest extends JFrame implements ActionListener{

    private JTextField answer;
    private JLabel question;
    private JButton ok;
    private JPanel panel;
    private JLabel okNok;

    public ExerciseUITest() {

        this.setSize(500,200);
        this.setTitle("LAPLE - Exercise test");
        this.setResizable(false);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        panel = new JPanel();
        question = new JLabel("Which romaji is equal to あ ? (try a)");
        answer = new JTextField("Enter answer here");
        ok = new JButton("Validate");
        ok.addActionListener(this);
        okNok = new JLabel();

        panel.add(question);
        panel.add(answer);
        panel.add(ok);
        panel.add(okNok);
        this.setContentPane(panel);

        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource().equals(ok))
        {
            Symbol wantedSymbol = new Symbol("a", "あ", null, null, null, null);
            Exercise exo = new Exercise(wantedSymbol, new ExModeTlangUlang(), new StandardExerciseSolver(), null);
            okNok.setText(String.valueOf(exo.solveExercice(answer.getText())));

        }

    }
}
