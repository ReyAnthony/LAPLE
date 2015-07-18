package fr.laple.character_recognition;

import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

public class Draw extends JPanel{
	/**
	 * first version
	 */
	private static final long serialVersionUID = 1L;
	//The coordinates of area
	private int posX;
	private int posY;
	private int heightY;
	private int widthX;
	public final int cX;
	public final int cY;
	private String result;
	

	//list of coordinate of point
	private ArrayList<Point> points=new ArrayList<Point>();
	
	//to get coordinates of mouse
	public MouseEvent e;
	
	
	public Draw() {
		 
		this.posX=-1;
		this.posY=-1;
		this.heightY=200;
		this.widthX=200;
		cX=heightY/8;
		cY=widthX/8;
		this.result="Dessiner T";
		this.addMouseListener(new MouseAdapter(){
			//save coordinate of mouse pressed 
		      public void mousePressed(MouseEvent e){
		    	if(e.getX()>posX && e.getX()<posX +widthX && e.getY()>posY && e.getY()<posY +heightY){
		    		points.add(new Point(e.getX(), e.getY(), 10));
		    		repaint();
		    	}
		      }
		    });

		    this.addMouseMotionListener(new MouseMotionListener(){
		      public void mouseDragged(MouseEvent e) {
		        //Save coordinate of mouse dragged
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
	/**
	 * create area to draw. 
	 */
	public void paintComponent(Graphics g){
		this.posX=this.getWidth()/4	;
		this.posY=this.getHeight()/4;
		g.setColor(Color.BLACK);
		g.drawString(result, this.posX, 20);
		g.drawRect(posX, posY, widthX, heightY);
		//we get 8 square in height and 8 square in width
		int y=0;
		int x=0;
		
		for(int i=0; i<heightY; i=i+cX){
			y=i+posY;
			g.drawLine(posX, y, posX+widthX, y);
		}
		
		for(int i=0; i<widthX; i=i+cY){
			x=posX+i;
			g.drawLine(x, posY, x, posY+heightY);
		}
		 for(Point p : this.points)
	      {
	        
	          g.fillOval(p.getMouseX(), p.getMouseY(), 10, 10);
	      }
	}
	
}
