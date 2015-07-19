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
 * This class contains Symbol Lessons
 * @author zaafranigabriel
 * @author anthonyrey
 *
 */
public class SymbolLessonContainer extends AbstractLessonContainer {

    private String lessonTypeName;
    private ArrayList<Lesson> lessons;

    /**
     * It's the constructor of Symbol lesson container
     * @param lessonTypeName String used to set the type  of the dictionary.
     */
    public SymbolLessonContainer(String lessonTypeName, ArrayList<Lesson> lessons){
        this.lessonTypeName = lessonTypeName;
        this.lessons = lessons;
    }

    public String getLessonTypeName(){

        return lessonTypeName;
    }

    public ArrayList<Lesson> getLessons()
    {
        return lessons;
    }

    public String toString()
    {
        return lessonTypeName;
    }

    @Override
    public void expectedBehavior(JTabbedPane tabbedPane, LapleDataModel model, RootData rootData)  {

        ListableConverter<Lesson> converter = new ListableConverter<>();
        List<IListable> listables = converter.typeToIListable(this.getLessons());
        
        TabTools.swapTab(tabbedPane, new ListView(model, listables, true,
                rootData));
    }
}
