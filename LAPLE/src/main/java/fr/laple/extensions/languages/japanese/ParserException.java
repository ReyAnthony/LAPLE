package fr.laple.extensions.languages.japanese;

/**
 * Created by anthonyrey on 06/06/2015.
 */
public class ParserException extends Exception {

    public ParserException(String filename)
    {
        super("Error with " + filename +
                ". Check for errors or reinstall the software");
    }
}
