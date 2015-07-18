package fr.laple.jdbc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Web implements IDbAccessor{
	private static String url;
	
	
    public Web() throws SQLException, ClassNotFoundException {
    	url="localhosgt/webservice";
    }


	@Override
	public double[] get(StatBundle stat, SettingBundle set)
			throws SQLException, CloneNotSupportedException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public boolean put(String table, String[] colo, String[] value) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean put(String email, String password, String language,
			String nameStat, String typeStat, String currentSymbol,
			String libId, String[] tenseStat, String[] tenseBelong,
			int idProfile, int idStatistic) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean delete(String table, String[][] condi) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public void get(SettingBundle bundleSetting, StatBundle bundleStat)
			throws SQLException, CloneNotSupportedException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public int put(SettingBundle bundleSetting) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public int put(SettingBundle bundleSetting, StatBundle bundleStat)
			throws SQLException, CloneNotSupportedException {
		// TODO Auto-generated method stub
		return 0;
	}
    

	
}
