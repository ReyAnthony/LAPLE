package fr.laple.jdbc;

import java.util.Date;

public class SettingBundle {
	private String email;
	private String pseudo;
	private String oldPwd;
	private String newPwd;
	private boolean connected;
	
	public SettingBundle(String email, String pseudo, String oldPwd) {
		this.email=email;
		this.pseudo = pseudo;
		this.oldPwd = oldPwd;
		this.connected=false;
		User.profile("chris", "chris@hotmail.fr", false, 1);
	}
	
	public SettingBundle(String email, String pseudo, String oldPwd, String newPwd) {
		this.email=email;
		this.pseudo = pseudo;
		this.oldPwd = oldPwd;
		this.newPwd = newPwd;
		this.connected=false;
		User.profile("chris", "chris@hotmail.fr", false, 1);
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPseudo() {
		return pseudo;
	}
	public String getOldPwd() {
		return oldPwd;
	}
	public String getNewPwd() {
		return newPwd;
	}
	public boolean getConnected(){
		return this.connected;
	}
	
	public void setConnected(boolean connected){
		this.connected=connected;
	}
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	public void setOldPwd(String oldPwd) {
		this.oldPwd = oldPwd;
	}
	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
	}
	
}
