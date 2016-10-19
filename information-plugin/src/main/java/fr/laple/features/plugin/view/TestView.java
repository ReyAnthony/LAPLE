package fr.laple.features.plugin.view;

import fr.laple.model.datamodel.LapleDataModel;

import javax.swing.*;
import java.awt.*;

/**
 * Created by anthonyrey on 21/07/2015.
 */
public class TestView extends JPanel{

    public TestView(LapleDataModel model)
    {
        this.setLayout(new BorderLayout());
        JTextArea area = new JTextArea();
        area.setEditable(false);
        area.setLineWrap(true);

        area.append("Types disponibles :");

        model.getSymbolContainer().forEach(sc -> {

            area.append("\n" + sc.getType());
            area.append("\n\tsymbols :\n");

            sc.getSymbolMap().keySet().forEach(s -> area.append("\t"+ s));
            area.append("\n");
        });


        JScrollPane scroll = new JScrollPane(area);
        this.add(scroll, BorderLayout.CENTER);
    }

}
