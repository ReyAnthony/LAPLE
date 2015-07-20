package fr.laple.model.datamodel;

import fr.laple.extensions.plugins.IPlugin;
import fr.laple.extensions.plugins.features.IFeaturePlugin;
import fr.laple.extensions.plugins.languages.ILanguagePlugin;
import fr.laple.model.exercises.answers.AbstractAnswerMode;
import fr.laple.model.exercises.exercisemode.IExerciseMode;
import fr.laple.model.language.SymbolContainer;
import fr.laple.model.lessons.AbstractLessonContainer;
import fr.laple.model.listable.IListable;
import fr.laple.view.exercises.ExerciseParameterizerView;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is the data model for the application
 * It contains all the data needed by the application
 *
 * @author anthonyrey
 */
public class LapleDataModel {

    private ILanguagePlugin languagePlugin;
    private List<IFeaturePlugin> features;
    private List<IPlugin> allDummyLanguagePlugins;

    /**
     * Constructor for LapleDataModel
     *
     * @param languagePlugin A language plugin
     * @param features A list of features in config file
     * @param allDummyLanguagePlugins A list of languages plugin in the config file
     */
    public LapleDataModel(ILanguagePlugin languagePlugin, List<IFeaturePlugin> features,
                          List<IPlugin> allDummyLanguagePlugins)
    {
        this.languagePlugin = languagePlugin;
        this.features = features;
        this.allDummyLanguagePlugins = allDummyLanguagePlugins;
    }

    public ArrayList<SymbolContainer> getSymbolContainer() {
        return languagePlugin.getSymbolContainer();
    }

    public String getVersion() {
        return languagePlugin.getVersion();
    }

    public ArrayList<IExerciseMode> getExercisesModes() {
        return languagePlugin.getExercisesModes();
    }

    public ArrayList<AbstractAnswerMode> getExercisesSolvingModes() {
        return languagePlugin.getExercisesSolvingModes();
    }

    public ArrayList<AbstractLessonContainer> getLessonContainers() {
        return languagePlugin.getLessonContainers();
    }

    public ArrayList<IListable> getExerciseTypes() {

        ArrayList<IListable> toReturn = new ArrayList<>();
        toReturn.add(new ExerciseParameterizerView(this));

        for(IFeaturePlugin fp : features)
        {
            toReturn.addAll(fp.getExerciseTypes());
        }

        return toReturn;

    }

    /**
     * Call add new tab for the feature plugins
     * @param ui
     */
    public void addNewTabs(JTabbedPane ui) {

        for(IFeaturePlugin fp : features)
        {
            fp.addNewTabs(ui, this);
        }
    }

    public List<IFeaturePlugin> getFeatures() {
        return features;
    }

    public List<IPlugin> getAllDummyLanguagePlugins() {
        return allDummyLanguagePlugins;
    }
}
