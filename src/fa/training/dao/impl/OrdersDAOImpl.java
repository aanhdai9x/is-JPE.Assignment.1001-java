package fa.training.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fa.training.dao.BaseDAO;
import fa.training.entities.Order;
import fa.training.utils.DataConfig;

public class OrdersDAOImpl implements BaseDAO<Order, String> {
	@Override
	public void create(Order obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void retrieve(String str) {
		List<Order> lo = new ArrayList<Order>();
		String SELECT_ORDER_BY_CUSTOMER_ID = "select * from Orders where customer_id = ? ";
		try {
			Connection conn = DataConfig.getConnection();
			PreparedStatement preparedStatement = conn.prepareStatement(SELECT_ORDER_BY_CUSTOMER_ID);
			preparedStatement.setString(1, str);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				lo.add(new Order(rs.getInt(1), rs.getDate(2), rs.getInt(3), rs.getInt(4), rs.getDouble(5)));
				System.out.println(rs.getInt(1) + "\t" + rs.getDate(2) + "\t" + rs.getInt(3) + "\t" + rs.getInt(4)
						+ "\t" + rs.getDouble(5));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void update(String str) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dalete(String str) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void findAll() {
		// TODO Auto-generated method stub
		
	}

}
