package fr.laple.model.exercises.solvercontainers;

import fr.laple.extensions.languages.japanese.neural.NeuralExerciseSolver;
import fr.laple.model.exercises.ExModeUserLangTranscriptLang;
import fr.laple.model.exercises.IExerciseMode;
import fr.laple.model.exercises.IExerciseSolver;
import fr.laple.view.exercises.AbstractExerciseView;
import fr.laple.view.exercises.FreeInputExcerciseView;

/**
 * Created by anthonyrey on 04/06/2015.
 */
public class DrawingMode extends AbstractAnswerMode {

    //todo add right view
    //TODO need less coupling with neural Solver (specific japaanese)
    //put drawing mode in japanese folder ?
    private IExerciseSolver solver;
    private AbstractExerciseView view;

    public DrawingMode()
    {
        this.solver = new NeuralExerciseSolver();
        this.view = new FreeInputExcerciseView();

    }

    @Override
    public IExerciseSolver getSolver() {
        return solver;
    }

    @Override
    public AbstractExerciseView getCorrespondingView() {
        return view;
    }

    @Override
    public boolean testIfAnswerAndQuestionAreCompatible(IExerciseMode mode) {

        if (mode instanceof ExModeUserLangTranscriptLang)
            return true;
        else
            return false;
    }

    public String toString()
    {
        return "Drawing mode";
    }
}
