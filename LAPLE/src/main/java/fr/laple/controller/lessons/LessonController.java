package fr.laple.controller.lessons;

import fr.laple.extensions.languages.plugins.ILanguagePlugin;
import fr.laple.model.lessons.Lesson;
import fr.laple.model.listable.RootData;
import fr.laple.view.lessons.LessonView;
import fr.laple.view.ListView;

import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by anthonyrey on 10/06/2015.
 */
public class LessonController implements ActionListener {

    private Lesson lesson;
    private LessonView view;
    private ILanguagePlugin model;

    public LessonController(ILanguagePlugin model, LessonView view, Lesson lesson)
    {

        this.view = view;
        this.lesson = lesson;
        this.model = model;

        view.getSymbol().setText(this.lesson.getSymbol().getSymbol());
        view.getParent().setEnabled(false);

        view.getBackButton().addActionListener(this);
        view.getSoundButton().addActionListener(this);
        view.getDescription().setText("translation : \n "+ this.lesson.getSymbol().getUserLangTranscript());
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource().equals(view.getBackButton()))
        {

            JTabbedPane tabbedPane = (JTabbedPane) view.getParent();
            tabbedPane.setEnabled(true);

            int selected = tabbedPane.getSelectedIndex();
            tabbedPane.remove(selected);
            tabbedPane.insertTab("Lessons", null, new ListView(model, model.getLessonContainers(),
                            false, "Select a Lesson category :", new RootData(model.getLessonContainers(),
                            "Select a Lesson category :" )),
                    null, selected);
            tabbedPane.setSelectedIndex(selected);

        }
        else if(e.getSource().equals(view.getSoundButton()))
        {
            Clip clip =  lesson.getSymbol().getPronunciation();
            clip.start();

        }
    }
}
