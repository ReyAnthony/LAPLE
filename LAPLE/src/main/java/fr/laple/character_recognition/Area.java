package fr.laple.character_recognition;

import java.util.ArrayList;

public class Area {
	public static final int VIDE=0;
	public static final int PLEIN=1;
	public static final int X=8;
	public static final int Y=8;
	
	private int[] matrix;
	
	public Area(int width, int height) {
		this.matrix = new int[X * Y];
		for(int i=0; i<matrix.length;i++)
			matrix[i]=VIDE;
	}
	
	
	public int[] getMatrix() {
		return matrix;
	}
	/**
	 * Convert the coordinated of point to pixel
	 * @param point	List of points. Result of  mouse dragged
	 * @param posX	Origin x of landmark
	 * @param posY	Origin y of landmark
	 * @param cX the side x of one square
	 * @param cY the side y of one square
	 *  @exception if one coordinate of point does'nt in area 
	 */
	public void pointToPixel(ArrayList<Point> point, int posX, int posY, int cX, int cY){
		int pixelX;
		int pixelY;
		for(Point p: point){
			pixelX = (p.getMouseX()-posX)/cX;
			pixelY=(p.getMouseY()-posY)/cY;
			try{
				if(pixelY==0){
					matrix[pixelX]=PLEIN;
				}
				else{
					matrix[pixelY*8 + pixelX]=PLEIN;
				}
			}catch(ArrayIndexOutOfBoundsException e){
				System.out.println(e);
			}
		}
	}
	@Override
	public String toString() {
		String str="{";
		for(int p : this.matrix){
	        
			str= str + ";" + p;
	    }
		str=str+"}";
		return str;
	}
	
}
