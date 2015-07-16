package fr.laple.extensions.plugins.features;

import fr.laple.extensions.plugins.ConfigFileParser;
import fr.laple.extensions.plugins.PluginLoadingFatalException;

/**
 * Created by anthonyrey on 01/06/2015.
 */
public class FeatureConfigFileParser extends ConfigFileParser {

    //At first I thought the 2 would be really different, but they are not
    public FeatureConfigFileParser() throws PluginLoadingFatalException {

        super("/fr/laple/extensions/features/", "feature_plugins.json");
    }

}
