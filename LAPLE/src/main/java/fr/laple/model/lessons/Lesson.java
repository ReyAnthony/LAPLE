package fr.laple.model.lessons;

import fr.laple.controller.lessons.LessonController;
import fr.laple.model.datamodel.LapleDataModel;
import fr.laple.model.language.Symbol;
import fr.laple.model.listable.IListable;
import fr.laple.model.listable.RootData;
import fr.laple.view.lessons.LessonView;
import fr.laple.ztools.tabTools.TabTools;

import javax.swing.*;

/**
 *  This class represents a Lesson.
 *
 *  @author zaafranigabriel
 *  @author anthonyrey
 *
 */
public class Lesson implements IListable {

    private String lessonName;
    private Symbol symbol;

    /**
     *  Constructor for Lesson class
     * @param lessonName The name of the lesson
     * @param symbol The symbol corresponding to the lesson
     */
    public Lesson(String lessonName, Symbol symbol){
        this.lessonName = lessonName;
        this.symbol = symbol;
    }

    public String getLessonName() {
        return lessonName;
    }


    public Symbol getSymbol() {
        return symbol;
    }

    public String toString()
    {
        return getLessonName();
    }

    @Override
    public boolean isSelectable() {

        return true;
    }

    @Override
    public void expectedBehavior(JTabbedPane tabbedPane, LapleDataModel model, RootData rootData)  {

        LessonView lessonView = new LessonView();
        TabTools.swapTab(tabbedPane, lessonView);
        new LessonController(model, lessonView, this, rootData);

    }
}
