package fr.laple.extensions.languages.japanese;

import fr.laple.model.language.Symbol;
import fr.laple.model.language.SymbolContainer;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.sound.sampled.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by anthonyrey on 04/06/2015.
 */
public class LanguageDictionnaryJsonParser {

    public SymbolContainer parseFile(String path) throws ParserException {
        SymbolContainer container;

        try(InputStream file = getClass().getResourceAsStream(path)){

            JsonReader jsonReader = Json.createReader(file);
            JsonObject rootObject = jsonReader.readObject();

            ArrayList<String> keys = new ArrayList<>(rootObject.keySet());
            String rootElem = keys.get(0);

            JsonArray root = rootObject.getJsonArray(rootElem);
            container = new SymbolContainer(rootElem);

            for(int i = 0; i < root.size(); i++)
            {
                JsonObject current = root.getJsonObject(i);
                String userLangTranscript = current .getString("userLangTranscript");
                String gottenSymbol = current.getString("symbol");
                Clip sound = loadSound
                        ("/fr/laple/extensions/languages/japanese/sounds/"
                                        + userLangTranscript + ".wav");

                Symbol symbol = new Symbol(userLangTranscript, gottenSymbol, null, null, sound, null);
                container.addSymbol(symbol);
            }
            if(container.getSize() < 4)
            {
                throw new ParserException("Dico < 4");
            }

        }
        catch(Exception e)
        {
            throw new ParserException(e.toString() + (" while loading language file"));
        }

        return container;
    }

    private Clip loadSound(String file) throws ParserException {

        try {
            Clip sound = AudioSystem.getClip();
            sound.open(AudioSystem.getAudioInputStream(
                    getClass().getResource(file)));

            sound.addLineListener(event -> {
                if (event.getType() == LineEvent.Type.STOP)
                    //tweak not to reload the stream
                    sound.stop();
                sound.setMicrosecondPosition(0);
            });

            return sound;
        }
        catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {

            throw new ParserException("Error while loading sound");
        }
    }

}
