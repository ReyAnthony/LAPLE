package fr.laple.extensions.languages.japanese;

import fr.laple.model.language.Symbol;
import fr.laple.model.language.SymbolContainer;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by anthonyrey on 04/06/2015.
 */
public class LanguageDictionnaryJsonParser {

    public SymbolContainer parseFile(String path) throws ParserExeption {
        SymbolContainer container = null;

        try(InputStream file = getClass().getResourceAsStream(path)){

            JsonReader jsonReader = Json.createReader(file);
            JsonObject rootObject = jsonReader.readObject();

            //TODO yaaay ugly code
            ArrayList<String> keys = new ArrayList<>(rootObject.keySet());
            String rootElem = keys.get(0);

            JsonArray root = rootObject.getJsonArray(rootElem);
            container = new SymbolContainer(rootElem);

            for(int i = 0; i < root.size(); i++)
            {
                JsonObject current = root.getJsonObject(i);
                String userLangTranscript = current .getString("userLangTranscript");
                String gottenSymbol = current.getString("symbol");
                //need the others one

                Symbol symbol = new Symbol(userLangTranscript, gottenSymbol, null, null, null , null);
                container.addSymbol(symbol);
            }


        }
        catch(Exception e)
        {
            throw new ParserExeption(path);
        }

        return container;
    }

}
