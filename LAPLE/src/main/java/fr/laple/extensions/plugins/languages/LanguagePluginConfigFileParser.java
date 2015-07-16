package fr.laple.extensions.plugins.languages;

import fr.laple.extensions.plugins.PluginConfigFileParser;
import fr.laple.extensions.plugins.PluginLoadingFatalException;

/**
 * Created by anthonyrey on 16/07/2015.
 */
public class LanguagePluginConfigFileParser extends PluginConfigFileParser {
    public LanguagePluginConfigFileParser() throws PluginLoadingFatalException {
        super("/fr/laple/extensions/languages/", "language_plugins.json");
    }
}
