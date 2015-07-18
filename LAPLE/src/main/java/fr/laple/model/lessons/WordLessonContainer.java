package fr.laple.model.lessons;

import fr.laple.model.datamodel.LapleDataModel;
import fr.laple.model.listable.IListable;
import fr.laple.model.listable.RootData;
import fr.laple.view.ListView;
import fr.laple.ztools.tabTools.TabTools;

import javax.swing.*;
import java.util.ArrayList;

/**
 * This class contains Word Lessons
 * @author zaafranigabriel
 * @author anthonyrey
 *
 */
public class WordLessonContainer extends AbstractLessonContainer implements IListable {

    private String lessonTypeName;
    private ArrayList<IListable> lessonCategories;

    /**
     * Constructor for the WordLessonContainer
     *
     * @param lessonTypeName The lessonType
     */
    public WordLessonContainer(String lessonTypeName)
    {
        this.lessonTypeName = lessonTypeName;
        this.lessonCategories = new ArrayList<>();

    }

    /**
     * Add a category to the list of categories
     *
     * @see fr.laple.model.lessons.LessonCategory
     *
     * @param name The name of the category
     * @param category The LessonCategory
     */
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
    public void expectedBehavior(JTabbedPane tabbedPane, LapleDataModel model, RootData rootData) {

        TabTools.swapTab(tabbedPane, new ListView(model, this.getLessonCategories(), true, rootData));

    }
}
