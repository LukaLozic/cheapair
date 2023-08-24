create table if not exists AIRPORTS (
	
  ID_AIRPORT SERIAL PRIMARY KEY,
  CODE varchar(50),
  NAME varchar(200),
  CITY_CODE varchar(50),
  CITY_NAME varchar(200),
  COUNTRY_NAME varchar(200),
  COUNTRY_CODE varchar(200),
  TIME_ZONE varchar(8),
  LAT varchar(32),
  LON varchar(32),
  NUM_AIRPORTS INTEGER,
  CITY varchar(5)
);


create table if not exists FLIGHTS (

	ID_FLIGHT SERIAL PRIMARY KEY,
	DEPARTURE_AIRPORT_ID INTEGER,
	ARRIVAL_AIRPORT_ID INTEGER,
	DEPARTURE_DATE DATE NOT NULL,
	RETURN_DATE DATE,
	PASSENGER_NUMBER INTEGER,
	CURRENCY VARCHAR(3),
	NUMBER_OF_STOPS INTEGER,
	PRICE DECIMAL(15,2)

);

ALTER TABLE flights ADD CONSTRAINT fk_flights_departure_airports FOREIGN KEY (departure_airport_id) REFERENCES airports (id_airport);
ALTER TABLE flights ADD CONSTRAINT fk_flights_arrival_airports FOREIGN KEY (arrival_airport_id) REFERENCES airports (id_airport);






INSERT INTO AIRPORTS (CODE, NAME, CITY_CODE, CITY_NAME, COUNTRY_NAME, COUNTRY_CODE, TIME_ZONE, LAT, LON, NUM_AIRPORTS, CITY) VALUES
	('ANW', 'Ainsworth Minicipal Arpt', 'ANW', 'Ainsworth', 'UNITED STATES', 'US', '-100', '42.58', '-99.9933', 1, 'true'),
	('ANU', 'V C Bird Intl Arpt', 'ANU', 'Antigua', 'ANTIGUA AND BARBUDA', 'AG', '-4', '17.136749', '-61.792667', 1, 'true'),
	('ANR', 'Antwerp Brussels North', 'ANR', 'Antwerp', 'BELGIUM', 'BE', '1', '51.189444', '4.460278', 1, 'true'),
	('ANP', 'Lee Annapolis Arpt', 'ANP', 'Annapolis', 'UNITED STATES', 'US', '-100', '0', '0', 1, 'true'),
	('ANI', 'Aniak Arpt', 'ANI', 'Aniak', 'UNITED STATES', 'US', '-9', '61.5816', '-159.543', 1, 'true'),
	('ESB', 'Esenboga Arpt', 'ANK', 'Ankara', 'TURKEY', 'TR', '2', '40.128082', '32.995083', 1, ''),
	('ANG', 'Brie Champniers', 'ANG', 'Angouleme', 'FRANCE', 'FR', '1', '45.729247', '0.221456', 1, 'true'),
	('ANF', 'Cerro Moreno Arpt', 'ANF', 'Antofagasta', 'CHILE', 'CL', '-4', '-23.444478', '-70.4451', 1, 'true'),
	('ANE', 'Marce Arpt', 'ANE', 'Angers', 'FRANCE', 'FR', '1', '47.5603', '-0.312222', 1, 'true'),
	('AND', 'Anderson Arpt', 'AND', 'Anderson', 'UNITED STATES', 'US', '-5', '34.494583', '-82.709389', 1, 'true'),
	('ANC', 'Anchorage Intl Arpt', 'ANC', 'Anchorage', 'UNITED STATES', 'US', '-9', '61.174361', '-149.996361', 1, 'true'),
	('ANB', 'Anniston Municipal Arpt', 'ANB', 'Anniston', 'UNITED STATES', 'US', '-6', '33.588167', '-85.858111', 1, 'true'),
	('ANA', 'Anaheim Arpt', 'ANA', 'Anaheim', 'UNITED STATES', 'US', '-100', '0', '0', 1, 'true'),
	('AMW', 'Ames Minicipal Arpt', 'AMW', 'Ames', 'UNITED STATES', 'US', '-100', '0', '0', 1, 'true'),
	('AMV', 'Amderma Airport', 'AMV', 'Amderma Airport', 'RUSSIA', 'RU', '4', '69.7633', '61.5564', 1, 'true'),
	('AMS', 'Schiphol Arpt', 'AMS', 'Amsterdam', 'NETHERLANDS', 'NL', '1', '52.308613', '4.763889', 2, 'true'),
	('ZYA', 'Amsterdam Central Rail Station', 'AMS', 'Amsterdam', 'NETHERLANDS', 'NL', '1', '52.7873', '4.90074', 2, ''),
	('AMQ', 'Pattimura Arpt', 'AMQ', 'Ambon', 'INDONESIA', 'ID', '9', '-3.710264', '128.089136', 1, 'true'),
	('AMM', 'Queen Alia Intl Arpt', 'AMM', 'Amman', 'JORDAN', 'JO', '2', '31.722556', '35.993214', 2, 'true'),
	('ADJ', 'Civil Marka Arpt', 'AMM', 'Amman', 'JORDAN', 'JO', '2', '31.972703', '35.991569', 2, ''),
	('AMI', 'Selaparang Airport', 'AMI', 'Mataram', 'INDONESIA', 'ID', '8', '-8.560708', '116.094656', 1, 'true');

insert into flights (departure_airport_id, arrival_airport_id, departure_date, return_date, passenger_number, currency, number_of_stops, price)
values (1, 2, '2022-12-14', '2022-12-18', 1, 'EUR', 1, 599.99);
	
