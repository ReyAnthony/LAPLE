package fr.laple.extensions.plugins.features;

import fr.laple.extensions.plugins.PluginConfigFileParser;
import fr.laple.extensions.plugins.PluginLoadingFatalException;

/**
 * Created by anthonyrey on 01/06/2015.
 */
public class FeaturePluginConfigFileParser extends PluginConfigFileParser {

    //At first I thought the 2 would be really different, but they are not
    public FeaturePluginConfigFileParser() throws PluginLoadingFatalException {

        super("/fr/laple/extensions/features/", "feature_plugins.json");
    }

}
