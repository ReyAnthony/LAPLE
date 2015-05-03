package reconnaissanceCarac;

import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Fenetre extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Draw pan = new Draw();
	private JPanel container= new JPanel();
	private Valider bouton= new Valider("Valider");
	
	
	public Fenetre() throws CloneNotSupportedException {
	// taille de la fenêtre de l'app est calculée en recupérant 
	// la taille de l'écran du pc et en divisant par 2
		
		int width=getToolkit().getScreenSize().width/2;
		int heigth=getToolkit().getScreenSize().height/2;
		this.setTitle("ma première interface");
		this.setSize(width, heigth);
		//permet de fermer la fenêtre lorsque l'on clique sur la croix de la fenêtre
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//position de la fenêtre, ici par défaut au centre
		this.setLocationRelativeTo(null);
		container.setLayout(new BorderLayout());
		container.add(pan, BorderLayout.CENTER);
		this.setContentPane(container);
		//bouton au nord de la panel
		this.getContentPane().add(bouton, BorderLayout.NORTH);
		this.setVisible(true);
		//valide l'évenement lorsque l'on clique sur le bouton
		while(!bouton.isValidation())
			bouton.validate();
		
		readWriteFile('r');
		
		if(pan.compareArray()){
			pan.setResult("caractère reconnu");
		}
		else{
			pan.setResult("caractère non reconnu");
		}
		repaint();
	}
	
	//Pour écrire et lire des coordonnées à partir du fichier coodonnee
	public void readWriteFile(char mode) throws CloneNotSupportedException {
	      
		   BufferedReader fis = null;
		   BufferedWriter fos = null;
		   File fich=null;
		   try {
	         if(mode=='w'){
	        	 fos = new BufferedWriter(new FileWriter("coordonnee.txt"));
	        	 
	        	 for(Point p: pan.getPoints()){ 
		        	 fos.write("" + p.getMouseX());
		        	 fos.newLine();
		        	 fos.write("" + p.getMouseY());
		        	 fos.newLine();		        	 
		         }
	         }
	         else{
	        	 fich=new File("coordonnee.txt");
	        	 fis = new BufferedReader(new FileReader("coordonnee.txt"));
	           	 String line;
		         int size=(int)fich.length();
		         int i=0;
		         int[] tmp=new int[size];
		         Point p= new Point();
		         
	        	 while ((line = fis.readLine()) != null) {
	        		 tmp[i]=Integer.parseInt(line);
	        		 i++;
	        	 }
	        	 i=0;
	        	 for(i=0; i<tmp.length; i=i+2){
	        		if((tmp[i]!=0 && tmp[i+1]!=0)){
		        		p.setMouseX(tmp[i]);
		        		p.setMouseY(tmp[i+1]);
		        		p.setMouseSize(10);
		        		pan.getPointFile().add((Point) p.clone());	
	        		}
	        		if(i==(tmp.length-3))
	        			break;
	        	 }
	         }  
	      }catch (FileNotFoundException e) {
	         // Cette exception est levée si l'objet fis ne trouve pas de fichier
	         e.printStackTrace();
	      } catch (IOException e) {
	         // Celle-ci se produit lors d'une erreur d'écriture ou de lecture
	         e.printStackTrace();
	      } finally {
	         // On ferme nos flux de données dans un bloc finally pour s'assurer
	         // que ces instructions seront exécutées dans tous les cas même si
	         // une exception est levée !
	         try {
	            if (fis != null)
	               fis.close();
	         } catch (IOException e) {
	            e.printStackTrace();
	         }

	         try {
	            if (fos != null)
	               fos.close();
	         } catch (IOException e) {
	            e.printStackTrace();
	         }
	      } 
	   }

}
