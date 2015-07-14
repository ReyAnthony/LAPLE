package fr.laple.extensions.languages.plugins;

import fr.laple.extensions.PluginLoadingException;
import fr.laple.extensions.languages.japanese.ParserException;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * Created by anthonyrey on 02/06/2015.
 */
public class PluginConfigObject {

    private String name;
    private String path;
    private String clazz;
    private Class plugin;

    public PluginConfigObject(String name, String path, String clazz) throws PluginLoadingException {

        this.name = name;
        this.path = path;
        this.clazz = clazz;
        loadClass();
    }

    private void loadClass() throws PluginLoadingException {


        if (name.isEmpty())
            throw new PluginLoadingException();

        File f = new File(path);

        if (!f.isFile()) {
            try {
                plugin = getClass().getClassLoader().loadClass(clazz);
            } catch (ClassNotFoundException e) {
                throw new PluginLoadingException();
            }
        } else {

            try {

                URL url = f.toURI().toURL();
                URL urls[] = {url};

                ClassLoader cl = new URLClassLoader(urls);
                this.plugin = cl.loadClass(clazz);

            } catch (ClassNotFoundException | MalformedURLException e1) {
                throw new PluginLoadingException();
            }

        }

    }

    public Class getPlugin() throws ParserException{
        return plugin;
    }

    public String getName() {
        return name;
    }
}
