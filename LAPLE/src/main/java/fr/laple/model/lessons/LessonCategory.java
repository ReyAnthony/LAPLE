package fr.laple.model.lessons;

import java.util.ArrayList;

/**
 * Created by anthonyrey on 06/06/2015.
 */
public class LessonCategory {

    private String categoryName;
    private ArrayList<Lesson> lessons;

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
}
