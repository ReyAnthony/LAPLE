package fr.laple.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public final class Local extends DbAccessor{
	private String user;
	private String passwd;
	
	
    public Local(String user, String passwd) throws SQLException, ClassNotFoundException {
    	this.user=user;
    	this.passwd=passwd;
    	DbAccessor.connect(this.user, this.passwd);	
    }
    

	@Override
	public ArrayList<StringBuilder> get(String[] select, String[] table, String[] condi) {
		// TODO Auto-generated method stub
		StringBuilder sb1=new StringBuilder();
		StringBuilder sb2=new StringBuilder();
		StringBuilder sb3=new StringBuilder();
		StringBuilder sb4=new StringBuilder();
		ArrayList<StringBuilder> result= new ArrayList<StringBuilder>();
		for(String s: select){
			sb1.append(s + ",");
		}
		int len=sb1.length();
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
		try{System.out.println("SELECT " + sb1 + " FROM "
				+ sb2 + ";" );
			Statement statement = DbAccessor.getConn().createStatement();
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
			    result.add(sb4);
			    int size=sb4.length();
			    sb4.delete(0, size);
		    }
		}catch (Exception e) {
			e.printStackTrace();
		}      
			return result;
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
		      /* Exécution d'une requête de lecture */
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
		      /* Exécution d'une requête de lecture */
				statement.executeQuery("DELETE FROM " + table + "WHERE " + condi +";");
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}      
		return true;
	}

	
    
	
	
}
