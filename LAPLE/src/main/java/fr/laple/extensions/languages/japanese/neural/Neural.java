package fr.laple.extensions.languages.japanese.neural;

import org.neuroph.core.NeuralNetwork;
import org.neuroph.imgrec.image.Image;
import org.neuroph.imgrec.image.ImageFactory;
import org.neuroph.ocr.OcrPlugin;

import java.io.InputStream;

/**
 * Created by anthonyrey on 27/05/2015.
 */
public class Neural {

    public static void main(String args[])
    {
        new Neural();
    }

    public Neural()
    {
        InputStream fis =  getClass().getResourceAsStream("/fr/laple/extensions/languages/japanese/neural/CharReconn.nnet");
        NeuralNetwork nnet = NeuralNetwork.load(fis);

        OcrPlugin ocrPlugin = (OcrPlugin)nnet.getPlugin(OcrPlugin.class);
        Image charImage = ImageFactory.getImage(getClass().getResource("/fr/laple/extensions/languages/japanese/testSet/a_hira.png"));
        Character ch = ocrPlugin.recognizeCharacter(charImage);
        System.out.println(ch);
    }

}
