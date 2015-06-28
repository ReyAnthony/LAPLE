package fr.laple.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public final class Local extends DbAccessor{
/*	private String user;
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
		       Exécution d'une requête de lecture 
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

	@Override
	public boolean put(String table, String[] colo, String[] value) {
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
		      /* Exécution d'une requête de lecture 
			statement.executeUpdate("INSERT INTO "+table+ " ("+ sb1+ ") VALUES " + "("+sb2+");");
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}      
		return true;
	}
	
	@Override
	public boolean put(String table, String[] col, String[][] value) {
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
		      /* Exécution d'une requête de lecture 
			statement.executeUpdate("UPDATE " + table +" SET " + sb1 + " WHERE " + sb2 +";");
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}      
		return true;
	}

	@Override
	public boolean delete(String table, String[][] condi) {
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
		      /* Exécution d'une requête de lecture 
				statement.executeUpdate("DELETE FROM " + table + " WHERE " + sb1 +";");
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}      
		return true;
	}

	*/
    
	
	
}
