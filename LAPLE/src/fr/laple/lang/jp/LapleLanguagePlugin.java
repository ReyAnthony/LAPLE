package fr.laple.lang.jp;

import fr.laple.language.ILanguagePlugin;
import fr.laple.language.ISymbolContainer;

/**
 * Created by anthonyrey on 11/04/2015.
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
