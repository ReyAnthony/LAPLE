package fr.laple.jdbc.patternDao.table;

public class Language implements Cloneable, Comparable<Language>{
	private int idLanguage;
	private String nameLangue;
	
	
	public Language() {
	}


	public Language(int idLanguage, String nameLangue) {
		this.idLanguage = idLanguage;
		this.nameLangue = nameLangue;
	}
	
	public Language(String nameLangue) {
		this.nameLangue = nameLangue;
	}
	
	
	public int getIdLanguage() {
		return idLanguage;
	}
	public void setIdLanguage(int idLanguage) {
		this.idLanguage = idLanguage;
	}
	public String getNameLangue() {
		return nameLangue;
	}
	public void setNameLangue(String nameLangue) {
		this.nameLangue = nameLangue;
	}


	@Override
	public String toString() {
		return "Language [idLanguage=" + idLanguage + ", nameLangue="
				+ nameLangue + "]";
	}
	
	protected Object clone(){
		try{
			return super.clone();
		}
		catch(CloneNotSupportedException e){
			return null;
		}
	}


	@Override
	public int compareTo(Language o) {
		// TODO Auto-generated method stub
		return (idLanguage==o.idLanguage)?0:1;
	}
	
}
