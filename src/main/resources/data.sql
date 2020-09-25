--clients
insert into client (id, first_name, last_name, zip_code, city, address, tel, email, comments ) values (null, 'Jan','Himilsbach','01-999', 'Warszawa','Górnośląska 13','600-500-400','jasiu@h.pl', 'pierwszy klient');
insert into client (id, first_name, last_name, zip_code, city, address, tel, email, comments ) values (null, 'Zdzisław','Maklakiewicz','01-999', 'Warszawa','Krakowskie Przesmieście 2','610-510-490','maklak@wp.pl', 'drugi klient');

--transports
insert into transport (id, name, zip_code, city, address, tel, email, www, comments) values (1, 'SPT','63-600', 'Kępno', 'Polna 3', '61-77-77-400', 'spt@spt.com','spt.com','szybki profesjonalny transport' );

--suppliers
insert into supplier (id, name, zip_code, city, address, tel, email, www, comments) values (1, 'MX','63-601', 'Warszawa', 'Główna 7', '61-80-80-800', 'mx@mx.com','mx.com','długo się czeka na towar' );
insert into supplier (id, name, zip_code, city, address, tel, email, www, comments) values (2, 'DX','63-600', 'Wrocław', 'Polna 8', '61-70-70-788', 'dx@dx.com','dx.com','tanie krzesła' );
insert into supplier (id, name, zip_code, city, address, tel, email, www, comments) values (3, 'KAR','63-608', 'Grójec', 'Bliska 77', '62-72-72-722', 'kar@kar.pl','kar.pl','sypialnie, meble w połysku' );

--furniture categories
insert into product_category (id, category_name, description) values (1, 'szafy', 'przesuwne i uchylne');
insert into product_category (id, category_name, description) values (2, 'krzesła', 'tapicerowane, nietapicerowne');
insert into product_category (id, category_name, description) values (3, 'stoły', 'rozkładane, nierozkładane, owalne');

--orders methods
insert into order_method (id, order_method_name) values (1, 'sklep stacjonarny');
insert into order_method (id, order_method_name) values (2, 'allegro');
insert into order_method (id, order_method_name) values (3, 'sklep internetowy');
insert into order_method (id, order_method_name) values (4, 'tel');
insert into order_method (id, order_method_name) values (5, 'mail');

--products
insert into product(id, product_name, supplier_price, my_price, description, supplier_id, product_category_id) values (1, 'szafa fi', 480, 620, 'biała / sonoma / z lustrem', 1, 1);
insert into product(id, product_name, supplier_price, my_price, description, supplier_id, product_category_id) values (2, 'krzesło 02', 85, 110, 'tapicerowane', 2, 2);
insert into product(id, product_name, supplier_price, my_price, description, supplier_id, product_category_id) values (3, 'szafa penelo', 610, 800, 'z lustrami', 1, 1);

--orders and details
insert into client_order(id, date_of_order_to_transport, date_of_send_to_client, comments, client_id, transport_id) values (1, '2020-09-01', '2020-09-10','no comments', 1, 1);
insert into order_details(id, client_order_id, product_id, quantity, date_of_order_by_client, order_method_id) values (1, 1, 2, 6, '2020-09-01', 1);
insert into order_details(id, client_order_id, product_id, quantity, date_of_order_by_client, order_method_id) values (2, 1, 3, 1, '2020-09-01', 1);

insert into client_order(id, date_of_order_to_transport, date_of_send_to_client, comments, client_id, transport_id) values (2, '2020-09-03', '2020-09-15','wniesienie mebli', 2, 1);
insert into order_details(id, client_order_id, product_id, quantity, date_of_order_by_client, order_method_id) values (3, 2, 2, 4, '2020-09-02', 2);
insert into order_details(id, client_order_id, product_id, quantity, date_of_order_by_client, order_method_id) values (4, 2, 1, 1, '2020-09-02', 2);

insert into client_order(id, date_of_order_to_transport, date_of_send_to_client, comments, client_id, transport_id) values (3, null, null,'nówka', 2, null);
insert into order_details(id, client_order_id, product_id, quantity, date_of_order_by_client, order_method_id) values (5, 3, 2, 15, null, 1);
insert into order_details(id, client_order_id, product_id, quantity, date_of_order_by_client, order_method_id) values (6, 3, 1, 15, null, 1);




