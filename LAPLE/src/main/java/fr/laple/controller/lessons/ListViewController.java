package fr.laple.controller.lessons;

import fr.laple.model.listable.IListable;
import fr.laple.model.language.ILanguagePlugin;
import fr.laple.model.listable.RootData;
import fr.laple.view.ListView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.List;

/**
 * This is a controller class for generic instances of listView
 *
 * @author anthonyrey
 */
//TODO add validation when pressing enter key
public class ListViewController implements ActionListener, ComponentListener {

    private List<IListable> displayModel;
    private ListView view;
    private ILanguagePlugin model;
    private RootData rootData;

    /**
     * This is the constructor to the ListViewController
     *
     * @param model A LapleModel
     * @param displayModel The model to display in the List
     * @param view The associated listView
     */
    public ListViewController(ILanguagePlugin model, List<IListable> displayModel, ListView view, RootData rootData) {
        this.displayModel = displayModel;
        this.view = view;
        this.model = model;
        this.rootData = rootData;
        setList();
        this.view.addComponentListener(this);
    }

    /**
     * This method creates the model according to the display model and put it
     * into the views's list
     *
     */
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

    /**
     * When clicking on a button
     * @param e The ActionEvent related object
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        IListable selectedValue = view.getList().getSelectedValue();

        if (e.getSource().equals(view.getValidationButton())) {

            JTabbedPane tabbedPane = (JTabbedPane) view.getParent();
            if (selectedValue == null)
                JOptionPane.showMessageDialog(view, "You must select a value !");
             else
                selectedValue.expectedBehavior(tabbedPane, model, rootData);
        } else
            goRootMenu();

    }


    /**
     * Delete the current tab and create a new tab set at the root view
     */
    private void goRootMenu()
    {
        //TODO fix
        //Go back to the main frame
        JTabbedPane tabbedPane = (JTabbedPane) view.getParent();

        int selected = tabbedPane.getSelectedIndex();
        String name = tabbedPane.getTitleAt(selected);

        tabbedPane.remove(selected);
        tabbedPane.insertTab(name, null, new ListView(model, rootData.getRootModel(), false,
                rootData.getRootTitle(), rootData), null, selected);
        tabbedPane.setSelectedIndex(selected);
    }

    @Override
    public void componentResized(ComponentEvent e) {

    }

    @Override
    public void componentMoved(ComponentEvent e) {

    }

    @Override
    public void componentShown(ComponentEvent e) {

        if(displayModel.size() == 1)
        {

            JTabbedPane tabbedPane = (JTabbedPane) view.getParent();
            IListable listable = displayModel.get(0);
            listable.oneInListPreAction();
            listable.expectedBehavior(tabbedPane, model, rootData);
        }
    }

    @Override
    public void componentHidden(ComponentEvent e) {

    }
}

