package fr.laple.extensions.plugins.languages.russian;

import fr.laple.extensions.plugins.Plugins;
import fr.laple.extensions.plugins.languages.ILanguagePlugin;
import fr.laple.extensions.plugins.languages.LanguageDictionnaryJsonParser;
import fr.laple.extensions.plugins.languages.LessonsJsonParser;
import fr.laple.extensions.plugins.languages.ParserException;
import fr.laple.model.exercises.answers.AbstractAnswerMode;
import fr.laple.model.exercises.answers.DrawingMode;
import fr.laple.model.exercises.answers.FreeInputMode;
import fr.laple.model.exercises.answers.QcmMode;
import fr.laple.model.exercises.exercisemode.ExModeTranscriptLangUserLang;
import fr.laple.model.exercises.exercisemode.ExModeUserLangTranscriptLang;
import fr.laple.model.exercises.exercisemode.IExerciseMode;
import fr.laple.model.language.SymbolContainer;
import fr.laple.model.lessons.AbstractLessonContainer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by anthonyrey on 21/07/2015.
 */
public class RussianLanguagePlugin implements ILanguagePlugin {
    private ArrayList<SymbolContainer> symbolContainers;
    private ArrayList<IExerciseMode> exerciseModes;
    private ArrayList<AbstractAnswerMode> exerciseSolvingModes;
    private ArrayList<AbstractLessonContainer> lessonContainers;

    /**
     * Constructor for the plugin :
     *
     *  loadSymbolContainers();
     *  loadLessons();
     *  populateExerciseModeList();
     *  populateExerciseSolvingModesList();
     *
     *  if there is any issue message then quit
     *
     *  @see fr.laple.extensions.plugins.Plugins
     *  @see fr.laple.model.language.SymbolContainer
     *  @see fr.laple.model.lessons.Lesson
     *
     */
    public RussianLanguagePlugin()  {

        try {

            loadSymbolContainers();
            loadLessons();
            populateExerciseModeList();
            populateExerciseSolvingModesList();

        } catch (ParserException e) {

            e.printStackTrace();
            Plugins.pluginError(e.getMessage());
        }


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
        return "Valentin REY";
    }

    @Override
    public File getPath() {
        //ok now that's pure tweaking ...
        return new File("Internal to LAPLE");
    }

    @Override
    public void setPath(File path) {
        //unused
    }

    @Override
    public boolean isInternal() {
        return true;
    }

    @Override
    public String getName() {
        return "Russian";
    }

    @Override
    public String getDescription() {
        return "Russian language plugin";
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
    public ArrayList<AbstractLessonContainer> getLessonContainers() {
        return lessonContainers;
    }

    /**
     * Populate the list of solving modes
     *
     * @see fr.laple.model.exercises.answers.AbstractAnswerMode
     * @see fr.laple.model.exercises.answers.QcmMode
     * @see fr.laple.model.exercises.answers.DrawingMode
     * @see fr.laple.model.exercises.answers.FreeInputMode
     *
     */
    private void populateExerciseSolvingModesList()
    {
        exerciseSolvingModes = new ArrayList<>();
        exerciseSolvingModes.add(new QcmMode());
        exerciseSolvingModes.add(new DrawingMode());
        exerciseSolvingModes.add(new FreeInputMode());

    }

    /**
     * Populate the list of Exercises Mode
     *
     * @see fr.laple.model.exercises.exercisemode.IExerciseMode
     * @see fr.laple.model.exercises.exercisemode.ExModeTranscriptLangUserLang
     * @see fr.laple.model.exercises.exercisemode.ExModeUserLangTranscriptLang
     */
    private void populateExerciseModeList()
    {
        exerciseModes = new ArrayList<>();
        exerciseModes.add(new ExModeTranscriptLangUserLang());
        exerciseModes.add(new ExModeUserLangTranscriptLang());
    }


    private void loadSymbolContainers() throws ParserException {

        symbolContainers = new ArrayList<>();
        LanguageDictionnaryJsonParser parser  = new LanguageDictionnaryJsonParser();
        symbolContainers.add(parser.parseFile("/fr/laple/extensions/languages/russian/alphabet_lowercase.json",
                "/fr/laple/extensions/languages/russian/sounds/"));
        symbolContainers.add(parser.parseFile("/fr/laple/extensions/languages/russian/words.json",
                "/fr/laple/extensions/languages/russian/sounds/"));

    }


    private void loadLessons() throws ParserException {

        LessonsJsonParser parser = new LessonsJsonParser(symbolContainers);
        List<String> acceptedValues = new ArrayList<>();
        acceptedValues.add("alphabet_lowercase");
         lessonContainers = parser.parseForSymbolLessons("/fr/laple/extensions/languages/russian/lessons.json", acceptedValues);
         lessonContainers.add(parser.parseForWordLessons("/fr/laple/extensions/languages/russian/lessons.json", "words"));

    }

    /**
     * @return The name of the plugin
     */
    public String toString()
    {
        return this.getName();
    }
}
