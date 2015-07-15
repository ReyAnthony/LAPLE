package fr.laple.extensions.plugins.features;

import fr.laple.extensions.plugins.ConfigFileParser;
import fr.laple.extensions.plugins.PluginLoadingException;

/**
 * Created by anthonyrey on 01/06/2015.
 */
public class FeatureConfigFileParser extends ConfigFileParser {

    //At first I thought the 2 would be really different, but they are not
    public FeatureConfigFileParser() throws PluginLoadingException {

        super("/fr/laple/extensions/features/", "feature_plugins.json");
    }

}
