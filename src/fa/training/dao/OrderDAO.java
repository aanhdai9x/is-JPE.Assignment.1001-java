package fa.training.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import fa.training.dao.impl.OrdersDAOImpl;
import fa.training.utils.DataConfig;

public class OrderDAO extends OrdersDAOImpl {

	public void computeOrderTotal(int orderId) {
		try {
			String COMPUTE_ORDER_TOTAL = "SELECT li.quantity*li.price AS total_price FROM LineItem li";
			Connection conn = DataConfig.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(COMPUTE_ORDER_TOTAL);
			while (rs.next()) {
				System.out.println("Total price for orderId = " + orderId + ": " + rs.getDouble(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean updateOrderTotal(int orderId) {
		boolean rowUpdated;
		String UPDATE_BY_ID = "UPDATE Orders SET total = ? WHERE order_id = ? ";
		try (Connection connection = DataConfig.getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_BY_ID);) {
			statement.setInt(1, 5);
			statement.setInt(2, orderId);
			rowUpdated = statement.executeUpdate() > 0;
			System.out.println("Update Success");
			return rowUpdated;
		} catch (SQLException e) {
			return false;
		}
	}

}
