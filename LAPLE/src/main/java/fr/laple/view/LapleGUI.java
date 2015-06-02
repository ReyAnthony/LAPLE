package fr.laple.view;

import fr.laple.model.language.ILanguagePlugin;
import fr.laple.tools.ScreenTools;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * This class is the main GUI for LAPLE application. It contains all the panels
 *
 * @see fr.laple.view.AboutPanel
 * @see fr.laple.view.MainPanel
 * @author anthonyrey
 */
public class LapleGUI extends JFrame implements WindowListener {

    private JTabbedPane UIPane;
    private AboutPanel aboutPanel;
    private MainPanel mainPanel;

    private ILanguagePlugin languagePlugin;


    public LapleGUI(ILanguagePlugin plugin){

        this.languagePlugin = plugin;
        languagePlugin.loadSymbolContainers();
        createUI();
    }

    private void createUI()
    {
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.addWindowListener(this);
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
        UIPane.add("Exercises" , null);
        UIPane.add("Statistics" , null);
        UIPane.add("User settings" , null);

        aboutPanel = new AboutPanel();
        UIPane.add("About LAPLE" , aboutPanel);

        this.add(UIPane);

    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {

        int response = JOptionPane.showConfirmDialog(this,
                "Do you really want to quit ? ", "LAPLE",
                JOptionPane.YES_NO_OPTION);
        if (response == JOptionPane.OK_OPTION){
            this.dispose();
        }

    }

    @Override
    public void windowClosed(WindowEvent e) {
        //ugly
        System.exit(0);
    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
