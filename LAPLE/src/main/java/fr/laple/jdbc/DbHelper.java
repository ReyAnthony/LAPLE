package fr.laple.jdbc;
import java.sql.SQLException;
import java.util.ArrayList;

import fr.laple.jdbc.User;
public class DbHelper {
	StatBundle bundleStat;
	SettingBundle bundleSetting;
	Local con;
	public DbHelper(StatBundle bundleStat, SettingBundle bundleSetting) throws ClassNotFoundException, SQLException {
		this.bundleStat = bundleStat;
		this.bundleSetting = bundleSetting;
		this.con= new Local("root", "");
       
	}
	
	public ArrayList<StringBuilder> getStat(){
		String[] select={"name", "email"};
		String[] table={"Profile"};
		String[] condi={""};
		ArrayList<StringBuilder> list= new ArrayList<StringBuilder>();
		 list=this.con.get(select, table, null);
		 return list;
	}
	public int addStat(){
		this.con.put(null, null, null, null);
		return 0;
	}
	
}
