package fr.laple.jdbc.patternDao.table;

public class Symbol extends Dico{
	// private Set<Dico> listDico= new HashSet<Dico>();
	 private String keyDico;
	 
	public Symbol() {
		super();
	}
	public Symbol(int idDico, String typeDico, String nameDico, String keyDico) {
		super.setTypeDico(typeDico);
		super.setNameDico(nameDico);
		super.setIdDico(idDico);
		super.setKeyDico(keyDico);
		this.keyDico = keyDico;
	}
	public Symbol(String typeDico, String nameDico, String keyDico) {
		super.setTypeDico(typeDico);
		super.setNameDico(nameDico);
		this.keyDico = keyDico;
	}
	public Symbol(String keyDico) {
		super();
		this.keyDico = keyDico;
	}
	/*
	public Set<Dico> getListDico() {
		return listDico;
	}
	public void setListDico(Set<Dico> listDico) {
		this.listDico = listDico;
	}
	*/
	public String getKeyDico() {
		return keyDico;
	}
	public void setKeyDico(String keyDico) {
		this.keyDico = keyDico;
	}
	@Override
	public String toString() {
		return "Symbol [idDico=" + super.getIdDico() +" typeDico="+super.getTypeDico()+ " nameDico="+super.getNameDico()+", keySymbol=" + keyDico
				+ "]";
	}
 
	
 
}
