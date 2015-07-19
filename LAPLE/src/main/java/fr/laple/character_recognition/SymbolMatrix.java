package fr.laple.character_recognition;

import java.io.Serializable;
import java.util.ArrayList;

public class SymbolMatrix implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5099046974538480178L;
	ArrayList<Point> points;
	public SymbolMatrix(ArrayList<Point> points) {
		this.points=points;
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
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((points == null) ? 0 : points.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof ArrayList){
			ArrayList<Point> points= (ArrayList<Point>)obj;
			Boolean bool=false;
			for(Point p: this.points){
				bool=false;
				for(Point q: points){
					if((p.equals(q))){
						bool=true;
						break;
					}
				}
			}
			if(bool==false)
				for(Point q: points){
					bool=false;
					for(Point p: this.points){
						if((q.equals(p))){
							bool=true;
							break;
						}
					}
				}
			if(this.points.size()> (points.size() +70) || (this.points.size() +70) < points.size())
				bool=false;
			return bool;
		}
		return false;	
	}
}
