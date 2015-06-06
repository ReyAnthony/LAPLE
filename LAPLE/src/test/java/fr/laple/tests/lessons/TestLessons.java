package fr.laple.tests.lessons;

import fr.laple.model.lessons.AbstractLessonContainer;
import fr.laple.model.lessons.SymbolLessonContainer;
import fr.laple.model.lessons.WordLessonContainer;
import junit.framework.Assert;
import org.junit.Test;

/**
 * Created by anthonyrey on 06/06/2015.
 */
public class TestLessons {

    @Test
    public void containerTypeTest()
    {
        AbstractLessonContainer container = new SymbolLessonContainer(null,null);
        Class clazz = container.getType();
        Assert.assertTrue(clazz.equals(SymbolLessonContainer.class));
        Assert.assertFalse(clazz.equals(WordLessonContainer.class));

    }


}
