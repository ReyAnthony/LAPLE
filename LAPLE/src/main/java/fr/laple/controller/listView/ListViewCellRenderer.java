package fr.laple.controller.listView;

import fr.laple.model.listable.IListable;

import javax.swing.*;
import java.awt.*;

/**
 * Created by anthonyrey on 10/07/2015.
 */
class ListViewCellRenderer extends JLabel implements ListCellRenderer {

    public ListViewCellRenderer()
    {
        this.setOpaque(true);
    }

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
