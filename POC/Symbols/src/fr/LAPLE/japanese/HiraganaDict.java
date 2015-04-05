package fr.LAPLE.japanese;

import fr.LAPLE.symbols.ISymbol;
import fr.LAPLE.symbols.ISymbolContainer;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by anthonyrey on 05/04/2015.
 */
public class HiraganaDict implements ISymbolContainer {

    HashMap<String, ISymbol> dictionary;

    public HiraganaDict()
    {
        dictionary = new HashMap<String, ISymbol>();
    }

    @Override
    public Map<String, ISymbol> getSymbolMap() {
        return dictionary;
    }

    @Override
    public ISymbol getSymbol(String key) {

       return dictionary.get(key);
    }

    @Override
    public void addSymbol(ISymbol symbol) {

        dictionary.put(symbol.getUserLangTranscript(), symbol);
    }
}
