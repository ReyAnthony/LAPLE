package fr.laple.controller.lessons;

import fr.laple.model.datamodel.LapleDataModel;
import fr.laple.model.lessons.Lesson;
import fr.laple.model.listable.RootData;
import fr.laple.view.ListView;
import fr.laple.view.lessons.LessonView;
import fr.laple.ztools.tabTools.TabTools;

import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The LessonController to the LessonView
 *
 * @see fr.laple.view.lessons.LessonView
 *
 * @author anthonyrey
 */
public class LessonController implements ActionListener {

    private Lesson lesson;
    private LessonView view;
    private LapleDataModel model;
    private RootData rootData;

    /**
     * The constructor to this class
     *
     * @see fr.laple.model.datamodel.LapleDataModel
     * @see fr.laple.view.lessons.LessonView
     * @see fr.laple.model.lessons.Lesson
     * @see fr.laple.model.listable.RootData
     *
     * @param model A LapleDataModel Object
     * @param view A LessonView Object
     * @param lesson A Lesson Object
     * @param rootData A RootData Object
     */
    public LessonController(LapleDataModel model, LessonView view, Lesson lesson, RootData rootData)
    {

        this.view = view;
        this.lesson = lesson;
        this.model = model;
        this.rootData = rootData;

        view.getSymbol().setText(this.lesson.getSymbol().getSymbol());
        view.getParent().setEnabled(false);

        view.getBackButton().addActionListener(this);
        view.getSoundButton().addActionListener(this);
        view.getDescription().setText("translation : \n "+ this.lesson.getSymbol().getUserLangTranscript());
    }

    /**
     * Actions performed when clicking on soundButton or BackButton
     *
     * @param e An ActionEvent Object
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource().equals(view.getBackButton()))
        {

            JTabbedPane tabbedPane = (JTabbedPane) view.getParent();
            TabTools.swapTab(tabbedPane,  new ListView(model, model.getLessonContainers(),
                    false, rootData));

            tabbedPane.setEnabled(true);

        }
        else if(e.getSource().equals(view.getSoundButton()))
        {
            Clip clip =  lesson.getSymbol().getPronunciation();
            clip.start();

        }
    }
}
