package fr.laple.view.lessons;

import javax.swing.*;
import java.awt.*;

/**
 * View for lessons
 *
 * @author anthonyrey
 */
public class LessonView extends JPanel {

    private JLabel symbol;
    private JButton soundButton;
    private JButton backButton;
    private JTextArea description;

    public LessonView()
    {

        BorderLayout layout = new BorderLayout();
        this.setLayout(layout);
        this.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        JLabel text = new JLabel("Learn more about / try drawing this symbol :");
        text.setHorizontalAlignment(JLabel.CENTER);

        //no need to resize as we never have words here
        symbol = new JLabel();
        symbol.setHorizontalAlignment(JLabel.CENTER);
        Font f = symbol.getFont().deriveFont(150.0f);
        symbol.setFont(f);

        JPanel symbolPanel = new JPanel();
        symbolPanel.setLayout(new BorderLayout());
        symbolPanel.add(symbol, BorderLayout.CENTER);

        JPanel commandPanel = new JPanel();

        soundButton = new JButton(" Hear it ");
        commandPanel.add(soundButton);
        symbolPanel.add(commandPanel, BorderLayout.PAGE_END);


        JPanel descriptionPanel = new JPanel();
        descriptionPanel.setLayout(new BorderLayout());
        description = new JTextArea("Here we should put the definition of the symbol");
        description.setLineWrap(true);
        description.setEditable(false);
        description.setWrapStyleWord(true);

        JScrollPane scroll = new JScrollPane(descriptionPanel);
        scroll.setHorizontalScrollBar(null);

        backButton = new JButton("Back");
        commandPanel.add(backButton);
        descriptionPanel.add(description, BorderLayout.CENTER);


        JPanel centralPanel = new JPanel();
        centralPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        GridLayout gLayout = new GridLayout(2,2);
        layout.setVgap(10);
        centralPanel.setLayout(gLayout);
        centralPanel.add(symbolPanel);
        centralPanel.add(scroll);

        this.add(centralPanel, BorderLayout.CENTER);

        this.add(text, BorderLayout.PAGE_START);
    }


    public JLabel getSymbol() {
        return symbol;
    }

    public JTextArea getDescription() {
        return description;
    }

    public JButton getSoundButton() {
        return soundButton;
    }

    public JButton getBackButton() {
        return backButton;
    }

}
