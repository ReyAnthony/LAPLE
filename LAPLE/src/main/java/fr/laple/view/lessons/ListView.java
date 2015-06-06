package fr.laple.view.lessons;

import javax.swing.*;
import java.awt.*;

/**
 * Created by anthonyrey on 06/06/2015.
 */
public class ListView extends JPanel {

    private JList list;
    private JButton validationButton;

    public ListView()
    {
        this.setLayout(new BorderLayout());
        JLabel message = new JLabel("Please select a lesson mode");
        list = new JList<>();
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        validationButton = new JButton("Ok");

        this.add(message, BorderLayout.PAGE_START);
        this.add(list, BorderLayout.CENTER);
        this.add(validationButton, BorderLayout.PAGE_END);
    }

    public JList getLessonsList()
    {
        return list;
    }

    public JButton getValidationButton() {
        return validationButton;
    }

}
