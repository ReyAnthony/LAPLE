package fr.laple.language;

import javax.sound.sampled.Clip;
import javax.swing.*;
import java.util.List;

/**
 * This interface define the minimal required methods for a symbol implementation
 *
 * @author anthonyrey
 */
public interface ISymbol {

    public String getUserLangTranscript();
    public Clip getPronunciation();
    public String getStudiedLangSymbol();
    public ImageIcon getStudiedSymbolImage();
    public List<ImageIcon> getComposition();
    public ImageIcon getGif();


}
