package fr.laple.view.exercises;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by anthonyrey on 04/06/2015.
 */
public class QCMExerciseView extends AbstractExerciseView {


    private JLabel question;
    private JLabel symbol;
    private ArrayList<JButton> qcmButtons;

    public QCMExerciseView()
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

        JPanel qcm = new JPanel();
        qcmButtons = new ArrayList<>();
        for(int i = 0; i < 4; i++)
        {
           JButton b = new JButton("N/A");
            qcm.add(b);
            qcmButtons.add(b);

        }

        this.add(question, gbc);
        gbc.gridy = 0;
        gbc.gridx = 1;
        this.add(getRemainingCount(),gbc);
        gbc.gridy = 1;
        gbc.gridx = 0;
        this.add(symbol, gbc);
        gbc.gridx = 1;
        this.add(getSuccesCount(), gbc );
        gbc.gridx = 0;
        gbc.gridy = 2;
        this.add(qcm, gbc);
        gbc.gridy = 3;
        this.add(this.getNextButton(), gbc);
        gbc.gridx = 1;
        this.add(getBackButton(), gbc);

    }


    @Override
    public void addActionListener(ActionListener al) {

        for(JButton e : qcmButtons)
            e.addActionListener(al);
    }

    @Override
    public JLabel getSymbol() {
        return symbol;
    }

    @Override
    public void setSymbol(JLabel symbol) {
        this.symbol = symbol;
    }

    @Override
    public JTextField getAnswer() {
        //non applicable
        return null;
    }

    @Override
    public void resetTheView() {

        //TODO opacity bug
        for(JButton b : qcmButtons)
        {
            b.setEnabled(true);
            b.setOpaque(false);
        }


        this.getNextButton().setEnabled(false);
    }

    public ArrayList<JButton> getQcmButtons() {
        return qcmButtons;
    }

}
