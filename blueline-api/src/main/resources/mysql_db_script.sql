drop database if exists bluelinetaxi_db;
create database bluelinetaxi_db;
use  bluelinetaxi_db;

create table taxi_booking(
	booking_id int primary key auto_increment,
	user_mobile BIGINT(10),
	source varchar(15),
	destination varchar(10),
	fare float(9,2),
	travel_date DATE,
	status CHAR NOT NULL
);


create table taxi_fare(
	fare_id int primary key,
	source varchar(15),
	destination varchar(15),
	fare float(9,2)
);

insert into taxi_fare values(1,'San Jose','Los Angles',340);
insert into taxi_fare values(2,'San Francisco','San Jose',48);
insert into taxi_fare values(3,'Los Angles','San Diego',120);
insert into taxi_fare values(4,'Pheonix','Tucson',114);

insert into taxi_booking values(1,9877766756,'San Jose','Los Angles',340,sysdate()+2,'B');
insert into taxi_booking values(2,8898766766,'San Francisco','San Jose',48,sysdate()+4,'B');

commit;

