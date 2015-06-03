package management;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Web extends DbAccessor{
	private String user;
	private String passwd;
	
	
    public Web(String user, String passwd) throws SQLException, ClassNotFoundException {
    	this.user=user;
    	this.passwd=passwd;
    	DbAccessor.connect(this.user, this.passwd);	
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
