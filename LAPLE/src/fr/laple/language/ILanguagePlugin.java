package fr.laple.language;

/**
 * Created by anthonyrey on 11/04/2015.
 */
public interface ILanguagePlugin {

    public String getLanguageName();
    public ISymbolContainer getSymbolContainer();
    public String getVersion();

}
