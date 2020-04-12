
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
import java.util.ArrayList;
import java.util.Arrays;

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

class IngredientUpdateInfo {
	public String orderid;
	public String type = null;
	public String weight = null;
}

class RestaurantInfo {
	public String shipname;
	public String restaurantName;
	public String roomNo;
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

class BudgetUpdateInfo {
	public String shipname;
	public String roomNo;
	public String newBudget;
}

class simpleJDBC {
	public SQLInfo info;

	// singleton
	private static simpleJDBC INSTANCE = null;

	private simpleJDBC() throws SQLException {
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

		System.out.println("JDBC User Constructor Succeeded & Finished");
	}

	public static simpleJDBC getInstance() throws SQLException {
		if (INSTANCE == null)
			INSTANCE = new simpleJDBC();

		return INSTANCE;
	}

	public void Close() throws SQLException {
		// Need to call this whenever the application is closed!
		this.info.statement.close();
		this.info.connection.close();

		System.out.println("Successfully closed statement & connection for User");
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
			
			throw new SQLException();
		}
	}

	public int CreateGrocery(GroceryInstanceInfo grocery) throws IllegalArgumentException, SQLException {
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
			throw new SQLException();
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
			throw new SQLException();
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
				throw new SQLException();
			}

			// Is perishable & produce
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
					throw new SQLException();
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
				throw new SQLException();
			}
		}

		// Update the used budgets
		// If it fails to update the used budget, it deletes the grocery it just added
		// It will throw an IllegalArgumentException if this occurs.
		try {
			System.out.println("Updating budget...");
			UpdateUsedBudgets();
			System.out.println("Updated budget");
		} catch (SQLException e) {
			System.out.println("Could not update budget, deleting grocery...");
			try {
				DeleteGrocery(groceryBarcode);
				System.out.println("Error with updating budgets- did not create grocery.");
				throw new IllegalArgumentException("Restaurant does not have enough budget for this grocery.");
			} catch (SQLException e1) {
				info.sqlCode = e.getErrorCode(); // Get SQLCODE
				info.sqlState = e.getSQLState(); // Get SQLSTATE
				System.out.println("Error deleting new grocery " + groceryBarcode);
				System.out.println("Code: " + info.sqlCode + "  sqlState: " + info.sqlState);
				
				throw new SQLException();
			}
		}

		return groceryBarcode;
	}

	public int CreateIngredient(IngredientInfo ingredient) throws SQLException {
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
			throw new SQLException();
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
			throw new SQLException();
		}

		// Insert the ingredient
		try {
			String insertSQL = "INSERT INTO restaurant_orders VALUES (";
			insertSQL = insertSQL + orderID + ", " + ingredient.roomNo + ", \'" + ingredient.shipName + "\');";
			System.out.println(insertSQL);
			info.statement.executeUpdate(insertSQL);
			System.out.println("Added ingredient with ID " + orderID + " to Ship " + ingredient.shipName + ", room no. "
					+ ingredient.roomNo);
		} catch (SQLException e) {
			info.sqlCode = e.getErrorCode(); // Get SQLCODE
			info.sqlState = e.getSQLState(); // Get SQLSTATE
			System.out.println("Error adding order to restaurant");
			System.out.println("Code: " + info.sqlCode + "  sqlState: " + info.sqlState);
			throw new SQLException();
		}

		return orderID;
	}

	public void UpdateBudget(BudgetUpdateInfo budgetInfo) throws SQLException {
		try {
			String updateSQL = "UPDATE restaurant SET restaurantbudget=" + budgetInfo.newBudget + " WHERE shipname=\'"
					+ budgetInfo.shipname + "\' AND roomnumber=" + budgetInfo.roomNo + ";";
			System.out.println(updateSQL);
			info.statement.executeUpdate(updateSQL);
			System.out.println("Updated budget for " + budgetInfo.shipname + ", roomNo. " + budgetInfo.roomNo + " to "
					+ budgetInfo.newBudget);
		} catch (SQLException e) {
			info.sqlCode = e.getErrorCode(); // Get SQLCODE
			info.sqlState = e.getSQLState(); // Get SQLSTATE
			System.out.println("Error updating budget");
			System.out.println("Code: " + info.sqlCode + "  sqlState: " + info.sqlState);
			throw new SQLException();
		}
	}

	public void UpdateIngredient(IngredientUpdateInfo ingInfo) throws SQLException {
		try {
			if (ingInfo.type != null) {
				String updateSQL = "UPDATE ingredients " + "SET ingredienttype=\'" + ingInfo.type + "\'"
						+ " WHERE orderid=" + ingInfo.orderid + ";";
				System.out.println(updateSQL);
				info.statement.executeUpdate(updateSQL);
			}

			if (ingInfo.weight != null) {
				String updateSQL = "UPDATE ingredients " + "SET ingredientweight=" + ingInfo.weight + ""
						+ " WHERE orderid=" + ingInfo.orderid + ";";
				System.out.println(updateSQL);
				info.statement.executeUpdate(updateSQL);
			}

			/*
			 * Update all at once String updateSQL = "UPDATE ingredients " +
			 * "SET ingredienttype=\'" + ingInfo.type + "\', ingredientweight=" +
			 * ingInfo.weight + "" + " WHERE orderid=" + ingInfo.orderid + ";";
			 * System.out.println(updateSQL); info.statement.executeUpdate(updateSQL);
			 */

			System.out.println("Updated ingredient " + ingInfo.orderid);
		} catch (SQLException e) {
			info.sqlCode = e.getErrorCode(); // Get SQLCODE
			info.sqlState = e.getSQLState(); // Get SQLSTATE
			System.out.println("Error updating ingredient");
			System.out.println("Code: " + info.sqlCode + "  sqlState: " + info.sqlState);
		
			throw new SQLException();
		}
	}

	// This function needs restaurant.shipName and restaurant.restaurantName
	public ArrayList<String[]> GetRestaurantOrders(RestaurantInfo restaurant) throws SQLException {
		try {
			String querySQL = "SELECT * FROM orders_info" + " WHERE shipname=\'" + restaurant.shipname
					+ "\' AND restaurant_name=\'" + restaurant.restaurantName + "\';";
			System.out.println(querySQL);
			ResultSet rs = info.statement.executeQuery(querySQL);
			System.out.println("Fetched orders for " + restaurant.shipname + ", " + restaurant.restaurantName);

			return Get2DArrayListFromResultSet(rs);

		} catch (SQLException e) {
			info.sqlCode = e.getErrorCode(); // Get SQLCODE
			info.sqlState = e.getSQLState(); // Get SQLSTATE
			System.out.println("Error getting ingredients info");
			System.out.println("Code: " + info.sqlCode + "  sqlState: " + info.sqlState);
			
			throw new SQLException();
		}
	}

	public ArrayList<String[]> GetBudgetInfo(String shipName) throws SQLException {
		try {
			String querySQL = "SELECT roomnumber, restaurantname, capacity, restaurantbudget, usedBudget"
					+ " FROM restaurant" + " WHERE shipname=\'" + shipName + "\';";
			System.out.println(querySQL);
			ResultSet rs = info.statement.executeQuery(querySQL);
			System.out.println("Fetched budgets for " + shipName);

			return Get2DArrayListFromResultSet(rs);

		} catch (SQLException e) {
			info.sqlCode = e.getErrorCode(); // Get SQLCODE
			info.sqlState = e.getSQLState(); // Get SQLSTATE
			System.out.println("Error getting restaurant budget info");
			System.out.println("Code: " + info.sqlCode + "  sqlState: " + info.sqlState);
			throw new SQLException();
		}
	}

	public ArrayList<String> GetShipNames() {
		try {
			String querySQL = "SELECT shipname FROM ship;";
			System.out.println(querySQL);
			ResultSet rs = info.statement.executeQuery(querySQL);
			System.out.println("Fetched all ships");

			return GetArrayListFromResultSet(rs);

		} catch (SQLException e) {
			info.sqlCode = e.getErrorCode(); // Get SQLCODE
			info.sqlState = e.getSQLState(); // Get SQLSTATE
			System.out.println("Error getting all ships");
			System.out.println("Code: " + info.sqlCode + "  sqlState: " + info.sqlState);
		}

		return null;
	}

	public ArrayList<String> GetRestaurants(String shipName) {
		try {
			String querySQL = "SELECT roomnumber, restaurantname FROM restaurant" + " WHERE shipname=\'" + shipName
					+ "\';";
			System.out.println(querySQL);
			ResultSet rs = info.statement.executeQuery(querySQL);
			System.out.println("Fetched all restauraunts from ship " + shipName);

			ArrayList<String> list = new ArrayList<String>();
			while (rs.next()) {
				String row = rs.getString(2) + " (" + rs.getInt(1) + ")";
				list.add(row);
			}

			return list;

		} catch (SQLException e) {
			info.sqlCode = e.getErrorCode(); // Get SQLCODE
			info.sqlState = e.getSQLState(); // Get SQLSTATE
			System.out.println("Error getting restaurants in ship " + shipName);
			System.out.println("Code: " + info.sqlCode + "  sqlState: " + info.sqlState);
		}

		return null;
	}

	public ArrayList<String> GetListOfDietaryRestrictions(String shipName) throws SQLException{
		try {
			String querySQL = "SELECT * FROM dietaryrestriction "
					+ " WHERE restrictiontype IN" 
					+ " (SELECT restrictiontype FROM has_restriction_type"
					+ " WHERE passengerid IN"
					+ " (SELECT passengerid FROM sailson"
					+ " WHERE shipname=\'" + shipName + "\'));";
			System.out.println(querySQL);
			ResultSet rs = info.statement.executeQuery(querySQL);
			System.out.println("Fetched restrictions for " + shipName);

			return GetArrayListFromResultSet(rs);

		} catch (SQLException e) {
			info.sqlCode = e.getErrorCode(); // Get SQLCODE
			info.sqlState = e.getSQLState(); // Get SQLSTATE
			System.out.println("Error getting restaurant budget info");
			System.out.println("Code: " + info.sqlCode + "  sqlState: " + info.sqlState);
			throw new SQLException();
		}
	}
	
	public ArrayList<String[]> GetPassengersWithDietaryRestriction(String restrictionType, String shipName) throws SQLException {
		try {
			String querySQL = "SELECT * FROM passenger" + " WHERE passengerid IN"
					+ " (SELECT passengerid FROM has_restriction_type" + " WHERE restrictiontype=\'" + restrictionType
					+ "\') AND passengerid IN"
					+ " (SELECT passengerid FROM sailson WHERE shipname=\'" + shipName +"\');";
			ResultSet rs = info.statement.executeQuery(querySQL);
			System.out.println(querySQL);

			return Get2DArrayListFromResultSet(rs);

		} catch (SQLException e) {
			info.sqlCode = e.getErrorCode(); // Get SQLCODE
			info.sqlState = e.getSQLState(); // Get SQLSTATE
			System.out.println("Error getting passengers with dietary restriction " + restrictionType);
			System.out.println("Code: " + info.sqlCode + "  sqlState: " + info.sqlState);
			throw new SQLException();
		}
	}

	public ArrayList<String[]> Get2DArrayListFromResultSet(ResultSet rs) throws SQLException {
		ResultSetMetaData rsmd = rs.getMetaData();
		int numCols = rsmd.getColumnCount();

		ArrayList<String[]> list = new ArrayList<String[]>();

		// Add column headers
		String[] row = new String[numCols];
		for (int i = 0; i < numCols; ++i) {
			row[i] = rsmd.getColumnName(i + 1);
		}
		list.add(row);

		// Add each row
		while (rs.next()) {
			row = new String[numCols];
			for (int i = 0; i < numCols; ++i) {
				row[i] = String.valueOf(rs.getObject(i + 1));
			}
			list.add(row);
		}

		return list;
	}
	
	public ArrayList<String> GetArrayListFromResultSet(ResultSet rs) throws SQLException{
		ArrayList<String> list = new ArrayList<String>();
		while (rs.next()) {
			list.add(String.valueOf(rs.getObject(1)));
		}
		
		return list;
	}

	public void UpdateUsedBudgets() throws SQLException {
		try {
			String querySQL = "SELECT update_usedBudgets();";
			System.out.println(querySQL);
			ResultSet rs = info.statement.executeQuery(querySQL);
			System.out.println("Updated used budgets successfully");
		} catch (SQLException e) {
			info.sqlCode = e.getErrorCode(); // Get SQLCODE
			info.sqlState = e.getSQLState(); // Get SQLSTATE
			System.out.println("Error updating used budgets!");
			System.out.println("Code: " + info.sqlCode + "  sqlState: " + info.sqlState);
			throw new SQLException("Budget Update failed");
		}
	}

	public void DeleteGrocery(int barcode) throws SQLException {
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

		UpdateUsedBudgets();
	}

	public void DeleteIngredient(int orderid) throws SQLException {
		System.out.println("Deleting order " + orderid);
		String getSQL = "SELECT grocerybarcode FROM grocery WHERE orderid=" + orderid + ";";
		Statement stmt2 = simpleJDBC.getInstance().info.connection.createStatement();
		ResultSet toDelete = stmt2.executeQuery(getSQL);
		
		while(toDelete.next()) {
			DeleteGrocery(toDelete.getInt("grocerybarcode"));
		}
		
		String delSQL = "DELETE FROM restaurant_orders WHERE orderid=" + orderid + ";";
		info.statement.executeUpdate(delSQL);
		System.out.println(delSQL);

		delSQL = "DELETE FROM ingredients WHERE orderid=" + orderid + ";";
		info.statement.executeUpdate(delSQL);
		System.out.println(delSQL);
	}

	public void DeleteTestVals() {
		try {
			System.out.println("\nDeleting test values...");
			Statement stmt2 = info.connection.createStatement();

			// Find all Test groceries
			String getSQL = "SELECT grocerybarcode FROM grocery WHERE type='Test';";
			ResultSet toDelete = stmt2.executeQuery(getSQL);
			System.out.println(getSQL);

			// Delete all test groceries from
			while (toDelete.next()) {
				int barcode = toDelete.getInt(1);
				DeleteGrocery(barcode);
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
				DeleteIngredient(orderid);
			}

			stmt3.close();

			System.out.println("Successfully deleted all test values");

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

			try {
				AssignRandomRestrictions(user);
//				TestCases(user);
//				TestFunction(user);
//				user.DeleteTestVals();
			} catch (IllegalArgumentException e) {
				System.out.println("Error occurred.");
			} catch (SQLException e) {
				System.out.println("SQL Error occured.");
			}

			user.Close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void TestFunction(simpleJDBC user) throws SQLException {
		// Creating test ingredient
		IngredientInfo ing1 = new IngredientInfo();
		ing1.shipName = "Titanic";
		ing1.roomNo = "301";
		ing1.type = "Test";
		ing1.weight = "1000";
		int id = user.CreateIngredient(ing1);

		// Create a non-perishable
		GroceryInstanceInfo gro1 = new GroceryInstanceInfo();
		gro1.type = "Test";
		gro1.weight = "100";
		gro1.price = "50";
		gro1.dimensions = "(10, 10, 10)";
		gro1.orderID = String.valueOf(id);
		user.CreateGrocery(gro1);
	}
	
	public static void AssignRandomRestrictions(simpleJDBC user) throws SQLException {
		String getSQL = "SELECT * FROM dietaryrestriction;";
		ResultSet rs = user.info.statement.executeQuery(getSQL);
		
		ArrayList<String> resList = new ArrayList<String>();
		while(rs.next()) {
			resList.add(rs.getString(1));
		}
		
		getSQL = "SELECT passengerid FROM passenger;";
		rs = user.info.statement.executeQuery(getSQL);
		ArrayList<Integer> pList = new ArrayList<Integer>();
		while(rs.next()) {
			pList.add(rs.getInt(1));
		}
		
		for(int i = 0; i < 5; i++) {
			int rIndex = (int) (Math.random() * resList.size());
			int pIndex = (int) (Math.random() * pList.size());
			String addSQL = "INSERT INTO has_restriction_type VALUES (\'"
					+ resList.get(rIndex) + "\', " + String.valueOf(pList.get(pIndex)) +");";
			user.info.statement.executeUpdate(addSQL);
		}
	}

	public static void TestCases(simpleJDBC user) throws SQLException {
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

		BudgetUpdateInfo b1 = new BudgetUpdateInfo();
		b1.newBudget = "1750";
		b1.roomNo = "101";
		b1.shipname = "Celebrity Edge";
		user.UpdateBudget(b1);

		IngredientUpdateInfo i1 = new IngredientUpdateInfo();
		i1.orderid = String.valueOf(id);
		i1.weight = "2000";
		user.UpdateIngredient(i1);

		// Uncomment this to delete all Test values
		user.DeleteTestVals();

		System.out.println();
		ArrayList<String> shipNames = user.GetShipNames();
		System.out.println("\nAll ship names:");
		System.out.println(Arrays.toString(shipNames.toArray()));

		System.out.println();
		ArrayList<String> restaurantNames = user.GetRestaurants(shipNames.get(0));
		System.out.println("\nAll restauraunts from " + shipNames.get(0));
		System.out.println(Arrays.toString(restaurantNames.toArray()));

		System.out.println();
		ArrayList<String[]> budgetInfo = user.GetBudgetInfo(shipNames.get(0));
		System.out.println("Budget info for Ship " + shipNames.get(0));
		for (int i = 0; i < budgetInfo.size(); ++i) {
			for (int j = 0; j < budgetInfo.get(0).length; ++j) {
				System.out.print(budgetInfo.get(i)[j] + "\t");
			}
			System.out.println();
		}

		System.out.println();
		RestaurantInfo r1 = new RestaurantInfo();
		r1.shipname = "Titanic";
		r1.restaurantName = "Pizza Pizza";
		ArrayList<String[]> restaurantOrders = user.GetRestaurantOrders(r1);
		System.out.println("Orders for " + r1.shipname + ", " + r1.restaurantName);
		for (int i = 0; i < restaurantOrders.size(); ++i) {
			for (int j = 0; j < restaurantOrders.get(0).length; ++j) {
				System.out.print(restaurantOrders.get(i)[j] + "\t");
			}
			System.out.println();
		}
		
		System.out.println();
		ArrayList<String> restrictionListTitanic = user.GetListOfDietaryRestrictions("Titanic");
		System.out.println("All restrictions from " + "Titanic");
		System.out.println(Arrays.toString(restrictionListTitanic.toArray()));
	}
}
