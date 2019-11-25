
insert into VehicleType values('Economy','AC and Heated Seats',5.00,4.00,7.00,6.00,5.00,8.00,1.00);

insert into VehicleType values('Compact','Heated Seats',6.60,9.10,17.50,8.80,9.15,3.21,1.00);

insert into VehicleType values('Mid-size','AC and Heated Seats',5.00,4.00,7.00,6.00,5.00,8.00,1.00);

insert into VehicleType values('Standard','AC and Heated Seats',7.60,5.04,4.68,9.30,4.50,7.00,1.00);

insert into VehicleType values('Fullsize','Heated Seats',2.00,4.00,6.00,9.00,2.00,1.00,1.50);

insert into VehicleType values('SUV','AC and Heated Seats',7.54,43.50,8.10,3.33,4.55,6.66,1.00);

insert into VehicleType values('Truck','AC',1.11,2.22,3.33,4.44,5.55,6.66,1.00);



insert into Customer values(1234567899,'Susan Martin','2222 Main St.',1111111111);

insert into Customer values(1114567899,'John Doe','4444 Lower Mall',2222222222);

insert into Customer values(1111111899,'Jane Doe','10 Downing St.',3333333333);

insert into Customer values(9999999999,'Sally Doe','87349 188 Ave.',4444444444);

insert into Customer values(8888888888,'John Martin','1354 W Broadway',5555555555);

insert into Customer values(7777777777,'Sally Doe','4555 Wallnut Ave.',6666666666);



insert into TimePeriod values(TO_DATE('2019-07-07','YYYY-MM-DD'),TIMESTAMP '2019-07-07 09:26:50',TO_DATE('2019-07-08',
'YYYY-MM-DD'),TIMESTAMP '2019-07-08 18:10:00');

insert into TimePeriod values(TO_DATE('2019-06-06','YYYY-MM-DD'),TIMESTAMP '2019-06-06 09:10:00',TO_DATE('2019-06-07',
'YYYY-MM-DD'),TIMESTAMP '2019-06-07 18:10:00');

insert into TimePeriod values(TO_DATE('2019-05-05','YYYY-MM-DD'),TIMESTAMP '2019-05-05 09:10:00',TO_DATE('2019-05-06',
'YYYY-MM-DD'),TIMESTAMP '2019-05-06 18:10:00');

insert into TimePeriod values(TO_DATE('2019-04-04','YYYY-MM-DD'),TIMESTAMP '2019-04-04 14:45:45',TO_DATE('2019-08-05',
'YYYY-MM-DD'),TIMESTAMP '2019-08-05 13:11:11');

insert into TimePeriod values(TO_DATE('2019-03-03','YYYY-MM-DD'),TIMESTAMP '2019-03-03 09:40:00',TO_DATE('2019-03-15',
'YYYY-MM-DD'),TIMESTAMP '2019-03-15 09:40:00');



insert into Reservation values(11111,'Economy',1234567899,TO_DATE('2019-07-07','YYYY-MM-DD'),TIMESTAMP '2019-07-07 09:26:50',TO_DATE('2019-07-08','YYYY-MM-DD'),TIMESTAMP '2019-07-08 18:10:00');

insert into Reservation values(22222,'Compact',7777777777,TO_DATE('2019-06-06','YYYY-MM-DD'),TIMESTAMP '2019-06-06 09:10:00',TO_DATE('2019-06-07','YYYY-MM-DD'),TIMESTAMP '2019-06-07 18:10:00');

insert into Reservation values(33333,'Mid-size',1114567899,TO_DATE('2019-05-05','YYYY-MM-DD'),TIMESTAMP '2019-05-05 09:10:00',TO_DATE('2019-05-06','YYYY-MM-DD'),TIMESTAMP '2019-05-06 18:10:00');

insert into Reservation values(44444,'Standard',1114567899,TO_DATE('2019-04-04','YYYY-MM-DD'),TIMESTAMP '2019-04-04 14:45:45',TO_DATE('2019-08-05','YYYY-MM-DD'),TIMESTAMP '2019-08-05 13:11:11');

insert into Reservation values(55555,'Fullsize',9999999999,TO_DATE('2019-03-03','YYYY-MM-DD'),TIMESTAMP '2019-03-03 09:40:00',TO_DATE('2019-03-15','YYYY-MM-DD'),TIMESTAMP '2019-03-15 09:40:00');

insert into Reservation values(66666,'SUV',1111111899,TO_DATE('2019-06-06','YYYY-MM-DD'),TIMESTAMP '2019-06-06 09:10:00',TO_DATE('2019-06-07','YYYY-MM-DD'),TIMESTAMP '2019-06-07 18:10:00');

insert into Reservation values(77777,'Truck',8888888888,TO_DATE('2019-07-07','YYYY-MM-DD'),TIMESTAMP '2019-07-07 09:26:50',TO_DATE('2019-07-08','YYYY-MM-DD'),TIMESTAMP '2019-07-08 18:10:00');
commit;