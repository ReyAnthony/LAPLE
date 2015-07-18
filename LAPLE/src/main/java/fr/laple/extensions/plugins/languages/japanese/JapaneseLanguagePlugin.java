package fr.laple.extensions.plugins.languages.japanese;


import fr.laple.model.listable.IListable;
import fr.laple.model.exercises.exercisemode.ExModeTranscriptLangUserLang;
import fr.laple.model.exercises.exercisemode.ExModeUserLangTranscriptLang;
import fr.laple.model.exercises.exercisemode.IExerciseMode;
import fr.laple.model.exercises.answers.AbstractAnswerMode;
import fr.laple.model.exercises.answers.DrawingMode;
import fr.laple.model.exercises.answers.FreeInputMode;
import fr.laple.model.exercises.answers.QcmMode;
import fr.laple.extensions.plugins.languages.ILanguagePlugin;
import fr.laple.model.language.SymbolContainer;

import java.io.File;
import java.util.ArrayList;

/**
 * This is the entry point for a Language plugin
 *
 * @author anthonyrey
 */
public class JapaneseLanguagePlugin implements ILanguagePlugin{

    private ArrayList<SymbolContainer> symbolContainers;
    private ArrayList<IExerciseMode> exerciseModes;
    private ArrayList<AbstractAnswerMode> exerciseSolvingModes;
    private ArrayList<IListable> lessonContainers;

    public JapaneseLanguagePlugin() throws ParserException {

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
    public String otherCredits() {
        return "Julien Arensma";
    }

    @Override
    public File getPath() {
        //ok now that's pure tweaking ...
        return new File("Internal to LAPLE");
    }

    @Override
    public void setPath(File path) {
        //no need
    }

    @Override
    public boolean isInternal() {
        return true;
    }

    @Override
    public String getName() {
        return "Japanese";
    }

    @Override
    public String getDescription() {
        return "Japanese language plugin";
    }

    @Override
    public String getDeveloper() {
        return "Anthony REY";
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
    public ArrayList<IListable> getLessonContainers() {
        return lessonContainers;
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

    private void loadSymbolContainers() throws ParserException {

        symbolContainers = new ArrayList<>();
        LanguageDictionnaryJsonParser parser  = new LanguageDictionnaryJsonParser();
        symbolContainers.add(parser.parseFile("/fr/laple/extensions/languages/japanese/hiragana.json"));
        symbolContainers.add(parser.parseFile("/fr/laple/extensions/languages/japanese/katakana.json"));
        symbolContainers.add(parser.parseFile("/fr/laple/extensions/languages/japanese/kanji.json"));

    }

    private void loadLessons() throws ParserException {

        LessonsJsonParser parser = new LessonsJsonParser(symbolContainers);
        lessonContainers = parser.parseForSymbolLessons("/fr/laple/extensions/languages/japanese/lessons.json");
        lessonContainers.add(parser.parseForWordLessons("/fr/laple/extensions/languages/japanese/lessons.json"));

    }

    public String toString()
    {
        return this.getName();
    }


}
