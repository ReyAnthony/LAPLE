package fr.laple.model.lessons;

/**
 * This class is an AbstractLessonContainer
 *
 * @author anthonyrey
 */
public abstract class AbstractLessonContainer {


    public abstract String getLessonTypeName();

    @Override
    public String toString()
    {
        return "Need to be overriden";
    }


}
