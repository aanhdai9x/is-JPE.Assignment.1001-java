package fa.training.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fa.training.dao.BaseDAO;
import fa.training.entities.LineItem;
import fa.training.utils.DataConfig;

public class LineItemDAOImpl implements BaseDAO<LineItem, String> {

	@Override
	public void create(LineItem obj) {
		try {
			String INSERT_LINE_ITEM = "INSERT INTO LineItem (order_id, product_id, quantity, price) VALUES (?, ?, ?, ?);";
			Connection conn = DataConfig.getConnection();
			CallableStatement stmt = conn.prepareCall(INSERT_LINE_ITEM);
			stmt.setInt(1, obj.getOrderId());
			stmt.setInt(2, obj.getProductId());
			stmt.setInt(3, obj.getQuantity());
			stmt.setDouble(4, obj.getPrice());
			stmt.execute();
			conn.close();
			System.out.println("Add success");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void retrieve(String str) {
		List<LineItem> li = new ArrayList<LineItem>();
		String SELECT_LINE_ITEM_BY_ORDER_ID = "select * from LineItem where order_id = ? ";
		try {
			Connection conn = DataConfig.getConnection();
			PreparedStatement preparedStatement = conn.prepareStatement(SELECT_LINE_ITEM_BY_ORDER_ID);
			preparedStatement.setString(1, str);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				li.add(new LineItem(rs.getInt("order_id"), rs.getInt("product_id"), rs.getInt("quantity"), rs.getDouble("price")));
				System.out.println(rs.getInt("order_id") +"\t"+ rs.getInt("product_id") +"\t"+ rs.getInt("quantity") +"\t"+ rs.getDouble("price"));
			}
		} catch (SQLException e) {
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
