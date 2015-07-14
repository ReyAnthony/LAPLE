package fr.laple.controller;

import fr.laple.model.datamodel.LapleDataModel;
import fr.laple.model.listable.RootData;
import fr.laple.view.LapleGUI;
import fr.laple.view.ListView;
import fr.laple.view.staticpanels.AboutPanel;
import fr.laple.view.staticpanels.MainPanel;

import javax.swing.*;

/**
 * Created by anthonyrey on 06/06/2015.
 */
public class LapleGUIController {

    private LapleGUI view;
    private LapleDataModel model;

    public LapleGUIController(LapleDataModel model)
    {
        this.model = model;
        view = new LapleGUI();

        view.addWindowListener(new WindowController(view));
        JTabbedPane ui = view.getUIPane();

        ui.add("Home Page", new MainPanel());
        ui.add("Lessons",  new ListView(model, model.getLessonContainers(), false, "Select a Lesson category :",
                new RootData(model.getLessonContainers(), "Select a Lesson category :")));

        //get Exercise type take into account modes added by plugins
        ui.add("Exercises", new ListView(model, model.getExerciseTypes(), false, "Select an exercise mode :",
                new RootData(model.getExerciseTypes(), "Select an exercise mode :")));

        //add tabs from plugins
        model.addNewTabs(ui);

        ui.add("Statistics", null);
        ui.add("User settings", null);

        AboutPanel aboutPanel = new AboutPanel();
        ui.add("About LAPLE", aboutPanel);

        view.add(ui);
        view.setVisible(true);

    }

}
