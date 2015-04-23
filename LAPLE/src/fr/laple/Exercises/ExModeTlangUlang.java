package fr.laple.Exercises;

import fr.laple.language.Symbol;

import javax.swing.*;

/**
 * Created by anthonyrey on 23/04/2015.
 */
public class ExModeTlangUlang implements IExerciseMode {


    @Override
    public String getQuestion(Symbol wantedSymbol) {
        return wantedSymbol.getSymbol();
    }

    @Override
    public String getAnswers(Symbol answer) {

        return answer.getUserLangTranscript();
    }

    @Override
    public Symbol createSymbolFromAnswer(String answer) {
        return new Symbol(answer, null, null, null , null, null );
    }

    @Override
    public Symbol createSymbolFromAnswer(ImageIcon anwser) {
        return new Symbol(null,null,anwser,null,null,null);
    }
}
