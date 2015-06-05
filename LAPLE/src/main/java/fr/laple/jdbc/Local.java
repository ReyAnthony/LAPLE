package management;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public final class Local extends DbAccessor{
	private String url;
	private String user;
	private String passwd;
	
	
    public Local(String url, String user, String passwd) throws SQLException, ClassNotFoundException {
    	this.url=url;
    	this.user=user;
    	this.passwd=passwd;
    	DbAccessor.connect(this.url, this.user, this.passwd);	
    }
    

	@Override
	public ArrayList get() {
		// TODO Auto-generated method stub
		try{
		Statement statement = DbAccessor.getConn().createStatement();
	      /* Exécution d'une requête de lecture */
	    ResultSet resultat = statement.executeQuery( "SELECT * FROM Animal;" );
	    while ( resultat.next() ) {
	    	int id = resultat.getInt( "id" );
  	    String espece = resultat.getString( "espece" );
  	    String nom = resultat.getString( "nom" );
	
  	    System.out.println(id + '|' + espece + '|' + nom);
	    	}
		}catch (Exception e) {
			e.printStackTrace();
		}      
		return null;
	}

	@Override
	public boolean put() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete() {
		// TODO Auto-generated method stub
		return false;
	}

	
    
	
	
}
