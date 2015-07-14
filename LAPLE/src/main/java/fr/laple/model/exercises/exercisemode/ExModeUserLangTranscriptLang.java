package fr.laple.model.exercises.exercisemode;

import fr.laple.model.language.Symbol;

import java.awt.*;

/**
 * This class is an implementation of IExerciceMode, it correspond to User Lang TO TeachedLang
 *
 * @author anthonyrey
 */
public class ExModeUserLangTranscriptLang implements IExerciseMode {


    @Override
    public String getQuestion(Symbol wantedSymbol) {
        return wantedSymbol.getUserLangTranscript();
    }

    @Override
    public String getAnswer(Symbol answer) {

        return answer.getSymbol();
    }

    @Override
    public Symbol createSymbolFromAnswer(String answer) {
        return new Symbol(null, answer, null, null , null, null );
    }

    @Override
    public Symbol createSymbolFromAnswer(Image anwser) {
        return new Symbol(null,null,anwser,null,null,null);
    }

    @Override
    public String getModeName() {
        return "User language to teached language";
    }

    public String toString()
    {
        return this.getModeName();
    }
}
