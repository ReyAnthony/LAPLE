package fr.laple.extensions.plugins;

import fr.laple.extensions.plugins.features.IFeaturePlugin;
import fr.laple.extensions.plugins.languages.ILanguagePlugin;
import fr.laple.model.datamodel.LapleDataModel;
import fr.laple.model.exercises.answers.AbstractAnswerMode;
import fr.laple.model.exercises.exercisemode.IExerciseMode;
import fr.laple.model.language.SymbolContainer;
import fr.laple.model.listable.IListable;

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;

/**
 * Created by anthonyrey on 15/07/2015.
 */
public class DummyPlugin implements IPlugin, ILanguagePlugin, IFeaturePlugin {

    private String name;
    private File path;
    private boolean internal;
    private String description;
    private String developer;
    private String version;
    private String otherCredits;

    public DummyPlugin(String name, File path, boolean isInternal)
    {
        this.name = name;
        this.path = path;
        this.internal = isInternal;

        this.description = "We were unable to load this plugin, so we only have a " +
                "limited amount of information to display";
        this.developer = "Unknown";
        this.version = "Unknown";
        this.otherCredits = "Unknown";
    }

    public DummyPlugin(String name, File path, boolean isInternal, String description, String developer,
                       String version, String otherCredits)
    {
        this.name = name;
        this.path = path;
        this.internal = isInternal;

        this.description = description;
        this.developer = developer;
        this.version = version;
        this.otherCredits = otherCredits;

    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getDeveloper() {
        return developer;
    }

    @Override
    public String getVersion() {
        return version;
    }

    @Override
    public String otherCredits() {
        return otherCredits;
    }

    @Override
    public File getPath() {
        return path;
    }

    @Override
    public void setPath(File path) {
        this.path = path;
    }

    @Override
    public boolean isInternal() {
        return internal;
    }

    public String toString()
    {
        return getName();
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
    public ArrayList<SymbolContainer> getSymbolContainer() {
        return null;
    }

    @Override
    public ArrayList<IExerciseMode> getExercisesModes() {
        return new ArrayList<>();
    }

    @Override
    public ArrayList<AbstractAnswerMode> getExercisesSolvingModes() {
        return new ArrayList<>();
    }

    @Override
    public ArrayList<IListable> getLessonContainers() {
        return new ArrayList<>();
    }
}
