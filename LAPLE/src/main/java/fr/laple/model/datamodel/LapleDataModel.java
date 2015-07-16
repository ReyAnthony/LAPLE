package fr.laple.model.datamodel;

import fr.laple.extensions.plugins.IPlugin;
import fr.laple.extensions.plugins.features.IFeaturePlugin;
import fr.laple.extensions.plugins.languages.ILanguagePlugin;
import fr.laple.model.exercises.answers.AbstractAnswerMode;
import fr.laple.model.exercises.exercisemode.IExerciseMode;
import fr.laple.model.language.SymbolContainer;
import fr.laple.model.listable.IListable;
import fr.laple.view.exercises.ExerciseParameterizer;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by anthonyrey on 14/07/2015.
 */
//TODO throw exceptions
public class LapleDataModel {

    private ILanguagePlugin languagePlugin;
    private List<IFeaturePlugin> features;
    private List<IPlugin> allDummyLanguagePlugins;

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

    public ArrayList<IListable> getLessonContainers() {
        return languagePlugin.getLessonContainers();
    }

    public ArrayList<IListable> getExerciseTypes() {

        ArrayList<IListable> toReturn = new ArrayList<>();
        toReturn.add(new ExerciseParameterizer(this));

        for(IFeaturePlugin fp : features)
        {
            toReturn.addAll(fp.getExerciseTypes());
        }

        return toReturn;

    }

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
