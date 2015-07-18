package fr.laple.controller;

import fr.laple.controller.config.PluginConfigController;
import fr.laple.model.datamodel.LapleDataModel;
import fr.laple.model.listable.IListable;
import fr.laple.model.listable.RootData;
import fr.laple.view.LapleGUI;
import fr.laple.view.ListView;
import fr.laple.view.staticpanels.AboutPanel;
import fr.laple.view.staticpanels.MainPanel;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

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
        ui.add("Lessons",  new ListView(model, model.getLessonContainers(), false,
                new RootData(model.getLessonContainers(), "Select a Lesson category :")));

        //get Exercise type take into account modes added by plugins",
                ui.add("Exercises", new ListView(model, model.getExerciseTypes(), false,
                new RootData(model.getExerciseTypes(), "Select an exercise mode :")));

        //add tabs from plugins
        model.addNewTabs(ui);


        ui.add("Statistics", null);

        List<IListable> settings = new ArrayList<>();
        PluginConfigController pcc = new PluginConfigController();
        settings.add(pcc);

        ui.add("User settings", new ListView(model, settings, false,
                new RootData(settings, "Select a settings page :")));

        AboutPanel aboutPanel = new AboutPanel();
        ui.add("About LAPLE", aboutPanel);

        view.add(ui);
        view.setVisible(true);

    }

}
