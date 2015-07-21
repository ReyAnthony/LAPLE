package fr.laple.jdbc.patternDao.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.laple.jdbc.patternDao.table.Profile;
/**
 * 
 * @author Christian EBONGUE
 *comment: This class allow you to do request in database about Profile table
 * profile table is which describe users
 */
public class ProfileDAO extends DAO<Profile>{

	public ProfileDAO(Connection connect){
		super(connect);
	}
	/**
	 * this method create a new obj profile in database.
	 * Return true if create success
	 * else false.
	 * @param obj(Set): profile
	 * @return boolean
	 */
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
	/**
	 * this method update a new obj profile in database.
	 * Return true if update success
	 * else false.
	 * @param obj(Set): obj contents object language, profile, statistics
	 * @return boolean
	 **/
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
	/**
	 * this method find obj Profile by id in database.
	 * else false.
	 * @param id (int): obj contents object language, profile, statistics
	 * @return obj Assoc
	 **/
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
	/**
	 * this method find last insert obj  in database. obj contents object language, profile, statistics
	 * else false.
	 * @param obj(Set): obj contents object language, profile, statistics
	 * @return obj Associate
	 **/
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
