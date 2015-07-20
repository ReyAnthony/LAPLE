package fr.laple.annot;

import sun.net.www.protocol.file.FileURLConnection;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.net.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * Created by zaafranigabriel on 13/07/2015.
 */
@Annot(title = "name", nom = "fonction nom", observation = "permet de changer le nom")
public class ClasseParse {
    @Annot(title = "Test XML",nom = "class",observation = "permet de modifier les elements de la class")
    public void calc(){

    }


    public LinkedList<Annot> listeAnnot(Class aclass){
        LinkedList<Annot> listeAnn = new LinkedList<Annot>();
        String value = this.nameAnnot(aclass.getName().toString());
        if(value.equals("Annot")){
            return null;
        }
        Annotation[] annotations = aclass.getDeclaredAnnotations();
        int sizea = annotations.length;
       if(annotations.length==0){

        }else {
            for (Annotation ann : annotations) {
                Annot anns = (Annot) ann;
                listeAnn.add(anns);
            }
            Method[] mets = aclass.getDeclaredMethods();

            for (Method met : mets) {
                if (met.getAnnotations().length == 0) {

                } else {
                    for (Annotation an : met.getAnnotations()) {
                        Annot ans = (Annot) an;
                        listeAnn.add(ans);
                    }
                }

            }
        }
        return listeAnn;
    }

    public void getMethod(Class aclass){
        Method[] mets = aclass.getMethods();
        for(Method met :mets ){
            for(Annotation an : met.getAnnotations()){
                Annot ans = (Annot) an;
                System.out.println("--> title"+ans.title());
                System.out.println("--> nom"+ans.nom());
                System.out.println("--> observation"+ans.observation());
            }
        }
    }

