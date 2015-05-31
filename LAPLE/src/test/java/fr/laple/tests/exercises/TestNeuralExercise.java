package fr.laple.tests.exercises;

import fr.laple.exercises.ExModeUserLangTranscriptLang;
import fr.laple.exercises.Exercise;
import fr.laple.extensions.languages.japanese.LapleLanguagePlugin;
import fr.laple.extensions.languages.japanese.neural.NeuralExerciseSolver;
import fr.laple.language.Symbol;
import fr.laple.language.SymbolContainer;
import fr.laple.tools.neuraNets.NeuralLapleHelper;
import org.junit.Assert;
import org.junit.Test;
import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.data.DataSet;
import org.neuroph.imgrec.ColorMode;
import org.neuroph.imgrec.image.Dimension;
import org.neuroph.ocr.OcrHelper;
import org.neuroph.ocr.OcrPlugin;
import org.neuroph.util.TransferFunctionType;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author anthonyrey
 */
public class TestNeuralExercise {

    @Test
    public void buildNeuralNet()
    {

        Dimension dimension = new Dimension(130,130);
        ColorMode colorMode = ColorMode.BLACK_AND_WHITE;

        File trainingSetPath = new File(getClass().getResource("/symbols/hira_training/").getPath());
        ArrayList<String> labels = NeuralLapleHelper.getLabelsFromFiles(trainingSetPath);
        ArrayList<Integer> layers = new ArrayList<>();
        layers.add(100);

        //100 pour 4 sorties

        TransferFunctionType transfertFuncType = TransferFunctionType.SIGMOID;
        NeuralNetwork net = OcrHelper.createNewNeuralNetwork("neuron", dimension, colorMode, labels,
               layers , transfertFuncType);

        DataSet data;

        //data = NeuralLapleHelper.createDataSetFromImageFolder(trainingSetPath, dimension);
        //NeuralLapleHelper.training(net, data);

        //trainingSetPath = new File(getClass().getResource("/symbols/hira_training_2/").getPath());
        //data = NeuralLapleHelper.createDataSetFromImageFolder(trainingSetPath, dimension);
        //NeuralLapleHelper.training(net, data);

        trainingSetPath = new File(getClass().getResource("/symbols/hira_training_3/").getPath());
        data = NeuralLapleHelper.createDataSetFromImageFolder(trainingSetPath, dimension);
        NeuralLapleHelper.training(net, data);

        OcrPlugin plugin = (OcrPlugin)net.getPlugin(OcrPlugin.class);
        File testSetPath = new File(getClass().getResource("/symbols/test_set").getPath());


        System.out.println("testing for "+testSetPath);
        NeuralLapleHelper.testASet(testSetPath, plugin, dimension);

        for(File f : testSetPath.listFiles())
        {
            if(f.isFile())
            {
                Assert.assertTrue(NeuralLapleHelper.testChar(plugin, f,
                        NeuralLapleHelper.removeExtensions(f.getName()), dimension));
            }
        }


        testSetPath = new File(getClass().getResource("/symbols/hira_training").getPath());
        NeuralLapleHelper.testASet(testSetPath, plugin, dimension);

        System.out.println("testing for "+testSetPath);
        for(File f : testSetPath.listFiles())
        {
            if(f.isFile())
            {
                Assert.assertTrue(NeuralLapleHelper.testChar(plugin, f,
                        NeuralLapleHelper.removeExtensions(f.getName()), dimension));
            }
        }

        testSetPath = new File(getClass().getResource("/symbols/hira_training_2").getPath());
        NeuralLapleHelper.testASet(testSetPath, plugin, dimension);

        System.out.println("testing for "+testSetPath);
        for(File f : testSetPath.listFiles())
        {
            if(f.isFile())
            {
                Assert.assertTrue(NeuralLapleHelper.testChar(plugin, f,
                        NeuralLapleHelper.removeExtensions(f.getName()), dimension));
            }
        }

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

            answer = ex.solveExercice(ImageIO.read(getClass().getResource("/symbols/a.bmp")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Assert.assertTrue(answer);

    }

}
