CREATE TABLE TRIPS
(
   id BIGINT NOT NULL AUTO_INCREMENT,
   name VARCHAR(255) NOT NULL,
   trip_from VARCHAR(255) NOT NULL,
   trip_to VARCHAR(255) NOT NULL,
   distance DOUBLE PRECISION NOT NULL,
   owner VARCHAR(255) NOT NULL,
   PRIMARY KEY (id)
);