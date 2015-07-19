package fr.laple.model.lessons;

import fr.laple.model.datamodel.LapleDataModel;
import fr.laple.model.listable.IListable;
import fr.laple.model.listable.ListableConverter;
import fr.laple.model.listable.RootData;
import fr.laple.view.ListView;
import fr.laple.ztools.tabTools.TabTools;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a lesson category
 *
 * @author zaafranigabriel
 * @author anthonyrey
 */
public class LessonCategory implements IListable {

    private String categoryName;
    private ArrayList<Lesson> lessons;

    /**
     * Constructor for the class
     *
     * @param name The name of the category
     * @param lessons an ArrayList of lessons
     */
    public LessonCategory(String name, ArrayList<Lesson> lessons)
    {
        this.categoryName = name;
        this.lessons = lessons;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public ArrayList<Lesson> getLessons() {
        return lessons;
    }

    public String toString()
    {
        return getCategoryName();
    }

    @Override
    public void expectedBehavior(JTabbedPane tabbedPane, LapleDataModel model, RootData rootData)  {

        ListableConverter<Lesson> converter = new ListableConverter<>();
        List<IListable> listables = converter.typeToIListable(this.getLessons());

        TabTools.swapTab(tabbedPane, new ListView(model, listables, true,
                rootData) );
    }
}
