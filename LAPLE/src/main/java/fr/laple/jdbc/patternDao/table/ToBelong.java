package fr.laple.jdbc.patternDao.table;

import java.sql.Timestamp;
import java.util.Set;
import java.util.Date;
import java.util.HashSet;

public class ToBelong{
	private Set<Object> list= new HashSet<Object>();
	private Set<Set<Object>> listPk= new HashSet<Set<Object>>();
	private Dico dico= new Dico();
	private Statistics statistic = new Statistics();
	private Timestamp statDate;
	//1 element c'est tryNumber et le 2ème c'est numbersuccess
	private Integer[] number;
	
	public ToBelong() {
	}
	public ToBelong(Dico dico, Statistics statistics, Timestamp statDate, int tryNumber, int numberSuccess) throws CloneNotSupportedException {
		this.dico=dico;
		this.statistic=statistics;
		this.statDate=statDate;
		this.number= new Integer[2];
		number[0]=tryNumber;
		number[1]=numberSuccess;
		this.list.add(this.dico);
		this.list.add(this.statistic);
		this.list.add(this.statDate);
		this.list.add(this.number);
		this.listPk.add(this.copy());
		this.list.clear();
	}
	
	private Set<Object> copy() throws CloneNotSupportedException{
		//super.clone, c'est ici qu'on fait le clonage
		Set<Object> l= new HashSet<Object>();
		for(Object o: this.list){
			if(o instanceof Statistics){
				Statistics statistics=(Statistics)o;
				l.add(statistics.clone());
			}
			if(o instanceof Dico){
				Dico dico=(Dico)o;
				l.add(dico.clone());
			}
			if(o instanceof Timestamp){
				Timestamp statDate=(Timestamp)o;
				l.add(statDate.clone());
			}
			if(o instanceof Integer[]){
				Integer[] number=(Integer[])o;
				l.add(number.clone());
			}
			if(o instanceof String){
				String keyDico=(String)o;
				l.add(keyDico.toString());
			}
		}
		return l;
	}
	
	public Set<Object> getList() {
		return list;
	}
	public void setList(Set<Object> list) {
		this.list = list;
	}
	
	
	public Set<Set<Object>> getListPk() {
		return listPk;
	}
	public void setListPk(Set<Set<Object>> listPk) {
		this.listPk = listPk;
	}
	public Dico getDico() {
		return dico;
	}
	public void setDico(Dico dico) {
		this.dico = dico;
	}
	public Statistics getStatistic() {
		return statistic;
	}
	public void setStatistic(Statistics statistic) {
		this.statistic = statistic;
	}
	
	
	public Timestamp getStatDate() {
		return statDate;
	}
	public void setStatDate(Timestamp statDate) {
		this.statDate = statDate;
	}
	
	public Integer[] getNumber() {
		return number;
	}
	public void setNumber(Integer[] number) {
		this.number = number;
	}
	public void addListPk(Dico dico, Statistics statistics, Timestamp statDate, int tryNumber, int numberSuccess) throws CloneNotSupportedException{
		this.list.add(dico);
		this.list.add(statistics);
		this.list.add(statDate);
		Integer[] numberTmp= new Integer[2];
		numberTmp[0]=tryNumber;
		numberTmp[1]=numberSuccess;
		this.list.add(numberTmp);
		this.listPk.add(this.copy());
		this.list.clear();
	}
	
	public void removeListPk(Dico dico, Statistics statistics, Timestamp statDate, int tryNumber, int numberSuccess){
		Integer[] numberTmp= new Integer[2];
		numberTmp[0]=tryNumber;
		numberTmp[1]=numberSuccess;
		this.list.add(dico);
		this.list.add(statistics);
		this.list.add(statDate);
		this.list.add(number);
		this.list.remove(this.list);
		this.list.clear();
	}
	@Override
	public String toString() {
		StringBuilder sb= new StringBuilder();
		for(Set<Object> o: this.listPk){
			sb.append(o.toString()+"\n");
		}
		return "ToBelong [listpk="+ sb +"]";
	}
	
}
