package fr.laple.features.plugin;

import fr.laple.extensions.plugins.features.IFeaturePlugin;
import fr.laple.model.datamodel.LapleDataModel;
import fr.laple.model.listable.IListable;

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;

/**
 * Created by anthonyrey on 14/07/2015.
 */
public class SpaceLettersPlugin implements IFeaturePlugin {

    private File path;

    @Override
    public void instanciateExerciseModes(LapleDataModel lapleDataModel) {
        //no new exercises
    }

    @Override
    public ArrayList<IListable> getExerciseTypes() {
        //no new exercises types
        return new ArrayList<>();
    }

    @Override
    public void addNewTabs(JTabbedPane ui, LapleDataModel model) {
        ui.add("Space Letters", null);
    }

    @Override
    public String getName() {
        return "Space letters";
    }

    @Override
    public String getDescription() {
        return "A small \"space invader\" like game for LAPLE";
    }

    @Override
    public String getDeveloper() {
        return "Anthony REY";
    }

    @Override
    public String getVersion() {
        return "14.06.15.b1";
    }

    @Override
    public String otherCredits() {
        return "N/A";
    }

    @Override
    public File getPath() {
        return path;
    }

    @Override
    public void setPath(File file) {
        this.path = file;
    }

    @Override
    public boolean isInternal() {
        return false;
    }

    public String toString()
    {
        return getName();
    }
}
