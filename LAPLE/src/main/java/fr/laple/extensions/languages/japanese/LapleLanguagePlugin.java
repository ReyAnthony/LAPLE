package fr.laple.extensions.languages.japanese;


import fr.laple.model.exercises.ExModeTranscriptLangUserLang;
import fr.laple.model.exercises.ExModeUserLangTranscriptLang;
import fr.laple.model.exercises.IExerciseMode;
import fr.laple.model.exercises.answers.AbstractAnswerMode;
import fr.laple.model.exercises.answers.DrawingMode;
import fr.laple.model.exercises.answers.FreeInputMode;
import fr.laple.model.exercises.answers.QcmMode;
import fr.laple.model.language.ILanguagePlugin;
import fr.laple.model.language.SymbolContainer;

import java.util.ArrayList;

/**
 * This is the entry point for a Language plugin
 *
 * @author anthonyrey
 */
public class LapleLanguagePlugin implements ILanguagePlugin{

    private ArrayList<SymbolContainer> symbolContainers;
    private ArrayList<IExerciseMode> exerciseModes;
    private ArrayList<AbstractAnswerMode> exerciseSolvingModes;

    public LapleLanguagePlugin() {

        loadSymbolContainers();
        loadLessons();
        populateExerciseModeList();
        populateExerciseSolvingModesList();
    }

    //TODO Get by name
    @Override
    public ArrayList<SymbolContainer> getSymbolContainer() {
        return symbolContainers;
    }

    @Override
    public String getVersion() {
        return "1.0";
    }

    @Override
    public ArrayList<IExerciseMode> getExercisesModes() {
        return exerciseModes;
    }

    @Override
    public ArrayList<AbstractAnswerMode> getExercisesSolvingModes() {
        return exerciseSolvingModes;
    }

    @Override
    public String getLanguageName() {
        return "Japanese";
    }

    private void populateExerciseSolvingModesList()
    {
        exerciseSolvingModes = new ArrayList<>();
        exerciseSolvingModes.add(new QcmMode());
        exerciseSolvingModes.add(new DrawingMode());
        exerciseSolvingModes.add(new FreeInputMode());

    }

    private void populateExerciseModeList()
    {
        exerciseModes = new ArrayList<>();
        exerciseModes.add(new ExModeTranscriptLangUserLang());
        exerciseModes.add(new ExModeUserLangTranscriptLang());
    }

    private void loadSymbolContainers() {

        symbolContainers = new ArrayList<>();
        LanguageDictionnaryJsonParser parser  = new LanguageDictionnaryJsonParser();
        symbolContainers.add(parser.parseFile( getClass().getResourceAsStream("/fr/laple/extensions/languages/japanese/hiragana.json")));
        symbolContainers.add(parser.parseFile(getClass().getResourceAsStream("/fr/laple/extensions/languages/japanese/katakana.json")));

    }

    private void loadLessons() {

        //TODO nothing works, as expected ..
        /*
       try {
           InputStream lesson = getClass().getResourceAsStream("/fr/laple/extensions/languages/japanese/lessons.json");
           JsonReader read = Json.createReader(lesson);
           JsonObject jsonObject = read.readObject();

           read.close();
           lesson.close();

           JsonObject objetArray = jsonObject.getJsonObject("lesson_type").getJsonArray("katakana").getJsonObject(0);

           JsonArray jsona = objetArray.getJsonArray("learning_order");

           jsona.toString();
           String val= "";
           // get all the elements of the Array
           symbolContainers.add(new SymbolContainer("katakana"));
           for(int i = 0;i<jsona.size();i++){
               // i use variable for stock all the array for the test.
               val+=jsona.getString(i);
           }

           objetArray = jsonObject.getJsonObject("lesson_type").getJsonArray("hiragana").getJsonObject(0);
           jsona = objetArray.getJsonArray("learning_order");
           jsona.toString();
           String val2 = "";
           symbolContainers.add(new SymbolContainer("hiragana"));
           for(int i=0;i<jsona.size();i++) {
               // i use variable for stock all the array for the test.
               val2 += jsona.getString(i);
           }
           // Get The Kanji Party.
           objetArray = jsonObject.getJsonObject("lesson_type").getJsonObject("kanji").getJsonArray("list").getJsonObject(0);
           symbolContainers.add(new SymbolContainer("hiragana"));
           String kanjiName =  objetArray.getString("name");
           String kanjiSymbole = objetArray.getString("symbol");



       }catch(Exception e){
            System.out.println("erreur e "+e.getMessage());
       }
        */
    }

}
