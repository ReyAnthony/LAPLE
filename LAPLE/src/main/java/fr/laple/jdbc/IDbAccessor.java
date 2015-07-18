package fr.laple.jdbc;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * 
 * @author Christian EBONGUE
 * @category JDBC
 * Comment: This class is static.There are some request to database
 */
public interface  IDbAccessor {
	
	double[] get(StatBundle stat, SettingBundle set) throws SQLException, CloneNotSupportedException;
	//ResultSet get(String email, String password, String[] tenseSql, String language, String nameStat, String typeStat, String typeDico, String currentSymbol) throws UnsupportedEncodingException, MalformedURLException, IOException;
	boolean put(String table, String[] colo, String[] value);
	boolean put(String email, String password, String language, String nameStat, String typeStat, String currentSymbol, String libId, String[] tenseStat, String[] tenseBelong, int idProfile , int idStatistic);
	boolean delete(String table, String[][] condi);
	void get(SettingBundle bundleSetting, StatBundle bundleStat) throws SQLException, CloneNotSupportedException;
	int put(SettingBundle bundleSetting) throws SQLException;
	int put(SettingBundle bundleSetting, StatBundle bundleStat) throws SQLException, CloneNotSupportedException;
}