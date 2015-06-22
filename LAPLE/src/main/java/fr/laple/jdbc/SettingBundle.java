package fr.laple.jdbc;

import java.util.Date;

public class SettingBundle {
	String name;
	char[] oldPwd;
	char[] newPwd;
	
	public SettingBundle(String name, char[] oldPwd, char[] newPwd) {
		this.name = name;
		this.oldPwd = oldPwd;
		this.newPwd = newPwd;
		User.profile("chris", "chris@hotmail.fr", false, 1);
	}
	public String getName() {
		return name;
	}
	public char[] getOldPwd() {
		return oldPwd;
	}
	public char[] getNewPwd() {
		return newPwd;
	}
	
	
}
