package fr.laple.language;

import javax.sound.sampled.Clip;
import javax.swing.*;
import java.util.List;

/**
 * Created by anthonyrey on 11/04/2015.
 */
public interface ISymbol {

    public String getUserLangTranscript();
    public Clip getPronunciation();
    public String getStudiedLangSymbol();
    public ImageIcon getStudiedSymbolImage();
    public List<ImageIcon> getComposition();
    public ImageIcon getGif();


}
