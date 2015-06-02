package fr.laple.model.exercises;

import fr.laple.model.language.Symbol;

import java.awt.*;

/**
 * This class is an implementation of IExerciceMode, it correspond to Teached Lang TO UserLang
 *
 * @author anthonyrey
 */
public class ExModeTranscriptLangUserLang implements IExerciseMode {


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
    public Symbol createSymbolFromAnswer(Image anwser) {
        return new Symbol(null,null,anwser,null,null,null);
    }

    @Override
    public String getModeName() {
        return "Teached language to user language";
    }
}
