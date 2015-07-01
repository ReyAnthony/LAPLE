package fr.laple.view.lessons;

import javax.swing.*;
import java.awt.*;

/**
 * Created by anthonyrey on 09/06/2015.
 */
public class LessonView extends JPanel {

    private JLabel symbol;
    private JButton soundButton;

    public LessonView()
    {

        GridBagLayout layout = new GridBagLayout();
        this.setLayout(layout);
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridy = 0;
        gbc.gridx = 0;

        JLabel text = new JLabel("Learn more about this symbol :");
        add(text, gbc);

        gbc.gridy = 1;

        symbol = new JLabel();
        symbol.setHorizontalAlignment(JLabel.CENTER);
        Font f = symbol.getFont().deriveFont(200.0f);
        symbol.setFont(f);

        soundButton = new JButton(" ");

        this.add(symbol, gbc);

        gbc.gridy = 2;

        this.add(soundButton, gbc);

        gbc.gridy = 1;
        gbc.gridx = 1;

        //For Kanji for eg.
        JPanel descriptionPanel = new JPanel();
        JLabel description = new JLabel("Test, long");
        descriptionPanel.add(description);

        this.add(descriptionPanel, gbc);


    }


    public JLabel getSymbol() {
        return symbol;
    }
}
