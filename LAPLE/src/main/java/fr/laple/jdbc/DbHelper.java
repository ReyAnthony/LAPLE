package fr.laple.jdbc;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Set;

import fr.laple.jdbc.patternDao.dao.AssociateDAO;
import fr.laple.jdbc.patternDao.dao.DAO;
import fr.laple.jdbc.patternDao.dao.DicoDAO;
import fr.laple.jdbc.patternDao.dao.LanguageDAO;
import fr.laple.jdbc.patternDao.dao.ProfileDAO;
import fr.laple.jdbc.patternDao.dao.StatisticsDAO;
import fr.laple.jdbc.patternDao.dao.ToBelongDAO;
import fr.laple.jdbc.patternDao.table.Associate;
import fr.laple.jdbc.patternDao.table.Dico;
import fr.laple.jdbc.patternDao.table.Language;
import fr.laple.jdbc.patternDao.table.Profile;
import fr.laple.jdbc.patternDao.table.Statistics;
import fr.laple.jdbc.patternDao.table.ToBelong;


/**
 * 
 * @author Christian EBONGUE
 * @category JDBC
 * Comment: This class updates and gets statistic and profile
 *
 */
public class DbHelper {
	private StatBundle bundleStat;
	private SettingBundle bundleSetting;
	private static String url="jdbc:mysql://www.laple.fr:3306/laple";
	private static String user= "root";
	private static String passwd="";
	private static IDbAccessor localWeb;
	private static BigDecimal allStatByStat=new BigDecimal(0.0);
	private static BigDecimal allStat=new BigDecimal(0.0);
	private static BigDecimal lessonStat=new BigDecimal(0.0);
	private static BigDecimal exerciseStat=new BigDecimal(0.0);
	private static BigDecimal funzoneLesson=new BigDecimal(0.0);
	private static BigDecimal dictationLesson=new BigDecimal(0.0);
	private static BigDecimal funzoneExercise=new BigDecimal(0.0);
	private static BigDecimal dictationExercise=new BigDecimal(0.0);
	private static BigDecimal successDicoFL=new BigDecimal(0.0); 
	private static BigDecimal successDicoDL=new BigDecimal(0.0);
	private static BigDecimal successDicoFE=new BigDecimal(0.0);
	private static BigDecimal successDicoDE=new BigDecimal(0.0); 
	private static Connection conn;
	private static DbHelper instance= new DbHelper();
	
