package reconnaissanceCarac;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

public class Valider extends JButton implements MouseListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean validation;
	private String name;
	
	public Valider(String str) {
		// TODO Auto-generated constructor stub
		 super(str);
		 validation=false;
		 this.name = str;
		 this.addMouseListener(this);   
	}
	        
	  public void paintComponent(Graphics g){
		  Graphics2D g2d = (Graphics2D)g;
		  GradientPaint gp = new GradientPaint(0, 0, Color.black, 0, 20, Color.green, true);
		  g2d.setPaint(gp); 
		  g2d.drawString(this.name, this.getWidth() / 2 - (this.getWidth() / 2 /4), (this.getHeight() / 2) + 5);
	  }

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		this.validation=true;
	}

	public boolean isValidation() {
		return validation;
	}

	public void setValidation(boolean validation) {
		this.validation = validation;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}       
}
