package fr.laple.view.exercises;

import javax.swing.*;
import java.awt.*;

/**
 * Created by anthonyrey on 02/06/2015.
 */
public class GenericExerciseModeSelector extends JPanel{

    private JLabel message;
    private JList<String> list;
    private JButton okButton;

    public GenericExerciseModeSelector()
    {
        BorderLayout layout = new BorderLayout();
        this.setLayout(layout);

        this.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        message = new JLabel("Select an exercise mode :");
        message.setHorizontalAlignment(JLabel.CENTER);
        message.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        list = new JList<>();

        JPanel buttonPanel = new JPanel();
        okButton = new JButton("OK");
        buttonPanel.add(okButton);

        DefaultListModel<String> model = new DefaultListModel<>();
        model.add(0, "test");
        model.add(1, "test2");

        list.setModel(model);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        this.add(message, BorderLayout.PAGE_START);
        this.add(list, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.PAGE_END);

    }


}
