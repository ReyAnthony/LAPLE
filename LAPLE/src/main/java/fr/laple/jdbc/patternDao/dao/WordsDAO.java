package fr.laple.jdbc.patternDao.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.laple.jdbc.patternDao.table.Symbol;
import fr.laple.jdbc.patternDao.table.Words;

public class WordsDAO extends DAO<Words>{

	public WordsDAO(Connection connect) {
		super(connect);
	}

	@Override
	public boolean create(Words obj) throws SQLException {
		int result=0;
		return (result=this.connect.createStatement().executeUpdate("INSERT INTO Dico (idDico, keyWords) "+
						"VALUES('"+obj.getIdDico()+ "','"+obj.getKeyDico()+"')"))==1;
	}

	@Override
	public boolean delete(Words obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Words obj) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Words obj, String[] condition) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Words find(int id) throws SQLException {
		Words words= new Words();
		ResultSet result=this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
						 ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT W.idDico, keyDico, typeDico, nameDico FROM Words W, Dico D"+
	 								" WHERE W.idDico="+id+" AND W.idDico=D.idDico;");
		if(result.first())
			words=new Words(id, result.getString("typeDico"), result.getString("nameDico"),result.getString("keyDico"));
		return words;
	}

	@Override
	public Words findLastId() throws SQLException {
		Words words= new Words();
		ResultSet result=this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
				 ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT DISTINCT W.idDico, keyDico, typeDico, nameDico FROM Words W, Dico D WHERE "
				 		+ "W.idDico=D.idDico ORDER BY W.idDico ASC");
		while(result.next())
			if(result.last())
				words= new Words(result.getInt("idDico"), result.getString("typeDico"), result.getString("nameDico"),result.getString("keyDico"));
		return words;
	}

	@Override
	public Words findByCondition(Words obj) throws SQLException {
		Words word= new Words();
		ResultSet result=this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
				 ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Words WHERE keyDico="+obj.getKeyDico()+" ;");
		while(result.next())
			word= new Words(result.getInt("idDico"), result.getString("typeDico"), result.getString("nameDico"), result.getString("keyDico"));
		return word;
	}
	
}
