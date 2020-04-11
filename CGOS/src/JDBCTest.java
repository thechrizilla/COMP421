
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

class SQLInfo {
	public Connection connection;
	public Statement statement;
	public int sqlCode;
	public String sqlState;

	public SQLInfo(Connection c, Statement s, int code, String state) {
		this.connection = c;
		this.statement = s;
		this.sqlCode = code;
		this.sqlState = state;
	}
}

class IngredientInfo {
	public String shipName;
	public String roomNo;
	public String type;
	public String weight;
}

class GroceryInstanceInfo {
	public boolean isPerishable = false;
	public boolean isProduce = false;
	public String pe_expiryDate = null;
	public String pe_storageTemp = null;
	public String pr_season = null;
	public String type = null;
	public String weight = null;
	public String price = null;
	public String dimensions = null;
	public String orderID = null;
}

class simpleJDBC {
	public SQLInfo info;
	
	public simpleJDBC() throws SQLException {
			int sqlCode = 0; // Variable to hold SQLCODE
			String sqlState = "00000"; // Variable to hold SQLSTATE

			// Register the driver. You must register the driver before you can use it.
			try {
				DriverManager.registerDriver(new org.postgresql.Driver());
			} catch (Exception cnfe) {
				System.out.println("Class not found");
			}

			// This is the url you must use for Postgresql.
			// Note: This url may not valid now !
			String url = "jdbc:postgresql://comp421.cs.mcgill.ca:5432/cs421";
			Connection con = DriverManager.getConnection(url, "cs421g71", "Omr1$uck$");
			Statement statement = con.createStatement();

			SQLInfo info = new SQLInfo(con, statement, sqlCode, sqlState);
			this.info = info;
			
			System.out.println("JDBC User Constructor Finished");
	}
	
	public void Close() throws SQLException {
		// Need to call this whenever the application is closed!
		this.info.statement.close();
		this.info.connection.close();
	}
	
	public ResultSet ExecuteQuery(String[] columns, String tableName) throws SQLException {
		try {
			String selectSQL = "SELECT ";

			for (int i = 0; i < columns.length - 1; ++i) {
				selectSQL = selectSQL + columns[i] + ", ";
			}
			selectSQL = selectSQL + columns[columns.length - 1];
			selectSQL = selectSQL + " FROM " + tableName + ";";

			System.out.println(selectSQL);
			ResultSet rs = info.statement.executeQuery(selectSQL);
			System.out.println("Query Succeeded");

			return rs;

		} catch (SQLException e) {
			info.sqlCode = e.getErrorCode(); // Get SQLCODE
			info.sqlState = e.getSQLState(); // Get SQLSTATE

			// Your code to handle errors comes here;
			// something more meaningful than a print would be good
			System.out.println("Error in ExecuteQuery");
			System.out.println("Code: " + info.sqlCode + "  sqlState: " + info.sqlState);
		}

		return null;
	}

