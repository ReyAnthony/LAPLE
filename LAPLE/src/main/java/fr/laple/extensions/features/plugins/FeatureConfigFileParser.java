package fr.laple.extensions.features.plugins;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Created by anthonyrey on 01/06/2015.
 */
public class FeatureConfigFileParser {

    private ArrayList<PluginDataObject> plugins;

    private static final String USER_PATH =  System.getProperty("user.home")+"/.laple";
    private static final String RESOURCE_PATH = "/fr/laple/extensions/features/";
    private static final String USER_PATH_NOFILE = USER_PATH+RESOURCE_PATH;
    private static final String CONFIG_FILE = "feature_plugins.json";
    private static final String FULL_USER_PATH = USER_PATH+ RESOURCE_PATH + CONFIG_FILE;
    private static final String FULL_JAR_PATH =  RESOURCE_PATH + CONFIG_FILE;

    public FeatureConfigFileParser() throws FeaturePluginLoadingException {

        try{

            createFileIfNotExist();
        }catch (IOException e)
        {
            throw new FeaturePluginLoadingException();
        }

        findPaths();

    }

    private void createFileIfNotExist() throws IOException, FeaturePluginLoadingException {

        File f = new File(FULL_USER_PATH);
        if(!f.exists())
        {
            if(!new File(USER_PATH_NOFILE).exists())
            {
                if(!new File(USER_PATH_NOFILE).mkdirs())
                    throw new FeaturePluginLoadingException();
            }

            Files.copy(this.getClass().getResourceAsStream(FULL_JAR_PATH), Paths.get(new File(FULL_USER_PATH).toURI()));

        }
    }

    private void findPaths() throws FeaturePluginLoadingException {

        try {
            plugins = new ArrayList<>();

            InputStream configFile = new FileInputStream(FULL_USER_PATH);

            JsonReader jsonReader = Json.createReader(configFile);
            JsonObject jsonObject = jsonReader.readObject();
            jsonReader.close();

            JsonArray jsonSymbols = jsonObject.getJsonArray("feature_plugins");

            for (int i = 0; i < jsonSymbols.size(); i++) {
                JsonObject current = jsonSymbols.getJsonObject(i);

                String path = current.getString("path");

                PluginDataObject pdo = new PluginDataObject(new File(path), current.getString("name"));
                plugins.add(pdo);
            }
        }
        catch(Exception e)
        {
            throw new FeaturePluginLoadingException();
        }

    }

    public ArrayList<PluginDataObject> getFiles()
    {
        return plugins;
    }

}
