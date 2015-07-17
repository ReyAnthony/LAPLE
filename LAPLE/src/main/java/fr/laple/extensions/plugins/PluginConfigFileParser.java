package fr.laple.extensions.plugins;

import fr.laple.extensions.plugins.features.IFeaturePlugin;
import fr.laple.extensions.plugins.languages.ILanguagePlugin;

import javax.json.*;
import java.io.*;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.Attributes;

/**
 * Created by anthonyrey on 15/07/2015.
 */
public abstract class PluginConfigFileParser {

    public enum PluginType
    {
        LANGUAGE_PLUGIN,
        FEATURE_PLUGIN
    }

    protected String resourcePath;
    protected String configFile;

    protected String userPath;
    protected String userPathNofile;
    protected String fullUserPath;
    protected String fullJarPath;

    protected PluginType pluginType;

    public PluginConfigFileParser(String resourcePath, String configFile, PluginType pluginType) throws PluginLoadingFatalException {

        this.resourcePath = resourcePath;
        this.configFile = configFile;

        userPath = System.getProperty("user.home")+"/.laple";
        userPathNofile = userPath + resourcePath;
        fullUserPath = userPath + resourcePath + configFile;
        fullJarPath =  resourcePath + configFile;

        this.pluginType = pluginType;

        //Will create config files if they do not exist
        try{

            createFileIfNotExist();
        }catch (IOException e)
        {
            throw new PluginLoadingFatalException(configFile, e.getMessage());
        }
    }

    private void createFileIfNotExist() throws IOException, PluginLoadingFatalException {

        File f = new File(fullUserPath);
        if(!f.exists())
        {
            if(!new File(userPathNofile).exists())
            {
                if(!new File(userPathNofile).mkdirs())
                    throw new PluginLoadingFatalException(configFile, "Could not create "+userPathNofile);
            }

            Files.copy(this.getClass().getResourceAsStream(fullJarPath), Paths.get(new File(fullUserPath).toURI()));

        }
    }


    public IPlugin addPlugin(File file) throws PluginLoadingException, PluginLoadingFatalException, PluginTypeException {

        //right now if there is any issue, we throw an exception
        String name = file.getName();
        IPlugin plugin = new DummyPlugin(name, file, false);
        plugin = getPlugin(plugin, false);

        try (InputStream configFile = new FileInputStream(fullUserPath)) {

            List<String> nameList = new ArrayList<>();
            boolean alreadyExist = false;

            JsonReader jsonReader = Json.createReader(configFile);
            JsonObject jsonObject = jsonReader.readObject();
            jsonReader.close();

            try (JsonWriter jsonWriter = Json.createWriter(new FileOutputStream(fullUserPath))) {

                JsonObjectBuilder out = Json.createObjectBuilder();
                JsonArrayBuilder array = Json.createArrayBuilder();

                JsonArray jsonSymbols = jsonObject.getJsonArray("plugins");

                for (int i = 0; i < jsonSymbols.size(); i++) {
                    JsonObject current = jsonSymbols.getJsonObject(i);

                    String localPath = current.getString("path");
                    String localName = current.getString("name");
                    boolean internal = current.getBoolean("internal");

                    array.add(Json.createObjectBuilder().add("path",
                            localPath).add("name", localName).add("internal", internal));

                    nameList.add(localName);

                }

                if(nameList.contains(plugin.getName()))
                {
                    alreadyExist = true;
                }
                else
                {
                    array.add(Json.createObjectBuilder().add("path",
                            plugin.getPath().getPath()).add("name", plugin.getName()).add("internal", false));
                }

                out.add("plugins", array);
                jsonWriter.writeObject(out.build());

                if(alreadyExist)
                    throw new PluginLoadingException(plugin.getName(), "This plugin already exist !");
            }

            return plugin;
        }
        catch(IOException e)
        {
            throw new PluginLoadingFatalException("Unknown", e.getMessage());
        }
    }



    public void removePlugin(IPlugin plugin) throws PluginLoadingException {

        try {
            InputStream configFile = new FileInputStream(fullUserPath);

            JsonReader jsonReader = Json.createReader(configFile);
            JsonObject jsonObject = jsonReader.readObject();
            jsonReader.close();

            JsonWriter jsonWriter = Json.createWriter(new FileOutputStream(fullUserPath));

            JsonObjectBuilder out = Json.createObjectBuilder();
            JsonArrayBuilder array = Json.createArrayBuilder();

            JsonArray jsonSymbols = jsonObject.getJsonArray("plugins");

            for (int i = 0; i < jsonSymbols.size(); i++) {
                JsonObject current = jsonSymbols.getJsonObject(i);

                String path = current.getString("path");
                String name = current.getString("name");
                boolean internal = current.getBoolean("internal");

                if(!plugin.getPath().equals(path) && !plugin.getName().equals(name))
                {
                    array.add(Json.createObjectBuilder().add("path",
                            path).add("name", name).add("internal", internal));
                }
            }

            out.add("plugins", array);
            jsonWriter.writeObject(out.build());

            configFile.close();
            jsonWriter.close();
        }
        catch(Exception e)
        {
            throw new PluginLoadingException(plugin.getName() ,e.getMessage());
        }
    }

