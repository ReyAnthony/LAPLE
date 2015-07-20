package fr.laple.jdbc;

import java.sql.Timestamp;
import java.util.Date;



public class StatBundle {
	private Timestamp completionDate;
	private int totalSymbol;
	private String currentSymbol;
	private int tryNumber;
	private int numberSuccess;
	private Boolean success;
	private String nameDico;
	private String typeDico;
	private String keySymbol;
	private String language;
	private String nameStat;
	private String typeStat;
	private int totalNumberEx;
	private int currentNumberEx;
	
	
	
	public StatBundle(String language, String nameStat, String typeStat, String typeDico) {
		this.completionDate = null;
		this.totalSymbol = -1;
		this.currentSymbol = null;
		this.success = false;
		this.numberSuccess = -1;
		this.nameDico = null;
		this.keySymbol = null;
		this.language = language;
		this.nameStat = nameStat;
		this.typeStat = typeStat;
		this.totalNumberEx = -1;
		this.currentNumberEx = -1;
		this.tryNumber= -1;
		this.typeDico=typeDico;
	}
	public StatBundle(Timestamp completionDate, int totalSymbol, String currentSymbol,
			Boolean success, String nameDico, String keySymbol,
			String language, String nameStat, String typeStat, int totalNumberEx,
			int currentNumberEx, int tryNumber, String typeDico) {
		this.completionDate = completionDate;
		this.totalSymbol = totalSymbol;
		this.currentSymbol = currentSymbol;
		this.success = success;
		this.nameDico = nameDico;
		this.keySymbol = keySymbol;
		this.language = language;
		this.nameStat = nameStat;
		this.typeStat = typeStat;
		this.totalNumberEx = totalNumberEx;
		this.currentNumberEx = currentNumberEx;
		this.tryNumber= tryNumber;
		this.typeDico=typeDico;
	}
	
	public int getTotalNumberEx() {
		return totalNumberEx;
	}
	public void setTotalNumberEx(int totalNumberEx) {
		this.totalNumberEx = totalNumberEx;
	}
	public int getCurrentNumberEx() {
		return currentNumberEx;
	}
	public void setCurrentNumberEx(int currentNumberEx) {
		this.currentNumberEx = currentNumberEx;
	}
	public Date getCompletionDate() {
		return completionDate;
	}
	public int getTotalSymbol() {
		return totalSymbol;
	}
	public String getCurrentSymbol() {
		return currentSymbol;
	}
	public Boolean getSuccess() {
		return success;
	}
	public String getNameDico() {
		return nameDico;
	}
	
	public String getLanguage() {
		return language;
	}
	public String getKeySymbol() {
		return keySymbol;
	}
	public void setKeySymbol(String keySymbol) {
		this.keySymbol = keySymbol;
	}
	public void setCompletionDate(Timestamp completionDate) {
		this.completionDate = completionDate;
	}
	public void setTotalSymbol(int totalSymbol) {
		this.totalSymbol = totalSymbol;
	}
	public void setCurrentSymbol(String currentSymbol) {
		this.currentSymbol = currentSymbol;
	}
	public void setSuccess(Boolean success) {
		this.success = success;
	}
	public void setNameDico(String nameDico) {
		this.nameDico = nameDico;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getNameStat() {
		return nameStat;
	}
	public void setNameStat(String nameStat) {
		this.nameStat = nameStat;
	}
	public String getTypeStat() {
		return typeStat;
	}
	public void setTypeStat(String typeStat) {
		this.typeStat = typeStat;
	}
	public int getTryNumber() {
		return tryNumber;
	}
	public void setTryNumber(int tryNumber) {
		this.tryNumber = tryNumber;
	}
	public int getNumberSuccess() {
		return numberSuccess;
	}
	public void setNumberSuccess(int numberSuccess) {
		this.numberSuccess = numberSuccess;
	}
	public String getTypeDico() {
		return typeDico;
	}
	public void setTypeDico(String typeDico) {
		this.typeDico = typeDico;
	}
	@Override
	public String toString() {
		return "StatBundle [completionDate=" + completionDate
				+ ", totalSymbol=" + totalSymbol + ", currentSymbol="
				+ currentSymbol + ", tryNumber=" + tryNumber
				+ ", numberSuccess=" + numberSuccess + ", success=" + success
				+ ", nameDico=" + nameDico + ", typeDico=" + typeDico
				+ ", keySymbol=" + keySymbol + ", language=" + language
				+ ", nameStat=" + nameStat + ", typeStat=" + typeStat
				+ ", totalNumberEx=" + totalNumberEx + ", currentNumberEx="
				+ currentNumberEx + "]";
	}
	
	
}
