package fr.laple.extensions.languages.plugins;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Created by anthonyrey on 01/06/2015.
 */
public class LanguageConfigFileParser {

    private ArrayList<PluginConfigObject> plugins;

    private static final String USER_PATH =  System.getProperty("user.home")+"/.laple";
    private static final String RESOURCE_PATH = "/fr/laple/extensions/languages/";
    private static final String USER_PATH_NOFILE = USER_PATH+RESOURCE_PATH;
    private static final String CONFIG_FILE = "language_plugins.json";
    private static final String FULL_USER_PATH = USER_PATH+ RESOURCE_PATH + CONFIG_FILE;
    private static final String FULL_JAR_PATH =  RESOURCE_PATH + CONFIG_FILE;

    public LanguageConfigFileParser() throws LangPluginLoadingException {

        try{

            createFileIfNotExist();
        }catch (IOException | URISyntaxException e)
        {
            throw new LangPluginLoadingException();
        }

        parseFiles();

    }

    private void createFileIfNotExist() throws IOException, LangPluginLoadingException, URISyntaxException {

        if(!new File(FULL_USER_PATH).exists())
        {
            if(!new File(USER_PATH_NOFILE).exists())
            {
               if(!new File(USER_PATH_NOFILE).mkdirs())
                    throw new LangPluginLoadingException();
            }

            Files.copy(this.getClass().getResourceAsStream(FULL_JAR_PATH), Paths.get(new File(FULL_USER_PATH).toURI()));

        }
    }

    private void parseFiles() throws LangPluginLoadingException {
        try {
            plugins = new ArrayList<>();

            InputStream configFile = new FileInputStream(FULL_USER_PATH);
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
            e.printStackTrace();
            throw new LangPluginLoadingException();
        }


    }
    public ArrayList<PluginConfigObject> getLanguagePluginsList()
    {
        return plugins;
    }

}
