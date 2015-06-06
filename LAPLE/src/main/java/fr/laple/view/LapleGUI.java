package fr.laple.view;

import fr.laple.ztools.ScreenTools;

import javax.swing.*;
import java.awt.*;

/**
 * This class is the main GUI for LAPLE application. It contains all the panels
 *
 * @see fr.laple.view.staticpanels.AboutPanel
 * @see fr.laple.view.staticpanels.MainPanel
 * @author anthonyrey
 */
public class LapleGUI extends JFrame {

    private JTabbedPane UIPane;


    public LapleGUI(){

        createUI();
    }

    private void createUI()
    {
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.setSize(560, 500);
        this.setMinimumSize(new Dimension(560,500));
        this.setLocation(ScreenTools.getCenteredPoint(this.getWidth(), this.getHeight()));
        this.setTitle("LAPLE - Logiciel d'Apprentissage de Langues Etrangères");
        this.setResizable(false);

        this.UIPane = new JTabbedPane();
    }


    public JTabbedPane getUIPane()
    {
        return UIPane;
    }

}
