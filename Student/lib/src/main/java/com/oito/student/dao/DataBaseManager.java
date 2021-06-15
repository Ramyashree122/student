package com.oito.student.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DataBaseManager {
	private Logger log = LoggerFactory.getLogger(DataBaseManager.class);

	public Connection getConnection() 
	{
		Connection connection = null;
		Properties properties = new Properties();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			properties.load(
					new DataBaseManager().getClass().getClassLoader().getResourceAsStream("application.properties"));
			String url = String.format("jdbc:mysql://%s:%s/%s", properties.getProperty("mysql_host"),
					properties.getProperty("mysql_port"), properties.getProperty("database"));
			connection = DriverManager.getConnection(url, properties.getProperty("username"),
					properties.getProperty("password"));
			connection.setAutoCommit(false);
		}
		catch (Exception e) 
		{
			log.error("file not exit :"+e);
		}

		return connection;
	}
}
