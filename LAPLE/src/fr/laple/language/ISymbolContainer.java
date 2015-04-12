package fr.laple.language;

import java.util.Map;

/**
 * This interface define the minimal elements for creating a symbol container
 *
 * @author anthonyrey
 */
public interface ISymbolContainer {

    public Map<String, ISymbol> getSynbolMap();
    public ISymbol getSymbol();
    public void addSymbol();
    public String getType();

}
