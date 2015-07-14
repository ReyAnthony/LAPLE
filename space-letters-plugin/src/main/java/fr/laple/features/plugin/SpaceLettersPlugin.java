package fr.laple.features.plugin;

import fr.laple.extensions.features.plugins.IFeaturePlugin;
import fr.laple.model.datamodel.LapleDataModel;
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
public class SpaceLettersPlugin implements IFeaturePlugin {
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

        List<IListable> exoList = new ArrayList<>();
        exoList.add(new ExerciseParameterizer(model));
        exoList.add(new ExerciseParameterizer(model));

        ui.add("MiniGame", new ListView(model, exoList, false, "Select an exercise mode :", new RootData(exoList,
                "Select an exercise mode :")));
    }
}
