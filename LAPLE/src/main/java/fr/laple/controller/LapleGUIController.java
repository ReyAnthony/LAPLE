package fr.laple.controller;

import fr.laple.model.listable.IListable;
import fr.laple.model.language.ILanguagePlugin;
import fr.laple.model.listable.RootData;
import fr.laple.view.LapleGUI;
import fr.laple.view.ListView;
import fr.laple.view.exercises.ExerciseParameterizer;
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
    private ILanguagePlugin model;

    public LapleGUIController(ILanguagePlugin model)
    {
        this.model = model;
        view = new LapleGUI();

        view.addWindowListener(new WindowController(view));
        JTabbedPane ui = view.getUIPane();

        ui.add("Home Page", new MainPanel());
        ui.add("Lessons",  new ListView(model, model.getLessonContainers(), false, "Select a Lesson category :",
                new RootData(model.getLessonContainers(), "Select a Lesson category :")));

        List<IListable> exoList = new ArrayList<>();
        exoList.add(new ExerciseParameterizer(model));
        //exoList.add(new ExerciseParameterizer(model));
        ui.add("Exercises", new ListView(model, exoList, false, "Select an exercise mode :", new RootData(exoList,
                "Select an exercise mode :")));

        //TODO add a list of tabs, we will add new tabs with plugins
        ui.add("Statistics", null);
        ui.add("User settings", null);

        AboutPanel aboutPanel = new AboutPanel();
        ui.add("About LAPLE", aboutPanel);

        view.add(ui);
        view.setVisible(true);

    }

}
