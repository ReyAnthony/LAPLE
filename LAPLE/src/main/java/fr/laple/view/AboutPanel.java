package fr.laple.view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * This panel displays copyright informations and a link to the website
 *
 * @author anthonyrey
 */
public class AboutPanel extends JPanel implements ActionListener {


    private JLabel copyrightInfos;
    private JButton linkToWebsite;
    private JLabel gplLogo;

    public AboutPanel()
    {

        copyrightInfos = new JLabel("<html><br/> LAPLE, Logiciel d'Apprentissage de Langues Etrangères. <br/><br/>" +
                "Copyright (c) 2015 <br/>" +
                "Anthony REY, Christian EBONGUE and Gabriel ZAAFRANI <br/><br/><br/>" +
                "This program is free software: you can redistribute it and/or modify<br/>" +
                "it under the terms of the GNU General Public License as published by<br/>" +
                "the Free Software Foundation, either version 3 of the License, <br/>" +
                "or any later version.<br/>" +
                "<br/>" +
                "This program is distributed in the hope that it will be useful,<br/>" +
                "but WITHOUT ANY WARRANTY; without even the implied warranty of<br/>" +
                "MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the<br/>" +
                "GNU General Public License for more details.<br/>" +
                "<br/>" +
                "You should have received a copy of the GNU General Public License<br/>" +
                "along with this program.  If not, see http://www.gnu.org/licenses. <br/><br/><br/> <html>");


        ImageIcon image = new ImageIcon(getClass().getResource("/images/gplv3.png"));
        gplLogo = new JLabel(image);
        linkToWebsite = new JButton("Go to the website");

        createUI();

    }

    private void createUI()
    {
        this.add(copyrightInfos);
        this.add(gplLogo);
        this.add(linkToWebsite);
        linkToWebsite.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource().equals(linkToWebsite)){
            goToWebsite();
        }

    }

    private void goToWebsite()
    {

        try {
            java.awt.Desktop.getDesktop().browse(
                    new URI("https://github.com/ReyAnthony/LAPLE"));
        } catch (IOException | URISyntaxException e1) {

            JOptionPane.showMessageDialog(this,
                    "Seems like there is a browser related error.. hum...");
        }
    }
}
