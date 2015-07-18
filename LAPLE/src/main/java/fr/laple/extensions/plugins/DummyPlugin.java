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
 * This class is a dummyPlugin
 * A dummy plugin is a plugin used as a substitute for any other plugin
 *
 * @author anthonyrey
 */
public class DummyPlugin implements IPlugin, ILanguagePlugin, IFeaturePlugin {

    private String name;
    private File path;
    private boolean internal;
    private String description;
    private String developer;
    private String version;
    private String otherCredits;

    /**
     * Constructor without data
     * When this constructor is used, unspecified data are set to unknown
     *
     * @param name The name of the plugin
     * @param path The path to the plugin
     * @param isInternal isInternal ?
     */
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

    /**
     * Constructor n2
     *
     * @param name The name of the plugin
     * @param path The path to the plugin
     * @param isInternal isInternal ?
     * @param description The description
     * @param developer The developer of the plugin
     * @param version The version of the plugin
     * @param otherCredits Other credits (like people who recorded sounds etc..)
     */
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

    /**
     * Not implemented
     * @param model
     */
    @Override
    public void instanciateExerciseTypes(LapleDataModel model) {

    }

    /**
     * Not implemented
     * @return
     */
    @Override
    public ArrayList<IListable> getExerciseTypes() {
        return new ArrayList<>();
    }

    /**
     * Not implemented
     * @param ui The tabbedPane from LapleUI
     * @param model the DataModel
     */
    @Override
    public void addNewTabs(JTabbedPane ui, LapleDataModel model) {

    }

    /**
     * Not implemented
     * @return
     */
    @Override
    public ArrayList<SymbolContainer> getSymbolContainer() {
        return null;
    }

    /**
     * Not implemented
     * @return
     */
    @Override
    public ArrayList<IExerciseMode> getExercisesModes() {
        return new ArrayList<>();
    }

    /**
     * Not implemented
     * @return
     */
    @Override
    public ArrayList<AbstractAnswerMode> getExercisesSolvingModes() {
        return new ArrayList<>();
    }

    /**
     * Not implemented
     * @return
     */
    @Override
    public ArrayList<IListable> getLessonContainers() {
        return new ArrayList<>();
    }
}
