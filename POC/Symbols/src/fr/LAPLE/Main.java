package fr.LAPLE;

import fr.LAPLE.japanese.Hiragana;
import fr.LAPLE.japanese.HiraganaDict;
import fr.LAPLE.symbols.ISymbol;
import fr.LAPLE.symbols.ISymbolContainer;

public class Main {

    public static void main(String[] args) {

        ISymbol a = new Hiragana("A", "あ");
        System.out.println(a.getStudiedLangSymbol());
        System.out.println(a.getUserLangTranscript());

        ISymbolContainer hiraganaDict = new HiraganaDict();
        hiraganaDict.addSymbol(a);

        System.out.println(hiraganaDict.getSymbol("A").getUserLangTranscript());
        System.out.println(hiraganaDict.getSymbol("A").getStudiedLangSymbol());

    }
}
