package fr.laple.model.exercises;

import fr.laple.model.language.Symbol;
import fr.laple.model.language.SymbolContainer;

import java.awt.*;

/**
 * This class describe an Exercice
 *
 * @author anthonyrey
 */
public class Exercise {

    private SymbolContainer sContainer;
    private Symbol wantedSymbol;
    private IExerciseMode mode;
    private IExerciseSolver solver;

    /**
     * Constructor to the Exercise Class
     *
     * @param wantedSymbol This is the learned symbol, the one we want the user to learn.
     * @param mode         The mode parameter wants an implementation of IExerciseMode, its job is to give to return the
     *                     Symbol in the right format (like English or Japanese), for both Anwsers (if applicable) and
     *                     Question.
     * @param solver       The solver parameter wants an implementation of IExerciseSolver, its job is to take the answer
     *                     of the user as a Symbol, the required answer and to tell if the user responded correctly.
     * @param sContainer   The container related to the symbol used in the Exercice, it is used to generate wrong answers
     *                     for QCM mode.
     */
    public Exercise(Symbol wantedSymbol, IExerciseMode mode, IExerciseSolver solver, SymbolContainer sContainer) {
        this.wantedSymbol = wantedSymbol;
        this.mode = mode;
        this.sContainer = sContainer;
        this.solver = solver;

    }

    /**
     * This method allow an Exercise to be solved, it relies on IExerciseMode and IExerciseSolver implementations
     * to do its job.
     *
     * @param answer The answer from the user
     * @return True if answer is correct, false otherwize
     */
    public boolean solveExercice(String answer) {

        Symbol s = mode.createSymbolFromAnswer(answer);
        return solver.solveExercise(s, wantedSymbol, mode);

    }

    /**
     * This method allow an Exercise to be solved, it relies on IExerciseMode and IExerciseSolver implementations
     * to do its job.
     *
     * @param image The generated image from the user drawing of the symbol
     * @return True if answer is correct, false otherwize
     */
    public boolean solveExercice(Image image) {

        Symbol s = mode.createSymbolFromAnswer(image);
        return solver.solveExercise(s, wantedSymbol, mode);
    }

    /**
     * @return The String in the right format for the wanted symbol
     */
    public String getQuestion()
    {
        return mode.getQuestion(wantedSymbol);
    }

}
