package fr.laple.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Web extends DbAccessor{
	/*private String url;
	private String user;
	private String passwd;
	
	
    public Web(String url, String user, String passwd) throws SQLException, ClassNotFoundException {
    	this.url=url;
    	this.user=user;
    	this.passwd=passwd;
    	DbAccessor.connect(this.url, this.user, this.passwd);	
    }
    

	@Override
	public ArrayList get(String[] select, String[] table, String[] condi){
		// TODO Auto-generated method stub
		try{
		Statement statement = DbAccessor.getConn().createStatement();
	      /* Exécution d'une requête de lecture 
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
	public boolean put(String type, String table, String[] col, String[] value) {
		// TODO Auto-generated method stub
		StringBuilder sb1=new StringBuilder();
		StringBuilder sb2=new StringBuilder();
		for(String c: col){
			sb1.append(c + ",");
		}
		int len=sb1.length();
		sb1.delete(len-1, len);
		for(String v: value){
			sb2.append(v + ",");
		}
		len=sb2.length();
		sb2.delete(len-1, len);
		try{
			Statement statement = DbAccessor.getConn().createStatement();
		      /* Exécution d'une requête de lecture /
			if(type.compareTo("UPDATE")!=0){
				statement.executeQuery(type + " " + table + " ("+sb2+");");
			}
			else{
			     statement.executeQuery(type + " " + table + " SET " + sb1 + " WHERE " + sb2 + ";" );
			}
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}      
		return true;
	}

	@Override
	public boolean delete(String table, String[] condi) {
		// TODO Auto-generated method stub
		StringBuilder sb1=new StringBuilder();
		for(String c: condi){
			sb1.append(c + ",");
		}
		int len=sb1.length();
		sb1.delete(len-1, len);
		try{
			Statement statement = DbAccessor.getConn().createStatement();
		      /* Exécution d'une requête de lecture /
				statement.executeQuery("DELETE FROM " + table + "WHERE " + condi +";");
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}      
		return true;
	}
	*/
}
