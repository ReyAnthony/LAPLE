package fr.laple.view;

import fr.laple.controller.WindowController;
import fr.laple.model.language.ILanguagePlugin;
import fr.laple.view.exercises.GenericExerciseParameterizer;
import fr.laple.view.staticpanels.AboutPanel;
import fr.laple.view.staticpanels.MainPanel;
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
    private AboutPanel aboutPanel;
    private MainPanel mainPanel;

    private ILanguagePlugin languagePlugin;


    public LapleGUI(ILanguagePlugin plugin){

        this.languagePlugin = plugin;
        createUI();
    }

    private void createUI()
    {
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowController(this));
        this.setSize(700, 500);
        this.setMinimumSize(new Dimension(560,500));
        this.setLocation(ScreenTools.getCenteredPoint(this.getWidth(), this.getHeight()));
        this.setTitle("LAPLE - Logiciel d'Apprentissage de Langues Etrangères");
        this.setResizable(true);

        createPanes();
        this.setVisible(true);
    }

    private void createPanes()
    {
        UIPane = new JTabbedPane();

        mainPanel = new MainPanel();
        UIPane.add("Home Page" , mainPanel);

        UIPane.add("Lessons" , null);

        UIPane.add("Exercises" , new GenericExerciseParameterizer(languagePlugin));

        UIPane.add("Statistics" , null);
        UIPane.add("User settings" , null);

        aboutPanel = new AboutPanel();
        UIPane.add("About LAPLE" , aboutPanel);

        this.add(UIPane);

    }

    public JTabbedPane getUIPane()
    {
        return UIPane;
    }

}
