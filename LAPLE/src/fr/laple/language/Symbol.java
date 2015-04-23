package fr.laple.language;

import javax.sound.sampled.Clip;
import javax.swing.*;

/**
 * Created by anthonyrey on 23/04/2015.
 */
public class Symbol {

    private String userLangTranscript;
    private String symbol;
    private ImageIcon symbolImage;
    private ImageIcon animatedImage;
    private Clip pronunciation;
    private String translation;

    public Symbol(String uLangTr, String sym, ImageIcon symImage, ImageIcon animImage, Clip pronunciation, String translation )
    {
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
