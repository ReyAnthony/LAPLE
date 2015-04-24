package fr.laple.Lesson;

import fr.laple.language.Symbol;

/**
 *  This class represent lesson.
 *
 *  @author zaafranigabriel
 *
 */
public class Lesson {
    private String lessonName;
    private boolean open;
    private Symbol symbol;

    /**
     *  This is lesson
     * @param lessonName
     * @param open
     * @param symbol
     */
    public Lesson(String lessonName,boolean open,Symbol symbol){
        this.lessonName = lessonName;
        this.open = open;
        this.symbol = symbol;
    }


}
