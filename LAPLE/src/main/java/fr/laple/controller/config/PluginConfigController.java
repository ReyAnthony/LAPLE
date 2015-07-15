package fr.laple.controller.config;

import fr.laple.extensions.plugins.IPlugin;
import fr.laple.extensions.plugins.PluginLoadingException;
import fr.laple.extensions.plugins.features.FeatureConfigFileParser;
import fr.laple.extensions.plugins.features.IFeaturePlugin;
import fr.laple.extensions.plugins.languages.ILanguagePlugin;
import fr.laple.model.datamodel.LapleDataModel;
import fr.laple.model.listable.IListable;
import fr.laple.model.listable.RootData;
import fr.laple.view.ListView;
import fr.laple.view.config.PluginConfigView;
import fr.laple.ztools.tabTools.TabTools;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by anthonyrey on 14/07/2015.
 */
public class PluginConfigController implements ActionListener, ItemListener, ListSelectionListener, IListable {

    private PluginConfigView view;
    private RootData rootData;
    private JTabbedPane tabbedPane;
    private LapleDataModel model;

    private List<ILanguagePlugin> languagePlugins;
    private List<IFeaturePlugin> featurePlugins;

    //TODO make listable clases not instiables ? and use a homebrew way
    public PluginConfigController()
    {
        this.view = new PluginConfigView();
        view.getAdd().addActionListener(this);
        view.getBack().addActionListener(this);
        view.getRemove().addActionListener(this);
        view.getPluginTypes().addItemListener(this);
        view.getPlugins().addListSelectionListener(this);


        view.getPluginTypes().addItem("Language plugin");
        view.getPluginTypes().addItem("Feature plugin");

        // LOADING plugins

        languagePlugins = new ArrayList<>();

        //TODO
         /*
        try {

            LanguageConfigFileParser lcfp = new LanguageConfigFileParser();
            for(PluginConfigObject pco : lcfp.getLanguagePluginsList() )
            {
                languagePlugins.add((ILanguagePlugin) pco.getPlugin().newInstance());
            }

        } catch (PluginLoadingException e) {
            e.printStackTrace();
        } catch (ParserException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        */
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource().equals(view.getBack()))
        {
            TabTools.swapTab(tabbedPane, new ListView(model, rootData.getRootModel(), false,
                    rootData));
        }
        else if(e.getSource().equals(view.getRemove()))
        {
            IPlugin selectedPlugin = (IPlugin) view.getPlugins().getSelectedValue();
            if (selectedPlugin == null) {

                //### In memoriam : Satoru Iwata ###
                JOptionPane.showMessageDialog(view, "You must select a value to remove !", "Please understand",
                        JOptionPane.OK_OPTION);
            }
            else
            {

                if(!selectedPlugin.isInternal())
                {
                    if(!languagePlugins.remove(selectedPlugin))
                    {
                        featurePlugins.remove(selectedPlugin);

                        try {
                            FeatureConfigFileParser fcfp = new FeatureConfigFileParser();
                            fcfp.removePlugin((IFeaturePlugin) selectedPlugin);
                        } catch (PluginLoadingException e1) {
                            e1.printStackTrace();
                        }

                        updateFeaturePluginView();
                    }
                    else
                    {
                        updateLanguagePluginView();
                    }

                    view.getDescription().setText("");
                    JOptionPane.showMessageDialog(view, "Your changes will be taken into account upon next restart",
                            "Please restart LAPLE",
                            JOptionPane.INFORMATION_MESSAGE);
                }
                else
                {
                    JOptionPane.showMessageDialog(view, "You cannot remove this plugin, you may delete it from the config file " +
                                    "at your own risk.",
                        "Error",
                        JOptionPane.WARNING_MESSAGE);
                }
            }
        }
        else if(e.getSource().equals(view.getAdd()))
        {
            JFileChooser chooser = new JFileChooser();
            chooser.setFileFilter(new FileFilter() {
                @Override
                public boolean accept(File f) {
                    return f.toString().endsWith(".jar");
                }

                @Override
                public String getDescription() {
                    return "Jar file";
                }
            });
            chooser.showOpenDialog(view);
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {

        if(e.getItem().equals("Language plugin"))
            updateLanguagePluginView();
        else
           updateFeaturePluginView();

        view.getDescription().setText("");

        if(view.getPlugins().getComponentCount() > 0)
        {
            this.view.getPlugins().setSelectedIndex(0);
        }
    }

    private void updateLanguagePluginView()
    {
        if(languagePlugins != null && !languagePlugins.isEmpty() )
        {
            DefaultListModel<ILanguagePlugin> model = new DefaultListModel<>();
            languagePlugins.forEach(model::addElement);
            view.getPlugins().setModel(model);
        }
        else
        {
            view.getPlugins().setModel(new DefaultListModel<>());
        }
    }

    private void updateFeaturePluginView()
    {
        if(featurePlugins != null && !featurePlugins.isEmpty() )
        {
            DefaultListModel<IFeaturePlugin> model = new DefaultListModel<>();
            featurePlugins.forEach(model::addElement);
            view.getPlugins().setModel(model);
        }
        else
        {
            view.getPlugins().setModel(new DefaultListModel<>());
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {

        IPlugin selectedPlugin = ((JList<IPlugin>) e.getSource()).getSelectedValue();
        if (selectedPlugin != null) {

            String desc = "Name : "+selectedPlugin.getName() + "\nPath : "+selectedPlugin.getPath() +
                    "\nDeveloper : " + selectedPlugin.getDeveloper() +
                    "\nOther credits : "+selectedPlugin.otherCredits() +
                    "\nVersion : " + selectedPlugin.getVersion() + "\n" + "Is internal : " + selectedPlugin.isInternal()
                    +"\n\n"+ selectedPlugin.getDescription();
            view.getDescription().setText(desc);
        }

    }

    public PluginConfigView getView() {
        return view;
    }

    @Override
    public void expectedBehavior(JTabbedPane tabbedPane, LapleDataModel model, RootData rootData) {

        this.rootData = rootData;
        this.tabbedPane = tabbedPane;
        this.model = model;

        TabTools.swapTab(tabbedPane, this.getView());

        //changing state manually ;)
        this.view.getPluginTypes().setSelectedIndex(1);
        this.view.getPluginTypes().setSelectedIndex(0);

        featurePlugins = model.getFeatures();
    }

    @Override
    public String toString()
    {
        return "Plugin settings";
    }

}
