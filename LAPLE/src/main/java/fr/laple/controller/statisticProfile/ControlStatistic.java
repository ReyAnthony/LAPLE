package fr.laple.controller.statisticProfile;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;

import fr.laple.jdbc.DbHelper;
import fr.laple.jdbc.SettingBundle;
import fr.laple.jdbc.StatBundle;
import fr.laple.view.statisticProfile.ViewStatistics;

public class ControlStatistic {

	public ControlStatistic() throws UnsupportedEncodingException, MalformedURLException, IOException, SQLException, CloneNotSupportedException {
		StatBundle stat= new StatBundle(Timestamp.from(Instant.now()), 10, "10", true, "Hiragana", "3", "japanese", "lesson", "dictation", 10, 3, 3, "Symbol");
		SettingBundle sett= new SettingBundle("fifi@hotmail.fr", "fifi", "pop");
		DbHelper helper=DbHelper.getInstance();
		helper.setBundleSetting(sett);
		helper.setBundleStat(stat);
		helper.getStat();
		helper.addStat();
		new ViewStatistics(500, 500);
	}

}
