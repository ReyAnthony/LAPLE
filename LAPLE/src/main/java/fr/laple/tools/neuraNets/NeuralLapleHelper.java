package fr.laple.tools.neuraNets;

import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.data.DataSet;
import org.neuroph.imgrec.FractionRgbData;
import org.neuroph.imgrec.ImageRecognitionPlugin;
import org.neuroph.imgrec.ImageUtilities;
import org.neuroph.imgrec.image.Dimension;
import org.neuroph.imgrec.image.Image;
import org.neuroph.imgrec.image.ImageJ2SE;
import org.neuroph.nnet.learning.*;
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
        int i = 5;
        System.out.println("Training");

        if(i == 1)
        {
            ConvolutionalBackpropagation backprop = new ConvolutionalBackpropagation();
            net.learn(data, backprop);

        }
        else if (i == 2)
        {
            BackPropagation backprop = new BackPropagation();
            net.learn(data, backprop);

        }
        else if(i == 3)
        {
            MomentumBackpropagation backprop = new MomentumBackpropagation();
            net.learn(data, backprop);
        }
        else if (i == 4)
        {
            ResilientPropagation backprop = new ResilientPropagation();
            net.learn(data, backprop);

        }
        else if (i == 5)
        {
            DynamicBackPropagation backprop = new DynamicBackPropagation();
            backprop.setMaxError(0.0001);
            net.learn(data, backprop);

        }



    }

    private static HashMap<String, BufferedImage> loadImagesFromFolder(File path, Dimension dimension)
    {
        HashMap<String, BufferedImage> images = new HashMap<>();

        if(path.isFile())
        {
            images.put(NeuralLapleHelper.removeExtensions(
                    path.getName()) , NeuralLapleHelper.createImage(path, dimension));
        }
        else
        {
            for(File f : path.listFiles())
            {
                if(f.isFile())
                {
                    images.put(NeuralLapleHelper.removeExtensions(
                            f.getName()) , NeuralLapleHelper.createImage(f, dimension));

                }
            }

        }

        return images;
    }

    private static BufferedImage createImage(File f, Dimension dimension)
    {

        BufferedImage image = null;
        try {
            image = ImageUtilities.resizeImage(ImageIO.read(f),
                    dimension.getWidth(), dimension.getHeight());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return image;
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
        if(path.isFile())
        {
            labels.add(NeuralLapleHelper.removeExtensions(path.getName()));
        }
        else
        {
            for(File f : path.listFiles())
            {
                if(f.isFile())
                    labels.add(NeuralLapleHelper.removeExtensions(f.getName()));
            }
        }

        return labels;
    }


    public static String removeExtensions(String str)
    {
        return str.split("(\\.[\\w]+)+")[0];
    }

    public static String getRecognizedChar(PluginBase plugin, Image charImage)
    {

        ImageRecognitionPlugin imageRecognition = (ImageRecognitionPlugin)plugin.getParentNetwork().getPlugin(ImageRecognitionPlugin.class);
        imageRecognition.recognizeImage(charImage);
        HashMap n = imageRecognition.getMaxOutput();
        return n.keySet().toArray()[0].toString();
    }

    public static boolean testChar(PluginBase plugin, File image , String wanted, Dimension dimension)
    {
        Image charImage = new ImageJ2SE(NeuralLapleHelper.loadImagesFromFolder(image, dimension).get(wanted));
        String recon;
        boolean toReturn = false;

        if ((recon = NeuralLapleHelper.getRecognizedChar(plugin, charImage)).equals(wanted))
            toReturn = true;

        System.out.println(recon+" for "+wanted);
        return toReturn;
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
