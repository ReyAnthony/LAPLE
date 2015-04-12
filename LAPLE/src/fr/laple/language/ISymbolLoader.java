package fr.laple.language;

import java.io.File;

/**
 * This interface is to be implemented in order to define how a json file is to be read for container creation
 *
 * @author anthonyrey
 */
public interface ISymbolLoader {

    public void loadSymbols(File jsonFile, ISymbolContainer container);
}
