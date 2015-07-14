package fr.laple.extensions.features.plugins;

import fr.laple.extensions.languages.plugins.ILanguagePlugin;
import fr.laple.model.listable.IListable;
import fr.laple.model.listable.RootData;
import fr.laple.view.ListView;
import fr.laple.view.exercises.ExerciseParameterizer;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by anthonyrey on 14/07/2015.
 */
public class FeatureTest implements IFeaturePlugin {

    ArrayList<IListable> features;

    @Override
    public void instanciateExerciseModes(ILanguagePlugin model) {

        features = new ArrayList<>();
        features.add(new ExerciseParameterizer(model));
        features.add(new ExerciseParameterizer(model));
    }

    @Override
    public ArrayList<IListable> getExerciseTypes() {
        return features;
    }

    @Override
    public void addNewTabs(JTabbedPane ui, ILanguagePlugin model) {

        List<IListable> exoList = new ArrayList<>();
        exoList.add(new ExerciseParameterizer(model));
        exoList.add(new ExerciseParameterizer(model));

        ui.add("Plugin Test", new ListView(model, exoList, false, "Select an exercise mode :", new RootData(exoList,
                "Select an exercise mode :")));
    }
}
