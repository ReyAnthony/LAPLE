package fr.laple.language;

import java.io.File;

/**
 * Created by anthonyrey on 11/04/2015.
 */
public interface ISymbolLoader {

    public void loadSymbols(File jsonFile, ISymbolContainer container);
}
