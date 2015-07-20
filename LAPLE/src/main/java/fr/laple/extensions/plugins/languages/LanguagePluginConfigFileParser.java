package fr.laple.extensions.plugins.languages;

import fr.laple.extensions.plugins.PluginConfigFileParser;
import fr.laple.extensions.plugins.PluginLoadingFatalException;

/**
 * This class is just giving the right parameter for LanguagePlugins
 * (  super("/fr/laple/extensions/languages/", "language_plugins.json", PluginType.LANGUAGE_PLUGIN))
 *
 * @see fr.laple.extensions.plugins.PluginConfigFileParser
 *
 * @author anthonyrey
 */
public class LanguagePluginConfigFileParser extends PluginConfigFileParser {
    public LanguagePluginConfigFileParser() throws PluginLoadingFatalException {
        super("/fr/laple/extensions/languages/", "language_plugins.json", PluginType.LANGUAGE_PLUGIN);
    }
}