    public LinkedList<Annot> getAnnotClass(Class aclass) {
        try {
            LinkedList<Annot> lanot = new LinkedList<Annot>();
            Annotation[] annotation = aclass.getAnnotations();
            if (annotation.length == 0) {
                return null;
            }
            for (Annotation anno : annotation) {
                Annot annos = (Annot) anno;
                lanot.add(annos);
            }
            Method[] met = aclass.getMethods();
            if (met.length == 0)
                return lanot;
            for (Method me : met) {
                for (Annotation ann : me.getAnnotations()) {
                    Annot annots = (Annot) ann;
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

    public void getAllAnnotations(File path,String fichier){
       try {
           URL[] url ={path.toURI().toURL()};
           URLClassLoader load = new URLClassLoader(url);
           Class clazz = load.loadClass(fichier);
           this.listeAnnot(clazz);
       }catch(Exception e){

       }

    }



    public void writeXmlXSL(String nameFIle,LinkedList<Annot> listeAnnot,String folderExport){
        String valuesXml = "<?xml version='1.0' encoding = 'UTF-8'?>";
        valuesXml += "<?xml-stylesheet type='text/xsl' href='fichier2.xsl'?>";
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
            valuesXml+=folderExport+"/"+nameFIle+".jpg";
            valuesXml+="</image>";
            valuesXml+="</Parameters>";

            valuesXml = valuesXml.replace("'","\"");
            Test.Write(folderExport + nameFIle + ".xml", valuesXml);
        }catch(Exception e){

        }
    }
    public void writeImage(String nameFile,String folderExit){
        BufferedImage img = this.getImage();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try{
            File outputfile = new File(folderExit+nameFile+".jpg");

            ImageIO.write(img, "jpg", outputfile);
        }catch(IOException e){

        }

    }
    private static void checkDirectory(File directory, String pckgname,
                                       ArrayList<Class<?>> classes) throws ClassNotFoundException {
        File tmpDirectory;

        if (directory.exists() && directory.isDirectory()) {
            final String[] files = directory.list();

            for (final String file : files) {
                if (file.endsWith(".class")) {
                    try {
                        classes.add(Class.forName(pckgname + '.'
                                + file.substring(0, file.length() - 6)));
                    } catch (final NoClassDefFoundError e) {
                        // do nothing. this class hasn't been found by the
                        // loader, and we don't care.
                    }
                } else if ((tmpDirectory = new File(directory, file))
                        .isDirectory()) {
                    checkDirectory(tmpDirectory, pckgname + "." + file, classes);
                }
            }
        }
    }
    public static ArrayList<Class<?>> getClassesForPackage(String pckgname)
            throws ClassNotFoundException {
        final ArrayList<Class<?>> classes = new ArrayList<Class<?>>();

        try {
            final ClassLoader cld = Thread.currentThread()
                    .getContextClassLoader();

            if (cld == null)
                throw new ClassNotFoundException("Can't get class loader.");

            final Enumeration<URL> resources = cld.getResources(pckgname
                    .replace('.', '/'));
            URLConnection connection;

            for (URL url = null; resources.hasMoreElements()
                    && ((url = resources.nextElement()) != null);) {
                try {
                    connection = url.openConnection();
                    if (connection instanceof JarURLConnection) {
                        checkJarFile((JarURLConnection) connection, pckgname,
                                classes);
                    } else if (connection instanceof FileURLConnection) {
                        try {
                            checkDirectory(
                                    new File(URLDecoder.decode(url.getPath(),
                                            "UTF-8")), pckgname, classes);

                        } catch (final UnsupportedEncodingException ex) {
                            throw new ClassNotFoundException(
                                    pckgname
                                            + " does not appear to be a valid package (Unsupported encoding)",
                                    ex);
                        }
                    } else
                        throw new ClassNotFoundException(pckgname + " ("
                                + url.getPath()
                                + ") does not appear to be a valid package");
                } catch (final IOException ioex) {
                    throw new ClassNotFoundException(
                            "IOException was thrown when trying to get all resources for "
                                    + pckgname, ioex);
                }
            }
        } catch (final NullPointerException ex) {
            throw new ClassNotFoundException(
                    pckgname
                            + " does not appear to be a valid package (Null pointer exception)",
                    ex);
        } catch (final IOException ioex) {
            throw new ClassNotFoundException(
                    "IOException was thrown when trying to get all resources for "
                            + pckgname, ioex);
        }

        return classes;
    }
    private static void checkJarFile(JarURLConnection connection,
                                     String pckgname, ArrayList<Class<?>> classes)

            throws ClassNotFoundException, IOException {
        final JarFile jarFile = connection.getJarFile();
        final Enumeration<JarEntry> entries = jarFile.entries();
        String name;

        for (JarEntry jarEntry = null; entries.hasMoreElements()
                && ((jarEntry = entries.nextElement()) != null);) {
            name = jarEntry.getName();

            if (name.contains(".class")) {
                name = name.substring(0, name.length() - 6).replace('/', '.');

                if (name.contains(pckgname)) {
                    classes.add(Class.forName(name));
                }
            }
        }
    }
    // gerer le cas ou ya pas de .
    public String nameAnnot(String anotName){

            String[] tab = anotName.split("\\.");
            String valeur = tab[tab.length-1];
            return tab[tab.length-1];
    }

    public void generate_Annotation(String packageName,String exitFolder, Boolean screen,Class annotation){
        try {
            ArrayList<Class<?>> arl = ClasseParse.getClassesForPackage(packageName);
            for(Class cl : arl) {

                String val = cl.getName().toString();
                Class cls = Class.forName(val);
                if(cls.isAnnotationPresent(annotation)) {
                    String nomFichier = this.nameAnnot(val);
                    LinkedList<Annot> listeAn = this.listeAnnot(cls);
                    System.out.println("test");
                    if (listeAn == null || listeAn.size() == 0) {

                    } else {
                        for (Annot an : listeAn) {
                            System.out.println("title -> " + an.title());
                            System.out.println("observation -> " + an.observation());
                            System.out.println("nom -> " + an.nom());
                        }
                        this.writeXmlXSL(nomFichier, listeAn,exitFolder);
                        if(screen==true){

                        }else {
                            this.writeImage(nomFichier,exitFolder);
                        }
                    }
                }else{

                }
            }
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }

    }


    public static void main(String[] args)  {


    /*
        String variable = "/Users/zaafranigabriel/Documents/Etudes/Etude/Java/Projet annuel/LAPLENewsProject2/LAPLE5/LAPLE/Annotation_parser/target/classes/fr/laple/Annot/";
        ClasseParse test = new ClasseParse();
   //     test.walk(variable);
        try{
            Class eleme = Class.forName(ClasseParse.class.getPackage().getName()+".AnotationTest");
            System.out.println("le chemin de -----> "+ClasseParse.class.getPackage().getName());
            LinkedList<Annot> listeA = test.getAnnotClass(eleme);
            for(Annot elem : listeA) {
                System.out.println("--> " + elem.nom());
                System.out.println("--> " + elem.observation());
                System.out.println("--> "+elem.title());
            }
        } catch(Exception er){

            System.out.println("");
        }
        String path = "/Users/zaafranigabriel/Documents/Etudes/Etude/Java/ProjetJavaAnnuel/LAPLENewsProject2/LAPLE5/LAPLE/Annotation_parser/target/classes/fr/laple/Annot";
        URL url ;
        try {
            url = new URL("file:///"+path);
            URLClassLoader ucl = new URLClassLoader(new URL[]{url});
            Class clazz = ucl.loadClass("fr.laple.Annot.AnotationTest");
        }catch(MalformedURLException e){
            System.out.println("-->"+e.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();

        }
        //test.walk(path);

        try {
            Class claz = Class.forName("fr.laple.Annot.AnotationTest");
            System.out.println(claz.getName().toString());
            LinkedList<Annot> an = test.getAnnotClass(claz);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        //


        ClasseParse classeP = new ClasseParse();
        classeP.generate_Annotation("fr.laple.Annot","/Users/zaafranigabriel/Documents/Etudes/ExportFolder/",false,Annot.class);

        */
        String champs = "fr.laple.Annot.annotation.class";
        System.out.println(champs.substring(0, champs.length() - 6));
        /*
        try {
            ArrayList<Class<?>> arl = ClasseParse.getClassesForPackage("fr.laple.Annot");
            for(Class cl : arl){
                String className = cl.getTypeName().toString();
               LinkedList<Annot> lanot =  test.listeAnnot(cl);
               for (Annot ann : lanot){
                   System.out.println("---> nom "+ann.nom());
                   System.out.println("---> nom "+ann.title());
                   System.out.println("---> nom "+ann.observation());
               }
            }

            System.out.println("file");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    */
   /*

        ClasseParse test = new ClasseParse();
        Class cl = test.getClass();
       LinkedList<Annot> listeAn =  test.listeAnnot(cl);
        for(Annot an : listeAn){
            System.out.println("Nom -->"+an.nom());
            System.out.println("Observation -->"+an.observation());
            System.out.println("Title -->"+an.title());
        }

   //     test.getMethod(cl);
   //     System.out.println("Execute FINISH ******************");
    */
    }

}
