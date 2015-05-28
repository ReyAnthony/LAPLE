package fr.laple.tests.exercises;

import fr.laple.exercises.ExModeUserLangTranscriptLang;
import fr.laple.exercises.Exercise;
import fr.laple.extensions.languages.japanese.LapleLanguagePlugin;
import fr.laple.extensions.languages.japanese.neural.NeuralExerciseSolver;
import fr.laple.language.Symbol;
import fr.laple.language.SymbolContainer;
import org.junit.Assert;
import org.junit.Test;
import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.data.DataSet;
import org.neuroph.imgrec.ColorMode;
import org.neuroph.imgrec.FractionRgbData;
import org.neuroph.imgrec.ImageUtilities;
import org.neuroph.imgrec.image.Dimension;
import org.neuroph.imgrec.image.ImageJ2SE;
import org.neuroph.nnet.learning.BackPropagation;
import org.neuroph.ocr.OcrHelper;
import org.neuroph.ocr.OcrPlugin;
import org.neuroph.util.TransferFunctionType;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by anthonyrey on 28/05/2015.
 */
public class TestNeuralExercise {

    @Test
    public void buildNeuralNet()
    {

        Dimension dimension = new Dimension(130,130);
        ColorMode colorMode = ColorMode.BLACK_AND_WHITE;
        ArrayList<String> labels = new ArrayList<>();
        labels.add("a");
        labels.add("e");
        labels.add("u");
        labels.add("i");
        labels.add("o");

        ArrayList<Integer> layerNeuronsCount = new ArrayList<>();

        TransferFunctionType transfertFuncType = TransferFunctionType.SIGMOID;

        NeuralNetwork net = OcrHelper.createNewNeuralNetwork("neuron", dimension, colorMode, labels,
                layerNeuronsCount, transfertFuncType);

        File trainingSetPath = new File(getClass().getResource("/symbols/hira_training").getPath());
        HashMap<String, BufferedImage> images = new HashMap<>();

        for(File f : trainingSetPath.listFiles())
        {
            try {
                images.put(f.getName().replace(".png", ""),
                        ImageUtilities.resizeImage(ImageIO.read(f), dimension.getWidth(), dimension.getHeight()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Map<String, FractionRgbData> fractions = ImageUtilities.getFractionRgbDataForImages(images);
        DataSet set = OcrHelper.createBlackAndWhiteTrainingSet(labels, fractions);
        net.learn(set, new BackPropagation());

        BufferedImage testImage = null;
        try {
            testImage = ImageIO.read(getClass().getResource("/symbols/a_hira.bmp"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        OcrPlugin plugin = (OcrPlugin)net.getPlugin(OcrPlugin.class);
        System.out.println(plugin.recognizeCharacterProbabilities(new ImageJ2SE(testImage)));

    }

    @Test
    public void newExercise(){
        LapleLanguagePlugin plugin = new LapleLanguagePlugin();
        plugin.loadSymbolContainers();

        SymbolContainer sContainer = plugin.getSymbolContainer().get(0);

        ExModeUserLangTranscriptLang mode = new ExModeUserLangTranscriptLang();
        NeuralExerciseSolver solver = new NeuralExerciseSolver();

        Symbol wantedSymbol = sContainer.getSymbol("a");
        Exercise ex = null;
        
        try {
            ex = new Exercise(wantedSymbol, mode, solver, sContainer);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Assert.assertNotNull(ex);

        boolean answer = false;
        try {

            answer = ex.solveExercice(ImageIO.read(getClass().getResource("/symbols/a_hira.bmp")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Assert.assertTrue(answer);

    }

}
