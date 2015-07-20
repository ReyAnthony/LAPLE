package fr.laple.jdbc.patternDao.table;

import java.util.Date;
import java.util.Set;
import java.util.HashSet;

public class Associate {
	private Set<Set<Object>> listPk= new HashSet<Set<Object>>();
	
	private Profile profile= new Profile();
	private Statistics statistic = new Statistics();
	private Language language = new Language();
	private Set<Object> list= new HashSet<Object>();
	public Associate() {
	}
	public Associate(Profile profile, Statistics statistics, Language language) throws CloneNotSupportedException {
		this.profile=profile;
		this.statistic=statistics;
		this.language=language;
		this.list.add(profile);
		this.list.add(statistics);
		this.list.add(language);
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
			if(o instanceof Profile){
				Profile profile=(Profile)o;
				l.add(profile.clone());
			}
			if(o instanceof Language){
				Language lanuage=(Language)o;
				l.add(lanuage.toString());
			}
		}
		return l;
	}
	
	
	
	public Set<Set<Object>> getListPk() {
		return listPk;
	}
	public void setListPk(Set<Set<Object>> listPk) {
		this.listPk = listPk;
	}
	public Set<Object> getList() {
		return list;
	}
	
	public void setList(Set<Object> list) {
		this.list = list;
	}
	
	
	
	
	public Profile getProfile() {
		return profile;
	}
	public void setProfile(Profile profile) {
		this.profile = profile;
	}
	public Statistics getStatistic() {
		return statistic;
	}
	public void setStatistic(Statistics statistic) {
		this.statistic = statistic;
	}
	public Language getLanguage() {
		return language;
	}
	public void setLanguage(Language language) {
		this.language = language;
	}
	public void addList(Profile profile, Statistics statistics, Language language) throws CloneNotSupportedException{
		this.list.add(profile);
		this.list.add(statistics);
		this.list.add(language);
		this.listPk.add(this.copy());
		this.list.clear();
	}
	public void removeList(Profile profile, Statistics statistics, Language language){
		
		this.list.add(profile);
		this.list.add(statistics);
		this.list.add(language);
		this.listPk.remove(list);
		this.list.clear();
	}
	@Override
	public String toString() {
		return "Associate [listPk=" + listPk + "]";
	}
	
}
