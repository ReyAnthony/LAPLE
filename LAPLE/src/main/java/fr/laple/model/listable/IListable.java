package fr.laple.model.listable;

import fr.laple.model.language.ILanguagePlugin;

import javax.swing.*;

/**
 * Created by anthonyrey on 11/07/2015.
 */
public interface IListable {

    public default void oneInListPreAction(){};
    public default boolean isSelectable()
    {
        return true;
    }
    public void expectedBehavior(JTabbedPane tabbedPane, ILanguagePlugin model, RootData roodtData);
}
