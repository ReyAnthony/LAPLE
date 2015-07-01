package fr.laple.jdbc;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
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
public interface  IDbAccessor {
	
	ArrayList<StringBuilder> get(String[] select, String[] table, String[] condi) throws UnsupportedEncodingException, MalformedURLException, IOException;
	boolean put(String table, String[] colo, String[] value);
	boolean put(String table, String[] col, String[][] value);
	boolean delete(String table, String[][] condi);
}