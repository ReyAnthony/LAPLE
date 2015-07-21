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
import java.net.*;
import java.util.*;

/**
 * A goal to generate code.
 *
 * @goal generate-doc
 * @phase package
 */
@Annot(title = "AnnotParserPlugin XML",nom = "class",observation = "permet de modifier les elements de la class")
public class AnnotParserPlugin extends AbstractMojo {

    /**
     * @parameter alias="folderExport"
     * @required
     */
    private String folderExport;

    /**
     * @parameter alias="takeScreen"
     * @required
     */
    Boolean takeScreenshot;

    /**
     * @parameter alias="root_path"
     * @required
     */
    private String rootPath;

    /**
     * @parameter alias="begin_path"
     * @required
     */
    private String beginPath;

    HashMap<String,LinkedList<Annot>> hashAnnot = new HashMap<String,LinkedList<Annot>>();


    @Annot(title = "calc", nom = "calculatrice", observation = "permet de calculer plus facilement")
    public void calc() {

    }

    @Annot(title = "name", nom = "fonction nom", observation = "permet de changer le nom")
    public void name() {

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

    public void writeXmlXSL(String nameFIle,LinkedList<Annot> listeAnnot,String folderExport){
        String valuesXml = "<?xml version='1.0' encoding = 'UTF-8'?>";
        valuesXml += "<?xml-stylesheet type='text/xsl' href='custom.xsl'?>";
        valuesXml += "<Parameters>";
        try {
            for (Annot annote : listeAnnot) {
                valuesXml += "<Doc>";
                valuesXml += "<Etape>" + annote.title() + "</Etape>";
                valuesXml += "<Name>" + annote.nom() + "</Name>";
                valuesXml += "<Description>" + annote.observation() + "</Description>";
                valuesXml += "</Doc>";
            }
            valuesXml+="<image>";
            valuesXml+=nameFIle+".jpg";
            valuesXml+="</image>";
            valuesXml+="</Parameters>";

            valuesXml = valuesXml.replace("'","\"");
            AnnotParserPlugin.Write(folderExport + nameFIle + ".xml", valuesXml);
        }catch(Exception e){

        }
    }

    public void writeImage(String nameFile,String folderExit){
        BufferedImage img = this.getImage();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try{
            File outputfile = new File(folderExit + nameFile + ".jpg");

            ImageIO.write(img, "jpg", outputfile);
        }catch(IOException e){

        }

    }


    public void listerRecursif(File file) {

        try
        {
            for(File f : file.listFiles())
            {
                if (f.exists()) {

                    if (f.isFile()) {

                        try {

                            String fqpn = f.getPath().replace(rootPath,"").replace(".class", "").replace("/", ".");

                            URLClassLoader urlcl = null;
                            urlcl = URLClassLoader.newInstance(new URL[]{new URL("file:"+ rootPath)},
                                    Thread.currentThread().getContextClassLoader());
                            Class<?> clazz = urlcl.loadClass(fqpn);
                            LinkedList<Annot> listeAnnot = new LinkedList<Annot>();
                            System.out.println("file found : -> "+f.getName());
                            for (Method m : clazz.getMethods()) {
                                if (m.isAnnotationPresent(Annot.class)) {

                                    for (Annotation anno : m.getDeclaredAnnotations()) {
                                        Annot an = (Annot) anno;
                                        listeAnnot.add(an);
                                        this.hashAnnot.put(f.getName(),listeAnnot);
                                    }
                                } else {

                                }

                            }
                        }
                        catch(Exception e)
                        {
                            System.out.println("Erreur "+e.getMessage());
                        }

                    }
                    else if (f.isDirectory()) {
                        listerRecursif(f);
                    }
                }

            }
        }
         catch (Exception e) {
             System.out.println("The error is "+e.getMessage());
        }


    }

    public void executeParsor(){
        try {
            beginPath = beginPath.replace("file:","");
            rootPath = rootPath.replace("file:","");
            folderExport= folderExport.replace("file:","");
            HashMap<String,Annot> hashAnn = new HashMap<String,Annot>();
            listerRecursif(new File(beginPath));
            Iterator entries = this.hashAnnot.entrySet().iterator();
            while(entries.hasNext()){
                Map.Entry ent = (Map.Entry) entries.next();
                String key = ent.getKey().toString();
                LinkedList<Annot> anns = (LinkedList<Annot>)ent.getValue();
                key=key.replace(".class","");
                writeXmlXSL(key, anns, folderExport);
                if(takeScreenshot==true){
                    writeImage(key,folderExport);
                }
            }
        }catch(Exception e){
            System.out.println("Error is  "+e.getMessage());
        }

    }


    public void execute() throws MojoExecutionException, MojoFailureException {
        System.out.println("----> "+rootPath);
        System.out.println("----> "+beginPath);
        this.executeParsor();
    }

}