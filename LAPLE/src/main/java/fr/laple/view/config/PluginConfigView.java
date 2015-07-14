package fr.laple.view.config;

import javax.swing.*;
import java.awt.*;

/**
 * Created by anthonyrey on 14/07/2015.
 */
public class PluginConfigView extends JPanel {

    private JTextArea description;
    private JComboBox pluginTypes;
    private JList plugins;
    private JButton add;
    private JButton remove;
    private JButton back;

    public PluginConfigView()
    {
        this.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        this.setLayout(new BorderLayout());

        description = new JTextArea();
        description.setEditable(false);

        JPanel topPanel = new JPanel();
        pluginTypes = new JComboBox();
        add = new JButton("Add new");
        remove = new JButton("Remove selected");
        topPanel.add(pluginTypes);
        topPanel.add(remove);
        topPanel.add(add);

        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BorderLayout());
        leftPanel.setBorder(BorderFactory.createEmptyBorder(0,0,0,20));
        plugins = new JList();
        plugins.setPreferredSize(new Dimension(150,500));
        plugins.setSize(200,500);
        leftPanel.add(plugins);

        back = new JButton("Back");

        this.add(topPanel, BorderLayout.PAGE_START);
        this.add(description, BorderLayout.CENTER);
        this.add(leftPanel, BorderLayout.LINE_START);
        this.add(back, BorderLayout.PAGE_END);

    }

    public JTextArea getDescription() {
        return description;
    }

    public JComboBox getPluginTypes() {
        return pluginTypes;
    }

    public JList getPlugins() {
        return plugins;
    }

    public JButton getAdd() {
        return add;
    }

    public JButton getRemove() {
        return remove;
    }

    public JButton getBack() {
        return back;
    }
}
