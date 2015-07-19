package fr.laple.character_recognition;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
public class Window extends JFrame{
	/**
	 * 
	 * @author christian
	 *
	 *to use matrix, you need to do Area.getMatrix() in this class
	 *This matrix build in 8px * 8px 
	 */
	ObjectOutputStream oos= null;
	ObjectInputStream ois=null;
	private static final long serialVersionUID = 1L;
	private Draw pan = new Draw();
	private JPanel container= new JPanel();
	private JButton bouton= new JButton("Valider");
	private JButton bouton2= new JButton("Reset");
	private Area area;
	public Window(){
	// The window's size is got by divide the screen's size by 2
		
		int width=getToolkit().getScreenSize().width/2;
		int heigth=getToolkit().getScreenSize().height/2;
		this.area= new Area(width, heigth);
		this.setTitle("ma première interface");
		this.setSize(width, heigth);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		container.setLayout(new BorderLayout());
		container.add(pan, BorderLayout.CENTER);
		this.setContentPane(container);
		
		this.getContentPane().add(bouton, BorderLayout.NORTH);
		this.getContentPane().add(bouton2, BorderLayout.SOUTH);
		this.setVisible(true);
		
		this.bouton.addActionListener(new ActionListener() {
			//convert coordinate to pixel when you validate your draw
			//@Override
			public void actionPerformed(ActionEvent e) {
				//area.pointToPixel(pan.getPoints(), pan.getPosX(), pan.getPosY(), pan.cX, pan.cY);
				//System.out.println(area);
				//pan.setImage(area.pixelToImage(pan.getwidthX(), pan.getHeightY(), pan.cX, pan.cY ));
				try {
					
					serializePoint('w');
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				repaint();
			}
		});
		
		this.bouton2.addActionListener(new ActionListener(){
			//Clear the draw
			//@Override
			public void actionPerformed(ActionEvent e) {
				pan.getPoints().clear();
				pan.setResult("Dessiner T");
				repaint();
			}
			
		});
	}

	public ArrayList<Point> serializePoint(char mode) throws IOException{
		SymbolMatrix sm=new SymbolMatrix(null);
		if('w'==mode){
			 sm=new SymbolMatrix(this.pan.getPoints());
			try{
				final FileOutputStream file=  new FileOutputStream("SymbolMatrix.ser");
				oos= new ObjectOutputStream(file);
				//oos.writeObject(new Date());
				oos.writeObject(sm);
				oos.flush();
			}catch(IOException e){
				e.printStackTrace();
			}finally{
				try{
					if(oos != null){
						oos.flush();
						oos.close();
					}
				}catch(final IOException ex){
					ex.printStackTrace();
				}
			}
		}
		else{
			try{
				FileInputStream file= new FileInputStream("SymbolMatrix.ser");
				ois=new ObjectInputStream(file);
				sm =  (SymbolMatrix)ois.readObject();
				System.out.println(sm);
			}catch(final IOException e){
				e.printStackTrace();
			}catch(final ClassNotFoundException c){
				c.printStackTrace();
			}finally{
				try{
					if(ois != null){
						ois.close();
					}
				}catch(final IOException ex){
					ex.printStackTrace();
					
				}
			}
		}
		return sm.getPoints();
	}
}
