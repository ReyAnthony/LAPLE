package fr.laple.extensions.plugins.languages;

/**
 * An exception when parsing fail
 *
 * @author anthonyrey
 */
public class ParserException extends Exception {

    public ParserException(String filename)
    {
        super("Error with " + filename +
                ". Check for errors or reinstall the software");
    }
}
