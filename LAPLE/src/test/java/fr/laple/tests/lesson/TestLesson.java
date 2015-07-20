package fr.laple.tests.lesson;

import fr.laple.model.language.Symbol;
import fr.laple.model.lessons.Lesson;
import junit.framework.Assert;
import org.junit.Test;

/**
 * Created by zaafranigabriel on 19/07/2015.
 */
public class TestLesson {

    @Test
    public void verificationCaractere(){
        Symbol sym = new Symbol("A","פ",null,null,null,"test");
        Lesson objetLesson = new Lesson("Hiragana",true,sym);
        Assert.assertEquals(sym.getUserLangTranscript(),"A");
    }




}
