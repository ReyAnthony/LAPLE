package fr.laple.jdbc;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.sql.SQLException;

import fr.laple.controller.viewProfile.ControllerStatistics;
import fr.laple.view.statisticProfile.ViewStatistics;


public class Main {

	public static void main(String[] args) throws SQLException, UnsupportedEncodingException, MalformedURLException, IOException, CloneNotSupportedException {
		
		new ViewStatistics(500, 500);
	}
}
