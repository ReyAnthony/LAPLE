package fr.laple.extensions.languages.japanese;


import fr.laple.model.language.SymbolContainer;
import fr.laple.model.lessons.*;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by anthonyrey on 06/06/2015.
 */
public class LessonsJsonParser {

    private ArrayList<SymbolContainer> symbolContainers;

    public LessonsJsonParser(ArrayList<SymbolContainer> sc)
    {
        this.symbolContainers = sc;
    }

    public ArrayList<AbstractLessonContainer> parseForSymbolLessons(String path) throws ParserException {

        ArrayList<AbstractLessonContainer> lessonContainers = new ArrayList<>();

        try( InputStream file = getClass().getResourceAsStream(path))
        {

            JsonReader jsonReader = Json.createReader(file);
            JsonObject lessonTypes = jsonReader.readObject();

            //only one ..
            for(String type : lessonTypes.keySet())
            {
                if(type.equals("hiragana") || type.equals("katakana"))
                {
                    JsonObject learningOrder = lessonTypes.getJsonObject(type);
                    JsonArray symbols = learningOrder.getJsonArray("learning_order");
                    ArrayList<Lesson> lessons = new ArrayList<>();

                    SymbolContainer containerForFile = null;

                    for(SymbolContainer sc : symbolContainers)
                    {
                        if(sc.getType().equals(type))
                            containerForFile = sc;
                    }

                    for(int i = 0 ; i < symbols.size(); i++)
                    {
                        String key = symbols.getString(i);
                        //public Lesson(String lessonName,boolean open,Symbol symbol){
                        lessons.add(new Lesson(type + " : "+key, true, containerForFile.getSymbol(key)));

                    }

                    SymbolLessonContainer lessonContainer = new SymbolLessonContainer(type, lessons);
                    lessonContainers.add(lessonContainer);
                }
                else
                    continue;

            }

        }
        catch(Exception e)
        {

            throw new ParserException(path);
        }


        return lessonContainers;

    }

    public AbstractLessonContainer parseForWordLessons(String path) throws ParserException {
        WordLessonContainer lessonContainer = new WordLessonContainer("kanji");

        try(InputStream file = getClass().getResourceAsStream(path)){

            JsonReader jsonReader = Json.createReader(file);
            JsonArray kanji = jsonReader.readObject().getJsonArray("kanji");

            //only one ..
            for(int i = 0; i < kanji.size(); i++)
            {
                JsonObject rootObj = kanji.getJsonObject(i);
                String category = rootObj.getString("category");
                JsonArray list = rootObj.getJsonArray("list");

                ArrayList<Lesson> lessons = new ArrayList<>();

                SymbolContainer containerForFile = null;

                for(SymbolContainer sc : symbolContainers)
                {
                    if(sc.getType().equals("kanji"))
                        containerForFile = sc;
                }

                for(int j = 0; j < list.size(); j++)
                {
                    JsonObject listObject = list.getJsonObject(j);
                    String name = listObject.getString("name");

                    lessons.add(new Lesson("Kanji  "+name, true, containerForFile.getSymbol(name)));

                }


                lessonContainer.addCategory(category, new LessonCategory(category, lessons));

            }

        }
        catch(Exception e)
        {

            throw new ParserException(path);
        }

        return lessonContainer;

    }

}
