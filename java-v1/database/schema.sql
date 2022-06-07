BEGIN TRANSACTION;

DROP TABLE IF EXISTS users;
DROP SEQUENCE IF EXISTS seq_user_id;

CREATE SEQUENCE seq_user_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;


CREATE TABLE users (
	user_id int DEFAULT nextval('seq_user_id'::regclass) NOT NULL,
	username varchar(50) NOT NULL,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');

CREATE TABLE airports (
    airport_code character(3) NOT NULL,
    airport_name varchar NOT NULL,
    city varchar NOT NULL,
    CONSTRAINT PK_airports PRIMARY KEY(airport_code)
);

CREATE TABLE aircraft (
    aircraft_code character(3) NOT NULL,
    model varchar NOT NULL,
    CONSTRAINT PK_aircraft PRIMARY KEY(aircraft_code)
);

CREATE TABLE flights (
    flight_id SERIAL,
    flight_no character(6) NOT NULL,
    scheduled_departure timestamp with time zone NOT NULL,
    scheduled_arrival timestamp with time zone NOT NULL,
    departure_airport character(3) NOT NULL,
    arrival_airport character(3) NOT NULL,
    aircraft_code character(3) NOT NULL,
    price money NOT NULL,
    CONSTRAINT PK_flights PRIMARY KEY(flight_id)
);

CREATE TABLE ticket (
    ticket_id SERIAL,
    customer_name varchar NOT NULL,
    CONSTRAINT PK_tickets1 PRIMARY KEY(ticket_id)
);

CREATE TABLE ticket_flight(

     ticket_id integer,
     flight_id integer,
     CONSTRAINT PK_ticket_flight PRIMARY KEY(flight_id, ticket_id)
);


ALTER TABLE flights ADD CONSTRAINT FK_flights_dep_airports FOREIGN KEY(departure_airport) REFERENCES airports(airport_code);
ALTER TABLE flights ADD CONSTRAINT FK_flights_arr_airports FOREIGN KEY(arrival_airport) REFERENCES airports(airport_code);
ALTER TABLE flights ADD CONSTRAINT FK_flights_aircraft FOREIGN KEY(aircraft_code) REFERENCES aircraft(aircraft_code);
ALTER TABLE ticket_flight ADD CONSTRAINT FK_ticket_flight_ticket FOREIGN KEY(ticket_id) REFERENCES ticket(ticket_id);
ALTER TABLE ticket_flight ADD CONSTRAINT FK_ticket_flight_flight FOREIGN KEY(flight_id) REFERENCES flights(flight_id);


INSERT INTO airports VALUES('ORD', 'O''hare International Airport', 'Chicago');
INSERT INTO airports VALUES('DTW', 'Detroit Metropolitan Airport', 'Detroit');
INSERT INTO airports VALUES('JFK', 'John F. Kenney International Airport', 'New York');
INSERT INTO airports VALUES('MIA', 'Miami International Airport', 'Miami');

INSERT INTO aircraft VALUES('737', 'Boeing 737');
INSERT INTO aircraft VALUES('330', 'Airbus A330');
INSERT INTO aircraft VALUES('120', 'Embraer CRJ 120');

INSERT INTO flights VALUES(100, '100', '2021-02-21 09:00:00', '2021-02-21 10:00:00', 'ORD', 'DTW', '120', 112);
INSERT INTO flights VALUES(150, '150', '2021-02-21 11:00:00', '2021-02-21 12:00:00', 'DTW', 'ORD', '120', 150);
INSERT INTO flights VALUES(200, '200', '2021-02-21 13:00:00', '2021-02-21 14:30:00', 'ORD', 'JFK', '737', 200);
INSERT INTO flights VALUES(250, '250', '2021-02-21 15:00:00', '2021-02-21 16:30:00', 'JFK', 'ORD', '737', 127);
INSERT INTO flights VALUES(300, '300', '2021-02-21 17:00:00', '2021-02-21 18:30:00', 'JFK', 'MIA', '330', 130);
INSERT INTO flights VALUES(350, '350', '2021-02-21 19:00:00', '2021-02-21 22:30:00', 'MIA', 'JFK', '330', 142);

INSERT INTO ticket(customer_name) VALUES ('Alice');

INSERT INTO ticket_flight VALUES (1, 150);
INSERT INTO ticket_flight VALUES (1, 200);

COMMIT TRANSACTION;
