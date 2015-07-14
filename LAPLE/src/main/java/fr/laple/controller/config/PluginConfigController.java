package fr.laple.controller.config;

import fr.laple.model.datamodel.LapleDataModel;
import fr.laple.model.listable.IListable;
import fr.laple.model.listable.RootData;
import fr.laple.view.ListView;
import fr.laple.view.config.PluginConfigView;
import fr.laple.ztools.tabTools.TabTools;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * Created by anthonyrey on 14/07/2015.
 */
public class PluginConfigController implements ActionListener, ItemListener, ListSelectionListener, IListable {

    private PluginConfigView view;
    private RootData rootData;
    private JTabbedPane tabbedPane;
    private LapleDataModel model;

    public PluginConfigController()
    {
        this.view = new PluginConfigView();
        view.getAdd().addActionListener(this);
        view.getBack().addActionListener(this);
        view.getRemove().addActionListener(this);
        view.getPluginTypes().addItemListener(this);
        view.getPlugins().addListSelectionListener(this);


        view.getPluginTypes().addItem("Language plugin");
        view.getPluginTypes().addItem("Feature plugin");

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource().equals(view.getBack()))
        {
            TabTools.swapTab(tabbedPane, new ListView(model, rootData.getRootModel(), false, "Select a setting page :",
                    rootData));
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {

        if(e.getItem().equals("LanguagePlugin"))
        {

        }
        else
        {

        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {

    }

    public PluginConfigView getView() {
        return view;
    }

    @Override
    public void expectedBehavior(JTabbedPane tabbedPane, LapleDataModel model, RootData rootData) {

        this.rootData = rootData;
        this.tabbedPane = tabbedPane;
        this.model = model;

        TabTools.swapTab(tabbedPane, this.getView());
    }

    @Override
    public String toString()
    {
        return "Plugin settings";
    }

}
