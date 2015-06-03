import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import management.DbAccessor;
import management.Local;
import management.Web;

/* A faire: Caster le arraylist de local et web
 ajouter d'autres variables de classe
 Bref ce n'était qu'un test, beaucoup de choses à modifier
 */
public class Main {
	
	  public static void main(String[] args) throws SQLException, ClassNotFoundException {      
		Local bdd= new Local("jdbc:mysql://localhost:3306/test", "root", "masters");
		Web bdd2= new Web("root", "masters");
		bdd.get();
		bdd2.get();
		if ( DbAccessor.getConn()!= null )
			try {
            /* Fermeture de la connexion */
        	DbAccessor.getConn().close();
			} catch ( SQLException ignore ) {
            /* Si une erreur survient lors de la fermeture, il suffit de l'ignorer. */
        }
	  }

}
