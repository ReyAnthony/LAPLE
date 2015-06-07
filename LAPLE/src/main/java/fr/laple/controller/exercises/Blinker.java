package fr.laple.controller.exercises;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by anthonyrey on 05/06/2015.
 */
public class Blinker extends Timer implements ActionListener {

    private Component comp;
    private Color col;
    private Color baseCol;

    public Blinker(Component c, boolean won) {

        super(1000, null);
        this.addActionListener(this);
        this.comp = c;
        this.setRepeats(true);
        baseCol = new Color(this.comp.getBackground().getRGB());

        if(won)
            col =Color.green;
        else
            col = Color.red;

        c.setBackground(col);

        this.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        Color c = new Color(this.comp.getBackground().getRGB());
        if(c.equals(col))
            this.comp.setBackground(baseCol);
        else
            this.comp.setBackground(col);

    }

    public void stop()
    {
        super.stop();
        this.comp.setBackground(baseCol);

    }
}
