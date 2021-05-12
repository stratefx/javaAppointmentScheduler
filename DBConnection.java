/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 * This class creates a connection with database. 
 * Uses JDBC interface to communicate with database.
 * @author Mike Bliss
 */
public class DBConnection {

	private static final String protocol = "jdbc";
	private static final String vendor = ":mysql:";
	private static final String iPAddress = "//wgudb.ucertify.com/";
	private static final String dbName = "WJ06ee5";

	private static final String jdbcURL = protocol + vendor + iPAddress + 
                dbName + "?connectionTimeZone=SERVER";

	private static final String MYSQLJDBCDriver = "com.mysql.cj.jdbc.Driver";

	private static final String username = "U06ee5";
        private static final String password = "53688743109";
	private static Connection connection = null;



        /**
         * Starts connection with database. 
         * Called from main() only once during run-time to maintain a connection.
         * @return connection A connection object
         */
	public static Connection startConnection() {
		try {
			Class.forName(MYSQLJDBCDriver);
			connection = DriverManager.getConnection(jdbcURL, username, password);
			System.out.println("Connection successful!");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
                        e.printStackTrace();
            }
		
		return connection;
	}

        

	/**
         * Getter for connection. 
         * Returns a connection object.
         * @return connection A connection object
         */
        public static Connection getConnection() {
		return connection;
	}


	/**
         * Closes connection with database. 
         * Closes connection with database when application is shut down.
         */
	public static void closeConnection() {
		try {
                    connection.close();
                    System.out.println("Connection Terminated!");
		} catch (Exception e) {
                    e.printStackTrace();
		}
	}



}
