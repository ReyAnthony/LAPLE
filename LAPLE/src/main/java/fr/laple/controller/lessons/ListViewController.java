package fr.laple.controller.lessons;

import fr.laple.model.language.ILanguagePlugin;
import fr.laple.view.ListView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Created by anthonyrey on 06/06/2015.
 */
public class ListViewController implements ActionListener {

    private List<IListable> displayModel;
    private ListView view;
    private ILanguagePlugin model;

    public ListViewController(ILanguagePlugin model, List displayModel, ListView view) {
        this.displayModel = displayModel;
        this.view = view;
        this.model = model;
        setList();

    }

    private void setList() {

        JList<IListable> list = view.getList();
        DefaultListModel<IListable> listModel = new DefaultListModel<>();
        list.addListSelectionListener(new MyListSelectionListener());

        for (int i = 0; i < displayModel.size(); i++) {
            listModel.add(i, displayModel.get(i));
        }

        list.setModel(listModel);
        list.setCellRenderer(new ListViewCellRenderer());
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        IListable selectedValue = (IListable) view.getList().getSelectedValue();

        if (e.getSource().equals(view.getValidationButton())) {

            JTabbedPane tabbedPane = (JTabbedPane) view.getParent();
            if (selectedValue == null)
                JOptionPane.showMessageDialog(view, "You must select a value !");
             else
                selectedValue.expectedBehavior(selectedValue, tabbedPane, model);
        } else
            goRootMenu();

    }

    private void goRootMenu()
    {
        //Go back to the main frame
        JTabbedPane tabbedPane = (JTabbedPane) view.getParent();

        int selected = tabbedPane.getSelectedIndex();
        tabbedPane.remove(selected);
        tabbedPane.insertTab("Lessons", null, new ListView<>(model, model.getLessonContainers(), false),
                null, selected);
        tabbedPane.setSelectedIndex(selected);
    }
}

