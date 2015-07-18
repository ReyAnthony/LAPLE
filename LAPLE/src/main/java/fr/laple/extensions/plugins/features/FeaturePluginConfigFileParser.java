package fr.laple.extensions.plugins.features;

import fr.laple.extensions.plugins.PluginConfigFileParser;
import fr.laple.extensions.plugins.PluginLoadingFatalException;

/**
 * This class is just giving the right parameter for featurePlugins
 * (super("/fr/laple/extensions/features/", "feature_plugins.json", PluginType.FEATURE_PLUGIN))
 *
 * @see fr.laple.extensions.plugins.PluginConfigFileParser
 *
 * @author anthonyrey
 */
public class FeaturePluginConfigFileParser extends PluginConfigFileParser {

    //At first I thought the 2 would be really different, but they are not
    public FeaturePluginConfigFileParser() throws PluginLoadingFatalException {

        super("/fr/laple/extensions/features/", "feature_plugins.json", PluginType.FEATURE_PLUGIN);
    }

}