	public int CreateGrocery(GroceryInstanceInfo grocery) {
		int groceryBarcode = -1;

		// Get the grocery barcode
		try {
			String getMaxBarcodeSQL = "SELECT MAX(grocerybarcode) FROM grocery";
			ResultSet rs = info.statement.executeQuery(getMaxBarcodeSQL);
			rs.next();
			groceryBarcode = rs.getInt(1) + 1;
		} catch (SQLException e) {
			info.sqlCode = e.getErrorCode(); // Get SQLCODE
			info.sqlState = e.getSQLState(); // Get SQLSTATE

			// Your code to handle errors comes here;
			// something more meaningful than a print would be good
			System.out.println("Error fetching max barcode");
			System.out.println("Code: " + info.sqlCode + "  sqlState: " + info.sqlState);
			System.out.println("Could not create new grocery");
			return -1;
		}
		
		// Insert the grocery
		try {
			String insertSQL = "INSERT INTO grocery VALUES (";
			insertSQL = insertSQL + groceryBarcode + ", \'" + grocery.type + "\', " + grocery.weight + ", "
					+ grocery.price + ", " + grocery.dimensions + ", " + grocery.orderID + ");";
			System.out.println(insertSQL);
			info.statement.executeUpdate(insertSQL);
			System.out.println("Created grocery");
		} catch (SQLException e) {
			info.sqlCode = e.getErrorCode(); // Get SQLCODE
			info.sqlState = e.getSQLState(); // Get SQLSTATE
			System.out.println("Error creating new Grocery");
			System.out.println("Code: " + info.sqlCode + "  sqlState: " + info.sqlState);
		}

		// Insert perishable/non-perishable:
		// Is perishable
		if (grocery.isPerishable) {
			try {
				String insertSQL = "INSERT INTO perishables VALUES (";
				insertSQL = insertSQL + groceryBarcode + ", " + grocery.pe_storageTemp + ", \'" + grocery.pe_expiryDate
						+ "\');";
				System.out.println(insertSQL);
				info.statement.executeUpdate(insertSQL);
				System.out.println("Created perishable");
			} catch (SQLException e) {
				info.sqlCode = e.getErrorCode(); // Get SQLCODE
				info.sqlState = e.getSQLState(); // Get SQLSTATE
				System.out.println("Error creating new Perishable");
				System.out.println("Code: " + info.sqlCode + "  sqlState: " + info.sqlState);
			}

			// Is grocery
			if (grocery.isProduce) {
				try {
					String insertSQL = "INSERT INTO produce VALUES (";
					insertSQL = insertSQL + groceryBarcode + ", \'" + grocery.pr_season + "\');";
					System.out.println(insertSQL);
					info.statement.executeUpdate(insertSQL);
					System.out.println("Created perishable");
				} catch (SQLException e) {
					info.sqlCode = e.getErrorCode(); // Get SQLCODE
					info.sqlState = e.getSQLState(); // Get SQLSTATE
					System.out.println("Error creating new Produce");
					System.out.println("Code: " + info.sqlCode + "  sqlState: " + info.sqlState);
				}
			}
		}
		// Is non-perishable
		else {
			try {
				String insertSQL = "INSERT INTO nonperishables VALUES (" + groceryBarcode + ");";
				System.out.println(insertSQL);
				info.statement.executeUpdate(insertSQL);
				System.out.println("Created perishable");
			} catch (SQLException e) {
				info.sqlCode = e.getErrorCode(); // Get SQLCODE
				info.sqlState = e.getSQLState(); // Get SQLSTATE
				System.out.println("Error creating new Non-perishable");
				System.out.println("Code: " + info.sqlCode + "  sqlState: " + info.sqlState);
			}
		}
		
		return groceryBarcode;
	}
	
	public int CreateIngredient(IngredientInfo ingredient) {
		int orderID = -1;

		// Get the grocery barcode
		try {
			String getMaxOrderID = "SELECT MAX(orderid) FROM ingredients;";
			ResultSet rs = info.statement.executeQuery(getMaxOrderID);
			System.out.println("Does this work");	
			rs.next();
			orderID = rs.getInt(1) + 1;
		} catch (SQLException e) {
			info.sqlCode = e.getErrorCode(); // Get SQLCODE
			info.sqlState = e.getSQLState(); // Get SQLSTATE

			// Your code to handle errors comes here;
			// something more meaningful than a print would be good
			System.out.println("Error fetching max orderID");
			System.out.println("Code: " + info.sqlCode + "  sqlState: " + info.sqlState);
			System.out.println("Could not create ingredient");
			return -1;
		}
		
		// Insert the ingredient
		try {
			String insertSQL = "INSERT INTO ingredients VALUES (";
			insertSQL = insertSQL + orderID + ", \'" + ingredient.type + "\', " + ingredient.weight + ");";
			System.out.println(insertSQL);
			info.statement.executeUpdate(insertSQL);
			System.out.println("Created ingredient with ID " + orderID);
		} catch (SQLException e) {
			info.sqlCode = e.getErrorCode(); // Get SQLCODE
			info.sqlState = e.getSQLState(); // Get SQLSTATE
			System.out.println("Error creating new Ingredient");
			System.out.println("Code: " + info.sqlCode + "  sqlState: " + info.sqlState);
		}
		
		// Insert the ingredient
		try {
			String insertSQL = "INSERT INTO restaurant_orders VALUES (";
			insertSQL = insertSQL + orderID + ", " + ingredient.roomNo + ", " + ingredient.shipName + ");";
			System.out.println(insertSQL);
			info.statement.executeUpdate(insertSQL);
			System.out.println("Added ingredient with ID " + orderID + " to Ship " + ingredient.shipName + ", room no. " + ingredient.roomNo);
		} catch (SQLException e) {
			info.sqlCode = e.getErrorCode(); // Get SQLCODE
			info.sqlState = e.getSQLState(); // Get SQLSTATE
			System.out.println("Error adding order to restaurant");
			System.out.println("Code: " + info.sqlCode + "  sqlState: " + info.sqlState);
		}
		
		return orderID;
	}
	
