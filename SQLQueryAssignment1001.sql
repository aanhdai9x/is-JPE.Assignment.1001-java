CREATE DATABASE SMS
GO

USE SMS
GO

CREATE TABLE Customer(
	customer_id		INT		PRIMARY KEY ,
	customer_name	VARCHAR(60) ,
)

CREATE TABLE Employee(
	employee_id		INT		PRIMARY KEY ,
	employee_name	VARCHAR(60) ,
	salary			MONEY ,
	supervisor_id	INT ,
)


CREATE TABLE Product(
	product_id		INT		PRIMARY KEY ,
	product_name	VARCHAR(60) ,
	list_price		MONEY ,
)

CREATE TABLE Orders(
	order_id		INT		PRIMARY KEY ,
	order_date		DATE ,
	customer_id		INT ,
	employee_id		INT	,
	total			INT ,
	FOREIGN KEY (customer_id) REFERENCES Customer (customer_id) ,
	FOREIGN KEY (employee_id) REFERENCES Employee (employee_id) ,
)

CREATE TABLE LineItem(
	order_id		INT		PRIMARY KEY ,
	product_id		INT ,
	quantity		INT ,
	price			MONEY ,
	FOREIGN KEY (order_id) REFERENCES Orders (order_id) ,
	FOREIGN KEY (product_id) REFERENCES Product (product_id) ,
)


INSERT INTO Customer(customer_id, customer_name) VALUES(5, 'Dong');

INSERT INTO Employee(employee_id, employee_name, salary, supervisor_id) VALUES (2, 'Duong', 2000, 2);

INSERT INTO Product(product_id, product_name, list_price) VALUES (1, 'Banh', 2000);

INSERT INTO Orders(order_id, order_date, customer_id, employee_id, total) VALUES('2', CAST('2021-12-09' AS DATE), '2', '2', '2');

INSERT INTO LineItem (order_id, product_id, quantity, price) VALUES (2, 1, 3, 3000);

SELECT * FROM  Orders, Customer
WHERE Customer.customer_id = Orders.customer_id;

SELECT li.quantity*li.price AS total_price FROM LineItem li
WHERE li.order_id = 1;