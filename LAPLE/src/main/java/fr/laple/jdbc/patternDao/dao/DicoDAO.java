package fr.laple.jdbc.patternDao.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.laple.jdbc.patternDao.table.Dico;

public class DicoDAO extends DAO<Dico> {

	public DicoDAO(Connection connect) {
		super(connect);
	}

	@Override
	public boolean create(Dico obj) throws SQLException {
		return (this.connect.createStatement().executeUpdate("INSERT INTO Dico (typeDico, nameDico, keyDico) "+
						"VALUES('"+obj.getTypeDico()+ "',"+"'"+obj.getNameDico()+"'"+obj.getKeyDico()+"')"))==1;
	}

	@Override
	public boolean delete(Dico obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Dico obj) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Dico obj, String[] condition) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Dico find(int id) throws SQLException {
		Dico dico= new Dico();
		ResultSet result=this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
						 ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT DISTINCT * FROM Dico D "+
								 	"WHERE idDico="+id+";");
		if(result.first())
			dico=new Dico(id, result.getString("typeDico"), result.getString("nameDico"), result.getString("keyDico"));
		return dico;
	}

	@Override
	public Dico findLastId() throws SQLException {
			
			Dico dico= new Dico();
		ResultSet result=this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
				 ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT DISTINCT * FROM Dico ORDER BY idDico ASC;");
		while(result.next())
			if(result.last())
				dico= new Dico(result.getInt("idDico"), result.getString("typeDico"), result.getString("nameDico"), result.getString("keyDico"));
		return dico;
	}

	@Override
	public Dico findByCondition(Dico obj) throws SQLException {
		Dico dico= new Dico();
		ResultSet result=this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
				 ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Dico WHERE typeDico='"+obj.getTypeDico()+
						 								"' AND nameDico='"+obj.getNameDico()+"' AND keyDico='"+obj.getKeyDico()+"';");
		while(result.next())
			dico= new Dico(result.getInt("idDico"), result.getString("typeDico"), result.getString("nameDico"), result.getString("keyDico"));
		return dico;
	}

}
