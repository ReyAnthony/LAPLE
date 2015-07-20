package fr.laple.jdbc.patternDao.dao;
import java.sql.Connection;
import java.sql.SQLException;


public abstract class DAO<T> {
	 protected Connection connect = null;
	   
	  public DAO(Connection connect){
	    this.connect = connect;
	  }
	  
	  /**
	   * Méthode de création
	   * @param obj
	   * @return boolean 
	 * @throws SQLException 
	   */
	   public abstract boolean create(T obj) throws SQLException;

	   /**
	   * Méthode pour effacer
	   * @param obj
	   * @return boolean 
	   */
	   public abstract boolean delete(T obj);

	   /**
	   * Méthode de mise à jour
	   * @param obj
	   * @return boolean
	 * @throws SQLException 
	   */
	   public abstract boolean update(T obj) throws SQLException;
	   public abstract boolean update(T obj, String[] condition) throws SQLException;
	   /**
	   * Méthode de recherche des informations via l'id
	   * @param id
	   * @return T
	 * @throws SQLException 
	 * @throws CloneNotSupportedException 
	   */
	   public abstract T find(int id) throws SQLException, CloneNotSupportedException;
	   /**
		   * Méthode de recherche des informations via sur le dernier enregistrement
		   * @param id
		   * @return T
		 * @throws SQLException 
	 * @throws CloneNotSupportedException 
		   */
	   public abstract T findLastId() throws SQLException, CloneNotSupportedException;
	   
	   public abstract T findByCondition(T obj) throws SQLException, CloneNotSupportedException;
	   
}
