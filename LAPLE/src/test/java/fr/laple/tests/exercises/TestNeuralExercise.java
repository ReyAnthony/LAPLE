package fr.laple.tests.exercises;

import fr.laple.model.exercises.ExModeUserLangTranscriptLang;
import fr.laple.model.exercises.Exercise;
import fr.laple.extensions.languages.japanese.LapleLanguagePlugin;
import fr.laple.extensions.languages.japanese.neural.NeuralExerciseSolver;
import fr.laple.model.language.Symbol;
import fr.laple.model.language.SymbolContainer;
import fr.laple.ztools.neuraNets.NeuralLapleHelper;
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
        File trainingSetPath = new File(getClass().getResource("/symbols/a_i_u_e/hira_training_set/").getPath());
        NeuralNetwork net;

        boolean load = true;

        if(load == false)
        {

            ArrayList<String> labels = NeuralLapleHelper.getLabelsFromFiles(trainingSetPath);
            ArrayList<Integer> layers = new ArrayList<>();
            layers.add(100);

            //100 pour 4 sorties

            TransferFunctionType transfertFuncType = TransferFunctionType.SIGMOID;
            net = OcrHelper.createNewNeuralNetwork("neuron", dimension, colorMode, labels,
                    layers , transfertFuncType);

        }
        else
        {
            net = NeuralNetwork.load(getClass().getResource("/net/hiragana/a_i_u_e.nnet").getFile());
        }

        DataSet data;

        trainingSetPath = new File(getClass().getResource("/symbols/a_i_u_e/hira_training_set/").getPath());
        data = NeuralLapleHelper.createDataSetFromImageFolder(trainingSetPath, dimension);
        NeuralLapleHelper.training(net, data);

        OcrPlugin plugin = (OcrPlugin)net.getPlugin(OcrPlugin.class);
        File testSetPath;

        testSetPath = new File(getClass().getResource("/symbols/a_i_u_e/test_set").getPath());
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


        testSetPath = new File(getClass().getResource("/symbols/a_i_u_e/test_set_2").getPath());

        System.out.println("testing for " + testSetPath);
        NeuralLapleHelper.testASet(testSetPath, plugin, dimension);

        for(File f : testSetPath.listFiles())
        {
            if(f.isFile())
            {
                Assert.assertTrue(NeuralLapleHelper.testChar(plugin, f,
                        NeuralLapleHelper.removeExtensions(f.getName()), dimension));
            }
        }


        testSetPath = new File(getClass().getResource("/symbols/a_i_u_e/test_set_3").getPath());
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

       // net.save(getClass().getResource("/net/hiragana/").getPath() + "a_i_u_e_final.nnet");

    }



    @Test
    public void newExercise(){

        LapleLanguagePlugin plugin = new LapleLanguagePlugin();

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

            answer = ex.solveExercice(ImageIO.read(getClass().getResource("/symbols/a_i_u_e/test_set/a.bmp")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Assert.assertTrue(answer);

    }

}
