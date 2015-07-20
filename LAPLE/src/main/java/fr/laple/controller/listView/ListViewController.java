package fr.laple.controller.listView;

import fr.laple.model.datamodel.LapleDataModel;
import fr.laple.model.listable.IListable;
import fr.laple.model.listable.RootData;
import fr.laple.view.ListView;
import fr.laple.ztools.tabTools.TabTools;

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
public class ListViewController implements ActionListener, ComponentListener {

    private List<IListable> displayModel;
    private ListView view;
    private LapleDataModel model;
    private RootData rootData;

    /**
     * This is the constructor to the ListViewController
     *
     * @param model A LapleModel
     * @param displayModel The model to display in the List
     * @param view The associated listView
     */
    public ListViewController(LapleDataModel model, List<IListable> displayModel, ListView view, RootData rootData) {
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
        list.addListSelectionListener(new ListViewSelectionListener());

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
        JTabbedPane tabbedPane = (JTabbedPane) view.getParent();
        TabTools.swapTab(tabbedPane, new ListView(model, rootData.getRootModel(), false, rootData) );
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
            //TODO fix, bug , because is lauhed even when not shown an get the selected tab which is not the right one
            /*
            JTabbedPane tabbedPane = (JTabbedPane) view.getParent();
            IListable listable = displayModel.get(0);
            listable.preAction();
            listable.expectedBehavior(tabbedPane, model, rootData);
            */
        }
    }

    @Override
    public void componentHidden(ComponentEvent e) {

    }
}

