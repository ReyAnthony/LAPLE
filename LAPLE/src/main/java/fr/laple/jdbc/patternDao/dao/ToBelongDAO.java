package fr.laple.jdbc.patternDao.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;

import fr.laple.jdbc.patternDao.table.Dico;
import fr.laple.jdbc.patternDao.table.Statistics;
import fr.laple.jdbc.patternDao.table.ToBelong;

public class ToBelongDAO extends DAO<ToBelong>{

	public ToBelongDAO(Connection connect) {
		super(connect);
	}

	@Override
	public boolean create(ToBelong obj) throws SQLException {
		Statistics statistics= new Statistics();
		Dico dico= new Dico();
		Timestamp statDate = null;
		Integer[] number=new Integer[2];
		Set<Set<Object>> tmpPk=obj.getListPk(); 
		for(Set<Object> oPk: tmpPk){
			if(oPk instanceof Set){
				Set<Object> tmp=(Set<Object>)oPk;
				for(Object o: tmp){System.out.println(o.getClass().getName());
					if(o instanceof Dico){
						dico = (Dico)o;
					}
					if(o instanceof Statistics){
						statistics  = (Statistics)o;
					}
					if(o instanceof Timestamp){
						
						statDate= (Timestamp)o;
					}
					if(o instanceof Integer[]){
						
						number= (Integer[])o;
					}
				}
			}
			try{
				this.connect.setAutoCommit(false);
				this.connect.createStatement().executeUpdate("INSERT INTO toBelong (idDico, idStatistic, "
						+ "statDate, tryNumber, numberSuccess) "+
							"VALUES("+dico.getIdDico()+ ","+statistics.getIdStatistic()+ ",'"+statDate+"',"+number[0].intValue()+
							","+number[1].intValue()+")");
				this.connect.commit();
			}catch(SQLException s){
				System.out.println(s);
				this.connect.rollback();
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean delete(ToBelong obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(ToBelong obj) throws SQLException {
		Statistics statistics= new Statistics();
		Dico dico= new Dico();
		Timestamp statDate= null;
		Integer[] number=new Integer[2];
		Set<Set<Object>> tmpPk=obj.getListPk(); 
		for(Set<Object> oPk: tmpPk){
			if(oPk instanceof Set){
				Set<Object> tmp=(Set<Object>)oPk;
				for(Object o: tmp){
					if(o instanceof Dico){
						dico = (Dico)o;
					}
					if(o instanceof Statistics){
						statistics  = (Statistics)o;
					}
					if(o instanceof Timestamp){
						
						statDate= (Timestamp)o;
					}
					if(o instanceof Integer[]){
						
						number= (Integer[])o;
					}
				}
			}
			try{
				this.connect.createStatement().executeUpdate("UPDATE toBelong SET statDate='"+statDate+
					"', tryNumber="+number[0]+ ", numberSuccess="+number[1]+
					"  WHERE idStatistic="+statistics.getIdStatistic()+" AND idDico="+dico.getIdDico()+";");
				this.connect.createStatement().executeUpdate("UPDATE Statistics SET type='"+statistics.getType()+
						"', nameStat='"+statistics.getNameStat()+ "', totalNumberEx="+statistics.getTotalNumberEx()+
						", currentNumberEx="+statistics.getCurrentNumberEx()+
						"  WHERE idStatistic="+statistics.getIdStatistic()+";");
				}catch(SQLException s){
					System.out.println(s);
					return false;
				}
		}
		
		return true;
	}

	@Override
	public boolean update(ToBelong obj, String[] condition) throws SQLException {
		// 
		return false;
	}

	@Override
	public ToBelong find(int id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ToBelong findLastId() throws SQLException, CloneNotSupportedException {
		
		Dico dico= new Dico();
		Statistics statistic= new Statistics();
		ToBelong toBelong= new ToBelong();
		String tenseSql="S.idStatistic, D.idDico, T.statDate, "+ 
							" nameDico, type, nameStat, totalNumberEX, currentNumberEx,"+
							"typeDico, tryNumber,  numberSuccess";
		String table="associate A, Statistics S, Dico D, Symbol Sy, Words W, toBelong T";
		String condi="S.idStatistic= T.idStatistic AND D.idDico= T.idDico";
		
		ResultSet result=this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
				 ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT "+ tenseSql+" FROM "+table+" WHERE "+condi+
						 									" ORDER BY idStatistic ASC;");
			if(result.last()){
				dico= new Dico(result.getInt("idDico"), result.getString("typeDico"), result.getString("nameDico"), "");
				statistic= new  Statistics(result.getInt("idStatistic"), result.getString("type"), 
											result.getString("nameStat"), result.getInt("totalNumberEx"), 
											result.getInt("currentNumberEx"));
				toBelong= new ToBelong(dico, statistic, result.getTimestamp("statDate"), result.getInt("tryNumber"), 
										result.getInt("numberSuccess"));
			}
				
		return toBelong;
	}

	@Override
	public ToBelong findByCondition(ToBelong obj) throws SQLException, CloneNotSupportedException {
		Statistics statistics= new Statistics();
		Dico dico= new Dico();
		ToBelong toBelong= new ToBelong();
		Set<Set<Object>> tmpPk=obj.getListPk(); 
		for(Set<Object> oPk: tmpPk){
			if(oPk instanceof Set){
				Set<Object> tmp=(Set<Object>)oPk;
				for(Object o: tmp){
					if(o instanceof Dico){
						dico = (Dico)o;
					}
					if(o instanceof Statistics){
						statistics  = (Statistics)o;
					}
				}
			}
			String tenseSql="S.idStatistic, D.idDico, T.statDate, T.statDate,"+ 
								" nameDico, type, keyDico, nameStat, totalNumberEX, currentNumberEx,"+
								"typeDico, tryNumber,  numberSuccess";
			String table="associate A, Statistics S, Dico D, toBelong T";
			String condi=null;
			if(dico.getIdDico()==0){
				condi="S.idStatistic= T.idStatistic AND D.idDico= T.idDico" +
							" AND T.idStatistic="+statistics.getIdStatistic();
			}
			else{
				condi="S.idStatistic= T.idStatistic AND D.idDico= T.idDico" +
						" AND T.idDico="+dico.getIdDico()+" AND T.idStatistic="+statistics.getIdStatistic()+
						" AND D.typeDico='"+dico.getTypeDico()+"' AND D.nameDico='"+dico.getNameDico()+
						"' AND D.keyDico='"+dico.getKeyDico()+"' ";
			}
			//System.out.println("SELECT DISTINCT "+ tenseSql+" FROM "+table+" WHERE "+condi+
				//			 									" ORDER BY T.idStatistic ASC;");
			ResultSet result=this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					 ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT DISTINCT "+ tenseSql+" FROM "+table+" WHERE "+condi+
							 									" ORDER BY T.idStatistic ASC;");
			//System.out.println("SELECT DISTINCT "+ tenseSql+" FROM "+table+" WHERE "+condi+
				//			 									" ORDER BY T.idStatistic ASC;");
			if(result.first()){
				dico= new Dico(result.getInt("idDico"), result.getString("typeDico"), result.getString("nameDico"), result.getString("keyDico"));
				statistics= new  Statistics(result.getInt("idStatistic"), result.getString("type"), 
											result.getString("nameStat"), result.getInt("totalNumberEx"), 
											result.getInt("currentNumberEx"));
				toBelong= new ToBelong(dico, statistics, result.getTimestamp("statDate"), result.getInt("tryNumber"), 
										result.getInt("numberSuccess"));
			}
			while(result.next()){
				dico= new Dico(result.getInt("idDico"), result.getString("typeDico"), result.getString("nameDico"), result.getString("keyDico"));
				statistics= new  Statistics(result.getInt("idStatistic"), result.getString("type"), 
											result.getString("nameStat"), result.getInt("totalNumberEx"), 
											result.getInt("currentNumberEx"));
				
					toBelong.addListPk(dico, statistics, result.getTimestamp("statDate"), result.getInt("tryNumber"), 
										result.getInt("numberSuccess"));
			}
		}
		
		
		
		return toBelong;
	}

	
}