	public void DeleteTestVals() {
		try {
			System.out.println("\nDeleting test values...");
			Statement stmt2 = info.connection.createStatement();
			System.out.println("Second statement created?");
			
			// Find all Test groceries
			String getSQL = "SELECT grocerybarcode FROM grocery WHERE type='Test';";
			ResultSet toDelete = stmt2.executeQuery(getSQL);
			System.out.println(getSQL);
			
			// Delete all test groceries from 
			while (toDelete.next()) {
				int barcode = toDelete.getInt(1);
				System.out.println(barcode);
				String delSQL = "DELETE FROM produce WHERE grocerybarcode=" + barcode + ";";
				info.statement.executeUpdate(delSQL);
				System.out.println(delSQL);

				delSQL = "DELETE FROM perishables WHERE grocerybarcode=" + barcode + ";";
				info.statement.executeUpdate(delSQL);
				System.out.println(delSQL);

				delSQL = "DELETE FROM nonperishables WHERE grocerybarcode=" + barcode + ";";
				info.statement.executeUpdate(delSQL);
				System.out.println(delSQL);
				
				delSQL = "DELETE FROM grocery WHERE grocerybarcode=" + barcode + ";";
				info.statement.executeUpdate(delSQL);
				System.out.println(delSQL);
			}
			
			stmt2.close();
			
			// Delete all Test ingredients
			getSQL = "SELECT orderid FROM ingredients WHERE ingredienttype='Test';";
			Statement stmt3 = info.connection.createStatement();
			toDelete = stmt3.executeQuery(getSQL);
			System.out.println(getSQL);
			
			// Delete all test groceries from 
			while (toDelete.next()) {
				int orderid = toDelete.getInt(1);
				System.out.println(orderid);
				String delSQL = "DELETE FROM restaurant_orders WHERE orderid=" + orderid + ";";
				info.statement.executeUpdate(delSQL);
				System.out.println(delSQL);
				
				delSQL = "DELETE FROM ingredients WHERE grocerybarcode=" + orderid + ";";
				info.statement.executeUpdate(delSQL);
				System.out.println(delSQL);
			}
			
			stmt3.close();
			
		} catch (SQLException e) {
			info.sqlCode = e.getErrorCode(); // Get SQLCODE
			info.sqlState = e.getSQLState(); // Get SQLSTATE
			System.out.println("Error deleting test values");
			System.out.println("Code: " + info.sqlCode + "  sqlState: " + info.sqlState);
		}
	}
	
