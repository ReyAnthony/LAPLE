package fr.laple.Exercises;

import fr.laple.language.Symbol;
import fr.laple.language.SymbolContainer;

import javax.swing.*;

/**
 * Created by anthonyrey on 23/04/2015.
 */
public class Exercise {

    private SymbolContainer sContainer;
    private Symbol wantedSymbol;
    private IExerciseMode mode;
    private IExerciseSolver solver;

    public Exercise(Symbol wantedSymbol, IExerciseMode mode, IExerciseSolver solver, SymbolContainer sContainer)
    {
        this.wantedSymbol = wantedSymbol;
        this.mode = mode;
        this.sContainer = sContainer;
        this.solver = solver;

    }

    public boolean solveExercice(String answer){

        Symbol s = mode.createSymbolFromAnswer(answer);
        return solver.solveExercise(s, wantedSymbol, mode);

    }

    public boolean solveExercice(ImageIcon image){

      //need neural net
      return false;
    }

}
