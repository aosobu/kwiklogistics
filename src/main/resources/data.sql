--data for medication table
insert into medication_tb (created_date, last_modified_date, code, image_path, name, weight)
values  ('2022-11-08 04:28:37.328478', '2022-11-08 04:28:37.328478',  'aug-90', 'images/Twitter-logo-png-1.png', 'augmentin-200g', 100);
insert into medication_tb (created_date, last_modified_date, code, image_path, name, weight)
values  ('2022-11-08 05:28:37.328478', '2022-11-08 05:28:37.328478',  'aug-90', 'images/Twitter-logo-png-1.png', 'augmentin-200g', 200);
insert into medication_tb (created_date, last_modified_date, code, image_path, name, weight)
values  ('2022-11-08 06:28:37.328478', '2022-11-08 06:28:37.328478',  'aug-90', 'images/Twitter-logo-png-1.png', 'augmentin-200g', 300);
insert into medication_tb (created_date, last_modified_date, code, image_path, name, weight)
values  ('2022-11-08 07:28:37.328478', '2022-11-08 07:28:37.328478',  'aug-90', 'images/Twitter-logo-png-1.png', 'augmentin-200g', 400);

--data for medication order
insert into medication_order_tb (medication_id, state, created_date, last_modified_date, drone_id, version)
values (1, 'IDLE', '2022-11-08 04:28:37.328478' , '2022-11-08 04:28:37.328478', 0, 0);
insert into medication_order_tb (medication_id, state, created_date, last_modified_date, drone_id, version)
values (2, 'IDLE', '2022-11-08 04:28:37.328478' , '2022-11-08 04:28:37.328478', 0, 0);
insert into medication_order_tb (medication_id, state, created_date, last_modified_date, drone_id, version)
values (3, 'IDLE', '2022-11-08 04:28:37.328478' , '2022-11-08 04:28:37.328478', 0, 0);
insert into medication_order_tb (medication_id, state, created_date, last_modified_date, drone_id, version)
values (4, 'IDLE', '2022-11-08 04:28:37.328478' , '2022-11-08 04:28:37.328478', 0, 0);


--data for drones
insert into drone_tb (created_date, last_modified_date, battery_capacity, model, serial_number, state, weight)
values ('2022-11-08 18:00:16.69169', '2022-11-08 18:00:16.69169', 26, 'CRUISERWEIGHT', 'XVH5547674734UTPO', 'IDLE', 500);
insert into drone_tb (created_date, last_modified_date, battery_capacity, model, serial_number, state, weight)
values ('2022-11-08 18:00:16.69169', '2022-11-08 18:00:16.69169', 45, 'MIDDLEWEIGHT', 'XVH5547674734UTPO', 'IDLE', 300);
insert into drone_tb (created_date, last_modified_date, battery_capacity, model, serial_number, state, weight)
values ('2022-11-08 18:00:16.69169', '2022-11-08 18:00:16.69169', 23, 'LIGHTWEIGHT', 'XVH5547674734UTPO', 'IDLE', 400);
insert into drone_tb (created_date, last_modified_date, battery_capacity, model, serial_number, state, weight)
values ('2022-11-08 18:00:16.69169', '2022-11-08 18:00:16.69169', 21, 'HEAVYWEIGHT', 'XVH5547674734UTPO', 'IDLE', 300);