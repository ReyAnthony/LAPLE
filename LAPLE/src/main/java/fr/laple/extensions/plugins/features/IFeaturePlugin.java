package fr.laple.extensions.plugins.features;

import fr.laple.extensions.plugins.IPlugin;
import fr.laple.model.datamodel.LapleDataModel;
import fr.laple.model.listable.IListable;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Created by anthonyrey on 14/07/2015.
 */
public interface IFeaturePlugin extends IPlugin {

    public void instanciateExerciseModes(LapleDataModel model);
    public ArrayList<IListable> getExerciseTypes();
    public void addNewTabs(JTabbedPane ui, LapleDataModel model);

}
