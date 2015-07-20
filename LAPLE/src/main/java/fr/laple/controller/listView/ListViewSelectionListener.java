package fr.laple.controller.listView;

import fr.laple.model.listable.IListable;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * This class is the SelectionListener used for the ListView class, it allows
 *
 * Class is default access so we cannot instanciate it elsewhere
 *
 * @see fr.laple.view.ListView
 * @see fr.laple.controller.listView.ListViewSelectionListener
 *
 * @author anthonyrey
 */
class ListViewSelectionListener implements ListSelectionListener{

    /**
     * if value is selected and you cannot (! isSelectable()) it disable it
     * @param e ListSelectionEvent
     */
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
