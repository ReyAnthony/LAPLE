package fr.laple.model.language;

import java.util.HashMap;
import java.util.Map;

/**
 * A SymbolContainer is composed of symbols and has methods to retrieve and add symbols to it
 *
 * @author anthonyrey
 */
public class SymbolContainer {

    private String type;
    private Map<String, Symbol> dictionnary;

    /**
     * Constructor for the symbol container
     *
     * @param type The type of the symbol container (to ditch ?)
     */
    public SymbolContainer(String type)
    {

        this.type = type;
        dictionnary = new HashMap<>();
        //on le popule

    }

    public Map<String, Symbol> getSymbolMap()
    {
        return dictionnary;
    }

    public Symbol getSymbol(String key)
    {
        return dictionnary.get(key);

    }

    public void addSymbol(Symbol s)
    {
        //key = english and not japanese symbol
        //should change that maybe ??
       dictionnary.put(s.getUserLangTranscript(), s);
    }

    public String getType() {
        return type;
    }

    public String toString() {
        return this.getType();
    }
}
