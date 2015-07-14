package fr.laple.model.lessons;

import fr.laple.model.datamodel.LapleDataModel;
import fr.laple.model.listable.IListable;
import fr.laple.model.listable.RootData;
import fr.laple.view.ListView;
import fr.laple.ztools.tabTools.TabTools;

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
    public void expectedBehavior(JTabbedPane tabbedPane, LapleDataModel model, RootData rootData)  {

        TabTools.swapTab(tabbedPane, new ListView(model, this.getLessons(), true, "Select a Lesson :",
                rootData) );
    }
}
