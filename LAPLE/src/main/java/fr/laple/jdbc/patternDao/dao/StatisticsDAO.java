package fr.laple.jdbc.patternDao.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.laple.jdbc.patternDao.table.Statistics;

public class StatisticsDAO extends DAO<Statistics> {

	public StatisticsDAO(Connection connect) {
		super(connect);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean create(Statistics obj) throws SQLException {
		int result=0;
		return (result=this.connect.createStatement().executeUpdate("INSERT INTO Statistics (type, nameStat, totalNumberEx, "+
						"currentNumberEx) VALUES('"+obj.getType()+
						"',"+"'"+obj.getNameStat()+"',"+"'"+obj.getTotalNumberEx()+"',"+"'"+obj.getCurrentNumberEx()+"')"))==1;
	}

	@Override
	public boolean delete(Statistics obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Statistics obj) throws SQLException {
		int result=0;
		return (result=this.connect.createStatement().executeUpdate("UPDATE Statistics SET type='"+obj.getType()+"'," + 
				"nameStat='"+obj.getNameStat()+"', totalNumberEx='"+obj.getTotalNumberEx()+"', "+
				"currentNumberEx='"+obj.getCurrentNumberEx()+"'WHERE type="+"'"+obj.getType()+"';"))==1;
	}
	
	@Override
	public boolean update(Statistics obj, String[] condition)
			throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Statistics find(int id) throws SQLException {
		Statistics statistics= new Statistics();
		ResultSet result=this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
						 ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Statistics WHERE idStatistic="+id);
		if(result.first())
			statistics=new Statistics(id, result.getString("type"), result.getString("nameStat"), result.getInt("totalNumberEx"), result.getInt("currentNumberEx"));
		return statistics;
	}

	@Override
	public Statistics findLastId() throws SQLException {
		Statistics statistics= new Statistics();
		ResultSet result=this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
				 ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Statistics ORDER BY idStatistic ASC");
		while(result.next())
			if(result.last())
				statistics= new Statistics(result.getInt("idStatistic"), result.getString("type"), result.getString("nameStat"), result.getInt("totalNumberEx"), result.getInt("currentNumberEx"));
		return statistics;
	}


	@Override
	public Statistics findByCondition(Statistics obj) throws SQLException {
		Statistics statistics= new Statistics();
		ResultSet result=this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
				 ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Statistics WHERE type='"+obj.getType()+
						 									"' AND nameStat='"+obj.getNameStat()+"';");
		while(result.next())
			statistics= new Statistics(result.getInt("idStatistic"), result.getString("type"), result.getString("nameStat"), 
										result.getInt("totalNumberEx"), result.getInt("currentNumberEx"));
		return statistics;
	}

}
