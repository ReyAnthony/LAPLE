package fr.laple.tests.exercises;

import fr.laple.model.exercises.ExModeTranscriptLangUserLang;
import fr.laple.model.language.Symbol;
import junit.framework.Assert;
import org.junit.Test;

/**
 * Created by zaafranigabriel on 06/06/2015.
 */
public class TestExModeTranscriptLangUserLang {
    @Test
    public void testGetQuestion(){
        String alef = "א";
        Symbol objet = new Symbol("A",alef,null,null,null,null);
        ExModeTranscriptLangUserLang objetT = new ExModeTranscriptLangUserLang();
        String valeur = objetT.getQuestion(objet);
        Assert.assertEquals(alef,"א");
    }

    @Test
    public void testGetAnswere(){
        String alef = "A";
        Symbol objet = new Symbol(alef,"א",null,null,null,null);
        ExModeTranscriptLangUserLang objetT = new ExModeTranscriptLangUserLang();
        String valeur = objetT.getAnswer(objet);
        Assert.assertEquals(valeur,"A");

    }

    @Test
    public void testCreateSymbolFromAnswere(){

        String answere = "b";
        ExModeTranscriptLangUserLang objetLang = new ExModeTranscriptLangUserLang();
        Symbol syboleResponse = objetLang.createSymbolFromAnswer(answere);
        Assert.assertEquals(syboleResponse.getUserLangTranscript(),answere);
    }


}
