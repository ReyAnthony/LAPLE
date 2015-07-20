package fr.laple.jdbc.patternDao.table;

public class Dico implements Cloneable, Comparable<Dico>{
	protected int idDico=0;
	protected String typeDico="";
	protected String nameDico="";
	protected String keyDico="";
	
	
	public Dico() {
	}
	public Dico(int idDico, String typeDico, String nameDico, String keyDico) {
		this.idDico = idDico;
		this.typeDico=typeDico;
		this.nameDico = nameDico;
		this.keyDico= keyDico;
	}
	public Dico(String typeDico, String nameDico, String keyDico) {
		this.typeDico=typeDico;
		this.nameDico = nameDico;
		this.keyDico=keyDico;
	}
	
	public String getKeyDico() {
		return keyDico;
	}
	public void setKeyDico(String keyDico) {
		this.keyDico = keyDico;
	}
	public int getIdDico() {
		return idDico;
	}
	public void setIdDico(int idDico) {
		this.idDico = idDico;
	}
	
	public String getTypeDico() {
		return typeDico;
	}
	public void setTypeDico(String typeDico) {
		this.typeDico = typeDico;
	}
	public String getNameDico() {
		return nameDico;
	}
	public void setNameDico(String nameDico) {
		this.nameDico = nameDico;
	}
	@Override
	public String toString() {
		return "Dico [idDico=" + idDico + ", typeDico=" + typeDico
				+ ", nameDico=" + nameDico + ", keyDico="+keyDico+ "]";
	}
	@Override
	public int compareTo(Dico o) {
		
		return (idDico==o.idDico)?0:1;
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
