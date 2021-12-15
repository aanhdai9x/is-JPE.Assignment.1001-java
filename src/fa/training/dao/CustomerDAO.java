package fa.training.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fa.training.entities.Customer;
import fa.training.entities.Order;

public class CustomerDAO {
	private static String jdbcURL = "jdbc:sqlserver://localhost:1433;" + "databaseName=SMS;";
	private static String jdbcUsername = "sa";
	private static String jdbcPassword = "root";

	private static final String SELECT_ALL_CUSTOMERS = "select * from Customer";

	private static final String SELECT_ORDER_BY_CUSTOMER_ID = "select * from Orders where customer_id = ? ";

	private static final String INSERT_CUSTOMER = "INSERT INTO Customer(customer_id, customer_name) VALUES( ? , ? )";

	private static final String DELETE_CUSSTOMER_BY_ID = "DELETE FROM Customer WHERE customer_id = ? ";

	private static final String SQL_UPDATE = "UPDATE Customer SET customer_name = ? WHERE customer_id = ?";

	public CustomerDAO() {
	}

	protected static Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}

	public List<Customer> getAllCustomer() {

		// using try-with-resources to avoid closing resources (boiler plate code)
		List<Customer> orders = new ArrayList<>();
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();

				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CUSTOMERS);) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int customer_id = rs.getInt("customer_id");
				String customer_name = rs.getString("customer_name");

				System.out.println(customer_id + "\t" + customer_name);
				orders.add(new Customer(customer_id, customer_name));
			}
		} catch (SQLException e) {

		}
		return orders;
	}

	public static List<Order> getAllOrdersByCustomerId(int customer_id) {
		List<Order> l2 = new ArrayList<Order>();
		System.out.println(SELECT_ORDER_BY_CUSTOMER_ID);
		try {
			Connection connection = getConnection();
			// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ORDER_BY_CUSTOMER_ID);
			preparedStatement.setInt(1, customer_id);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			// show data
			while (rs.next()) {
				l2.add(new Order(rs.getInt(1), rs.getDate(2), rs.getInt(3), rs.getInt(4), rs.getDouble(5)));
				System.out.println(rs.getInt(1) + "\t" + rs.getDate(2) + "\t" + rs.getInt(3) + "\t" + rs.getInt(4)
						+ "\t" + rs.getDouble(5));
			}
			return l2;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return l2;
	}

	public boolean addCustomer(Customer customer) {
		try {
			Connection conn = getConnection();
			CallableStatement stmt = conn.prepareCall(INSERT_CUSTOMER);
			stmt.setInt(1, customer.getCustomerId());
			stmt.setString(2, customer.getCustomerName());
			// get data from table 'student'
			stmt.execute();
			conn.close();
			System.out.println("Add success");
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public boolean deleteCustomer(int customer_id) {
		boolean rowDeleted;
		try {
			Connection connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(DELETE_CUSSTOMER_BY_ID);
			statement.setInt(1, customer_id);
			rowDeleted = statement.executeUpdate() > 0;
			System.out.println("Deleted Customer id = " + customer_id);
			return rowDeleted;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}

	public boolean updateCustomer(Customer customer){
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_UPDATE);) {
			statement.setString(1, customer.getCustomerName());
			statement.setInt(2, customer.getCustomerId());
			rowUpdated = statement.executeUpdate() > 0;
			System.out.println("Update Success");
			return rowUpdated;
		} catch(SQLException e) {
			return false;
		}
		
	}

}
