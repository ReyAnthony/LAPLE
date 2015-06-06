package fr.laple.view.lessons;

import fr.laple.controller.lessons.ListViewController;
import fr.laple.model.language.ILanguagePlugin;

import javax.swing.*;
import java.awt.*;

/**
 * Created by anthonyrey on 06/06/2015.
 */
public class ListView extends JPanel {

    private JList list;
    private JButton validationButton;

    public ListView(ILanguagePlugin model)
    {
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        JLabel message = new JLabel("Please select a lesson mode");
        list = new JList<>();
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        validationButton = new JButton("Ok");

        this.add(message, BorderLayout.PAGE_START);
        this.add(list, BorderLayout.CENTER);
        this.add(validationButton, BorderLayout.PAGE_END);

        validationButton.addActionListener(new ListViewController(model, this));
    }

    public JList getList()
    {
        return list;
    }

    public JButton getValidationButton() {
        return validationButton;
    }

}
