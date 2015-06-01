package fr.laple.model.exercises.exceptions;

/**
 * Created by anthonyrey on 28/05/2015.
 */
public class ExcerciseModeException extends Exception {

    public ExcerciseModeException()
    {
        super("This mode cannot be used with this solver");
    }

}
