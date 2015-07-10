package fr.laple.controller.lessons;

import fr.laple.model.lessons.Lesson;

import javax.swing.*;
import java.awt.*;

/**
 * Created by anthonyrey on 10/07/2015.
 */
public class ListViewCellRenderer extends JLabel implements ListCellRenderer {

    //TODO must get classes that implements something like ILapleListable
    //This interface would then get sure that the part is currently displayable (returning open for a lesson
    //and true for anything else

    public ListViewCellRenderer()
    {
        this.setOpaque(true);
    }

    @Override
    public Component getListCellRendererComponent(JList list, Object value,
                                                  int index, boolean isSelected, boolean cellHasFocus) {

        //TODO to get in object fashion

        if(value instanceof Lesson)
        {
            if(((Lesson) value).isOpen())
                this.setEnabled(true);
            else
                this.setEnabled(false);
        }

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
