package fr.laple.view.lessons;

import javax.swing.*;
import java.awt.*;

/**
 * Created by anthonyrey on 09/06/2015.
 */
public class LessonView extends JPanel {

    private JLabel symbol;

    public LessonView()
    {

        GridBagLayout layout = new GridBagLayout();
        this.setLayout(layout);
        GridBagConstraints gbc = new GridBagConstraints();

        symbol = new JLabel();
        symbol.setHorizontalAlignment(JLabel.CENTER);
        Font f = symbol.getFont().deriveFont(200.0f);
        symbol.setFont(f);

        this.add(symbol, gbc);


    }


    public JLabel getSymbol() {
        return symbol;
    }
}
