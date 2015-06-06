package fr.laple.model.lessons;

import java.util.HashMap;

/**
 * Created by anthonyrey on 06/06/2015.
 */
public class WordLessonContainer {

    private String lessonTypeName;
    private HashMap<String, LessonCategory> lessonCategories;

    public WordLessonContainer(String lessonTypeName)
    {
        this.lessonTypeName = lessonTypeName;
        this.lessonCategories = new HashMap<>();

    }

    public void addCategory(String name, LessonCategory category)
    {
        lessonCategories.put(name, category);
    }

    public String getLessonTypeName() {
        return lessonTypeName;
    }

   public HashMap<String, LessonCategory> getLessonCategories()
   {
       return lessonCategories;
   }
}
