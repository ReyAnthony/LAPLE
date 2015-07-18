package fr.laple.controller;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by anthonyrey on 02/06/2015.
 */
public class WindowController extends WindowAdapter {

    private JFrame frame;

    public WindowController(JFrame frame)
    {
        this.frame = frame;
    }

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
