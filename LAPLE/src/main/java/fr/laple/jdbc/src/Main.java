import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Main {

	//CTRL + SHIFT + O pour générer les imports
	
	  public static void main(String[] args) {      
		Connection conn=null;
	    try {
	      Class.forName( "com.mysql.jdbc.Driver" );
	      System.out.println("Driver O.K.");

	      String url = "jdbc:mysql://localhost:3306/test";
	      String user = "root";
	      String passwd = "masters";

	      conn = DriverManager.getConnection(url, user, passwd);
	      System.out.println("Connexion effective !");
	      Statement statement = conn.createStatement();
	      /* Exécution d'une requête de lecture */
	      ResultSet resultat = statement.executeQuery( "SELECT * FROM Animal;" );
	      while ( resultat.next() ) {
	    	    int id = resultat.getInt( "id" );
	    	    String espece = resultat.getString( "espece" );
	    	    String nom = resultat.getString( "nom" );

	    	    System.out.println(id + '|' + espece + '|' + nom);
	    	}
	    } catch (Exception e) {
	      e.printStackTrace();
	    }finally {
	        if ( conn != null )
	            try {
	                /* Fermeture de la connexion */
	            	conn.close();
	            } catch ( SQLException ignore ) {
	                /* Si une erreur survient lors de la fermeture, il suffit de l'ignorer. */
	            }
	    }      
	  }

}
