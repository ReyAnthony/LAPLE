package fr.laple.view.lessons;

import fr.laple.controller.lessons.LessonPickerController;
import fr.laple.model.language.ILanguagePlugin;
import fr.laple.model.lessons.AbstractLessonContainer;

import javax.swing.*;

/**
 * Created by anthonyrey on 06/06/2015.
 */
public class LessonPickerView extends JPanel {

    private JComboBox<AbstractLessonContainer> lessons;
    private JButton validationButton;

    public LessonPickerView(ILanguagePlugin plugin)
    {
        JLabel message = new JLabel("Please select a lesson mode");
        lessons = new JComboBox<>();
        validationButton = new JButton("Ok");

        this.add(message);
        this.add(lessons);
        this.add(validationButton);
        validationButton.addActionListener(new LessonPickerController(plugin, this));

    }

    public JComboBox<AbstractLessonContainer> getLessonsComboBox()
    {
        return lessons;
    }

}
