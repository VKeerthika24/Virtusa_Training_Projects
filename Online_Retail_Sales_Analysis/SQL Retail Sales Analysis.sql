create database online_retail;
use online_retail;

create table Customers(
customer_id int  primary key,
name varchar(100),
city varchar(50)
);

create table Products(
product_id int primary key,
name varchar(100),
category varchar(50),
price decimal(10,2)
);

create table Orders(
order_id int primary key,
customer_id int,
order_date date,
foreign key (customer_id) references Customers(customer_id)
);

create table Order_Items(
order_id int,
product_id int,
quantity int,
foreign key(order_id) references Orders(order_id),
foreign key(product_id) references Products(product_id)
);

insert into Customers values
(1,'Keerthika','Tiruppur'),
(2,'Keerthana','Coimbatore'),
(3,'Jothisha','Chennai'),
(4,'Varsan','Bangalore'),
(5,'Anu','Mumbai');
select * from Customers;

insert into Products values
(101,'Laptop','Electronics',50000),
(102,'Phone','Electronics',20000),
(103,'Shoes','Fashion',3000),
(104,'Watch','Accessories',5000),
(105,'Bag','Fashion',2000);
select * from Products;

insert into Orders values
(1001, 1, '2024-01-10'),
(1002, 2, '2024-02-15'),
(1003, 1, '2024-03-05'),
(1004, 3, '2024-03-10'),
(1005, 4, '2024-04-01');
select * from Orders;

insert into Order_Items values
(1001, 101, 1),
(1001, 103, 2),
(1002, 102, 1),
(1003, 101, 1),
(1003, 104, 1),
(1004, 105, 3),
(1005, 103, 1);
select * from Order_Items;

-- To find top selling product id

select product_id , sum(quantity) from Order_Items
group by product_id order by sum(quantity) desc;

-- To find top selling product full details
select p.product_id, p.name, sum(oi.quantity) as total_sold
from Order_Items oi join Products p
on oi.product_id=p.product_id
group by p.product_id, p.name
order by total_sold desc;

-- Most valuable customer

select c.customer_id, c.name, sum(p.price*quantity) as total_spent
from Customers c 
join Orders o on o.customer_id=c.customer_id
join Order_Items oi on oi.order_id=o.order_id
join Products p on p.product_id=oi.product_id
group by c.customer_id, c.name 
order by total_spent desc;

-- Monthly revenue calculation

select date_format(o.order_date,'%Y-%m') as month, sum(oi.quantity*p.price) as revenue
from Order_Items oi 
join Orders o on oi.order_id=o.order_id
join Products p on p.product_id=oi.product_id
group by month
order by month;

-- Category-wise sales analysis

select p.category, sum(oi.quantity) as total_items_sold,
sum(p.price*oi.quantity) as total_revenue
from Products p 
join Order_Items oi on p.product_id=oi.product_id
group by p.category;

-- Detect inactive customers

select c.customer_id, c.name
from Customers c left join Orders o on c.customer_id=o.customer_id
where o.order_id is null;























