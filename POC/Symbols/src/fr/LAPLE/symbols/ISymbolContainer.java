package fr.LAPLE.symbols;

import java.util.Map;

/**
 * Created by anthonyrey on 05/04/2015.
 */
public interface ISymbolContainer {

    /**
     * Used to get the whole symbol map
     * The choice of the Map is given to the programmer as Map is abstract
     *
     * @return A Map of ISymbol with String keys
     */
    public Map<String, ISymbol> getSymbolMap();

    /**
     * Used to retrieve one ISymbol only
     *
     * @param key  The key to the required symbol
     * @return The requested ISymbol
     */
    public ISymbol getSymbol(String key);

    /**
     * Used to add a symbol into the SymbolContainer
     * The userLangTranscript will be used as a key
     *
     * @param symbol The symbol to add
     */
    public void addSymbol(ISymbol symbol);

}
