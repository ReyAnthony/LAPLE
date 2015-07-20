package fr.laple.jdbc.patternDao.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.laple.jdbc.patternDao.table.Language;

public class LanguageDAO  extends DAO<Language>{

	public LanguageDAO(Connection connect) {
		super(connect);
	}

	@Override
	public boolean create(Language obj) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Language obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Language obj) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Language obj, String[] condition) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Language find(int id) throws SQLException {
		Language language= new Language();
		ResultSet result=this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
						 ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Language WHERE idLanguage="+id);
		if(result.first())
			language=new Language(id, result.getString("nameLanguage"));
		return language;
	}

	@Override
	public Language findLastId() throws SQLException {
		Language language= new Language();
		ResultSet result=this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
				 ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Language ORDER BY idLanguage ASC");
		while(result.next())
			if(result.last())
				language= new Language(result.getInt("idLanguage"), result.getString("nameLanguage"));
		return language;
	}


	@Override
	public Language findByCondition(Language obj) throws SQLException {
		Language language= new Language();
		ResultSet result=this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
				 ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Language WHERE nameLanguage='"+ obj.getNameLangue()+ "';");
		while(result.next())
			language= new Language(result.getInt("idLanguage"), result.getString("nameLanguage"));
		return language;
	}

}
