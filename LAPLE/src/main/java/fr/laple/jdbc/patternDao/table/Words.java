package fr.laple.jdbc.patternDao.table;



public class Words extends Dico {
	//private Set<Dico> listDico= new HashSet<Dico>();
	private String keyDico="";
	
	
	public Words() {
		super();
	}
	public Words(int idDico, String typeDico, String nameDico, String keyDico) {
		super.setIdDico(idDico);
		super.setTypeDico(typeDico);
		super.setNameDico(nameDico);
		super.setKeyDico(keyDico);
		this.keyDico = keyDico;
	}
	public Words(String typeDico, String nameDico, String keyDico) {
		super.setTypeDico(typeDico);
		super.setNameDico(nameDico);
		this.keyDico = keyDico;
	}
	public Words(String keyDico) {
		super();
		this.keyDico = keyDico;
	}
	/*public Set<Dico> getListDico() {
		return listDico;
	}
	public void setListDico(Set<Dico> listDico) {
		this.listDico = listDico;
	}*/
	public String getKeyDico() {
		return keyDico;
	}
	public void setKeyDico(String keyDico) {
		this.keyDico = keyDico;
	}
	@Override
	public String toString() {
		return "Words [idDico=" + super.getIdDico() + "nameDico="+super.getNameDico()+" Words [key=" + keyDico + "]";
	}
	
	

}
