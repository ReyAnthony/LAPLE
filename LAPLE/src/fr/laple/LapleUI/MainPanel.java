package fr.laple.LapleUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by anthonyrey on 11/04/2015.
 */
public class MainPanel extends JPanel {

    private JTextArea manual;

    public MainPanel(){

        manual = new JTextArea();
        manual.setEnabled(false);

        createUI();

    }

    private void createUI()
    {
        manual.setText("LAPLE manual");
        this.add(manual);

    }

}
