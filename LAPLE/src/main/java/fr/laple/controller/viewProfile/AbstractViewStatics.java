package fr.laple.controller.viewProfile;

import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.time.Instant;

import javax.swing.JLabel;
import javax.swing.JMenuItem;

import fr.laple.jdbc.DbHelper;
import fr.laple.jdbc.SettingBundle;
import fr.laple.jdbc.StatBundle;
import fr.laple.view.statisticProfile.ViewStatistics;

public abstract class AbstractViewStatics implements ActionListener{
	
	StatBundle stat= new StatBundle(Timestamp.from(Instant.now()), 10, "10", true, "Hiragana", "3", "japanese", "lesson", "dictation", 10, 3, 3, "Symbol");
	SettingBundle sett= new SettingBundle("fifi@hotmail.fr", "fifi", "pop");
	DbHelper helper=DbHelper.getInstance();
	ViewStatistics vs= new ViewStatistics(700, 700);
	public AbstractViewStatics(){
		// TODO Auto-generated constructor stub
		helper.setBundleSetting(sett);
		helper.setBundleStat(stat);
	}
	public void actionPerformed(JMenuItem jMenuItem) {
		// TODO Auto-generated method stub
		
	}
	
}
