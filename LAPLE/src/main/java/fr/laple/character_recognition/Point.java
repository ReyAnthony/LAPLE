package fr.laple.character_recognition;


public class Point{
	int mouseX;
	int mouseY;
	int mouseSize;

	public Point(){
		this.mouseX = -1;
		this.mouseY = -1;
		this.mouseSize = -1;
	}
	public Point(int mouseX, int mouseY, int mouseSize) {
		this.mouseX = mouseX;
		this.mouseY = mouseY;
		this.mouseSize = mouseSize;
	}
	public int getMouseX() {
		return mouseX;
	}
	public void setMouseX(int mouseX) {
		this.mouseX = mouseX;
	}
	public int getMouseY() {
		return mouseY;
	}
	public void setMouseY(int mouseY) {
		this.mouseY = mouseY;
	}
	public int getMouseSize() {
		return mouseSize;
	}
	public void setMouseSize(int mouseSize) {
		this.mouseSize = mouseSize;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + mouseSize;
		result = prime * result + mouseX;
		result = prime * result + mouseY;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Point)){
			return false;
		}
		
		Point other = (Point) obj;
		if (this.mouseSize != other.mouseSize)
			return false;
		if ((this.mouseX <other.mouseX-10) || (this.mouseX >other.mouseX+10))
			return false;
		if ((this.mouseY <other.mouseY-10) || (this.mouseY >other.mouseY+10))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Point [mouseX=" + mouseX + " mouseY=" + mouseY + "] \n";
	}
	

}
