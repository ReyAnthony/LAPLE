package fr.laple.controller;

import com.apple.eawt.Application;
import fr.laple.controller.config.PluginConfigController;
import fr.laple.model.datamodel.LapleDataModel;
import fr.laple.model.lessons.AbstractLessonContainer;
import fr.laple.model.listable.IListable;
import fr.laple.model.listable.ListableConverter;
import fr.laple.model.listable.RootData;
import fr.laple.view.LapleGUI;
import fr.laple.view.ListView;
import fr.laple.view.staticpanels.AboutPanel;
import fr.laple.view.staticpanels.MainPanel;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is the main GUI controller
 * It initialize the whole laple app (after language selection)
 * It also manage OSX tweak for exit actions
 *
 * @author anthonyrey
 */
public class LapleGUIController {

    private LapleGUI view;
    private LapleDataModel model;

    /**
     * Constructor for the class
     * add a window listenner to the frame
     * set the quit handler (osx)
     *
     * and init the plugins, views etc..
     *
     * @param model
     */
    public LapleGUIController(LapleDataModel model)
    {
        this.model = model;
        view = new LapleGUI();

        //OSX tweak for closing
        String osName = System.getProperty("os.name").toLowerCase();
        boolean isMacOs = osName.startsWith("mac os x");
        if (isMacOs)
        {
            Application.getApplication().setQuitHandler(
                    (quitEvent, quitResponse) ->
                    {
                        int response = JOptionPane.showConfirmDialog(null,
                                "Do you really want to quit ? ", "LAPLE",
                                JOptionPane.YES_NO_OPTION);
                        if (response == JOptionPane.OK_OPTION){
                            quitResponse.performQuit();
                        }

                        quitResponse.cancelQuit();
                    });
        }

        view.addWindowListener(new WindowController(view));
        JTabbedPane ui = view.getUIPane();

        ui.add("Home Page", new MainPanel());

        ListableConverter<AbstractLessonContainer> converter = new ListableConverter<>();
        List<IListable> listables = converter.typeToIListable(model.getLessonContainers());
        ;
        ui.add("Lessons",  new ListView(model, listables, false,
                new RootData(listables, "Select a Lesson category :")));

        //get Exercise type take into account modes added by plugins",
                ui.add("Exercises", new ListView(model, model.getExerciseTypes(), false,
                new RootData(model.getExerciseTypes(), "Select an exercise mode :")));

        //add tabs from plugins
        model.addNewTabs(ui);


        ui.add("Statistics", null);

        List<IListable> settings = new ArrayList<>();
        PluginConfigController pcc = new PluginConfigController();
        settings.add(pcc);

        ui.add("User settings", new ListView(model, settings, false,
                new RootData(settings, "Select a settings page :")));

        AboutPanel aboutPanel = new AboutPanel();
        ui.add("About LAPLE", aboutPanel);

        view.add(ui);
        view.setVisible(true);

    }

}
