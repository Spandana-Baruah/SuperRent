drop table Reservation;
drop table VehicleType;
drop table Customer;
drop table TimePeriod;

create table VehicleType
(vtname varchar2(30) not null PRIMARY KEY,
features varchar2(50) not null,
wrate number not null,
drate number not null,
hrate number not null,
wirate number not null,
dirate number not null,
hirate number not null,
krate number not null );

create table Customer
(cellphone number(10,0) not null UNIQUE,
name varchar2(30) not null,
address varchar2(50) not null,
dlicense number(10,0) not null PRIMARY KEY );

create table TimePeriod
(fromDate date not null,
fromTime timestamp not null,
toDate date not null,
toTime timestamp not null,
PRIMARY KEY (fromDate, fromTime, toDate, toTime));

create table Reservation
( confNo number(5,0) not null PRIMARY KEY,
vtname varchar2(30) not null,
cellphone number(10,0) not null,
fromDate date not null,
fromTime timestamp not null,
toDate date not null,
toTime timestamp not null,
foreign key (vtname) references VehicleType (vtname),
foreign key (cellphone) references Customer (cellphone),
foreign key (fromDate, fromTime, toDate, toTime) references TimePeriod (fromDate, fromTime, toDate, toTime) );



--To make a reservation, a customer provides the location, the type of the
--vehicle, and the day and time for which she/he would like to pick up and return the vehicle. If
--there is a vehicle of the requested type available in that location, the system asks the customer
--for any additional options and shows an estimation of the cost. The customer can then proceed
--and make a reservation or cancel it. To make a reservation, the customer provides her/his
--name and phone number, and the system prints a confirmation number. To cancel a
--reservation, a customer must provide either the confirmation number or their phone number
--and the dates.

commit;