package fr.laple.jdbc.patternDao.table;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Profile implements Cloneable, Comparable<Profile> {
	private int idProfile=0;
	private Boolean connected;
	private String email="";
	private String mdp="";
	private String newMdp="";
	private String pseudo="";
	
	public Profile() {
		
	}
	public Profile(int idProfile, String email, String mdp, String pseudo) {
		this.idProfile = idProfile;
		this.email = email;
		this.mdp = encode(mdp);
		this.pseudo = pseudo;
	}
	public Profile(String email, String mdp, String pseudo) {
		this.email = email;
		this.mdp = encode(mdp);
		this.pseudo = pseudo;
	}
	public Profile(String email, String mdp, String newMdp, String pseudo) {
		this.email = email;
		this.mdp = encode(mdp);
		this.newMdp=encode(newMdp);
		this.pseudo = pseudo;
	}
	public int getIdProfile() {
		return idProfile;
	}
	public void setIdProfile(int idProfile) {
		this.idProfile = idProfile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNewMdp(){
		return this.newMdp;
	}
	public void setNewMdp(String newMdp){
		this.newMdp=newMdp;
	}
	public String getMdp() {
		return mdp;
	}
	public void setMdp(String mdp) {
		this.mdp = encode(mdp);
	}
	public String getPseudo() {
		return pseudo;
	}
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	
	
	public Boolean getConnected() {
		return connected;
	}
	public void setConnected(Boolean connected) {
		this.connected = connected;
	}
	@Override
	public String toString() {
		return "Profile [idProfile=" + idProfile + ", email=" + email
				+ ", mdp=" + mdp + ", pseudo=" + pseudo + "]";
	}
	
	/**
	 * This method allow to encode string to md5
	 * @param pwd String: string to encode
	 * @return encode string
	 */
	 private String encode(String pwd){
        byte[] key = pwd.getBytes();
        byte[] hash = null;

        try
        {
            hash = MessageDigest.getInstance("MD5").digest(key);
        }
        catch (NoSuchAlgorithmException e)
        {
            throw new Error("No Class encode MD5");
        }

        StringBuilder pwdEncode = new StringBuilder();
        for (int i = 0; i < hash.length; i++)
        {
            String hexa = Integer.toHexString(hash[i]);
            if (hexa.length() == 1)
            {
                pwdEncode.append('0');
                pwdEncode.append(hexa.charAt(hexa.length() - 1));
            }
            else
                pwdEncode.append(hexa.substring(hexa.length() - 2));
        }
        return pwdEncode.toString();
    }
	@Override
	public int compareTo(Profile o) {
		
		return (idProfile==o.idProfile)?0:1;
	}
	
	protected Object clone(){
		try{
			return super.clone();
		}
		catch(CloneNotSupportedException e){
			return null;
		}
	}
}
