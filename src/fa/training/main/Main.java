package fa.training.main;

import java.util.Scanner;

import fa.training.dao.CustomerDAO;
import fa.training.dao.OrderDAO;
import fa.training.dao.impl.CustomerDAOImpl;
import fa.training.dao.impl.LineItemDAOImpl;
import fa.training.dao.impl.OrdersDAOImpl;
import fa.training.entities.Customer;

public class Main {
	static CustomerDAOImpl customerDAOImpl = new CustomerDAOImpl();
	static OrdersDAOImpl ordersDAOImpl = new OrdersDAOImpl();
	static LineItemDAOImpl lineItemDAOImpl = new LineItemDAOImpl();
	static OrderDAO orderDAO = new OrderDAO();
	static CustomerDAO customerDAO = new CustomerDAO();

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		// 1.
		// System.out.println("Get all Customers: ");
		// customerDAOImpl.findAll();

		// 2.
		// System.out.println("Get order by id: ");
		// System.out.println("Nhập customer_id: "); String customer_id2 = scan.next();
		// ordersDAOImpl.retrieve(customer_id2);

		// 3.
		// System.out.println("Get LineItem by order id: ");
		// System.out.println("Nhập order_id: "); String order_id3 = scan.next();
		// lineItemDAOImpl.retrieve(order_id3);

		// 4.
		// System.out.println("Nhập order_id: "); int order_id4 = scan.nextInt();
		// orderDAO.computeOrderTotal(order_id4);

		// 5.
		// System.out.println("Nhập customer_id: "); int customer_id2 = scan.nextInt();
		// System.out.println("Nhập customer_name: "); String customer_name2 =
		// scan.next();
		// if(customerDAO.addCustomer(new Customer(customer_id2, customer_name2))) {

		// 6.
		//System.out.println("Delete by customer_id: ");
		//String customer_id6 = scan.next();
		//customerDAOImpl.dalete(customer_id6);

		//7.
		//System.out.println("Update Customer by customer_id: ");
		//String customer_id7 = scan.next();
		//customerDAOImpl.update(customer_id7);
		
		//9.
		//System.out.println("Create Line Item: ");
		//lineItemDAOImpl.create(null);
		
		//10.
		System.out.println("Nhập order_id: "); int order_id5 = scan.nextInt();
		orderDAO.updateOrderTotal(order_id5);
		
	}
}

