package fr.laple.model.lessons;

import fr.laple.controller.lessons.IListable;
import fr.laple.model.language.ILanguagePlugin;
import fr.laple.view.ListView;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Created by anthonyrey on 06/06/2015.
 */
public class WordLessonContainer extends AbstractLessonContainer implements IListable {

    private String lessonTypeName;
    private ArrayList<LessonCategory> lessonCategories;

    public WordLessonContainer(String lessonTypeName)
    {
        this.lessonTypeName = lessonTypeName;
        this.lessonCategories = new ArrayList<>();

    }

    public void addCategory(String name, LessonCategory category)
    {
        lessonCategories.add(category);
    }

    public String getLessonTypeName() {
        return lessonTypeName;
    }

    public ArrayList<LessonCategory> getLessonCategories()
   {
       return lessonCategories;
   }

    public String toString()
    {
        return lessonTypeName;
    }

    @Override
    public void expectedBehavior(Object selectedValue, JTabbedPane tabbedPane, ILanguagePlugin model) {

        WordLessonContainer wordLessonContainer = (WordLessonContainer) selectedValue;
        int selected = tabbedPane.getSelectedIndex();
        tabbedPane.remove(selected);
        //view = new ListView<>(wordLessonContainer.getLessonCategories(), true);
        tabbedPane.insertTab("Lessons", null, new ListView<>(model, wordLessonContainer.getLessonCategories(), true), null, selected);
        tabbedPane.setSelectedIndex(selected);

    }
}
