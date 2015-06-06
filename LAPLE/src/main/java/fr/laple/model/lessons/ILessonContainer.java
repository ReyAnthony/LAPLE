package fr.laple.model.lessons;

/**
 * Created by anthonyrey on 06/06/2015.
 */
public interface ILessonContainer {

    public String getLessonTypeName();

    default public Class getType()
    {
        return this.getClass();
    }

}
