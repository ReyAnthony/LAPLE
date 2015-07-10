package fr.laple.model.lessons;

import fr.laple.model.language.Symbol;

/**
 *  This class represents a Lesson.
 *
 *  @author zaafranigabriel
 *  @author anthonyrey
 *
 */
public class Lesson {

    private String lessonName;
    private boolean open;
    private Symbol symbol;

    /**
     *  Constructor for Lesson class
     * @param lessonName The name of the lesson
     * @param open If the lesson is available
     * @param symbol The symbol corresponding to the lesson
     */
    public Lesson(String lessonName,boolean open,Symbol symbol){
        this.lessonName = lessonName;
        this.open = open;
        this.symbol = symbol;
    }

    public String getLessonName() {
        return lessonName;
    }


    public Symbol getSymbol() {
        return symbol;
    }

    public boolean isOpen() {
        
        return open;
    }

    public String toString()
    {
        return getLessonName();
    }
}
