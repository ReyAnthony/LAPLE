package fr.laple.controller;

import fr.laple.model.language.ILanguagePlugin;
import fr.laple.view.LapleGUI;
import fr.laple.view.exercises.ExerciseParameterizer;
import fr.laple.view.lessons.ListView;
import fr.laple.view.staticpanels.AboutPanel;
import fr.laple.view.staticpanels.MainPanel;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Created by anthonyrey on 06/06/2015.
 */
public class LapleGUIController implements ChangeListener {

    LapleGUI view;
    ILanguagePlugin model;

    public LapleGUIController(ILanguagePlugin model)
    {
        this.model = model;
        view = new LapleGUI();

        view.addWindowListener(new WindowController(view));
        JTabbedPane ui = view.getUIPane();

        view.getUIPane().add("Home Page", new MainPanel());

        ui.add("Lessons",  new ListView(model) );


        view.getUIPane().add("Exercises", new ExerciseParameterizer(model));
        view.getUIPane().add("Statistics", null);
        view.getUIPane().add("User settings", null);

        AboutPanel aboutPanel = new AboutPanel();
        view.getUIPane().add("About LAPLE", aboutPanel);
        view.getUIPane().addChangeListener(this);

        view.add(ui);
        view.setVisible(true);

    }

    @Override
    public void stateChanged(ChangeEvent e) {


    }
}
