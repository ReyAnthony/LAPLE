package fr.laple.extensions.languages.japanese;

import fr.laple.model.language.Symbol;
import fr.laple.model.language.SymbolContainer;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.sound.sampled.*;
import java.io.BufferedInputStream;
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
                Clip sound = loadSound(current.getString("soundFile"));
                //need the others one

                Symbol symbol = new Symbol(userLangTranscript, gottenSymbol, null, null, sound , null);
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

    private Clip loadSound(String path) throws ParserException {

        path = "/a.wav";
        try(InputStream file = getClass().getResourceAsStream(path)){

            BufferedInputStream bis = new BufferedInputStream(file);

            AudioInputStream ais = AudioSystem.getAudioInputStream(bis);
            DataLine.Info info = new DataLine.Info(Clip.class, ais.getFormat());
            Clip clip = (Clip) AudioSystem.getLine(info);
            clip.open(ais);
            clip.addLineListener(event -> {
                if (event.getType() == LineEvent.Type.STOP)
                    //tweak not to reload the stream
                    clip.stop();
                    clip.setMicrosecondPosition(0);
            });

            return clip;

        } catch (IOException | UnsupportedAudioFileException | LineUnavailableException e) {
            throw new ParserException(e.getMessage());
        }
    }

}
