package fr.laple.jdbc;

import java.util.Date;

import fr.laple.model.language.Symbol;

public class StatBundle {
	private Date completionDate;
	private int totalSymbol;
	private int currentSymbol;
	private Boolean success;
	private String symbolType;
	private String keySymbol;
	private String language;

	public StatBundle(Date completionDate, int totalSymbol, int currentSymbol,
			Boolean success, String symbolType, String keySymbol,
			String language) {
		this.completionDate = completionDate;
		this.totalSymbol = totalSymbol;
		this.currentSymbol = currentSymbol;
		this.success = success;
		this.symbolType = symbolType;
		this.keySymbol = keySymbol;
		this.language = language;
	}
	public Date getCompletionDate() {
		return completionDate;
	}
	public int getTotalSymbol() {
		return totalSymbol;
	}
	public int getCurrentSymbol() {
		return currentSymbol;
	}
	public Boolean getSuccess() {
		return success;
	}
	public String getSymbolType() {
		return symbolType;
	}
	public String getLanguage() {
		return language;
	}
}
