package character_recognition;


import javax.swing.SwingUtilities;

public class Main {


	public static void main(String[] args){
		// TODO Auto-generated method stub
		Runnable tr= new Runnable() {	
			//@Override
			public void run() {
				// TODO Auto-generated method stub
				new Window();
			}
		};
		SwingUtilities.invokeLater(tr);
	}

}
