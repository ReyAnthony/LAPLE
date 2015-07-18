package fr.laple.extensions.plugins.features;

import fr.laple.extensions.plugins.IPlugin;
import fr.laple.model.datamodel.LapleDataModel;
import fr.laple.model.listable.IListable;

import javax.swing.*;
import java.util.ArrayList;

/**
 * An interface for the feature Plugins
 *
 * @author anthonyrey
 */
public interface IFeaturePlugin extends IPlugin {

    /**
     *  This is to add new Exercises in the ListView of Exercises
     *  We need to instanciate them AFTER the constructor because
     *  it needs the LapleDataModel which is constructed using them !
     *
     *  @see fr.laple.controller.LapleGUIController
     *
     * @param model
     */
    public void instanciateExerciseTypes(LapleDataModel model);


    public ArrayList<IListable> getExerciseTypes();

    /**
     * Adds a new tab to LapleGUI
     *
     *  @see fr.laple.controller.LapleGUIController
     *
     * @param ui The tabbedPane from LapleUI
     * @param model the DataModel
     */
    public void addNewTabs(JTabbedPane ui, LapleDataModel model);

}
