package fr.laple.model.lessons;

import fr.laple.extensions.languages.plugins.ILanguagePlugin;
import fr.laple.model.listable.IListable;
import fr.laple.model.listable.RootData;
import fr.laple.view.ListView;

import javax.swing.*;
import java.util.ArrayList;

/**
 * This class contains Symbol Lessons
 * @author zaafranigabriel
 * @author anthonyrey
 *
 */
public class SymbolLessonContainer extends AbstractLessonContainer implements IListable{

    private String lessonTypeName;
    private ArrayList<IListable> lessons;

    /**
     * It's the constructor of Symbol lesson container
     * @param lessonTypeName String used to set the type  of the dictionary.
     */
    public SymbolLessonContainer(String lessonTypeName, ArrayList<IListable> lessons){
        this.lessonTypeName = lessonTypeName;
        this.lessons = lessons;
    }

    public String getLessonTypeName(){

        return lessonTypeName;
    }

    public ArrayList<IListable> getLessons()
    {
        return lessons;
    }

    public String toString()
    {
        return lessonTypeName;
    }

    @Override
    public void expectedBehavior(JTabbedPane tabbedPane, ILanguagePlugin model, RootData rootData)  {

        int selected = tabbedPane.getSelectedIndex();
        tabbedPane.remove(selected);
        //view = new ListView<>(symbolLessonContainer.getLessons(), true);
        tabbedPane.insertTab("Lessons", null, new ListView(model, this.getLessons(), true, "Select a Lesson :", rootData),
                null, selected);
        tabbedPane.setSelectedIndex(selected);
    }
}
