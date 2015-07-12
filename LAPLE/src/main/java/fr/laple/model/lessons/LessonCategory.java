package fr.laple.model.lessons;

import fr.laple.model.IListable;
import fr.laple.model.language.ILanguagePlugin;
import fr.laple.view.ListView;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Created by anthonyrey on 06/06/2015.
 */
public class LessonCategory implements IListable {

    private String categoryName;
    private ArrayList<IListable> lessons;

    public LessonCategory(String name, ArrayList<IListable> lessons)
    {
        this.categoryName = name;
        this.lessons = lessons;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public ArrayList<IListable> getLessons() {
        return lessons;
    }

    public String toString()
    {
        return getCategoryName();
    }

    @Override
    public void expectedBehavior(Object selectedValue, JTabbedPane tabbedPane, ILanguagePlugin model) {

        LessonCategory symbolLessonContainer = (LessonCategory) selectedValue;
        int selected = tabbedPane.getSelectedIndex();
        tabbedPane.remove(selected);
        //view = new ListView<>(symbolLessonContainer.getLessons(), true);
        tabbedPane.insertTab("Lessons", null, new ListView(model, symbolLessonContainer.getLessons(), true),
                null, selected);
        tabbedPane.setSelectedIndex(selected);
    }
}
