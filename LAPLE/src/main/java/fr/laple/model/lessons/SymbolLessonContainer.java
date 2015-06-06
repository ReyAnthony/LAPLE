package fr.laple.model.lessons;

import java.util.ArrayList;

/**
 * This class contains Symbol Lessons
 * @author zaafranigabriel
 * @author anthonyrey
 *
 */
public class SymbolLessonContainer {

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


}
