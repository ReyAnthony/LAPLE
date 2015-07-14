package fr.laple.extensions.features.plugins;

import javax.swing.*;
import java.io.File;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.Attributes;

/**
 * Created by anthonyrey on 14/07/2015.
 */
public class FeaturePluginLoader {

    private List<IFeaturePlugin> featurePlugin;

    public FeaturePluginLoader() throws FeaturePluginLoadingException {
        //TODO test
        JFileChooser chooser = new JFileChooser();
        featurePlugin = new ArrayList<>();

        chooser.showOpenDialog(null);

        File chosen = chooser.getSelectedFile();
        loadPlugin(chosen);


    }

    private void loadPlugin(File chosen) throws FeaturePluginLoadingException
    {
        try {

            URL url = new URL("jar", "",  chosen.toURI().toURL()+"!/");
            JarURLConnection uc = (JarURLConnection)url.openConnection();
            URLClassLoader cl = new URLClassLoader(new URL[]{url});
            Class clazz = cl.loadClass(uc.getMainAttributes().getValue(Attributes.Name.MAIN_CLASS));
            featurePlugin.add((IFeaturePlugin) clazz.newInstance());

        } catch (Exception e) {
            throw new FeaturePluginLoadingException();
            //Those are not fatal, we just ignore the plugin and send a message
        }

    }

    public List<IFeaturePlugin> getLoadedPlugin()
    {
        return featurePlugin;
    }

}
