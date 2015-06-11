package fr.laple.tests.Lesson;

import fr.laple.model.language.Symbol;
import fr.laple.model.lessons.Lesson;
import org.junit.Test;

/**
 * Created by zaafranigabriel on 06/06/2015.
 */
public class TestLesson {
    @Test
    public void createLesson(){
        Symbol objetSymbol = new Symbol("a","ד",null,null,null,null);
        Lesson objetLesson = new Lesson("katagana",true,objetSymbol);

    }
}
