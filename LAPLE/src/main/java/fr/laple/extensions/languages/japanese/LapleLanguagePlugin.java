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
import fr.laple.model.lessons.SymbolLessonContainer;

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
    private ArrayList<SymbolLessonContainer> symbolLessonContainers;

    public LapleLanguagePlugin() throws ParserExeption {

        loadSymbolContainers();
        loadLessons();
        populateExerciseModeList();
        populateExerciseSolvingModesList();
    }

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
    public ArrayList<SymbolLessonContainer> getSymbolLessonContainers() {
        return symbolLessonContainers;
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

    private void loadSymbolContainers() throws ParserExeption  {

        symbolContainers = new ArrayList<>();
        LanguageDictionnaryJsonParser parser  = new LanguageDictionnaryJsonParser();
        symbolContainers.add(parser.parseFile("/fr/laple/extensions/languages/japanese/hiragana.json"));
        symbolContainers.add(parser.parseFile("/fr/laple/extensions/languages/japanese/katakana.json"));
        symbolContainers.add(parser.parseFile("/fr/laple/extensions/languages/japanese/kanji.json"));
        //TODO kanji

    }

    private void loadLessons() throws ParserExeption {
        LessonsJsonParser parser = new LessonsJsonParser(symbolContainers);

       symbolLessonContainers = parser.parseForSymbolLessons("/fr/laple/extensions/languages/japanese/lessons.json");
         parser.parseForWordLessons("/fr/laple/extensions/languages/japanese/lessons.json");
        System.out.println("test");
    }

}