    public IPlugin getPlugin(IPlugin chosen, boolean withData) throws PluginLoadingException,
            PluginLoadingFatalException, PluginTypeException {

        //with data = get the full class instance, otherwize only get superficial data
        //if internal, we get the name of the plugin : class from the LAPLE.jar manifest
        IPlugin toReturn;

        if(chosen.isInternal())
        {
            //if internal not loading == fatal error

            try {

                URLClassLoader cl = (URLClassLoader) getClass().getClassLoader();

                //URL url = cl.findResource("/META-INF/MANIFEST.MF");
                //Manifest manifest = new Manifest(url.openStream());
                Class clazz = cl.loadClass("fr.laple.extensions.plugins.languages.japanese.JapaneseLanguagePlugin");

                //Class clazz = cl.loadClass(manifest.getMainAttributes().getValue(chosen.getName()));
                toReturn = (IPlugin) clazz.newInstance();

                testPluginType(toReturn);

                if(!withData)
                    toReturn = getWithoutDataPlugin(toReturn);

                //internal = no set path

            } catch (ClassNotFoundException | ClassCastException | InstantiationException | IllegalAccessException e) {
                //So the program end
                throw new PluginLoadingFatalException(chosen.getName(), e.getMessage() );
            }

        }
        else
        {

            try {
                URL url = new URL("jar", "",  chosen.getPath().toURI().toURL()+"!/");
                JarURLConnection uc = (JarURLConnection)url.openConnection();
                URLClassLoader cl = new URLClassLoader(new URL[]{url});
                Class clazz = cl.loadClass(uc.getMainAttributes().getValue(Attributes.Name.MAIN_CLASS));

                toReturn = (IPlugin) clazz.newInstance();
                toReturn.setPath(chosen.getPath());

                testPluginType(toReturn);

                if(!withData)
                    toReturn = getWithoutDataPlugin(toReturn);

            } catch (ClassNotFoundException | ClassCastException | IllegalAccessException | IOException | InstantiationException e) {
                //we continue and return the usual data
                throw new PluginLoadingException(chosen.getName() , e.getMessage());
            }
        }

        return toReturn;
    }

    private void testPluginType(IPlugin plugin) throws PluginTypeException {


        if(pluginType == PluginType.LANGUAGE_PLUGIN)
        {
            if(!(plugin instanceof ILanguagePlugin))
                throw new PluginTypeException(plugin.getName());
        }
        else if(pluginType == PluginType.FEATURE_PLUGIN)
        {
            if(!(plugin instanceof IFeaturePlugin))
                 throw new PluginTypeException(plugin.getName());
        }

    }

    private IPlugin getWithoutDataPlugin(IPlugin plugin)
    {
        String name = plugin.getName();
        File path = plugin.getPath();
        boolean isInternal = plugin.isInternal();
        String version = plugin.getVersion();
        String desc = plugin.getDescription();
        String dev = plugin.getDeveloper();
        String otherCredits = plugin.otherCredits();

        return new DummyPlugin(name, path, isInternal, desc, dev, version, otherCredits);

    }

    public ArrayList<IPlugin> getDummies() throws PluginLoadingFatalException {

        ArrayList<IPlugin> plugins = new ArrayList<>();

        try {
            InputStream configFile = new FileInputStream(fullUserPath);

            JsonReader jsonReader = Json.createReader(configFile);
            JsonObject jsonObject = jsonReader.readObject();
            jsonReader.close();

            JsonArray jsonSymbols = jsonObject.getJsonArray("plugins");

            for (int i = 0; i < jsonSymbols.size(); i++) {

                JsonObject current = jsonSymbols.getJsonObject(i);
                String path = current.getString("path");
                String name = current.getString("name");
                boolean isInternal = current.getBoolean("internal");

                IPlugin dummies = new DummyPlugin(name, new File(path), isInternal);
                plugins.add(dummies);
            }
        }
        catch(Exception e)
        {
            throw new PluginLoadingFatalException("Dummy", e.getMessage());
        }

        return plugins;
    }
}
