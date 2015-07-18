package fr.laple.jdbc.patternDao.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.laple.jdbc.patternDao.table.Dico;
import fr.laple.jdbc.patternDao.table.Symbol;

public class SymbolDAO extends DAO<Symbol> {

	public SymbolDAO(Connection connect) {
		// TODO Auto-generated constructor stub
		super(connect);
	}

	@Override
	public boolean create(Symbol obj) throws SQLException {
		// TODO Auto-generated method stub
		int result=0;
		return (result=this.connect.createStatement().executeUpdate("INSERT INTO Symbol (idDico, keySymbol) "+
						"VALUES('"+obj.getIdDico()+ "','"+obj.getKeyDico()+"')"))==1;
	}

	@Override
	public boolean delete(Symbol obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Symbol obj) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Symbol obj, String[] condition) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Symbol find(int id) throws SQLException {
		Symbol symbol= new Symbol();
		ResultSet result=this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
						 ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT S.idDico, keyDico, typeDico, nameDico FROM Symbol S, Dico D"+
								 								" WHERE S.idDico="+id+" AND S.idDico=D.idDico;");
		if(result.first())
			symbol=new Symbol(id, result.getString("typeDico"), result.getString("nameDico"),result.getString("keyDico"));
		return symbol;
	}

	@Override
	public Symbol findLastId() throws SQLException {
		Symbol symbol= new Symbol();
		ResultSet result=this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
				 ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT DISTINCT S.idDico, keyDico, typeDico, nameDico FROM Symbol S, Dico D WHERE "
				 		+ "S.idDico=D.idDico ORDER BY S.idDico ASC");
		while(result.next())
			if(result.last())
				symbol= new Symbol(result.getInt("idDico"), result.getString("typeDico"), result.getString("nameDico"),result.getString("keyDico"));
		return symbol;
	}

	@Override
	public Symbol findByCondition(Symbol obj) throws SQLException {
		Symbol symbol= new Symbol();
		ResultSet result=this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
				 ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Symbol WHERE keyDico="+obj.getKeyDico()+" ;");
		int idDico=0;
		String keyDico=new String();
		if(result.first()){
			idDico=result.getInt("idDico");
			keyDico=result.getString("keyDico");
		}
		
		ResultSet result2=this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
				 ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Dico WHERE idDico="+idDico+" ;");
		if(result2.first())
			symbol= new Symbol(result2.getInt("idDico"), result2.getString("typeDico"), result2.getString("nameDico"), keyDico);
		return symbol;
	}

}
