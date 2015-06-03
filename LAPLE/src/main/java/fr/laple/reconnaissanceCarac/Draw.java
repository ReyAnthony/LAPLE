package reconnaissanceCarac;

import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

public class Draw extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//Coordonnées du rectangle quadriller
	private int posX;
	private int posY;
	private int heightY;
	private int widthX;
	private String result;
	

	//Les points qui composent le dessin du caractère
	private ArrayList<Point> points=new ArrayList<Point>();
	private ArrayList<Point> pointFile=new ArrayList<Point>();
	
	//permettra de recupérer les coordonnées de la souris
	public MouseEvent e;
	
	
	public Draw() {
		 
		this.posX=-1;
		this.posY=-1;
		this.heightY=200;
		this.widthX=200;
		this.result="Dessiner T";
		this.addMouseListener(new MouseAdapter(){
			//recupère les cordonnées lorsque l'on presse sur la souris
		      public void mousePressed(MouseEvent e){
		    	if(e.getX()>posX && e.getX()<posX +widthX && e.getY()>posY && e.getY()<posY +heightY){
		    		points.add(new Point(e.getX(), e.getY(), 10));
		    		repaint();
		    	}
		      }
		    });

		    this.addMouseMotionListener(new MouseMotionListener(){
		      public void mouseDragged(MouseEvent e) {
		        //Tant que la souris est pressée pendant son mouvement
		    	 //On récupère les coordonnées de la souris
		    	  if(e.getX()>posX && e.getX()<posX +widthX && e.getY()>posY && e.getY()<posY +heightY){
		    	  points.add(new Point(e.getX(), e.getY(), 10));
		        repaint();
		    	}
		      }

		      public void mouseMoved(MouseEvent e) {}
		    });
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
	public ArrayList<Point> getPointFile() {
		return pointFile;
	}
	
	public void setPointFile(ArrayList<Point> pointFile) {
		this.pointFile = pointFile;
	}
	
	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}
	
	public int getHeightY() {
		return heightY;
	}
	
	public void setHeigthY(int heigthY){
		this.heightY=heigthY;
	}
	
	public int getwidthX() {
		return widthX;
	}
	
	public void setHeigth(int widthX){
		this.widthX=widthX;
	}
	
	public ArrayList<Point> getPoints() {
		return points;
	}

	public void setPoints(ArrayList<Point> points) {
		this.points = points;
	}
	
	
	@Override
	public String toString() {
		String str="";
		for(Point p : this.points){
	        
			str= p.toString() + str;
	    }
		return str;
	}
	
	//dessin des composants de la panel
	public void paintComponent(Graphics g){
		this.posX=this.getWidth()/4	;
		this.posY=this.getHeight()/4;
		g.setColor(Color.BLACK);
		g.drawString(result, this.posX, 20);
		g.drawRect(posX, posY, widthX, heightY);
		//on veut obtenir 8 carreaux le long  de la hauteur et
		// de la largeur
		int countX=widthX/8;
		int countY=heightY/8;
		int y=0;
		int x=0;
		
		for(int i=0; i<heightY; i=i+countX){
			y=i+posY;
			g.drawLine(posX, y, posX+widthX, y);
		}
		
		for(int i=0; i<widthX; i=i+countY){
			x=posX+i;
			g.drawLine(x, posY, x, posY+heightY);
		}
		 for(Point p : this.points)
	      {
	        
	          g.fillOval(p.getMouseX(), p.getMouseY(), 10, 10);
	      }
	}

	public boolean compareArray(){
		Boolean bool=false;
		for(Point p: this.points){
			bool=false;
			for(Point q: this.pointFile){
				if((p.equals(q))){
					bool=true;
					break;
				}
			}
		}
		if(bool==false)
			for(Point q: this.pointFile){
				bool=false;
				for(Point p: this.points){
					if((q.equals(p))){
						bool=true;
						break;
					}
				}
			}
		if(this.points.size()> (this.pointFile.size() +70) || (this.points.size() +70) < this.pointFile.size())
			bool=false;
		return bool;
	}
	
}
