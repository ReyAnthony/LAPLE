package fr.laple.LapleUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * Created by anthonyrey on 11/04/2015.
 */
public class LapleGUI extends JFrame implements ActionListener, WindowListener {

    private JTabbedPane UIPane;
    private AboutPanel aboutPanel;
    private MainPanel mainPanel;
    //dico de symbols

    public LapleGUI(String langPath){

        //charger lapleLanguagePlugin via le path + classloader
        //pour recuperer les dicos de symboles du lang courant
        createUI();
    }

    private void createUI()
    {
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.addWindowListener(this);
        this.setSize(560, 500);
        this.setTitle("LAPLE - Logiciel d'Apprentissage de Langues Etrangères");
        this.setResizable(false);

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
    public void actionPerformed(ActionEvent e) {


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
