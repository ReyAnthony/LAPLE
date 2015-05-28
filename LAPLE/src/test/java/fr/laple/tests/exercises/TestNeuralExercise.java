package fr.laple.tests.exercises;

import fr.laple.exercises.ExModeUserLangTranscriptLang;
import fr.laple.exercises.Exercise;
import fr.laple.extensions.languages.japanese.LapleLanguagePlugin;
import fr.laple.extensions.languages.japanese.neural.NeuralExerciseSolver;
import fr.laple.language.Symbol;
import fr.laple.language.SymbolContainer;
import org.junit.Assert;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.io.IOException;

/**
 * Created by anthonyrey on 28/05/2015.
 */
public class TestNeuralExercise {


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
