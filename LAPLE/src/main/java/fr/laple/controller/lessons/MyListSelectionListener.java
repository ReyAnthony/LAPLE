package fr.laple.controller.lessons;

import fr.laple.model.listable.IListable;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * Created by anthonyrey on 10/07/2015.
 */
class MyListSelectionListener implements ListSelectionListener{

    @Override
    public void valueChanged(ListSelectionEvent e) {

        try
        {

            JList list = (JList) e.getSource();
            ListModel lm = list.getModel();
            for (int i=e.getFirstIndex(); i<=e.getLastIndex(); i++) {
                if (list.getSelectionModel().isSelectedIndex(i)) {
                    if (!((IListable) lm.getElementAt(i)).isSelectable()) {
                        list.removeSelectionInterval(i, i);
                    }
                }
            }

        }
        catch(ClassCastException ex)
        {
           //Should never happen if we do not use this class elsewhere (class is default access)
        }



    }
}
