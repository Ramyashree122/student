package com.oito.student.dao;
import java.io.IOException;
import java.util.Properties;
import org.apache.commons.dbcp.ConnectionFactory;
import org.apache.commons.dbcp.DriverManagerConnectionFactory;
import org.apache.commons.dbcp.PoolableConnectionFactory;
import org.apache.commons.dbcp.PoolingDataSource;
import org.apache.commons.pool.impl.GenericObjectPool;

public class DataBaseManagerConnectionPool 
{
	
		private static GenericObjectPool genericPoolObject = null;
		static
		{
			Properties property=new Properties();
			try 
			{
				property.load(new DataBaseManagerConnectionPool().getClass().getClassLoader().getResourceAsStream("application.properties"));
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
			String userName=property.getProperty("username");
			String password=property.getProperty("password");
			String url="jdbc:mysql://"+property.getProperty("mysql_host")+":"+property.getProperty("mysql_port")+"/"+property.getProperty("database");
			ConnectionFactory connectionFactory = new DriverManagerConnectionFactory(url, userName, password);
            try 
            {
				Class.forName("com.mysql.cj.jdbc.Driver");
			}
            catch (ClassNotFoundException e) 
            {
				e.printStackTrace();
			}
	        
	        genericPoolObject = new GenericObjectPool();
	        genericPoolObject.setMaxActive(2);
			PoolableConnectionFactory poolableConnectionFactory = new PoolableConnectionFactory(connectionFactory, genericPoolObject, null, null, false, true);          
		}
		public  PoolingDataSource setConnection()
		{
		     return new PoolingDataSource(genericPoolObject);
		}
}
