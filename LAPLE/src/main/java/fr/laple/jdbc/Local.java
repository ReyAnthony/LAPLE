package fr.laple.jdbc;

import java.math.BigDecimal;
import java.sql.Connection;
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

public class Local implements IDbAccessor{
	
	Connection conn;
	private static double allStatByStat=0;
	private static double allStat=0;
	private static double lessonStat=0;
	private static double exerciseStat=0;
	private static double funzoneLesson=0;
	private static double dictationLesson=0;
	private static double funzoneExercise=0;
	private static double dictationExercise=0;
	private static double successDicoFL=0; 
	private static double successDicoDL=0;
	private static double successDicoFE=0;
	private static double successDicoDE=0;
    public Local(Connection conn) throws SQLException, ClassNotFoundException {
    	this.conn=conn;
    }
    
    

    /**
	 * Equivalent to select about request sql
	 * @param select array of string: Fields to display
	 * @param table String: Name of table in base
	 * @param condi Array of String: the where condition 
	 * @return list ArrayList of the result of select
     * @throws CloneNotSupportedException 
     * @throws SQLException 
	 */
    @Override
	public double[] get(StatBundle bundleStat, SettingBundle bundleSetting) throws SQLException, CloneNotSupportedException {
    	allStatByStat=0;
		allStat = 0;
		lessonStat=0;
		exerciseStat=0;
		funzoneLesson=0;
		dictationLesson=0;
		funzoneExercise=0;
		dictationExercise=0;
		successDicoFL=0;
		successDicoDL=0;
		successDicoFE=0;
		successDicoDE=0;
		
		double[] statUser=new double[12];
		
		DAO<Profile> profileDao= new ProfileDAO(conn);
		DAO<Dico> dicoDao= new DicoDAO(conn);
		DAO<Language> languageDao= new LanguageDAO(conn);
		DAO<Associate> associateDao= new AssociateDAO(conn);
		DAO<ToBelong> toBelongDao= new ToBelongDAO(conn);
		
		
		//chargement du profil avec son id
		Profile profile= profileDao.findByCondition(new Profile(bundleSetting.getEmail(), 
																bundleSetting.getOldPwd(),
																bundleSetting.getPseudo()));
		
		
		//Chargement du dico avec son id
		Dico dico= dicoDao.findByCondition(new Dico(bundleStat.getTypeDico(), bundleStat.getNameDico(), bundleStat.getKeySymbol()));
				
		//Chargement de la langue
		Language language=languageDao.findByCondition(new Language(bundleStat.getLanguage()));
		Statistics statistics= new Statistics();
		
		//Chargement de toutes les stats du profile pour un language donnée
		Associate associate= associateDao.findByCondition(new Associate(profile, statistics, language));
		
		Set<Set<Object>> associateSet=associate.getListPk();
		ToBelong toBelong= new ToBelong();
		//Parcours du resultat du chargment de associate
		for(Set<Object> oPk: associateSet){
			//Vérification du type table
			for(Object o: oPk)
				if(o instanceof Statistics){
					statistics=(Statistics)o;
					//Pour chaque statistique, on cherche le dictionnaire associé
					toBelong= toBelongDao.findByCondition( new ToBelong(dico, statistics,  Timestamp.from(Instant.now()),
							bundleStat.getTotalSymbol(), bundleStat.getTryNumber()));
					if(toBelong.getStatistic().getType()==null){
						System.out.println("NO statistic with params:"+bundleStat);
						continue;
					}
					switch(toBelong.getStatistic().getNameStat()){
						case "lesson": switch(toBelong.getStatistic().getType()){
											case "funzone": funzoneLesson=funzoneLesson + ((toBelong.getStatistic().getCurrentNumberEx() / (double)toBelong.getStatistic().getTotalNumberEx()) *100)/2.0;
															successDicoFL=successDicoFL + (toBelong.getNumber()[1]/(double)toBelong.getNumber()[0])*100;
															
															System.out.println(toBelong.getDico().getNameDico()+" "+toBelong.getDico().getKeyDico()+" :"+
																				"successFunzone="+new BigDecimal(successDicoFL).setScale(2, BigDecimal.ROUND_DOWN)+"%");
												break;
											case "dictation": dictationLesson=(dictationLesson + (toBelong.getStatistic().getCurrentNumberEx()/(double) toBelong.getStatistic().getTotalNumberEx()) *100)/2;
																successDicoDL=successDicoDL + (toBelong.getNumber()[1]/(double)toBelong.getNumber()[0])*100;
												break;
											default: System.out.println("error 1");return null;
										}
										System.out.println(" dictationLesson="+new BigDecimal(dictationLesson).setScale(2, BigDecimal.ROUND_DOWN).doubleValue()+ "% " +
												"funzoneLesson="+ new BigDecimal(funzoneLesson).setScale(2, BigDecimal.ROUND_DOWN)+"% " +"");
							break;	
						case "exercise": switch(toBelong.getStatistic().getType()){
											case "funzone": funzoneExercise=(funzoneExercise + ((toBelong.getStatistic().getCurrentNumberEx() / (double)toBelong.getStatistic().getTotalNumberEx()) *100))/2.0;
															successDicoFE=successDicoFE + (toBelong.getNumber()[1]/(double)toBelong.getNumber()[0])*100;
												break;
											case "dictation": dictationExercise=(dictationExercise + (toBelong.getStatistic().getCurrentNumberEx()/(double) toBelong.getStatistic().getTotalNumberEx()) *100)/2;
															successDicoDE=successDicoDE + (toBelong.getNumber()[1]/(double)toBelong.getNumber()[0])*100;
												break;
											default: System.out.println("error 1");return null;
										}
										System.out.println(" dictationExercise="+new BigDecimal(dictationExercise).setScale(2, BigDecimal.ROUND_DOWN).doubleValue()+ "% " +
										"funzoneExercise="+ new BigDecimal(funzoneExercise).setScale(2, BigDecimal.ROUND_DOWN)+"% ");
									break;
						}
				lessonStat=lessonStat + (funzoneLesson + dictationLesson)/2;
				exerciseStat= exerciseStat + (funzoneExercise + dictationExercise)/2;
				allStatByStat= allStatByStat + (lessonStat + exerciseStat)/2;
					System.out.println("allstatByStat="+new BigDecimal(allStatByStat).setScale(2, BigDecimal.ROUND_DOWN).doubleValue()+"% "+
										"lessonStat="+ new BigDecimal(lessonStat).setScale(2, BigDecimal.ROUND_DOWN).doubleValue() + "% " +
										"exerciseStat="+new BigDecimal(exerciseStat).setScale(2, BigDecimal.ROUND_DOWN).doubleValue()+ "% ");
				}
				allStat=(allStat+allStatByStat)/2.0;
		}
		System.out.println("allStat="+new BigDecimal(allStat).setScale(2, BigDecimal.ROUND_DOWN)+"%");
		statUser[0]=allStat;
		statUser[1]=allStatByStat;
		statUser[2]=lessonStat;
		statUser[3]=exerciseStat;
		statUser[4]=funzoneLesson;
		statUser[5]=dictationLesson;
		statUser[6]=funzoneExercise;
		statUser[7]=dictationExercise;
		statUser[8]=successDicoFL;
		statUser[9]=successDicoDL;
		statUser[10]=successDicoFE;
		statUser[11]=successDicoDE;
		return statUser;
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
	public void get(SettingBundle bundleSetting, StatBundle bundleStat) throws SQLException, CloneNotSupportedException {
		DAO<Profile> profileDao= new ProfileDAO(conn);
		Profile profile= profileDao.findByCondition(new Profile(bundleSetting.getEmail(), bundleSetting.getOldPwd(),
																bundleSetting.getPseudo()));
		bundleSetting.setEmail(profile.getEmail());
		bundleSetting.setOldPwd(profile.getMdp());
		bundleSetting.setPseudo(profile.getPseudo());
		
	}



	@Override
	public int put(SettingBundle bundleSetting) throws SQLException {
		DAO<Profile> profileDao= new ProfileDAO(conn);
		System.out.println((profileDao.update(new Profile(bundleSetting.getEmail(), bundleSetting.getOldPwd(),
																bundleSetting.getPseudo()))==true)?
																"Change password ok":"failed change passwords");
		return 1;
	}



	@Override
	public int put(SettingBundle bundleSetting, StatBundle bundleStat) throws SQLException, CloneNotSupportedException {
		DAO<Profile> profileDao= new ProfileDAO(conn);
		DAO<Language> languageDao= new LanguageDAO(conn);
		DAO<Statistics> statisticsDao= new StatisticsDAO(conn);
		DAO<Associate> associateDao= new AssociateDAO(conn);
		DAO<ToBelong> toBelongDao= new ToBelongDAO(conn);
		
		//chargement du profil avec son id
				Profile profile= profileDao.findByCondition(new Profile(bundleSetting.getEmail(), 
																		bundleSetting.getOldPwd(),
																		bundleSetting.getPseudo()));
				
		//Chargement du dico avec son id
				DAO<Dico> dicoDao= new DicoDAO(conn);
				Dico dico= dicoDao.findByCondition(new Dico(bundleStat.getTypeDico(), 
													bundleStat.getNameDico(), 
													bundleStat.getKeySymbol()));
				//System.out.println("---"+dico);
				
		//Chargement de la langue indiquée
		Language language=languageDao.findByCondition(new Language(bundleStat.getLanguage()));
				
		
		int statId= statisticsDao.findLastId().getIdStatistic();
		ToBelong toBelongVerif = new ToBelong();
		for(int i=0 ; i<statId; i++){
			Statistics statTmp=statisticsDao.find(i);
			//Chargement de la statistique avec param bundleStat
			toBelongVerif=toBelongDao.findByCondition(new ToBelong(dico, new Statistics(statTmp.getIdStatistic(),
																bundleStat.getTypeStat(), bundleStat.getNameStat(), 
																bundleStat.getTotalNumberEx(), bundleStat.getCurrentNumberEx()),
																Timestamp.from(Instant.now()), bundleStat.getTotalSymbol(), 
																bundleStat.getTryNumber()));
				
			if(toBelongVerif.getListPk().size()==1)
				break;
		}
		System.out.println(toBelongVerif.getListPk());
		Associate associateVerif= associateDao.find(toBelongVerif.getStatistic().getIdStatistic());
		//System.out.println(associateVerif);
		if(toBelongVerif.getListPk().size()==1 && associateVerif.getStatistic().getIdStatistic()!=0){
			System.out.println((statisticsDao.update(new Statistics(associateVerif.getStatistic().getIdStatistic(),
												bundleStat.getTypeStat(), bundleStat.getNameStat(),
												bundleStat.getTotalNumberEx(), bundleStat.getCurrentNumberEx()))==true)?
														"update sucess":"failed update");
			Integer[] i=null;
			Timestamp t=null;
			for(Set<Object> o: toBelongVerif.getListPk()){
				Object tmp=(Object)o;
				if(tmp instanceof Timestamp){
					t=(Timestamp)tmp;
					t=t.from(Instant.now());
					tmp=t;
				}
				if(tmp instanceof Integer[]){
					i=(Integer[])tmp;
					i[0]=bundleStat.getTotalSymbol();
					i[1]=bundleStat.getTryNumber();
					tmp=i;
				}
			}
			
			System.out.println((toBelongDao.update(toBelongVerif)==true)?"update tobelongadd sucess":"failed update tobelongadd");
		}
		else{
			ToBelong toBelong=toBelongDao.findByCondition(new ToBelong(dico, associateVerif.getStatistic(), Timestamp.from(Instant.now()), 
					bundleStat.getTotalSymbol(), bundleStat.getTryNumber()));
			if(toBelong.getListPk().size()==0){
				System.out.println((statisticsDao.create(new Statistics(bundleStat.getTypeStat(),bundleStat.getNameStat(), 
						bundleStat.getTotalNumberEx(),bundleStat.getCurrentNumberEx()))==true)?
						"addStat success":"failed addStat");
				Statistics lastStat=statisticsDao.findLastId();
				
				System.out.println((associateDao.create(new Associate(profile, lastStat, language))==true)?
									"addAssociate success":"failed addAssociate");
				//Associate associate= associateDao.findLastId();
				
				System.out.println((toBelongDao.create(new ToBelong(dico, lastStat, Timestamp.from(Instant.now()), 
						bundleStat.getTotalSymbol(), bundleStat.getTryNumber()))==true)?
									"addBelong success":"failed addToBelong");
			}

		}
		return 1;
		
	}
    
    
    
    
}