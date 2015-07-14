package fr.laple.extensions.languages.plugins;

import fr.laple.extensions.PluginLoadingException;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by anthonyrey on 01/06/2015.
 */
public class LanguageConfigFileParser {

    private ArrayList<PluginConfigObject> plugins;

    public LanguageConfigFileParser() throws PluginLoadingException {

        try {
            plugins = new ArrayList<>();

            InputStream configFile = getClass().getResourceAsStream("/fr/laple/extensions/languages/language_plugins.json");
            JsonReader jsonReader = Json.createReader(configFile);
            JsonObject jsonObject = jsonReader.readObject();
            jsonReader.close();

            JsonArray jsonSymbols = jsonObject.getJsonArray("lang_plugins");

            for (int i = 0; i < jsonSymbols.size(); i++) {
                JsonObject current = jsonSymbols.getJsonObject(i);

                String name = current.getString("name");
                String path = current.getString("path");
                String clazz = current.getString("class");

                PluginConfigObject pco = new PluginConfigObject(name, path, clazz);
                plugins.add(pco);
            }
        }
        catch(Exception e)
        {
            throw new PluginLoadingException();
        }

    }
    public ArrayList<PluginConfigObject> getLanguagePluginsList()
    {
        return plugins;
    }

}
