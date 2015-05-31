package fr.laple.extensions.languages.japanese;

import com.oracle.javafx.jmx.json.JSONReader;
import com.sun.tools.doclets.internal.toolkit.util.DocFinder;
import com.sun.xml.internal.bind.v2.runtime.output.SAXOutput;
import fr.laple.language.ILanguagePlugin;
import fr.laple.language.Symbol;
import fr.laple.language.SymbolContainer;
import jdk.nashorn.internal.parser.JSONParser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * This is the entry point for a Language plugin
 *
 * @author anthonyrey
 */
public class LapleLanguagePlugin implements ILanguagePlugin{

    private ArrayList<SymbolContainer> symbolContainers;

    @Override
    public String getLanguageName() {
        return "Japanese";
    }

    @Override
    public void loadSymbolContainers() {

        //should load json file
        //but we'll do it by hand for testing purpose
        symbolContainers = new ArrayList<>();

        try{

            InputStream hiragana = getClass().getResourceAsStream("/fr/laple/extensions/languages/japanese/hiragana.json");
            JsonReader jsonReader = Json.createReader(hiragana);
            JsonObject jsonObject = jsonReader.readObject();
            jsonReader.close();
            hiragana.close();

            JsonArray jsonSymbols = jsonObject.getJsonArray("hiragana");
            SymbolContainer hiraganaContainer = new SymbolContainer("hiragana");
            symbolContainers.add(hiraganaContainer);

            for(int i = 0; i < jsonSymbols.size(); i++)
            {
                JsonObject current = jsonSymbols.getJsonObject(i);

                String userLangTranscript = current.getString("userLangTranscript");
                String gottenSymbol = current.getString("symbol");
                //need the others one

                Symbol symbol = new Symbol(userLangTranscript, gottenSymbol, null, null, null , null);
                hiraganaContainer.addSymbol(symbol);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void loadLessons() {
       try {
           InputStream lesson = getClass().getResourceAsStream("/fr/laple/extensions/languages/japanese/lessons.json");
           JsonReader read = Json.createReader(lesson);
          JsonObject jsonObject = read.readObject();

           read.close();
           lesson.close();

          JsonObject objetArray = jsonObject.getJsonObject("lesson_type").getJsonArray("katakana").getJsonObject(0);
           System.out.println("Hello");
          JsonArray jsona = objetArray.getJsonArray("learning_order");
           System.out.println("hello");
           jsona.toString();
           System.out.println("Hey");
           String val= "";
           for(int i = 0;i<jsona.size();i++){
                val+=jsona.getString(i);
           }
           System.out.println("test");

           objetArray = jsonObject.getJsonObject("lesson_type").getJsonArray("hiragana").getJsonObject(0);

           jsona = objetArray.getJsonArray("learning_order");
           System.out.println("hello");
           jsona.toString();
           System.out.println("hey");
           String val2 = "";
           for(int i=0;i<jsona.size();i++){
              // SymbolContainer containerSymbole = new SymbolContainer()
              // symbolContainers.add()
               val2+=jsona.getString(i);
           }
           System.out.println("test");
          objetArray = jsonObject.getJsonObject("lesson_type").getJsonObject("kanji").getJsonArray("list").getJsonObject(0);
           System.out.println("test");
           String vael =  objetArray.getString("name");
           String vael2 = objetArray.getString("symbol");
           jsona = objetArray.getJsonArray("list");
          String name = jsona.getString(0);
          String symbole = jsona.getString(1);
           System.out.println("test2");




       }catch(Exception e){
            System.out.println("erreur e "+e.getMessage());
       }

    }

    @Override
    public ArrayList<SymbolContainer> getSymbolContainer() {
        return symbolContainers;
    }

    @Override
    public String getVersion() {
        return "1.0";
    }

    public String readFile(String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        try {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append("\n");
                line = br.readLine();
            }
            return sb.toString();
        } finally {
            br.close();
        }
    }

    public static void main(String[] args){
        LapleLanguagePlugin lap = new LapleLanguagePlugin();
        lap.loadLessons();
    }
}
