
/**
 * Make sure the Postgresql JDBC driver is in your classpath.
 * You can download the JDBC 4 driver from here if required.
 * https://jdbc.postgresql.org/download.html
 *
 * take care of the variables usernamestring and passwordstring to use 
 * appropriate database credentials before you compile !
 *
**/

import java.sql.*;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

class simpleJDBC {
	public static ResultSet ExecuteQuery(Connection con, Statement statement, int sqlCode, String sqlState, String[] columns, String tableName) throws SQLException {		
		try {
			String selectSQL = "SELECT ";
			if (columns.length == 1) {
				selectSQL = selectSQL + columns[0];
			}
			else {
				for (int i = 0; i < columns.length-1; ++i) {
					selectSQL = selectSQL + columns[i] + ", ";
				}
				selectSQL = selectSQL + columns[columns.length-1];
			}
			
			selectSQL = selectSQL + " FROM " + tableName + ";";
			System.out.println(selectSQL);
			ResultSet rs = statement.executeQuery(selectSQL);
			System.out.println("Query Succeeded");
			
			return rs;
			
		} catch (SQLException e) {
			sqlCode = e.getErrorCode(); // Get SQLCODE
			sqlState = e.getSQLState(); // Get SQLSTATE
 
			// Your code to handle errors comes here;
			// something more meaningful than a print would be good
			System.out.println("Error in ExecuteQuery");
			System.out.println("Code: " + sqlCode + "  sqlState: " + sqlState);
		}
		
		return null;
	}
	
	public static void main(String[] args) throws SQLException {
		// Unique table names. Either the user supplies a unique identifier as a command
		// line argument, or the program makes one up.
		String tableName = "";
		int sqlCode = 0; // Variable to hold SQLCODE
		String sqlState = "00000"; // Variable to hold SQLSTATE

		// Register the driver. You must register the driver before you can use it.
		try {
			DriverManager.registerDriver(new org.postgresql.Driver());
		} catch (Exception cnfe) {
			System.out.println("Class not found");
		}

		// This is the url you must use for Postgresql.
		//Note: This url may not valid now !
		String url = "jdbc:postgresql://comp421.cs.mcgill.ca:5432/cs421";
		Connection con = DriverManager.getConnection(url, "cs421g71", "Omr1$uck$");
		
		
		// Do work here
		String[] columns = {"*"};
		String table = "grocery";
		Statement statement = con.createStatement();
		ResultSet rs = ExecuteQuery(con, statement, sqlCode, sqlState, columns, table);
		ResultSetMetaData rsmd = rs.getMetaData();
		int numCols = rsmd.getColumnCount();

		for (int i = 1; i < numCols; ++i) {
			System.out.print(rsmd.getColumnName(i) + "\t");
			System.out.print("\n");
		}
		
		while(rs.next()) {
			for (int i = 1; i < numCols; ++i) {
				Object colResult = rs.getObject(i);
				System.out.print(String.valueOf(colResult) + "\t");
			}
			
			System.out.print("\n");
		}
		statement.close();

		// Finally but importantly close the statement and connection
		con.close();
	}
}
