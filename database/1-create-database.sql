create database hotel;

use hotel;

create table Guests (

Id INT AUTO_INCREMENT,
Name VARCHAR(50)NOT NULL,
Lastname VARCHAR(100)NOT NULL,
Birthdate DATE,
Nationality VARCHAR(50),
(Telephone VARCHAR(15), CONSTRAINT chk_telephone CHECK (Telephone REGEXP '^[0-9]{10}$'))
PRIMARY KEY(Id)
)Engine=InnoDB;

use hotel;

create table Bookings (

Id INT AUTO_INCREMENT,
Entry_date DATE,
Departure_date DATE,
Valor,
Method_payment
)Engine=InnoDB;

