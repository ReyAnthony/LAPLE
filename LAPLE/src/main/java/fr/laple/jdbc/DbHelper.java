package fr.laple.jdbc;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;


import fr.laple.jdbc.DbAccessor;

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
	public static void activeConnect(){
		DbAccessor.connect("root", "czu387");
	}

	/**
	 * SELECT sql
	 * @param selectTense array of string: Fields to display
	 * @param tableName String: Name of table in base
	 * @param condiTense Array of String: the where condition 
	 * @return list ArrayList of the result of select
	 */
	private static ArrayList<StringBuilder> getStat(String[] tableName, String[] selectTense, String[] condiTense){
		
		ArrayList<StringBuilder> list= new ArrayList<StringBuilder>();
		list=DbAccessor.get(selectTense, tableName, condiTense);
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
	private static int addStat(String tableName, String[] colTense, String[][] condiTense){
		DbAccessor.put(tableName, colTense, condiTense);
		return 0;
	}

	/**
	 * Insert
	 * @param insertTense array of string: value to insert
	 * @param tableName String: Name of table in base
	 * @param colTense Array of String: column to be insert 
	 * @return 1 if add success
	 */
	private static int addStat(String tableName, String[] colTense, String[] insertTense){
		DbAccessor.put(tableName,colTense, insertTense);
		return 0;
	}

	//to do
	private static boolean deleteStat(){
		String[][] condi={{"typeDico", "LIKE", "'Hiragana'"}};
		return DbAccessor.delete("Dico", condi);
	}
	public static String updateSetting(){
		String table="Profile";
		String name="'"+bundleSetting.getName()+"'";
		String newPwd="'"+encode(bundleSetting.getNewPwd())+"'";
		String oldPwd="'"+encode(bundleSetting.getOldPwd())+"'";
		String[] col={"mdp="+ newPwd};
		String[][] condi={{"name", "LIKE", name}, {"AND"}, {"mdp", "LIKE", oldPwd}};
		addStat(table, col, condi);
		return null;
	}
	
	public static String updateStat(StatBundle bundleStat){
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


