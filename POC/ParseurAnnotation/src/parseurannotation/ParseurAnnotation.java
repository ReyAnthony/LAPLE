/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parseurannotation;
import java.lang.annotation.*;
import java.lang.reflect.Field;
import java.util.Collection;
import jdk.internal.org.objectweb.asm.tree.TryCatchBlockNode;

/**
 *
 * @author zaafranigabriel
 */
@ImplementationAnnot2(id=12,name="ESGI",value="Test d'annotation ",family="Inge-com")
@AnnotationImplem(name="someName",  value = "Hello World",content="Ceci est un parseur")

public class ParseurAnnotation  {

    /**
     *
     */
   
    public void parse(){
    
    }
  
   public static String getAnnotations(Class clazz){
    String val="";
    for(Field field : clazz.getDeclaredFields()){
        Class type = field.getType();
        String name = field.getName();
      //  val = field.getDeclaredAnnotations().toString();
        val = field.getAnnotation(clazz).toString();
    }
    return val;
    }
   
   public static void printAnnotations2(Class clas){
     
   }
   
   public static void testAnnot2(){
       Class aclas = ParseurAnnotation.class;
      // ----> technique pour recuperer les methodes Methods met = aclas.getMethods();
       Annotation[] annot = aclas.getAnnotations();
       for(Annotation anot : annot){
           if(anot instanceof ImplementationAnnot2){
               ImplementationAnnot2 AnnotType = (ImplementationAnnot2) anot;
               System.out.println("L'id est "+AnnotType.id());
               System.out.println("Le nom est "+AnnotType.name());
               System.out.println("La valeur est "+AnnotType.value());
               System.out.println("L'id est "+AnnotType.family());
           }
       }
   }
   
   public static void printAnnotations(Class clas){
       try {
          
       Class ParsC = clas.getClass();
      Annotation[] annot =  ParsC.getAnnotations();
      
      int number = annot.length;
      System.out.println("Le nom de la classe est "+clas.getName()+" le nombre d'annotiation est "+annot.length);
      for(int i=0;i<number;i++){
          System.out.println("l'annotation numero id est "+annot[i].annotationType().getName());
         /*
          System.out.println("l'annotation numero id est "+annot[i].synopsis());
          System.out.println("l'annotation numero id est "+annot[i].engineer());
          System.out.println("l'annotation numero id est "+annot[i].date());
        */
          
      }      
       } catch (Exception e) {
           System.out.println("erreur de type "+e.getMessage());
       }
     
   }
  
   
   
    public static void main(String[] args) {
         try {
        Class aClass = ParseurAnnotation.class;
        Annotation[] annotations = aClass.getAnnotations();

            for( Annotation annotation : annotations){
            if(annotation instanceof  AnnotationImplem){
                 AnnotationImplem myAnnotation = (AnnotationImplem) annotation;
                System.out.println("name: " + myAnnotation.name());
                System.out.println("value: " + myAnnotation.value());
                System.out.println("value: " + myAnnotation.content());
            }
          ParseurAnnotation.testAnnot2();
}
    

        
        } catch (Exception e) {
            System.out.println(" le catch est "+e.getMessage());
        
        }
     
        
    }

   
    
}
