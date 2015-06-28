package fr.laple.jdbc;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * 
 * @author Christian EBONGUE
 * @category JDBC
 * Comment: This class is static.There are some request to database
 */
public class DbAccessor {
	private static Connection conn;
	private static String url;
	private static String user;
	private static String passwd;
	/**
	 * This allow to connect to database. If there is no connexion,
	 * the connexion will be etablished to local
	 * @param users String: Users of laple in base
	 * @param passwds String: password of laple in base
	 * @exception ClassNotFoundException is up if Driver jdbc not found
	 * @exception SQLException is up if sql error or any communication to database.
	 * In particular not connexion to network
	 * @exception Exception if exceptions above had not been catch
	 */
	static void connect(String users, String passwds) {
		url="jdbc:mysql://www.laple.fr:3306/laple";
		user = users;
		passwd=passwds;
			try{
	    		try {
					Class.forName( "com.mysql.jdbc.Driver" );
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    	    System.out.println("Driver O.K.");
	    	    conn = DriverManager.getConnection(url, user, passwd);
			} catch (SQLException s) {
				// TODO Auto-generated catch block
				System.out.println("NO connexion to network. going to connect to local");
				url="jdbc:mysql://localhost:3306/laple";
				try{
		    	    conn = DriverManager.getConnection(url, user, passwd);
				}catch (Exception e) {
			  	      e.printStackTrace();
				}
			}catch (Exception e) {
				 e.printStackTrace();
			}
	}
	
	static Connection getConn(){
		return conn;
	}
	
	static void setConn(Connection con){
		conn=con;
	}

	/**
	 * Equivalent to select about request sql
	 * @param select array of string: Fields to display
	 * @param table String: Name of table in base
	 * @param condi Array of String: the where condition 
	 * @return list ArrayList of the result of select
	 */
	static ArrayList<StringBuilder> get(String[] select, String[] table, String[] condi) {
		// TODO Auto-generated method stub
		StringBuilder sb1=new StringBuilder();
		StringBuilder sb2=new StringBuilder();
		StringBuilder sb3=new StringBuilder();
		StringBuilder sb4=new StringBuilder();
		ArrayList<StringBuilder> result= new ArrayList<StringBuilder>();
		//on transforme le tableau en chaine séparée par des virgules, afin de
		// de respecter la syntaxe sql
		for(String s: select){
			sb1.append(s + ",");
		}
		int len=sb1.length();
		//suppression de la virgule en fin de chaine
		sb1.delete(len-1, len);
		for(String t: table){
			sb2.append(t + ",");
		}
		len=sb2.length();
		sb2.delete(len-1, len);
		if(condi!=null){
			for(String c: condi){
				sb3.append(c);
			}
		}
		try{
			Statement statement = getConn().createStatement();
		      /* Exécution d'une requête de lecture */
			ResultSet resultat;
			if(condi==null){
				resultat = statement.executeQuery("SELECT " + sb1 + " FROM "
						+ sb2 + ";" );
			}
			else{
			    resultat = statement.executeQuery("SELECT " + sb1 + " FROM "
			    								  + sb2 +" WHERE " + sb3 + ";" );
			}
			
		    while ( resultat.next() ) {
			    for(String s: select){
			    	sb4.append(resultat.getString(s) + "|");
			    }
			    StringBuilder cpy=new StringBuilder();
			    cpy.append(sb4);
			    result.add(cpy);
			    int size=sb4.length();
			    sb4.delete(0, size);
		    }
		}catch (Exception e) {
			e.printStackTrace();
		}      
		return result;
	}

	/**
	 * Equivalent to INSERT sql
	 * @param table String: name table in database
	 * @param colo Array of String: column to be insert
	 * @param value Array of String: value to insert
	 * @return true if insert success, false if not
	 */
	static boolean put(String table, String[] colo, String[] value) {
		// TODO Auto-generated method stub
		StringBuilder sb1=new StringBuilder();
		StringBuilder sb2=new StringBuilder();
		int len=0;
		for(String c: colo){
			sb1.append(c + ",");
		}
		len=sb1.length();
		sb1.delete(len-1, len);
		for(String v: value){
			sb2.append(v + ",");
		}
			len=sb2.length();
			sb2.delete(len-1, len);
		try{System.out.println("INSERT INTO "+table+ " ("+ sb1+ ") VALUES " + "("+sb2+");");
			Statement statement = DbAccessor.getConn().createStatement();
		      /* Exécution d'une requête de lecture */
			statement.executeUpdate("INSERT INTO "+table+ " ("+ sb1+ ") VALUES " + "("+sb2+");");
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}      
		return true;
	}
	
	/**
	 * Equivalent to UPDATE
	 * @param table String: name table in database
	 * @param col Array String: it's SET. Example: {"col1='value1'","col2='value2'"}
	 * @param value Array of Array String: It's Where. Example: {{"col1 = val1"},{"AND"}, {"col2 = val2"}}
	 * @return true if update success, false if not
	 */
	static boolean put(String table, String[] col, String[][] value) {
		// TODO Auto-generated method stub
		StringBuilder sb1=new StringBuilder();
		StringBuilder sb2=new StringBuilder();
		int len=0;
		if(col!=null){
			for(String c: col){
				sb1.append(c + ",");
			}
			len=sb1.length();
			sb1.delete(len-1, len);
		}
		for(String[] v1: value){
			for(String v2: v1){
				sb2.append(v2 + " ");
			}
			
		}
		try{System.out.println("UPDATE " + table +" SET " + sb1 + " WHERE " + sb2 +";");
			Statement statement = DbAccessor.getConn().createStatement();
		      /* Exécution d'une requête de lecture */
			statement.executeUpdate("UPDATE " + table +" SET " + sb1 + " WHERE " + sb2 +";");
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}      
		return true;
	}

	/**
	 * Delete a line on table in database
	 * @param table String: Name table in database
	 * @param condi: Array of Array String: It's Where. Example: {{"col1 = val1"},{"AND"}, {"col2 = val2"}}
	 * @return true if delete success, false if not
	 */
	static boolean delete(String table, String[][] condi) {
		// TODO Auto-generated method stub
		StringBuilder sb1=new StringBuilder();
		for(String[] c1: condi){
			for(String c2: c1)
				sb1.append(c2 + " ");
		}
		int len=sb1.length();
		sb1.delete(len-1, len);
		try{System.out.println("DELETE FROM " + table + " WHERE " + sb1 +";");
			Statement statement = DbAccessor.getConn().createStatement();
		      /* Exécution d'une requête de lecture */
				statement.executeUpdate("DELETE FROM " + table + " WHERE " + sb1 +";");
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}      
		return true;
	}
    
}
