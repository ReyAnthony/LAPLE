package fr.laple.extensions.languages.japanese.neural;

import fr.laple.exercises.ExModeTranscriptLangUserLang;
import fr.laple.exercises.IExerciseMode;
import fr.laple.exercises.IExerciseSolver;
import fr.laple.exercises.exceptions.ExcerciseModeException;
import fr.laple.language.Symbol;
import org.neuroph.core.NeuralNetwork;
import org.neuroph.imgrec.image.ImageJ2SE;
import org.neuroph.ocr.OcrPlugin;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by anthonyrey on 27/05/2015.
 */
public class NeuralExerciseSolver implements IExerciseSolver {

    @Override
    public boolean solveExercise(Symbol answer, Symbol wanted, IExerciseMode mode) {

        Image image = answer.getSymbolImage();
        return solveWithNeuralNet((BufferedImage) image, wanted.getUserLangTranscript());
    }

    @Override
    public void testIfModeAndSolverAreCompatible(IExerciseMode mode) throws ExcerciseModeException {

        if(mode instanceof ExModeTranscriptLangUserLang)
           throw new ExcerciseModeException();

    }

    private boolean solveWithNeuralNet(BufferedImage image, String wanted)
    {
        Boolean toReturn = false;

        try {

            InputStream fis = getClass().getResourceAsStream(
                    "/fr/laple/extensions/languages/japanese/neural/hiragana/a_i_u_e.nnet");
            NeuralNetwork nnet = NeuralNetwork.load(fis);
            fis.close();

            OcrPlugin plugin = (OcrPlugin)nnet.getPlugin(OcrPlugin.class);
            String charRecognized = plugin.recognizeCharacter(new ImageJ2SE(image)).toString().toLowerCase();

            if(charRecognized.equals(wanted))
                toReturn = true;
            else
                toReturn = false;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return toReturn;

    }
}
