package fr.laple.jdbc.patternDao.table;

public class Statistics implements Cloneable, Comparable<Statistics>{
	private int idStatistic=0;
	private String type;
	private String nameStat;
	private int totalNumberEx;
	private int currentNumberEx;
	
	
	public Statistics() {
	}


	public Statistics(int idStatistic, String type, String nameStat, int totalNumberEx,
			int currentNumberEx) {
		this.idStatistic = idStatistic;
		this.nameStat = nameStat;
		this.totalNumberEx = totalNumberEx;
		this.currentNumberEx = currentNumberEx;
		this.type=type;
	}
	public Statistics(String type, String nameStat, int totalNumberEx,
			int currentNumberEx) {
		this.nameStat = nameStat;
		this.totalNumberEx = totalNumberEx;
		this.currentNumberEx = currentNumberEx;
		this.type=type;
	}


	public int getIdStatistic() {
		return idStatistic;
	}


	public void setIdStatistic(int idStatistic) {
		this.idStatistic = idStatistic;
	}


	public String getNameStat() {
		return nameStat;
	}


	public void setNameStat(String nameStat) {
		this.nameStat = nameStat;
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


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	@Override
	public String toString() {
		return "Statistics [idStatistic=" + idStatistic + ", type=" + type
				+ ", nameStat=" + nameStat + ", totalNumberEx=" + totalNumberEx
				+ ", currentNumberEx=" + currentNumberEx + "]";
	}


	@Override
	public int compareTo(Statistics o) {
		// TODO Auto-generated method stub
		return (idStatistic==o.idStatistic)?0:1;
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
