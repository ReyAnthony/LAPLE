package fr.laple.extensions.features.plugins;

import fr.laple.model.datamodel.LapleDataModel;
import fr.laple.model.listable.IListable;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Created by anthonyrey on 15/07/2015.
 */
public class DummyFeaturePlugin implements IFeaturePlugin {

    private String name;
    private String path;

    public DummyFeaturePlugin(String name)
    {
        this.name = name;
    }

    @Override
    public void instanciateExerciseModes(LapleDataModel model) {

    }

    @Override
    public ArrayList<IListable> getExerciseTypes() {
        return new ArrayList<>();
    }

    @Override
    public void addNewTabs(JTabbedPane ui, LapleDataModel model) {

    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return "We were unable to load this plugin, so we only have a " +
                "limited amount of information to display";
    }

    @Override
    public String getDeveloper() {
        return "Unknown";
    }

    @Override
    public String getVersion() {
        return "Unknown";
    }

    @Override
    public String otherCredits() {
        return "Unknown";
    }

    @Override
    public String getPath() {
        return path;
    }

    @Override
    public void setPath(String path) {
        this.path = path;
    }

    public String toString()
    {
        return getName();
    }
}
