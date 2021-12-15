package fa.training.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import fa.training.common.Common;



public class DataConfig {
	
	static ReadProperties read = ReadProperties.getInstance();
	
	public static Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName(Common.forName);
			connection = DriverManager.getConnection(read.getProperty("JDBC_URL"), read.getProperty("JDBC_USER"), read.getProperty("JDBC_PASSWORD"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
}
}
