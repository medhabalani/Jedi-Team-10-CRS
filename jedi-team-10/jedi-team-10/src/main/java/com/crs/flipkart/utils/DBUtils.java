/**
 * 
 */
package com.crs.flipkart.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author devanshugarg
 *
 */

/*
 * This is the Database Utils file in which the connection is setup
 * Here, we use the JDBC Drivers and pass the username and password
 * Once the connection is setup we can perform the requests 
 * with the database.
 */

public class DBUtils {

	private static Connection connection = null;
	
	public static Connection getConnection() {
		
		if (connection != null) {
			try {
				if (connection.isClosed()) {	
					connection = null;
					return getConnection();
				} else {
				return connection;
				}
			} catch (SQLException e) {
				return getConnection();
			}
		} else {
			try {
				Properties properties = new Properties();
				InputStream inputStream = DBUtils.class.getClassLoader().getResourceAsStream("./config.properties");
				properties.load(inputStream);
				String driver = properties.getProperty("driver");
				String url = properties.getProperty("url");
				String username = properties.getProperty("username");
				String password = properties.getProperty("password");
				Class.forName(driver);
				connection = DriverManager.getConnection(url, username, password);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return connection;
		}
	}
}
