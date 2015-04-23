package fr.laple.lang.jp;

import fr.laple.language.ILanguagePlugin;
import fr.laple.language.Symbol;
import fr.laple.language.SymbolContainer;

import java.io.File;
import java.util.ArrayList;

/**
 * This is the entry point for a Language plugin
 *
 * @author anthonyrey
 */
public class LapleLanguagePlugin implements ILanguagePlugin{

    private ArrayList<SymbolContainer> symbolContainers;

    @Override
    public String getLanguageName() {
        return "Japanese";
    }

    @Override
    public void loadSymbolContainers(File json) {

        //should load json file
        //but we'll do it by hand for testing purpose

        SymbolContainer currentContainer;
        currentContainer = new SymbolContainer("Hiragana");

        Symbol s = new Symbol("a", "あ", null, null, null, null);
        currentContainer.addSymbol(s);

        s = new Symbol("i", "い", null, null, null, null);
        currentContainer.addSymbol(s);

        s = new Symbol("u", "う", null, null, null, null);
        currentContainer.addSymbol(s);

        s = new Symbol("e", "え", null, null, null, null);
        currentContainer.addSymbol(s);

        s = new Symbol("o", "お", null, null, null, null);
        currentContainer.addSymbol(s);

        symbolContainers = new ArrayList<>();
        symbolContainers.add(currentContainer);
    }

    @Override
    public void loadLessons(File json) {

    }

    @Override
    public ArrayList<SymbolContainer> getSymbolContainer() {
        return symbolContainers;
    }

    @Override
    public String getVersion() {
        return "1.0";
    }
}
