package fr.laple.jdbc;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;


/**
 * 
 * @author Christian EBONGUE
 * @category JDBC
 * Comment: This class update statistic and profile
 *
 */
public class DbHelper {
	
	public static StatBundle bundleStat;
	public static SettingBundle bundleSetting;
	public static Connection conn;
	private static String url;
	private static String user;
	private static String passwd;
	static IDbAccessor localWeb;
	static IDbAccessor web;
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
	public static void connect(String users, String passwds) throws ClassNotFoundException, SQLException {
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
	    	    localWeb= new Web(url, bundleSetting.getName(), bundleSetting.getOldPwd());
			} catch (SQLException s) {
				// TODO Auto-generated catch block
				System.out.println("NO connexion to network. going to connect to local");
				url="jdbc:mysql://localhost:3306/laple";
				try{
		    	    conn = DriverManager.getConnection(url, user, passwd);
		    	    localWeb=new Local(bundleSetting.getName(), bundleSetting.getOldPwd());
				}catch (Exception e) {
			  	      e.printStackTrace();
				}
			}catch (Exception e) {
				 e.printStackTrace();
			}
	}
	
	

	/**
	 * SELECT sql
	 * @param selectTense array of string: Fields to display
	 * @param tableName String: Name of table in base
	 * @param condiTense Array of String: the where condition 
	 * @return list ArrayList of the result of select
	 * @throws IOException 
	 * @throws MalformedURLException 
	 * @throws UnsupportedEncodingException 
	 */
	public static ArrayList<StringBuilder> getStat(String[] tableName, String[] selectTense, String[] condiTense) throws UnsupportedEncodingException, MalformedURLException, IOException{
		
		ArrayList<StringBuilder> list= new ArrayList<StringBuilder>();
		list=localWeb.get(selectTense, tableName, condiTense);
		for(StringBuilder l: list){
			System.out.println(l);
		}
		 return list;
	}
	

	/**
	 * Equivalent to UPDATE
	 * @param tableName String: name table in database
	 * @param colTense Array String: it's SET. Example: {"col1='value1'","col2='value2'"}
	 * @param condiTense Array of Array String: It's Where. Example: {{"col1 = val1"},{"AND"}, {"col2 = val2"}}
	 * @return 0 if update success
	 */
	public static int addStat(String tableName, String[] colTense, String[][] condiTense){
		localWeb.put(tableName, colTense, condiTense);
		return 0;
	}

	/**
	 * Insert
	 * @param insertTense array of string: value to insert
	 * @param tableName String: Name of table in base
	 * @param colTense Array of String: column to be insert 
	 * @return 1 if add success
	 */
	public static int addStat(String tableName, String[] colTense, String[] insertTense){
		localWeb.put(tableName,colTense, insertTense);
		return 0;
	}

	//to do
	public static boolean deleteStat(){
		String[][] condi={{"typeDico", "LIKE", "'Hiragana'"}};
		return localWeb.delete("Dico", condi);
	}
	public static String updateSetting(){
		String table="Profile";
		String name="'"+bundleSetting.getName()+"'";
		String newPwd="'"+encode(bundleSetting.getNewPwd())+"'";
		String oldPwd="'"+encode(bundleSetting.getOldPwd())+"'";
		String[] col={"mdp="+ newPwd};
		String[][] condi={{"pseudo", "LIKE", name}, {"AND"}, {"mdp", "LIKE", oldPwd}};
		addStat(table, col, condi);
		return null;
	}
	
	public static String updateStat(StatBundle bundleStat) throws UnsupportedEncodingException, MalformedURLException, IOException{
		//recupère le trynumber
		String[] tableName={"Dico D", "Symbol S"};
		String[] selectTense={"numberSuccess", "tryNumber"};
		String[] condiTense={"D.idDico=S.idDico", "AND", "keySymbol=12"};
		ArrayList<StringBuilder> list=new ArrayList<StringBuilder>();
		list=getStat(tableName, selectTense, condiTense);
		
		String table="Dico";
		String[] colo={"typeDico", "tryNumber"};
		String[] insert={"'Hiragana'", "4", "1"};
		addStat(table, colo, insert);
		return null;
	}
	/**
	 * This method allow to encode string to md5
	 * @param pwd String: string to encode
	 * @return encode string
	 */
	 private static String encode(String pwd){
        byte[] key = pwd.getBytes();
        byte[] hash = null;

        try
        {
            hash = MessageDigest.getInstance("MD5").digest(key);
        }
        catch (NoSuchAlgorithmException e)
        {
            throw new Error("No Class encode MD5");
        }

        StringBuilder pwdEncode = new StringBuilder();
        for (int i = 0; i < hash.length; i++)
        {
            String hexa = Integer.toHexString(hash[i]);
            if (hexa.length() == 1)
            {
                pwdEncode.append('0');
                pwdEncode.append(hexa.charAt(hexa.length() - 1));
            }
            else
                pwdEncode.append(hexa.substring(hexa.length() - 2));
        }
        return pwdEncode.toString();
    }
}


