package fr.laple.model.lessons;

import fr.laple.extensions.languages.plugins.ILanguagePlugin;
import fr.laple.model.listable.IListable;
import fr.laple.model.listable.RootData;
import fr.laple.view.ListView;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Created by anthonyrey on 06/06/2015.
 */
public class WordLessonContainer extends AbstractLessonContainer implements IListable {

    private String lessonTypeName;
    private ArrayList<IListable> lessonCategories;

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

    public ArrayList<IListable> getLessonCategories()
   {
       return lessonCategories;
   }

    public String toString()
    {
        return lessonTypeName;
    }

    @Override
    public void expectedBehavior(JTabbedPane tabbedPane, ILanguagePlugin model, RootData rootData) {

        int selected = tabbedPane.getSelectedIndex();
        tabbedPane.remove(selected);
        //view = new ListView<>(wordLessonContainer.getLessonCategories(), true);
        tabbedPane.insertTab("Lessons", null, new ListView(model, this.getLessonCategories(), true,
                "Select a Lesson category :", rootData), null, selected);
        tabbedPane.setSelectedIndex(selected);

    }
}
