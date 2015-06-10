package fr.laple.controller.lessons;

import fr.laple.model.lessons.Lesson;
import fr.laple.view.lessons.LessonView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by anthonyrey on 10/06/2015.
 */
public class LessonController implements ActionListener {

    private Lesson model;
    private LessonView lessonView;

    public LessonController(LessonView lessonView, Lesson model)
    {

        this.lessonView = lessonView;
        this.model = model;

        lessonView.getSymbol().setText(model.getSymbol().getSymbol());

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
