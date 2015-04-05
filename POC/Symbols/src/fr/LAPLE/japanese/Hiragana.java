package fr.LAPLE.japanese;

import fr.LAPLE.symbols.ISymbol;

import java.util.ArrayList;

/**
 * Created by anthonyrey on 05/04/2015.
 */
public class Hiragana implements ISymbol {

    private String userLangTranscript;
    private String studiedLangSymbol;
    private static final String type = "Hiragana";

    //Small test
    //We'll be using json files to load the symbols for the lang packages
    public Hiragana(){

        this.userLangTranscript = "A";
        this.studiedLangSymbol  = "あ";

    }

    @Override
    public String getUserLangTranscript() {
        return userLangTranscript;
    }

    @Override
    public ArrayList<String> getPronunciations() {
        return null;
    }

    @Override
    public String getStudiedLangSymbol() {
        return studiedLangSymbol;
    }

    @Override
    public String getStudiedSymbolImage() {
        return null;
    }

    @Override
    public String getType() {
        return type;
    }
}