	public static void main(String[] args) {
		try {
			simpleJDBC user = new simpleJDBC();
			
			// Creating test ingredient
			IngredientInfo ing1 = new IngredientInfo();
			ing1.shipName = "Titanic";
			ing1.roomNo = "212";
			ing1.type = "Test";
			ing1.weight = "100";
			int id = user.CreateIngredient(ing1);
			
			// Create a non-perishable
			GroceryInstanceInfo gro1 = new GroceryInstanceInfo();
			gro1.type = "Test";
			gro1.weight = "100";
			gro1.price = "10";
			gro1.dimensions = "(10, 10, 10)";
			gro1.orderID = String.valueOf(id);
			user.CreateGrocery(gro1);
			
			// Create a perishable but not a produce
			GroceryInstanceInfo gro2 = new GroceryInstanceInfo();
			gro2.type = "Test";
			gro2.weight = "200";
			gro2.price = "20";
			gro2.dimensions = "(20, 20, 20)";
			gro2.orderID = String.valueOf(id);
			gro2.isPerishable = true;
			gro2.pe_expiryDate = "2020-01-01";
			gro2.pe_storageTemp = "1000";
			gro2.isProduce = true;
			gro2.pr_season = "Winter";
			user.CreateGrocery(gro2);
			
			// Create a produce
			GroceryInstanceInfo gro3 = new GroceryInstanceInfo();
			gro3.type = "Test";
			gro3.weight = "200";
			gro3.price = "20";
			gro3.dimensions = "(20, 20, 20)";
			gro3.orderID = String.valueOf(id);
			gro3.isPerishable = true;
			gro3.pe_expiryDate = "2020-01-01";
			gro3.pe_storageTemp = "1000";
			user.CreateGrocery(gro3);
			
			// Uncomment this to delete all Test values
//			user.DeleteTestVals();
			
			user.Close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*
	public static void main2(String[] args) throws SQLException {
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
		// Note: This url may not valid now !
		String url = "jdbc:postgresql://comp421.cs.mcgill.ca:5432/cs421";
		Connection con = DriverManager.getConnection(url, "cs421g71", "Omr1$uck$");
		Statement statement = con.createStatement();

		SQLInfo info = new SQLInfo(con, statement, sqlCode, sqlState);

		// Do work here
		String[] columns = { "*" };
		String table = "grocery";
		ResultSet rs = ExecuteQuery(columns, table);
		ResultSetMetaData rsmd = rs.getMetaData();
		int numCols = rsmd.getColumnCount();

		for (int i = 1; i < numCols; ++i) {
			System.out.print(rsmd.getColumnName(i) + "\t");
			System.out.print("\n");
		}

		while (rs.next()) {
			for (int i = 1; i < numCols; ++i) {
				Object colResult = rs.getObject(i);
				System.out.print(String.valueOf(colResult) + "\t");
			}

			System.out.print("\n");
		}
//		statement.close();
		//////////////////////////////////////// EXAMPLES

		// Creating a table
		try {
			String createSQL = "CREATE TABLE " + tableName + " (id INTEGER, name VARCHAR (25)) ";
			System.out.println(createSQL);
			statement.executeUpdate(createSQL);
			System.out.println("DONE");
		} catch (SQLException e) {
			sqlCode = e.getErrorCode(); // Get SQLCODE
			sqlState = e.getSQLState(); // Get SQLSTATE

			// Your code to handle errors comes here;
			// something more meaningful than a print would be good
			System.out.println("Code: " + sqlCode + "  sqlState: " + sqlState);
		}

		// Inserting Data into the table
		try {
			String insertSQL = "INSERT INTO " + tableName + " VALUES ( 1 , \'Vicki\' ) ";
			System.out.println(insertSQL);
			statement.executeUpdate(insertSQL);
			System.out.println("DONE");

			insertSQL = "INSERT INTO " + tableName + " VALUES ( 2 , \'Vera\' ) ";
			System.out.println(insertSQL);
			statement.executeUpdate(insertSQL);
			System.out.println("DONE");
			insertSQL = "INSERT INTO " + tableName + " VALUES ( 3 , \'Franca\' ) ";
			System.out.println(insertSQL);
			statement.executeUpdate(insertSQL);
			System.out.println("DONE");

		} catch (SQLException e) {
			sqlCode = e.getErrorCode(); // Get SQLCODE
			sqlState = e.getSQLState(); // Get SQLSTATE

			// Your code to handle errors comes here;
			// something more meaningful than a print would be good
			System.out.println("Code: " + sqlCode + "  sqlState: " + sqlState);
		}

		// Querying a table
		try {
			String querySQL = "SELECT id, name from " + tableName + " WHERE NAME = \'Vicki\'";
			System.out.println(querySQL);
			rs = statement.executeQuery(querySQL);
			while (rs.next()) {
				int id = rs.getInt(1);
				String name = rs.getString(2);
				System.out.println("id:  " + id);
				System.out.println("name:  " + name);
			}
			System.out.println("DONE");
		} catch (SQLException e) {
			sqlCode = e.getErrorCode(); // Get SQLCODE
			sqlState = e.getSQLState(); // Get SQLSTATE

			// Your code to handle errors comes here;
			// something more meaningful than a print would be good
			System.out.println("Code: " + sqlCode + "  sqlState: " + sqlState);
		}

		// Updating a table
		try {
			String updateSQL = "UPDATE " + tableName + " SET NAME = \'Mimi\' WHERE id = 3";
			System.out.println(updateSQL);
			statement.executeUpdate(updateSQL);
			System.out.println("DONE");

			// Dropping a table
			String dropSQL = "DROP TABLE " + tableName;
			System.out.println(dropSQL);
			statement.executeUpdate(dropSQL);
			System.out.println("DONE");
		} catch (SQLException e) {
			sqlCode = e.getErrorCode(); // Get SQLCODE
			sqlState = e.getSQLState(); // Get SQLSTATE

			// Your code to handle errors comes here;
			// something more meaningful than a print would be good
			System.out.println("Code: " + sqlCode + "  sqlState: " + sqlState);
		}

		// Finally but importantly close the statement and connection
		statement.close();

		////////////////////////////////////////

		// Finally but importantly close the statement and connection
		con.close();
	}
*/
}

