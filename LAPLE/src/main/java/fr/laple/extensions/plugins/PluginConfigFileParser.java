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
import java.util.Enumeration;
import java.util.List;
import java.util.jar.Attributes;
import java.util.jar.Manifest;

/**
 * This class is an abstract class managing the parsing of plugin config files
 *
 * @author anthonyrey
 */
public abstract class PluginConfigFileParser {

    /**
     * The type of the plugin
     */
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

    /**
     * Constructor for the PluginConfigFIleParser
     * The class is abstract so you need to instanciate a FeaturePluginConfigFileParser or
     * LanguagePluginConfigFileParser
     *
     * @see fr.laple.extensions.plugins.features.FeaturePluginConfigFileParser
     * @see fr.laple.extensions.plugins.languages.LanguagePluginConfigFileParser
     *
     * @param resourcePath The path to the resource folder
     * @param configFile The path to the config file
     * @param pluginType The plugin type
     * @throws PluginLoadingFatalException If there is any error
     */
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

    /**
     * Create the config file if it does not exist
     *
     * @throws IOException If there is any problem with the IO
     * @throws PluginLoadingFatalException If there is any other issue
     */
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

    /**
     * Add a plugin te the config file
     *
     * @param file The file of the plugin
     * @return The loaded plugin
     * @throws PluginLoadingException if non fatal error
     * @throws PluginLoadingFatalException if fatal error
     * @throws PluginTypeException if wrong type
     */
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


    /**
     * Remove the specified plugin
     *
     * @param plugin The plugin to remove
     * @throws PluginLoadingException  If could not remove
     */
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

    /**
     * Get the plugin from a dummy (or another plugin even if useless)
     *
     * @param chosen The dummy
     * @param withData Do you want the plugin with data or another dummy ?
     * @return The plugin
     * @throws PluginLoadingException
     * @throws PluginLoadingFatalException
     * @throws PluginTypeException If the type is wrong
     */
    public IPlugin getPlugin(IPlugin chosen, boolean withData) throws PluginLoadingException,
            PluginLoadingFatalException, PluginTypeException {

        //with data = get the full class instance, otherwize only get superficial data
        //if internal, we get the name of the plugin : class from the LAPLE.jar manifest
        IPlugin toReturn = null;

        if(chosen.isInternal())
        {
            //if internal not loading == fatal error

            try {

                URLClassLoader cl = (URLClassLoader) getClass().getClassLoader();

                Enumeration<URL> urls = cl.getResources("META-INF/MANIFEST.MF");
                //Looking into all manifest ...
                /*
                    this is a tweak for it to work both on intellij and jar
                    We make a dummy manifest mf in intellij and the real one is generated by maven
                 */
                while(urls.hasMoreElements())
                {
                    Manifest manifest = new Manifest(urls.nextElement().openStream());
                    String packageRef= manifest.getMainAttributes().getValue(chosen.getName());

                    if(packageRef != null)
                    {
                        Class clazz = cl.loadClass(manifest.getMainAttributes().getValue(chosen.getName()));
                        toReturn = (IPlugin) clazz.newInstance();

                        testPluginType(toReturn);

                        if(!withData)
                            toReturn = getWithoutDataPlugin(toReturn);

                        break;
                    }

                }

                if(toReturn == null)
                {
                    throw new PluginLoadingFatalException(chosen.getName(),
                            "Could not find "+chosen.getName()+" value in manifest.mf");
                }


            } catch (ClassNotFoundException | ClassCastException | InstantiationException | IllegalAccessException e) {
                //So the program end
                throw new PluginLoadingFatalException(chosen.getName(), e.getMessage() );
            } catch (IOException e) {
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

    /**
     * Testing the plugin type
     *
     * @param plugin The plugin to test
     * @throws PluginTypeException If the type is wrong
     */
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

    /**
     * Get a plugin without Data
     *
     * @param plugin The plugin we want to be stripped of it's data
     * @return A DummyPlugin (as an IPlugin)
     */
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

    /**
     * Get the dummies from the config file
     *
     * @return An arrayList of Dummy plugins (as IPlugin)
     * @throws PluginLoadingFatalException If there is an error
     */
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
