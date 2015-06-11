package fr.laple.view.exercises;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Created by anthonyrey on 02/06/2015.
 */
public class FreeInputExerciseView extends AbstractExerciseView {

    private JLabel question;
    private JLabel symbol;
    private JTextField answer;
    private JButton validationButton;

    public FreeInputExerciseView()
    {
        super();
        GridBagLayout layout = new GridBagLayout();
        this.setLayout(layout);
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(10,10,10,10);
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
        gbc.gridx = 4;
        this.add(getCounterLabel());
        gbc.gridx = 0;
        gbc.gridy = 2;
        this.add(answer, gbc);
        gbc.gridy = 3;
        this.add(validationButton, gbc);
        gbc.gridy = 4;
        this.add(this.getNextButton(), gbc);


    }

    public void addActionListener(ActionListener al)
    {
        validationButton.addActionListener(al);
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

    @Override
    public void resetTheView() {
        answer.setEnabled(true);
        this.getNextButton().setVisible(false);
        this.getValidationButton().setEnabled(true);
        this.getAnswer().setText("");
    }

    public JButton getValidationButton() {
        return validationButton;
    }
}
