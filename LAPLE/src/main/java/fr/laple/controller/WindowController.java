package fr.laple.controller;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Manage the code when exiting (DO NOT WORK ON OSX)
 *
 * @see fr.laple.controller.LapleGUIController for OSX
 *
 * @author anthonyrey
 */
public class WindowController extends WindowAdapter {

    private JFrame frame;

    /**
     * Constructor to the class
     *
     * @param frame The frame you want to check for closing operations
     */
    public WindowController(JFrame frame)
    {
        this.frame = frame;
    }

    /**
     * prompt response ? quit : noquit
     *
     * @param e A WindowEvent
     */
    @Override
    public void windowClosing(WindowEvent e) {

        int response = JOptionPane.showConfirmDialog(frame,
                "Do you really want to quit ? ", "LAPLE",
                JOptionPane.YES_NO_OPTION);
        if (response == JOptionPane.OK_OPTION){
            frame.dispose();
            System.exit(0);
        }

    }

}
