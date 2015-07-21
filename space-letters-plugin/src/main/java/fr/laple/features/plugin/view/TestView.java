package fr.laple.features.plugin.view;

import fr.laple.model.datamodel.LapleDataModel;
import fr.laple.model.language.SymbolContainer;

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

        area.append("Types disponibles :");

        for(SymbolContainer sc :  model.getSymbolContainer() )
        {
            area.append("\n" + sc.getType());

            area.append("\nsymbols :");
            for(String s : sc.getSymbolMap().keySet())
                 area.append("\n"+s );


        }

        JScrollPane scroll = new JScrollPane(area);
        this.add(scroll, BorderLayout.CENTER);



    }

}
