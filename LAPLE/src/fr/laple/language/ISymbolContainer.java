package fr.laple.language;

import java.util.Map;

/**
 * Created by anthonyrey on 11/04/2015.
 */
public interface ISymbolContainer {

    public Map<String, ISymbol> getSynbolMap();
    public ISymbol getSymbol();
    public void addSymbol();
    public String getType();

}
