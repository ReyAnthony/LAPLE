package fr.laple.annot;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.LinkedList;

/**
 * Created by zaafranigabriel on 25/06/2015.
 */
@annot(title = "test XML",nom = "class",observation = "permet de modifier les elements de la class")
public class test {

    @annot(title = "calc", nom = "calculatrice", observation = "permet de calculer plus facilement")
    public void calc() {

    }

    @annot(title = "name", nom = "fonction nom", observation = "permet de changer le nom")
    public void name() {

    }

    public LinkedList<annot> getAnnotClass(Class aclass) {
        try {
            LinkedList<annot> lanot = new LinkedList<annot>();
            Annotation[] annotation = aclass.getAnnotations();
            if (annotation.length == 0) {
                return null;
            }
            for (Annotation anno : annotation) {
                annot annos = (annot) anno;
                lanot.add(annos);
            }
            Method[] met = aclass.getMethods();
            if (met.length == 0)
                return lanot;
            for (Method me : met) {
                for (Annotation ann : me.getAnnotations()) {
                        annot annots = (annot) ann;
                        lanot.add(annots);
                }
            }
            return lanot;
        } catch (Exception e) {
            return null;
        }
    }

    public BufferedImage getImage(){
        Rectangle screen = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
        BufferedImage capt = null;
        try {
            capt = new Robot().createScreenCapture(screen);

        } catch (AWTException e) {
            e.printStackTrace();
        }
        return capt;
    }

    public static void Write(String nameFile,String content){
        try {
            Writer write = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(nameFile)));
            write.write(content);
            write.close();
        }catch(IOException err){
            System.out.println("err"+err.getMessage());
        }
    }
    public static void main(String[] args) {
       /*
        try {
            Class aClass =  test.class;
            Annotation[] annot = aClass.getAnnotations();
            for (Annotation an : annot) {
                annot annots = (annot) an;
                System.out.println("---->" + annots.title() + "---->" + annots.nom() + "---->" + annots.observation());
            }
            Method[] met = aClass.getMethods();
            for(Method mets : met){
               Annotation[] ann = mets.getAnnotations();
                for(Annotation anns : ann){
                    annot anot = (annot) anns;
                    System.out.println("function ---->"+anot.title()+"---->"+anot.nom()+"--->"+anot.observation());
                }
            }
        } catch (Exception e) {

        }
    */
        test te = new test();
        BufferedImage img = te.getImage();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try{
            File outputfile = new File("/Users/zaafranigabriel/Documents/Java/Serializable/saved.png");

            ImageIO.write(img, "jpg", outputfile);
        }catch(IOException e){

        }
        byte[] bytes = baos.toByteArray();
        // welcome Anthony
        LinkedList<annot> lista = te.getAnnotClass(test.class);
        for (annot annotation : lista) {
            System.out.println("--> " + annotation.nom() + "-->" + annotation.title() + "---->" + annotation.observation());
        }
        String valuesXml = "<?xml version='1.0' encoding = 'UTF-8'?>";
        valuesXml += "<?xml-stylesheet type='text/xsl' href='fichier2.xsl'?>";
        valuesXml += "<Parameters>";
        try {
            for (annot annote : lista) {
                valuesXml += "<Doc>";
                valuesXml += "<Etape>" + annote.title() + "</Etape>";
                valuesXml += "<Name>" + annote.nom() + "</Name>";
                valuesXml += "<Description>" + annote.observation() + "</Description>";
                valuesXml += "</Doc>";
            }
            valuesXml+="<image>";
            valuesXml+="/Users/zaafranigabriel/Documents/Java/Serializable/saved.png";
            valuesXml+="</image>";
            valuesXml+="</Parameters>";

            valuesXml = valuesXml.replace("'","\"");
            test.Write("/Users/zaafranigabriel/Documents/Etudes/fichier.xml",valuesXml);
        }catch(Exception e){

        }


    }
}