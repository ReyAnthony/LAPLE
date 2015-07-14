package fr.laple.view.staticpanels;

import fr.laple.controller.staticpanels.AboutPanelController;

import javax.swing.*;
import java.awt.*;

/**
 * This panel displays copyright informations and a link to the website
 *
 * @author anthonyrey
 */
public class AboutPanel extends JPanel  {


    private JLabel copyrightInfos;
    private JButton linkToWebsite;
    private JLabel gplLogo;

    public AboutPanel()
    {
        BorderLayout layout = new BorderLayout();
        this.setLayout(layout);
        this.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        copyrightInfos = new JLabel("<html><br/> LAPLE, Logiciel d'Apprentissage de Langues Etrangères. <br/><br/>" +
                "Copyright (c) 2015 <br/>" +
                "Anthony REY, Christian EBONGUE and Gabriel ZAAFRANI <br/><br/>" +
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

        copyrightInfos.setHorizontalAlignment(JLabel.CENTER);


        ImageIcon image = new ImageIcon(getClass().getResource("/images/gplv3.png"));
        gplLogo = new JLabel(image);
        linkToWebsite = new JButton("Go to the website");

        createUI();

    }

    private void createUI()
    {
        this.add(copyrightInfos, BorderLayout.CENTER);

        JPanel bottom = new JPanel();
        bottom.add(gplLogo);
        bottom.add(linkToWebsite);
        this.add(bottom, BorderLayout.PAGE_END);

        linkToWebsite.addActionListener(new AboutPanelController(this));
    }


}
