package fr.laple.ztools.tabTools;

import javax.swing.*;

/**
 * Created by anthonyrey on 14/07/2015.
 */
public class TabTools {

    public static void swapTab(JTabbedPane tabbedPane, JPanel viewToInsert)
    {
        int selected = tabbedPane.getSelectedIndex();
        String name = tabbedPane.getTitleAt(selected);
        tabbedPane.remove(selected);
        tabbedPane.insertTab(name, null, viewToInsert, null, selected);
        tabbedPane.setSelectedIndex(selected);

    }

}
