package fr.laple.model.lessons;

import fr.laple.model.listable.IListable;

/**
 * This class is an AbstractLessonContainer
 *
 * @author anthonyrey
 */
public abstract class AbstractLessonContainer implements IListable{


    public abstract String getLessonTypeName();

    @Override
    public String toString()
    {
        return "Need to be overriden";
    }


}
