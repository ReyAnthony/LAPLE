package fr.laple.model.exercises;

import fr.laple.model.language.Symbol;

import java.awt.*;

/**
 * This interface is giving prototypes needed for checking modes Exercise class
 *
 * @author anthonyrey
 */
public interface IExerciseMode {

    //Translate the Symbol to the right thing to show

    /**
     * This method returns the given Symbol in the format relative to the one needed for the question.
     * Ex : English to Japanese == Question : A and Anwser : あ.
     *
     * @param wantedSymbol The symbol corresponding to the user learned symbol
     * @return a String containing the right format of the symbol
     */
    public String getQuestion(Symbol wantedSymbol);

    /**
     * This method returns the given Symbol in the format relative to the one needed for the answer.
     * Ex : English to Japanese == Question : A and Anwser : あ.
     *
     * @param answer The symbol corresponding to one of the possible answer in QCM mode
     * @return a String containing the right format of the symbol
     */
    public String getAnswer(Symbol answer);

    /**
     * This method returns a Symbol containing only the parameter corresponding to the needed format for answer
     * It is a bit messy, but this helps keeping polymorphism work when using solver.
     * Ex : English to Japanese == new Symbol(null, answer, null, null, null, null).
     *
     * @see fr.laple.model.language.Symbol
     *
     * @param answer The String containing user response to the exercise
     * @return a Symbol containing the right format of the String only
     */
    public Symbol createSymbolFromAnswer(String answer);

    /**
     * This method returns a Symbol containing only the parameter corresponding to the needed format for answer
     * It is a bit messy, but this helps keeping polymorphism work when using solver.
     * Ex : English to Japanese == new Symbol(null, null, answer, null, null, null).
     *
     * @see fr.laple.model.language.Symbol
     *
     * @param answer The Image generated from the user drawing
     * @return a Symbol containing the right format of the String only
     */
    public Symbol createSymbolFromAnswer(Image answer);

    /**
     * This method returns the name of the current exercise mode
     *
     * @return
     */
    public String getModeName();


}
