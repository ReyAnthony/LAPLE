package fr.laple.controller.lessons;

import fr.laple.model.language.ILanguagePlugin;
import fr.laple.model.lessons.ILessonContainer;
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

    public LessonPickerController(ILanguagePlugin model)
    {
        this.model = model;
        setComboBox();

    }

    private void setComboBox()
    {
        JComboBox<ILessonContainer> lessons = view.getLessonsComboBox();
        ComboBoxModel<ILessonContainer> comboBoxModel = new DefaultComboBoxModel<>(model.getLessonContainers().toArray(new ILessonContainer[]{}));

        lessons.setModel(comboBoxModel);

    }


    @Override
    public void actionPerformed(ActionEvent e) {


    }
}