	/*************************************constructeurs singleton*****************************************************/
	/**
	 * This allow to connect to database. If there is no connexion,
	 * the connexion will be etablished to local
	 * @exception ClassNotFoundException is up if Driver jdbc not found
	 * @exception SQLException is up if sql error or any communication to database.
	 * In particular not connexion to network
	 * @exception Exception if exceptions above had not been catch
	 */
	private DbHelper(){		
		try{
    		try {
				Class.forName( "com.mysql.jdbc.Driver" );
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	    System.out.println("Driver O.K.");
    	    conn = DriverManager.getConnection(url, user, passwd);
    	    bundleSetting.setConnected(true);
    	    localWeb= new Web();
    	    bundleSetting.setConnected(true);
		} catch (SQLException s) {
			// TODO Auto-generated catch block
			System.out.println("NO connexion to network. going to connect to local");
			url="jdbc:mysql://localhost:3306/laple";
			try{
	    	    conn = DriverManager.getConnection(url, user, passwd);
	    	    localWeb=new Local(conn);
	    	    
			}catch (Exception e) {
		  	      e.printStackTrace();
			}
		}catch (Exception e) {
			 e.printStackTrace();
		}
	}
	/****************************************instanciation unique******************************************/
	public static DbHelper getInstance(){
		return instance;
	}
	
	/******************************************accesseurs**********************************************/
	
	public StatBundle getBundleStat() {
		return bundleStat;
	}

	public static String getUrl() {
		return url;
	}
	public static void setUrl(String url) {
		DbHelper.url = url;
	}
	public static String getUser() {
		return user;
	}
	public static void setUser(String user) {
		DbHelper.user = user;
	}
	public static String getPasswd() {
		return passwd;
	}
	public static void setPasswd(String passwd) {
		DbHelper.passwd = passwd;
	}
	public static IDbAccessor getLocalWeb() {
		return localWeb;
	}
	public static void setLocalWeb(IDbAccessor localWeb) {
		DbHelper.localWeb = localWeb;
	}
	
	
	
	public static BigDecimal getAllStatByStat() {
		return allStatByStat;
	}
	public static void setAllStatByStat(BigDecimal allStatByStat) {
		DbHelper.allStatByStat = allStatByStat;
	}
	public static BigDecimal getAllStat() {
		return allStat;
	}
	public static void setAllStat(BigDecimal allStat) {
		DbHelper.allStat = allStat;
	}
	public static BigDecimal getLessonStat() {
		return lessonStat;
	}
	public static void setLessonStat(BigDecimal lessonStat) {
		DbHelper.lessonStat = lessonStat;
	}
	public static BigDecimal getExerciseStat() {
		return exerciseStat;
	}
	public static void setExerciseStat(BigDecimal exerciseStat) {
		DbHelper.exerciseStat = exerciseStat;
	}
	public static BigDecimal getFunzoneLesson() {
		return funzoneLesson;
	}
	public static void setFunzoneLesson(BigDecimal funzoneLesson) {
		DbHelper.funzoneLesson = funzoneLesson;
	}
	public static BigDecimal getDictationLesson() {
		return dictationLesson;
	}
	public static void setDictationLesson(BigDecimal dictationLesson) {
		DbHelper.dictationLesson = dictationLesson;
	}
	public static BigDecimal getFunzoneExercise() {
		return funzoneExercise;
	}
	public static void setFunzoneExercise(BigDecimal funzoneExercise) {
		DbHelper.funzoneExercise = funzoneExercise;
	}
	public static BigDecimal getDictationExercise() {
		return dictationExercise;
	}
	public static void setDictationExercise(BigDecimal dictationExercise) {
		DbHelper.dictationExercise = dictationExercise;
	}
	public static BigDecimal getSuccessDicoFL() {
		return successDicoFL;
	}
	public static void setSuccessDicoFL(BigDecimal successDicoFL) {
		DbHelper.successDicoFL = successDicoFL;
	}
	public static BigDecimal getSuccessDicoDL() {
		return successDicoDL;
	}
	public static void setSuccessDicoDL(BigDecimal successDicoDL) {
		DbHelper.successDicoDL = successDicoDL;
	}
	public static BigDecimal getSuccessDicoFE() {
		return successDicoFE;
	}
	public static void setSuccessDicoFE(BigDecimal successDicoFE) {
		DbHelper.successDicoFE = successDicoFE;
	}
	public static BigDecimal getSuccessDicoDE() {
		return successDicoDE;
	}
	public static void setSuccessDicoDE(BigDecimal successDicoDE) {
		DbHelper.successDicoDE = successDicoDE;
	}
	public static void setConn(Connection conn) {
		DbHelper.conn = conn;
	}
	public static void setInstance(DbHelper instance) {
		DbHelper.instance = instance;
	}
	public void setBundleStat(StatBundle bundleStat) {
		this.bundleStat = bundleStat;
	}

	public SettingBundle getBundleSetting() {
		return bundleSetting;
	}

	public void setBundleSetting(SettingBundle bundleSetting) {
		this.bundleSetting = bundleSetting;
	}

	public static Connection getConn() {
		return conn;
	}
	
	
	
	/************************statistique and  setting*****************/
	/**
	 * @throws SQLException 
	 * @throws CloneNotSupportedException 
	**/
	
	/**
	 * put in bundleSetting  result of getSetting
	 * @throws SQLException
	 * @throws CloneNotSupportedException
	 */
	public void getSetting() throws SQLException, CloneNotSupportedException{
		localWeb.get(this.bundleSetting, this.bundleStat);
	}
	
	public void addSetting() throws SQLException{
		localWeb.put(this.bundleSetting);
	}
	
	/**
	 *  
	 * @throws UnsupportedEncodingException
	 * @throws MalformedURLException
	 * @throws IOException
	 * @throws SQLException
	 * @throws CloneNotSupportedException
	 */
	public void getStat() throws UnsupportedEncodingException, MalformedURLException, IOException, SQLException, CloneNotSupportedException{
		
		allStatByStat=new BigDecimal(0.0);
		allStat=new BigDecimal(0.0);
		lessonStat=new BigDecimal(0.0);
		exerciseStat=new BigDecimal(0.0);
		funzoneLesson=new BigDecimal(0.0);
		dictationLesson=new BigDecimal(0.0);
		funzoneExercise=new BigDecimal(0.0);
		dictationExercise=new BigDecimal(0.0);
		successDicoFL=new BigDecimal(0.0);
		successDicoDL=new BigDecimal(0.0);
		successDicoFE=new BigDecimal(0.0);
		successDicoDL=new BigDecimal(0.0);
		successDicoDE=new BigDecimal(0.0);
		double[] result=localWeb.get(this.bundleStat, this.bundleSetting);
		allStat=new BigDecimal(result[0]).setScale(2, BigDecimal.ROUND_DOWN);
		allStatByStat=new BigDecimal(result[1]).setScale(2, BigDecimal.ROUND_DOWN);
		lessonStat=new BigDecimal(result[2]).setScale(2, BigDecimal.ROUND_DOWN);
		exerciseStat=new BigDecimal(result[3]).setScale(2, BigDecimal.ROUND_DOWN);
		funzoneLesson=new BigDecimal(result[4]).setScale(2, BigDecimal.ROUND_DOWN);
		dictationLesson=new BigDecimal(result[5]).setScale(2, BigDecimal.ROUND_DOWN);
		funzoneExercise=new BigDecimal(result[6]).setScale(2, BigDecimal.ROUND_DOWN);
		dictationExercise=new BigDecimal(result[7]).setScale(2, BigDecimal.ROUND_DOWN);
		successDicoFL=new BigDecimal(result[8]).setScale(2, BigDecimal.ROUND_DOWN);
		successDicoDL=new BigDecimal(result[9]).setScale(2, BigDecimal.ROUND_DOWN);
		successDicoFE=new BigDecimal(result[10]).setScale(2, BigDecimal.ROUND_DOWN);
		successDicoDE=new BigDecimal(result[11]).setScale(2, BigDecimal.ROUND_DOWN);
	}
	
	
	
	/**
	 * add new statistic in database
	 * @return
	 * @throws SQLException
	 * @throws CloneNotSupportedException
	 */
	
	public int addStat() throws SQLException, CloneNotSupportedException {
		localWeb.put(this.bundleSetting, this.bundleStat);
		return 1;
	}
}


