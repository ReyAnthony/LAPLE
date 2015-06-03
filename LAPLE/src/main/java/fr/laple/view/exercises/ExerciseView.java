package fr.laple.view.exercises;

import fr.laple.controller.exercises.ExerciseController;
import fr.laple.model.exercises.Exercise;

import javax.swing.*;
import java.awt.*;

/**
 * Created by anthonyrey on 02/06/2015.
 */
public class ExerciseView extends JPanel {

    private JLabel question;
    private JLabel symbol;
    private JTextField answer;
    private JButton validationButton;

    public ExerciseView(Exercise ex)
    {

        GridBagLayout layout = new GridBagLayout();
        this.setLayout(layout);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;

        question = new JLabel("Which symbol corresponds to :");
        symbol = new JLabel();
        symbol.setHorizontalAlignment(JLabel.CENTER);
        Font f = symbol.getFont().deriveFont(200.0f);
        symbol.setFont(f);
        answer = new JTextField();
        validationButton = new JButton("Ok");


        this.add(question, gbc);
        gbc.gridy = 1;
        this.add(symbol, gbc);
        gbc.gridy = 2;
        this.add(answer, gbc);
        gbc.gridy = 3;
        this.add(validationButton, gbc);

        ExerciseController controller = new ExerciseController(this, ex);
        validationButton.addActionListener(controller);

    }

    public JLabel getSymbol() {
        return symbol;
    }

    public void setSymbol(JLabel symbol) {
        this.symbol = symbol;
    }

    public JTextField getAnswer() {
        return answer;
    }

}
