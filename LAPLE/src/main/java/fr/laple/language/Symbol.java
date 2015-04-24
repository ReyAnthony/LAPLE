package fr.laple.language;

import javax.sound.sampled.Clip;
import javax.swing.*;

/**
 * This class describes a symbol, which is the most important element for this software.
 * A symbol is a generic object which act as an association, values here are equals but in different contexts.
 *
 * @author anthonyrey
 */
public class Symbol {

    private String userLangTranscript;
    private String symbol;
    private ImageIcon symbolImage;
    private ImageIcon animatedImage;
    private Clip pronunciation;
    private String translation;

    /**
     * This is the constructor to create a Symbol
     *
     * @param uLangTr       A string corresponding to the "western transcription" of the symbol (ex : "a")
     * @param sym           A string containing the actual symbol (ex : "あ")
     * @param symImage      An ImageIcon containing the image for the symbol
     * @param animImage     An ImageIcon containing the animation for the symbol (a gif somewhat)
     * @param pronunciation A Clip for the pronunciation of the symbol
     * @param translation   A string containing the translation (used for words)
     */
    public Symbol(String uLangTr, String sym, ImageIcon symImage, ImageIcon animImage, Clip pronunciation, String translation) {
        this.userLangTranscript = uLangTr;
        this.symbol = sym;
        this.symbolImage = symImage;
        this.animatedImage = animImage;
        this.pronunciation = pronunciation;
        this.translation = translation;
    }


    public String getUserLangTranscript() {
        return userLangTranscript;
    }

    public String getSymbol() {
        return symbol;
    }

    public ImageIcon getSymbolImage() {
        return symbolImage;
    }

    public ImageIcon getAnimatedImage() {
        return animatedImage;
    }

    public Clip getPronunciation() {
        return pronunciation;
    }

    public String getTranslation() {
        return translation;
    }
}
