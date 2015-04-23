package fr.laple.Exercises;

import fr.laple.language.Symbol;

import javax.swing.*;

/**
 * Created by anthonyrey on 23/04/2015.
 */
public class ExModeUlangTlang implements IExerciseMode {


    @Override
    public String getQuestion(Symbol wantedSymbol) {
        return wantedSymbol.getUserLangTranscript();
    }

    @Override
    public String getAnswers(Symbol answer) {

        return answer.getSymbol();
    }

    @Override
    public Symbol createSymbolFromAnswer(String answer) {
        return new Symbol(null, answer, null, null , null, null );
    }

    @Override
    public Symbol createSymbolFromAnswer(ImageIcon anwser) {
        return new Symbol(null,null,anwser,null,null,null);
    }
}
