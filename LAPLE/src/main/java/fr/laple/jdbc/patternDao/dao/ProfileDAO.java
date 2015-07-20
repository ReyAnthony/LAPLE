package fr.laple.jdbc.patternDao.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.laple.jdbc.patternDao.table.Profile;

public class ProfileDAO extends DAO<Profile>{
	
	public ProfileDAO(Connection connect){
		super(connect);
	}

	@Override
	public boolean create(Profile obj) throws SQLException {
		return (this.connect.createStatement().executeUpdate("INSERT INTO Profile (email, mdp, pseudo) VALUES('"+obj.getEmail()+
						"',"+"'"+obj.getMdp()+"',"+"'"+obj.getPseudo()+"')"))==1;
	}

	@Override
	public boolean delete(Profile obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Profile obj) throws SQLException {
		return (this.connect.createStatement().executeUpdate("UPDATE Profile SET mdp='"+obj.getNewMdp()+"'  WHERE mdp="+
																		"'"+obj.getMdp()+"';"))==1;
	}
	
	@Override
	public boolean update(Profile obj, String[] condition) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Profile find(int idProfile) throws SQLException {
		Profile profile= new Profile();
		ResultSet result=this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
						 ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Profile WHERE id_Profile="+idProfile);
		if(result.first())
			profile=new Profile(idProfile, result.getString("email"), result.getString("mdp"), result.getString("pseudo"));
		return profile;
	}
	@Override
	public Profile findLastId() throws SQLException{
		Profile profile= new Profile();
		ResultSet result=this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
				 ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Profile ORDER BY id_profile ASC");
		while(result.next())
			if(result.last())
				profile= new Profile(Integer.parseInt(result.getString("id_profile")), result.getString("email"), result.getString("mdp"), result.getString("pseudo"));
		return profile;
		
	}

	@Override
	public Profile findByCondition(Profile obj) throws SQLException {
		Profile profile= new Profile();
		ResultSet result=this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
				 ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Profile WHERE email='"+obj.getEmail()+
						 					"' AND mdp='"+obj.getMdp()+"' AND pseudo='"+obj.getPseudo()+"';");
			if(result.first())
				profile= new Profile(result.getInt("id_profile"), result.getString("email"), result.getString("mdp"), result.getString("pseudo"));
		return profile;
	}
}
