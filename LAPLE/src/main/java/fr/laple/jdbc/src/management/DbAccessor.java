package management;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;

public abstract class DbAccessor {
	private static Connection conn;
	private static String url;
	private static String user;
	private static String passwd;
	
	public static void connect(String users, String passwds){
		url="jdbc:mysql://localhost:3306/test";
		user = users;
		passwd=passwds;
			try{
	    		Class.forName( "com.mysql.jdbc.Driver" );
	    	    System.out.println("Driver O.K.");
	    	    conn = DriverManager.getConnection(url, user, passwd);
			}catch (Exception e) {
		  	      e.printStackTrace();
			}
	}
	
	public static void connect(String urls, String users, String passwds){
		url=urls;
		user = users;
		passwd=passwds;
			try{
	    		Class.forName( "com.mysql.jdbc.Driver" );
	    	    System.out.println("Driver O.K.");
	    	    conn = DriverManager.getConnection(url, user, passwd);
			}catch (Exception e) {
		  	      e.printStackTrace();
			}
	}
	public static Connection getConn(){
		return conn;
	}
	
	public static void setConn(Connection con){
		conn=con;
	}
	public abstract ArrayList get();

	public abstract boolean put();
	
	public abstract boolean delete();
    
}
