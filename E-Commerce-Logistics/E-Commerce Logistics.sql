create database swiftship_db;
use swiftship_db;

create table Partners(
partner_id int auto_increment primary key,
partner_name varchar(100) not null,
contact_email varchar(100)
);

create table Shipments(
shipment_id int auto_increment primary key,
order_id int not null,
partner_id int,
origin_city varchar(100),
destination_city varchar(100),
shipped_date date,
promised_date date,
actual_delivery_date date,
status varchar(50),
foreign key (partner_id) references Partners(partner_id)
);

create table DeliveryLogs(
log_id int auto_increment primary key,
shipment_id int,
status_update varchar(100),
log_time timestamp default current_timestamp,
foreign key (shipment_id) references Shipments(shipment_id)
);

INSERT INTO Partners (partner_name, contact_email) VALUES
('DHL', 'dhl@gmail.com'),
('FedEx', 'fedex@gmail.com'),
('BlueDart', 'bluedart@gmail.com');
select * from Partners;

INSERT INTO Shipments 
(order_id, partner_id, origin_city, destination_city, shipped_date, promised_date, actual_delivery_date, status)
VALUES
(101, 1, 'Chennai', 'Bangalore', '2026-03-25', '2026-03-28', '2026-03-29', 'Delivered'),   
(102, 2, 'Mumbai', 'Delhi', '2026-03-26', '2026-03-30', '2026-03-30', 'Delivered'),
(103, 3, 'Chennai', 'Hyderabad', '2026-03-27', '2026-03-29', '2026-03-31', 'Delivered'), 
(104, 1, 'Delhi', 'Pune', '2026-03-28', '2026-04-01', NULL, 'In Transit'),  
(105, 2, 'Mumbai', 'Chennai', '2026-03-29', '2026-04-02', '2026-04-04', 'Returned'),
(106, 3, 'Chennai', 'Delhi', '2026-03-30', '2026-04-02', '2026-04-02', 'Delivered'), 
(107, 1, 'Bangalore', 'Chennai', '2026-03-31', '2026-04-03', '2026-04-05', 'Delivered'); 
select * from Shipments;

INSERT INTO DeliveryLogs (shipment_id, status_update) VALUES
(1, 'Shipped'),
(1, 'Out for Delivery'),
(1, 'Delivered'),
(2, 'Shipped'),
(2, 'Delivered'),
(3, 'Shipped'),
(3, 'Delayed'),
(3, 'Delivered'),
(4, 'Shipped'),
(5, 'Shipped'),
(5, 'Returned'),
(6, 'Shipped'),
(6, 'Delivered'),
(7, 'Shipped'),
(7, 'Delayed'),
(7, 'Delivered');
select * from DeliveryLogs;

-- Find Delayed Shipments

select shipment_id,order_id, partner_id,promised_date, actual_delivery_date
from Shipments
where actual_delivery_date>promised_date;

-- Partner Performance - Successful vs Returned

select p.partner_name,
count(case when s.status='delivered' then 1 end) as successful_deliveries,
count(case when s.status='returned' then 1 end) as returned_deliveries
from Partners p 
left join Shipments s 
on p.partner_id=s.partner_id
group by p.partner_name;

-- Most Popular Destination last 30 days

select destination_city, count(*) as total_orders
from Shipments
where shipped_date>= curdate()-interval 30 day
group by destination_city
order by total_orders desc
limit 1;

-- Partner Scorecard

select p.partner_name,
count(s.shipment_id) as total_shipments,
count(case 
when s.actual_delivery_date >s.promised_date then 1 
end) as delayed_shipments,
count( case 
when s.status='delivered'
then 1
end) as succesful_deliveries,

round(
count(case when s.status='delivered' then 1 end)*100.0
/count(s.shipment_id),2
)as success_rate
from Partners p 
left join Shipments s
on p.partner_id=s.partner_id
group by p.partner_name
order by delayed_shipments asc;






