package fr.laple.controller.lessons;

import fr.laple.model.language.ILanguagePlugin;
import fr.laple.model.lessons.AbstractLessonContainer;
import fr.laple.view.lessons.LessonPickerView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by anthonyrey on 06/06/2015.
 */
public class LessonPickerController implements ActionListener{

    ILanguagePlugin model;
    LessonPickerView view;

    public LessonPickerController(ILanguagePlugin model, LessonPickerView view)
    {
        this.model = model;
        this.view = view;
        setList();

    }

    private void setList()
    {
        JList<AbstractLessonContainer> lessons = view.getLessonsList();
        DefaultListModel<AbstractLessonContainer> listModel = new DefaultListModel<>();

        for(int i = 0; i < model.getLessonContainers().size(); i++)
        {
            listModel.add(i, model.getLessonContainers().get(i));
        }

        lessons.setModel(listModel);



    }


    @Override
    public void actionPerformed(ActionEvent e) {


    }
}
