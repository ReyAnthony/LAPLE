package fr.laple.controller.exercises;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A blinker is a standalone object which takes a component and makes it blink at a fixed rate
 *
 * @author anthonyrey
 */
public class Blinker extends Timer implements ActionListener {

    private Component comp;
    private Color col;
    private Color baseCol;

    /**
     * Constructor for the blinker class
     *
     * If won == true, it blinks green
     * else it blinks red
     *
     * @param c The Component Object to blink
     * @param won boolean
     */
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

    /**
     * The action to performs (the blinking process)
     *
     * @param e An ActionEvent Object
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        Color c = new Color(this.comp.getBackground().getRGB());
        if(c.equals(col))
            this.comp.setBackground(baseCol);
        else
            this.comp.setBackground(col);

    }

    /**
     * Stop the blinking and revert to default background color
     */
    public void stop()
    {
        super.stop();
        this.comp.setBackground(baseCol);

    }
}