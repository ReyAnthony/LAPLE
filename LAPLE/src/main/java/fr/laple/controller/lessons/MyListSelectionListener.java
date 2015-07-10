package fr.laple.controller.lessons;

import fr.laple.model.lessons.Lesson;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * Created by anthonyrey on 10/07/2015.
 */
public class MyListSelectionListener implements ListSelectionListener{

    //TODO should check if the cell renderer is the laple one, and throw an exception otherwize
    //TODO remove the shitty if

    @Override
    public void valueChanged(ListSelectionEvent e) {

        try
        {

            JList list = (JList) e.getSource();
            ListModel lm = list.getModel();
            for (int i=e.getFirstIndex(); i<=e.getLastIndex(); i++) {
                if (list.getSelectionModel().isSelectedIndex(i)) {
                    if (!((Lesson) lm.getElementAt(i)).isOpen()) {
                        list.removeSelectionInterval(i, i);
                    }
                }
            }

        }
        catch(ClassCastException ex)
        {
            System.out.println("fuck ye, ya filthy basterd");
        }



    }
}
