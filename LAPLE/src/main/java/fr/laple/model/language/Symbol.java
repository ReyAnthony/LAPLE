package fr.laple.model.language;

import javax.sound.sampled.Clip;
import java.awt.*;

/**
 * This class describes a symbol, which is the most important element for this software.
 * A symbol is a generic object which act as an association, values here are equals but in different contexts.
 *
 * @author anthonyrey
 */
public class Symbol {

    private String userLangTranscript;
    private String symbol;
    private Image symbolImage;
    private Image animatedImage;
    private Clip pronunciation;
    private String translation;

    /**
     * This is the constructor to create a Symbol
     *
     * @param uLangTr       A string corresponding to the "western transcription" of the symbol (ex : "a")
     * @param sym           A string containing the actual symbol (ex : "あ")
     * @param symImage      An Image containing the image for the symbol
     * @param animImage     An Image containing the animation for the symbol (a gif somewhat)
     * @param pronunciation A Clip for the pronunciation of the symbol
     * @param translation   A string containing the translation (used for words)
     */
    public Symbol(String uLangTr, String sym, Image symImage, Image animImage, Clip pronunciation, String translation) {
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

    public Image getSymbolImage() {
        return symbolImage;
    }

    public Image getAnimatedImage() {
        return animatedImage;
    }

    public Clip getPronunciation() {
        return pronunciation;
    }

    public String getTranslation() {
        return translation;
    }
}
