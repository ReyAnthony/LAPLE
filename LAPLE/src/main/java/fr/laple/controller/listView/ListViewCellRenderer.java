package fr.laple.controller.listView;

import fr.laple.model.listable.IListable;

import javax.swing.*;
import java.awt.*;

/**
 * This class is the cellRenderer used for the ListView class, it allows for greying out the disabled values
 * Used in conjonction with the ListViewSelectionListener
 *
 * Class is default access not to instanciate it elsewhere
 *
 * @see fr.laple.view.ListView
 * @see fr.laple.controller.listView.ListViewSelectionListener
 *
 * @author anthonyrey
 */
class ListViewCellRenderer extends JLabel implements ListCellRenderer {

    /**
     * Constructor for the class
     */
    public ListViewCellRenderer()
    {
        this.setOpaque(true);
    }

    /**
     * If the Ilistable in the list is not isSelectable, it is shown greyed out
     *
     * @param list
     * @param value
     * @param index
     * @param isSelected
     * @param cellHasFocus
     * @return
     */
    @Override
    public Component getListCellRendererComponent(JList list, Object value,
                                                  int index, boolean isSelected, boolean cellHasFocus) {

        if(((IListable) value).isSelectable())
            this.setEnabled(true);
        else
            this.setEnabled(false);


        Color background;

        if(!isSelected)
            background = Color.WHITE;
        else
        {
            background = Color.BLUE;

        }

        setBackground(background);

        this.setText(value.toString());

        return this;
    }


}
