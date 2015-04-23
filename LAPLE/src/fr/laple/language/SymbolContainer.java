package fr.laple.language;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by anthonyrey on 23/04/2015.
 */
public class SymbolContainer {

    private String type;
    private Map<String, Symbol> dictionnary;

    public SymbolContainer(String type)
    {

        type = type;
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



}
