package fr.laple.extensions.features.plugins;

import fr.laple.extensions.languages.plugins.ILanguagePlugin;
import fr.laple.model.listable.IListable;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Created by anthonyrey on 14/07/2015.
 */
public interface IFeaturePlugin {

    public void instanciateExerciseModes(ILanguagePlugin model);
    public ArrayList<IListable> getExerciseTypes();
    public void addNewTabs(JTabbedPane ui, ILanguagePlugin model);

}
