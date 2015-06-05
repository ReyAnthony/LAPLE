package fr.laple.controller.exercises;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by anthonyrey on 05/06/2015.
 */
public class Blinker extends Timer implements ActionListener {

    private JPanel panel;
    private Color col;
    private Color baseCol;

    public Blinker(JPanel panel, boolean won) {

        super(1000, null);
        this.addActionListener(this);
        this.panel = panel;
        this.setRepeats(true);
        baseCol = new Color(this.panel.getBackground().getRGB());

        if(won)
            col =Color.green;
        else
            col = Color.red;

        panel.setBackground(col);

        this.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        Color c = new Color(panel.getBackground().getRGB());
        if(!c.equals(col))
            panel.setBackground(col);
        else
            panel.setBackground(baseCol);
    }

    public void stop()
    {
        super.stop();
        this.panel.setBackground(baseCol);

    }
}
