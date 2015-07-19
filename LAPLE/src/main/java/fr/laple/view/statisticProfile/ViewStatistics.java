package fr.laple.view.statisticProfile;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import fr.laple.jdbc.DbHelper;
import fr.laple.jdbc.SettingBundle;
import fr.laple.jdbc.StatBundle;

public class ViewStatistics extends JFrame{
	private int heightWin;
	private int widthWin;
	
	

	private JMenuBar jmb= new JMenuBar();
	
	private JMenuItem all_stat= new JMenuItem("All stat");
	private JMenu lesson= new JMenu("Lesson");
	private JMenu exercise= new JMenu("Exercise");
	private JMenu set= new JMenu("Setting");
	private JMenu stat=new JMenu("Statistics");
	
	private JMenuItem funzone1= new JMenuItem("funzone");
	private JMenuItem dictation1= new JMenuItem("dictation");
	private JMenuItem funzone2= new JMenuItem("funzone");
	private JMenuItem dictation2= new JMenuItem("dictation");
	private JMenuItem item1= new JMenuItem("display");
	private JMenuItem item2= new JMenuItem("Change password");
	
	public ViewStatistics(int heightWin, int widthWin) {
		this.heightWin=heightWin;
		this.widthWin=widthWin;
		setSize(this.heightWin, this.widthWin);
		setTitle("Pannel statistic");
		this.lesson.add(this.funzone1);
		this.lesson.add(this.dictation1);
		
		this.exercise.add(this.funzone2);
		this.exercise.add(this.dictation2);
	
		this.stat.add(this.all_stat);
		this.stat.add(this.lesson);
		this.stat.add(this.exercise);
		
		this.set.add(item1);
		this.set.add(this.item2);
		this.jmb.add(this.stat);
		this.jmb.add(this.set);
		this.setJMenuBar(jmb);
		this.funzone1.addActionListener(new ActionListener() {
			private JLabel jl= new JLabel();
			@Override
			public void actionPerformed(ActionEvent e) {
				
				StatBundle stat= new StatBundle(Timestamp.from(Instant.now()), 10, "10", true, "Hiragana", "3", "japanese", "lesson", "dictation", 10, 3, 3, "Symbol");
				SettingBundle sett= new SettingBundle("fifi@hotmail.fr", "fifi", "pop");
				DbHelper helper=DbHelper.getInstance();
				helper.setBundleSetting(sett);
				helper.setBundleStat(stat);
				try {
					helper.getStat();
				} catch (IOException | SQLException
						| CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					helper.addStat();
				} catch (SQLException | CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				String text=DbHelper.getAllStat()+"";
				jl.setText("Lesson:"+text);
				Container ct= getContentPane();
				ct.add(jl, BorderLayout.NORTH);
				setVisible(true);
			}
		});
		//ct.add(this.jmb);
		this.setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
	}


}
