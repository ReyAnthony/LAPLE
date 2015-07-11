package fr.laple.controller.lessons;

import fr.laple.model.language.ILanguagePlugin;

import javax.swing.*;

/**
 * Created by anthonyrey on 11/07/2015.
 */
public interface IListable {

    public default boolean isSelectable()
    {
        return true;
    }
    public void expectedBehavior(Object selectedValue,JTabbedPane tabbedPane, ILanguagePlugin model);

}
