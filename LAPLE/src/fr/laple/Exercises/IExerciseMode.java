package fr.laple.Exercises;

import fr.laple.language.Symbol;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Created by anthonyrey on 23/04/2015.
 */
public interface IExerciseMode {

    //Translate the Symbol to the right thing to show
    public String getQuestion(Symbol wantedSymbol);
    public String getAnswers(Symbol answer);
    public Symbol createSymbolFromAnswer(String anwser);
    public Symbol createSymbolFromAnswer(ImageIcon anwser);


}
