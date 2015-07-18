package fr.laple.jdbc.patternDao.dao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

import fr.laple.jdbc.patternDao.table.Associate;
import fr.laple.jdbc.patternDao.table.Language;
import fr.laple.jdbc.patternDao.table.Profile;
import fr.laple.jdbc.patternDao.table.Statistics;

public class AssociateDAO extends DAO <Associate>{

	public AssociateDAO(Connection connect) {
		super(connect);
	}

	@Override
	public boolean create(Associate obj) throws SQLException {
		Profile profile =new Profile();
		Statistics statistics= new Statistics();
		Language language= new Language();
		Set<Set<Object>> tmpPk=obj.getListPk(); 
		for(Set<Object> oPk: tmpPk){
			if(oPk instanceof Set){
				Set<Object> tmp=(Set<Object>)oPk;
				for(Object o: tmp){
					if(o instanceof Language){
						language = (Language)o;
					}
					if(o instanceof Statistics){
						statistics  = (Statistics)o;
					}
					if(o instanceof Profile){
						profile= (Profile)o;
					}
				}
			}
			try{
				this.connect.createStatement().executeUpdate("INSERT INTO associate (id_profile, idStatistic, idLanguage) "+
							"VALUES("+profile.getIdProfile()+ ","+statistics.getIdStatistic()+","+obj.getLanguage().getIdLanguage()+")");
			}catch(SQLException s){
				System.out.println(s);
				return false;
			}
		}
		
		return true;
	}

	@Override
	public boolean delete(Associate obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Associate obj) throws SQLException {
		Profile profile =new Profile();
		Statistics statistics= new Statistics();
		Language language= new Language();
		Set<Set<Object>> tmpPk=obj.getListPk(); 
		for(Set<Object> oPk: tmpPk){
			if(oPk instanceof Set){
				Set<Object> tmp=(Set<Object>)oPk;
				for(Object o: tmp){
					if(o instanceof Language){
						language = (Language)o;
					}
					if(o instanceof Statistics){
						statistics  = (Statistics)o;
					}
					if(o instanceof Profile){
						profile= (Profile)o;
					}
				}
			}
			try{
			this.connect.createStatement().executeUpdate("UPDATE associate SET idStatistic="+statistics.getIdStatistic()+
																			"  WHERE id_profile="+profile.getIdProfile()+" AND idLanguage="+
																			language.getIdLanguage()+";");
			}catch(SQLException s){
				System.out.println(s);
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean update(Associate obj, String[] condition)
			throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Associate find(int id) throws SQLException, CloneNotSupportedException {
		Associate associate= new Associate();
		Profile profile =new Profile();
		Statistics statistics= new Statistics();
		Language language= new Language();
		ResultSet result=this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
		 		ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT DISTINCT A.id_profile, A.idStatistic, A.idLanguage, type,"+
				 "nameStat, totalNumberEx, currentNumberEx, pseudo, email, mdp, nameLanguage FROM associate A, Statistics S"+
		 		", Profile P, Language L"+
				" WHERE A.idLanguage=L.idLanguage AND A.id_profile=P.id_profile AND A.idStatistic=S.idStatistic "+
		 				" AND A.idStatistic="+id+";");
		if(result.first()){
			profile=new Profile(result.getInt("A.id_profile"),result.getString("email"), result.getString("mdp"), result.getString("pseudo"));
			statistics= new Statistics(result.getInt("idStatistic"), result.getString("type"), result.getString("nameStat"), result.getInt("totalNumberEx"),
										result.getInt("currentNumberEx"));
			language= new Language(result.getInt("idLanguage"), result.getString("nameLanguage"));
			associate=new Associate(profile, statistics, language);
		}
		return associate;
	}

	@Override
	public Associate findLastId() throws SQLException, CloneNotSupportedException {
		Associate associate= new Associate();
		Profile profile= new Profile();
		Statistics statistics = new Statistics();
		Language language= new Language();
		ResultSet result=this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
				 		ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT A.id_profile, A.idStatistic, A.idLanguage, type,"+
						 "nameStat, totalNumber, currentNumberEx, pseudo, email, mdp, nameLanguage FROM associate A"+
				 		", Profile P, Language L"+
						" WHERE A.idLanguage=L.idLanguage AND A.id_profile=P.id_profile AND A.idStatistics=S.idStatistics"+
				 		"ORDER BY idStatistic ASC");
		if(result.last()){
			profile=new Profile(result.getInt("A.id_profile"),result.getString("email"), result.getString("mdp"), result.getString("pseudo"));
			statistics= new Statistics(result.getInt("idStatistic"), result.getString("type"), result.getString("nameStat"), result.getInt("totalNumberEx"), result.getInt("currentNumbeerEx"));
			language= new Language(result.getInt("idLanguage"), result.getString("nameLanguage"));
		}
		associate=new Associate(profile, statistics, language);
		return associate;
	}
	
	@Override
	public Associate findByCondition(Associate obj) throws SQLException, CloneNotSupportedException {
		Associate associate= new Associate();
		Profile profile =new Profile();
		Statistics statistics= new Statistics();
		Language language= new Language();
		Set<Set<Object>> tmpPk=obj.getListPk(); 
		for(Set<Object> oPk: tmpPk){
			if(oPk instanceof Set){
				Set<Object> tmp=(Set<Object>)oPk;
				for(Object o: tmp){
					if(o instanceof Language){
						language = (Language)o;
					}
					if(o instanceof Statistics){
						statistics  = (Statistics)o;
					}
					if(o instanceof Profile){
						profile= (Profile)o;
					}
				}
			}
			ResultSet result=this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					 		ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT DISTINCT A.id_profile, A.idStatistic, A.idLanguage, type,"+
							 "nameStat, totalNumberEx, currentNumberEx, pseudo, email, mdp, nameLanguage FROM associate A, Statistics S"+
					 		", Profile P, Language L"+
							" WHERE P.id_profile="+profile.getIdProfile()+ " AND L.idLanguage="+
					 				obj.getLanguage().getIdLanguage()+
							 		" AND A.idLanguage=L.idLanguage AND A.id_profile=P.id_profile"
							 		+ " AND A.idStatistic=S.idStatistic");
			if(result.first()){
				profile=new Profile(result.getInt("A.id_profile"),result.getString("email"), result.getString("mdp"), result.getString("pseudo"));
				statistics= new Statistics(result.getInt("idStatistic"), result.getString("type"), result.getString("nameStat"), result.getInt("totalNumberEx"),
											result.getInt("currentNumberEx"));
				language= new Language(result.getInt("idLanguage"), result.getString("nameLanguage"));
				associate=new Associate(profile, statistics, language);
			}
			while(result.next()){
				profile=new Profile(result.getInt("A.id_profile"),result.getString("email"), result.getString("mdp"), result.getString("pseudo"));
				statistics= new Statistics(result.getInt("idStatistic"), result.getString("type"), result.getString("nameStat"), result.getInt("totalNumberEx"),
											result.getInt("currentNumberEx"));
				language= new Language(result.getInt("idLanguage"), result.getString("nameLanguage"));
				
				associate.addList(profile, statistics, language);
			}
		}
		return associate;
	}
}
