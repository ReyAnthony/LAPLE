package fr.laple.controller;

import fr.laple.view.AboutPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by anthonyrey on 02/06/2015.
 */
public class AboutPanelController implements ActionListener {

    private AboutPanel aboutPanel;

    public AboutPanelController(AboutPanel aboutPanel){

        this.aboutPanel = aboutPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
            goToWebsite();
    }

    private void goToWebsite()
    {

        try {
            java.awt.Desktop.getDesktop().browse(
                    new URI("https://github.com/ReyAnthony/LAPLE"));
        } catch (IOException | URISyntaxException e1) {

            JOptionPane.showMessageDialog(aboutPanel,
                    "Seems like there is a browser related error.. hum...");
        }
    }
}
