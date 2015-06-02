package fr.laple.extensions.languages.japanese;

import fr.laple.model.exercises.ExModeTranscriptLangUserLang;
import fr.laple.model.exercises.ExModeUserLangTranscriptLang;
import fr.laple.model.exercises.IExerciseMode;
import fr.laple.model.language.ILanguagePlugin;
import fr.laple.model.language.Symbol;
import fr.laple.model.language.SymbolContainer;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * This is the entry point for a Language plugin
 *
 * @author anthonyrey
 */
public class LapleLanguagePlugin implements ILanguagePlugin{

    private ArrayList<SymbolContainer> symbolContainers;
    private ArrayList<IExerciseMode> exerciseModes;

    public LapleLanguagePlugin() {

        loadSymbolContainers();
        loadLessons();
        populateExerciseModeList();
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
    public String getLanguageName() {
        return "Japanese";
    }

    private void populateExerciseModeList()
    {
        exerciseModes = new ArrayList<>();
        exerciseModes.add(new ExModeTranscriptLangUserLang());
        exerciseModes.add(new ExModeUserLangTranscriptLang());
    }

    private void loadSymbolContainers() {

        symbolContainers = new ArrayList<>();

        try{

            InputStream hiragana = getClass().getResourceAsStream("/fr/laple/extensions/languages/japanese/hiragana.json");
            JsonReader jsonReader = Json.createReader(hiragana);
            JsonObject jsonObject = jsonReader.readObject();
            jsonReader.close();
            hiragana.close();

            JsonArray jsonSymbols = jsonObject.getJsonArray("hiragana");
            SymbolContainer hiraganaContainer = new SymbolContainer("hiragana");
            symbolContainers.add(hiraganaContainer);

            for(int i = 0; i < jsonSymbols.size(); i++)
            {
                JsonObject current = jsonSymbols.getJsonObject(i);

                String userLangTranscript = current.getString("userLangTranscript");
                String gottenSymbol = current.getString("symbol");
                //need the others one

                Symbol symbol = new Symbol(userLangTranscript, gottenSymbol, null, null, null , null);
                hiraganaContainer.addSymbol(symbol);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void loadLessons() {

    }
}
