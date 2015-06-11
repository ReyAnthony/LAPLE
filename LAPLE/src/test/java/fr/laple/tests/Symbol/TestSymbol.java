package fr.laple.tests.Symbol;

import fr.laple.model.language.Symbol;
import junit.framework.Assert;
import org.junit.Test;

/**
 * Created by zaafranigabriel on 06/06/2015.
 */
public class TestSymbol {
    @Test
    public void createSymbol(){
        Symbol objet = new Symbol("A","א",null,null,null,null);
        String val =objet.getSymbol();
        Assert.assertEquals(val,"א");
    }

    @Test
    public void createTranslation(){
        Symbol objet = new Symbol("A","א",null,null,null,"Alef");
        String translation = objet.getTranslation();
        Assert.assertEquals(translation,"Alef");
    }


}
