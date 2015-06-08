package fr.laple.model.lessons;

import java.util.ArrayList;

/**
 * Created by anthonyrey on 06/06/2015.
 */
public class WordLessonContainer extends AbstractLessonContainer {

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

}
