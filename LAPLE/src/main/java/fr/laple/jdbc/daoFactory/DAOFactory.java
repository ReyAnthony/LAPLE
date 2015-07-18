package fr.laple.jdbc.daoFactory;

import java.sql.Connection;

import fr.laple.jdbc.DbHelper;
import fr.laple.jdbc.patternDao.dao.*;


public class DAOFactory {
	protected static final Connection connect=DbHelper.getConn();
	
	
	public static DAO<?> getAssociateDAO() {
		return new AssociateDAO(connect);
	}
	
	public static DAO<?> getDicoDAO(){
		return new DicoDAO(connect);
	}
	
	public static DAO<?> getLanguageDAO(){
		return new LanguageDAO(connect);
	}
	
	public static DAO<?> getProfileDAO(){
		return new ProfileDAO(connect);
	}
	
	public static DAO<?> getStatisticsDAO(){
		return new StatisticsDAO(connect);
	}
	
	public static DAO<?> getSymbolDAO(){
		return new SymbolDAO(connect);
	}
	
	public static DAO<?> getToBelongDAO(){
		return new ToBelongDAO(connect);
	}
	
	public static DAO<?> getWordsDAO(){
		return new WordsDAO(connect);
	}

}
