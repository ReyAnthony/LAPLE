package fr.laple.view.lessons;

import fr.laple.controller.lessons.ListViewController;

import javax.swing.*;
import java.awt.*;

/**
 * Created by anthonyrey on 06/06/2015.
 */
public class ListView<T> extends JPanel {

    private JList<T> list;
    private JButton validationButton;
    private JButton backButton;

    public ListView(java.util.List<T> model, boolean hasBackButton)
    {

        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        JLabel message = new JLabel("Please select a lesson mode");

        JPanel scrollPanel = new JPanel();
        scrollPanel.setLayout(new BorderLayout());
        JScrollPane scroll = new JScrollPane(scrollPanel);
        scroll.setHorizontalScrollBar(null);
        list = new JList<>();
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scrollPanel.add(list, BorderLayout.CENTER);

        JPanel buttons = new JPanel();
        validationButton = new JButton("Ok");
        backButton = new JButton("Return to root menu");
        buttons.add(validationButton);
        buttons.add(backButton);

        this.add(message, BorderLayout.PAGE_START);
        this.add(scroll, BorderLayout.CENTER);
        this.add(buttons, BorderLayout.PAGE_END);

        //todo give the .class to the controller so we can generalize
        ListViewController controller = new ListViewController(model, this);
        validationButton.addActionListener(controller);
        backButton.addActionListener(controller);

        if(!hasBackButton)
            backButton.setVisible(false);

    }

    public JList<T> getList()
    {
        return list;
    }

    public JButton getBackButton() {
        return backButton;
    }

    public JButton getValidationButton() {
        return validationButton;
    }
}
