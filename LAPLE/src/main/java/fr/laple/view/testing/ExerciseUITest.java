package fr.laple.view.testing;

import fr.laple.model.exercises.ExModeTranscriptLangUserLang;
import fr.laple.model.exercises.Exercise;
import fr.laple.model.exercises.StandardExerciseSolver;
import fr.laple.model.language.Symbol;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Test GUI for exercises
 *
 * @author anthonyrey
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
            Exercise exo = null;

            try {
                exo = new Exercise(wantedSymbol, new ExModeTranscriptLangUserLang(), new StandardExerciseSolver(), null);
            } catch (Exception e1) {
                e1.printStackTrace();
            }

            boolean win;
            win = exo.solveExercice(answer.getText());
            okNok.setText(String.valueOf(win));

            if(win == true)
            {
                okNok.setForeground(Color.green);
            }
            else
            {
                okNok.setForeground(Color.red);
            }






        }

    }
}
