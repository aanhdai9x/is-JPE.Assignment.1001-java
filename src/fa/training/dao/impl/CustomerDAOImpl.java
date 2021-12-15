package fa.training.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fa.training.dao.BaseDAO;
import fa.training.entities.Customer;
import fa.training.utils.DataConfig;

public class CustomerDAOImpl implements BaseDAO<Customer, String> {
	@Override
	public void create(Customer obj) {
		try {
			String INSERT_CUSTOMER = "INSERT INTO Customer(customer_id, customer_name) VALUES( ? , ? )";
			Connection conn = DataConfig.getConnection();
			CallableStatement stmt = conn.prepareCall(INSERT_CUSTOMER);
			stmt.setInt(1, obj.getCustomerId());
			stmt.setString(2, obj.getCustomerName());
			// get data from table 'student'
			stmt.execute();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void retrieve(String str) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(String str) {
		boolean rowUpdated;
		String SQL_UPDATE = "UPDATE Customer SET customer_name = ? WHERE customer_id = ?";
		try (Connection connection = DataConfig.getConnection();
				PreparedStatement statement = connection.prepareStatement(SQL_UPDATE);) {
			statement.setString(1, "Lop");
			statement.setString(2, str);
			rowUpdated = statement.executeUpdate() > 0;
			System.out.println("Update Success");
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void dalete(String str) {
		boolean rowDeleted;
		try {
			String DELETE_CUSSTOMER_BY_ID = "DELETE FROM Customer WHERE customer_id = ? ";
			Connection connection = DataConfig.getConnection();
			PreparedStatement statement = connection.prepareStatement(DELETE_CUSSTOMER_BY_ID);
			statement.setString(1, str);
			rowDeleted = statement.executeUpdate() > 0;
			System.out.println("Deleted Customer id = " + str);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		
	}

	@Override
	public void findAll() {
		List<Customer> orders = new ArrayList<>();
		try {
			String SELECT_ALL_CUSTOMERS = "select * from Customer";
			Connection conn = DataConfig.getConnection();
			PreparedStatement preparedStatement = conn.prepareStatement(SELECT_ALL_CUSTOMERS);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				int customer_id = rs.getInt("customer_id");
				String customer_name = rs.getString("customer_name");

				System.out.println(customer_id + "\t" + customer_name);
				orders.add(new Customer(customer_id, customer_name));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
