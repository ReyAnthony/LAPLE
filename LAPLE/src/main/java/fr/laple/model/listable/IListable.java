package fr.laple.model.listable;


import fr.laple.model.datamodel.LapleDataModel;

import javax.swing.*;

/**
 *This Interface is to be implemented by classes you want to be listed in ListView
 *
 * @see fr.laple.view.ListView
 *
 * @author anthonyrey
 */
public interface IListable {

    /**
     * Done before expected behavior
     */
    public default void preAction(){};

    /**
     * Is the value selectable ?
     * @return true if selectable else false
     */
    public default boolean isSelectable()
    {
        return true;
    }

    /**
     * The behavior of the implementor
     * ListView is swapping the view by a new one for example
     *
     * @see fr.laple.view.ListView
     * @see fr.laple.ztools.tabTools.TabTools
     * @see fr.laple.model.listable.RootData
     * @see fr.laple.model.datamodel.LapleDataModel
     *
     * @param tabbedPane The tabbed pane
     * @param model     The LapleDataModel
     * @param rootData  The RootData object
     */
    public void expectedBehavior(JTabbedPane tabbedPane, LapleDataModel model, RootData rootData);
}
