package fr.laple.jdbc;

import java.util.Date;

public class SettingBundle {
	private String name;
	private String oldPwd;
	private String newPwd;
	
	public SettingBundle(String name, String oldPwd, String newPwd) {
		this.name = name;
		this.oldPwd = oldPwd;
		this.newPwd = newPwd;
		User.profile("chris", "chris@hotmail.fr", false, 1);
	}
	public String getName() {
		return name;
	}
	public String getOldPwd() {
		return oldPwd;
	}
	public String getNewPwd() {
		return newPwd;
	}
	
	
}
