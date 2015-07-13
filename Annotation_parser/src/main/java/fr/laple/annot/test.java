package fr.laple.annot;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

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
@annot(title = "Test XML",nom = "class",observation = "permet de modifier les elements de la class")
public class Test extends AbstractMojo {

    /**
     * @parameter alias="path"
     * @required
     */
    private String path;



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
            System.out.println("err" + err.getMessage());
        }
    }
    public File[] listeRepertoire(File path, LinkedList<String> allFiles){
        File[] list = null;
        try {
            if (path.isDirectory()) {
                list = path.listFiles();
                if (list != null) {
                    for (int i = 0; i < list.length; i++) {
                        listeRepertoire(list[i], allFiles);
                    }
                } else {
                    System.err.println(path + " : Erreur de lecture.");
                }
            } else {
                String currentFilePath = path.getAbsolutePath();
                System.out.println(currentFilePath);
                allFiles.add(currentFilePath);
            }
        }catch(Exception e){

        }finally {
            return list;
        }

    }
    // ajout de wak a finaliser
    public void walk( String path ) {

        File root = new File( path );
        File[] list = root.listFiles();

        if (list == null) return;

        for ( File f : list ) {
            if ( f.isDirectory() ) {
                walk( f.getAbsolutePath() );
                System.out.println( "Dir:" + f.getAbsoluteFile() );
            }
            else {
                if(f.getAbsoluteFile().toString().contains(".class")){
                   String[] tableau =  f.getAbsoluteFile().toString().split("/");
                   String fichier = tableau[tableau.length];
                   Class fichierClass = fichier.getClass();
                    LinkedList<annot> listeAnnot= this.getAnnotClass(fichierClass);
                    if(listeAnnot==null){

                    }else{
                        String[] files = fichier.split(".java");
                        this.writeXmlXSL(files[0],listeAnnot);
                        this.writeImage(files[0]);
                    }
                }
                System.out.println( "File:" + f.getAbsoluteFile() );
            }
        }
    }

    public void writeXmlXSL(String nameFIle,LinkedList<annot> listeAnnot){
        String valuesXml = "<?xml version='1.0' encoding = 'UTF-8'?>";
        valuesXml += "<?xml-stylesheet type='text/xsl' href='fichier2.xsl'?>";
        valuesXml += "<Parameters>";
        try {
            for (annot annote : listeAnnot) {
                valuesXml += "<Doc>";
                valuesXml += "<Etape>" + annote.title() + "</Etape>";
                valuesXml += "<Name>" + annote.nom() + "</Name>";
                valuesXml += "<Description>" + annote.observation() + "</Description>";
                valuesXml += "</Doc>";
            }
            valuesXml+="<image>";
            valuesXml+="/Users/zaafranigabriel/Documents/Java/Serializable/"+nameFIle+".png";
            valuesXml+="</image>";
            valuesXml+="</Parameters>";

            valuesXml = valuesXml.replace("'","\"");
            Test.Write("/Users/zaafranigabriel/Documents/Etudes/" + nameFIle + ".xml", valuesXml);
        }catch(Exception e){

        }
    }

    public void writeImage(String nameFile){
        BufferedImage img = this.getImage();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try{
            File outputfile = new File("/Users/zaafranigabriel/Documents/Java/Serializable/"+nameFile+".jpg");

            ImageIO.write(img, "jpg", outputfile);
        }catch(IOException e){

        }

    }

    public static void main(String[] args) {


    }

    public void execute() throws MojoExecutionException, MojoFailureException {
        try {
            Test test = new Test();
            test.walk(this.path);
            System.out.println("Test");
        }catch(Exception e){
            System.out.println("Erreur");
        }
    }
}