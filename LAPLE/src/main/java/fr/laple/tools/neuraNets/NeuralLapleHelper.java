package fr.laple.tools.neuraNets;

import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.data.DataSet;
import org.neuroph.imgrec.FractionRgbData;
import org.neuroph.imgrec.ImageRecognitionPlugin;
import org.neuroph.imgrec.ImageUtilities;
import org.neuroph.imgrec.image.Dimension;
import org.neuroph.imgrec.image.Image;
import org.neuroph.imgrec.image.ImageJ2SE;
import org.neuroph.nnet.learning.BackPropagation;
import org.neuroph.ocr.OcrHelper;
import org.neuroph.ocr.OcrPlugin;
import org.neuroph.util.plugins.PluginBase;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author anthonyrey
 *
 * Helper to create and train LAPLE related neural networks
 *
 */
public class NeuralLapleHelper {

    private NeuralLapleHelper() {}

    public static void training(NeuralNetwork net, DataSet data)
    {
        BackPropagation backprop = new BackPropagation();
        net.learn(data, backprop);

    }

    private static HashMap<String, BufferedImage> loadImagesFromFolder(File path, Dimension dimension)
    {
        HashMap<String, BufferedImage> images = new HashMap<>();
        for(File f : path.listFiles())
        {
            if(f.isFile())
            {
                String name = NeuralLapleHelper.removeExtensions(f.getName());
                try {
                    images.put(name, ImageUtilities.resizeImage(ImageIO.read(f),
                            dimension.getWidth(), dimension.getHeight()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

        return images;

    }

    public static DataSet createDataSetFromImageFolder(File path, Dimension dimension)
    {
        HashMap<String, BufferedImage> images = NeuralLapleHelper.loadImagesFromFolder(path, dimension);
        ArrayList<String> labels = NeuralLapleHelper.getLabelsFromFiles(path);
        Map<String, FractionRgbData> fractions = ImageUtilities.getFractionRgbDataForImages(images);
        DataSet set = OcrHelper.createBlackAndWhiteTrainingSet( labels, fractions);
        return set;
    }

    public static ArrayList<String> getLabelsFromFiles(File path)
    {
        ArrayList<String> labels = new ArrayList<>();
        for(File f : path.listFiles())
        {
            labels.add(NeuralLapleHelper.removeExtensions(f.getName()));
        }

        return labels;

    }

    public static String removeExtensions(String str)
    {
        return str.split("(.([\\w]+)+)")[0];
    }

    public static String getRecognizedChar(PluginBase plugin, Image charImage)
    {

        ImageRecognitionPlugin imageRecognition = (ImageRecognitionPlugin)plugin.getParentNetwork().getPlugin(ImageRecognitionPlugin.class);
        imageRecognition.recognizeImage(charImage);
        HashMap n = imageRecognition.getMaxOutput();
        return n.keySet().toArray()[0].toString();
    }

    public static void testASet(File path, OcrPlugin plugin, Dimension dimension)
    {
        HashMap<String, BufferedImage> testImages = NeuralLapleHelper.loadImagesFromFolder(path, dimension);

        for(String name : testImages.keySet())
        {
            String recognized = NeuralLapleHelper.getRecognizedChar(plugin, new ImageJ2SE(testImages.get(name)));
            System.out.println("Recognized " + recognized + " for " + name);
            System.out.println(plugin.recognizeCharacterProbabilities(new ImageJ2SE(testImages.get(name))));
        }

    }


}
