package fr.laple.controller;

import fr.laple.model.language.ILanguagePlugin;
import fr.laple.view.LapleGUI;
import fr.laple.view.exercises.ExerciseParameterizer;
import fr.laple.view.lessons.ListView;
import fr.laple.view.staticpanels.AboutPanel;
import fr.laple.view.staticpanels.MainPanel;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Created by anthonyrey on 06/06/2015.
 */
public class GUIController implements ChangeListener {

    LapleGUI view;
    ILanguagePlugin model;

    public GUIController(ILanguagePlugin model)
    {
        this.model = model;
        view = new LapleGUI();
        
        view.addWindowListener(new WindowController(view));

        view.getUIPane().add("Home Page", new MainPanel());

        ListView listView = new ListView();
        view.getUIPane().add("Lessons", listView);
        view.getUIPane().add("Exercises", new ExerciseParameterizer(model));
        view.getUIPane().add("Statistics", null);
        view.getUIPane().add("User settings", null);

        AboutPanel aboutPanel = new AboutPanel();
        view.getUIPane().add("About LAPLE", aboutPanel);
        view.getUIPane().addChangeListener(this);

        view.add(view.getUIPane());
        view.setVisible(true);

    }

    @Override
    public void stateChanged(ChangeEvent e) {


    }
}
