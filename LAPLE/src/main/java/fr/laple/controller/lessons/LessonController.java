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
    private LessonView view;

    public LessonController(LessonView view, Lesson model)
    {

        this.view = view;
        this.model = model;

        view.getSymbol().setText(model.getSymbol().getSymbol());
        view.getParent().setEnabled(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
