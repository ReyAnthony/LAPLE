package fr.laple.lang.jp;

import fr.laple.language.ILanguagePlugin;
import fr.laple.language.ISymbolContainer;

/**
 * This is the entry point for a Language plugin
 *
 * @author anthonyrey
 */
public class LapleLanguagePlugin implements ILanguagePlugin{

    @Override
    public String getLanguageName() {
        return "Japanese";
    }

    @Override
    public ISymbolContainer getSymbolContainer() {
        return null;
    }

    @Override
    public String getVersion() {
        return "1.0";
    }
}
