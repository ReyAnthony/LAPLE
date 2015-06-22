package fr.laple.jdbc;


import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;

public abstract class DbAccessor {
	private static Connection conn;
	private static String url;
	private static String user;
	private static String passwd;
	
	public static void connect(String users, String passwds){
		url="jdbc:mysql://localhost:3306/laple";
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
	public abstract ArrayList get(String[] select, String[] table, String[] condi);

	public abstract boolean put(String type, String table, String[] col, String[] value);
	
	public abstract boolean delete(String table, String[] condi);
    
}
