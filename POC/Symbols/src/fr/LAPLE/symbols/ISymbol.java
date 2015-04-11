package fr.LAPLE.symbols;

import java.util.ArrayList;

/**
 * Created by anthonyrey on 05/04/2015.
 */
public interface ISymbol {

    //We'll be using arrayList because they will not move during runtime
    //So they will be using less memory than a HashMap or a linkedList

    /**
     * Used to get the list of Transcript in the user Lang
     *
     * @return A String containing the "transcript" in the user language for the given symbol
     *
     */
    public String getUserLangTranscript();

    /**
     * Used to get the pronunciation of the current symbol
     * As we can have more than one pronunciation we except an arrayList
     * It is the responsibility of the developer to ensure that he knows the category of
     * the sound he gets from it
     *
     * See pronunciations in Japanese for example (On'yomi and Kun'yomi)
     *
     * @return An ArrayList of Sound objects (Currently not implemented)
     */
    public ArrayList<String> getPronunciations();

    /**
     *Used to get a symbol in the studied language
     *For example in japanese :
     *
     * あ　＝ A (HIRAGANA)
     * ア　＝ A (KATAKANA)
     *
     * In latin alphabet an equivalent might be :
     * A / a
     *
     * @return A String of the current language symbol
     */
    public String getStudiedLangSymbol();


    /**
     *Used to get an image of the symbols in the studied language
     *For example in japanese :
     *
     * あ　＝ A (HIRAGANA)
     * ア　＝ A (KATAKANA)
     *
     * In latin alphabet an equivalent might be :
     * A / a
     *
     * @return An image of the current language symbol
     */
    public String getStudiedSymbolImage();

}
