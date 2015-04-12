package fr.laple.language;

/**
 * This interface is to be implemented by a LapleLanguagePlugin class. It will ensure that the software can have the needed informations from the language plugin
 *
 * @author anthonyrey
 *
 */
public interface ILanguagePlugin {

    public String getLanguageName();
    public ISymbolContainer getSymbolContainer();
    public String getVersion();

}
