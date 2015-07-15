package fr.laple.extensions.features.plugins;

import javax.swing.*;
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

        featurePlugin = new ArrayList<>();

        FeatureConfigFileParser cfp = new FeatureConfigFileParser();

        for(PluginDataObject f : cfp.getFiles())
        {
            loadPlugin(f);
        }
    }

    private void loadPlugin(PluginDataObject chosen) throws FeaturePluginLoadingException
    {
        try {

            URL url = new URL("jar", "",  chosen.getPath().toURI().toURL()+"!/");
            JarURLConnection uc = (JarURLConnection)url.openConnection();
            URLClassLoader cl = new URLClassLoader(new URL[]{url});
            Class clazz = cl.loadClass(uc.getMainAttributes().getValue(Attributes.Name.MAIN_CLASS));

            IFeaturePlugin fp = (IFeaturePlugin) clazz.newInstance();
            fp.setPath(chosen.getPathString());

            featurePlugin.add(fp);

        } catch (Exception e) {

            //If there is an error, we create a dummy plugin
            JOptionPane.showMessageDialog(null, "There was an error loading feature \" "+ chosen.getName() + " \" ",
                    "Error", JOptionPane.WARNING_MESSAGE  );

            DummyFeaturePlugin dp = new DummyFeaturePlugin(chosen.getName());
            dp.setPath(chosen.getPathString());
            featurePlugin.add(dp);

        }

    }

    public List<IFeaturePlugin> getLoadedPlugins()
    {
        return featurePlugin;
    }

}
