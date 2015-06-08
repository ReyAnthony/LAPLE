package fr.laple.model.lessons;

/**
 * Created by anthonyrey on 06/06/2015.
 */
public abstract class AbstractLessonContainer {


    public abstract String getLessonTypeName();

    @Override
    public String toString()
    {
        return "Need to be overriden";
    }


}
