DROP DATABASE IF EXISTS hitstpa;

CREATE DATABASE hitstpa; 

USE hitstpa;

GRANT ALL PRIVILEGES ON . TO 'hitstpa'@'localhost' IDENTIFIED BY 'hitstpa';

CREATE TABLE Stereotype
(
	id int NOT NULL AUTO_INCREMENT, PRIMARY KEY (id),
	name varchar(50) NOT NULL,
	description varchar(200)
) ENGINE = INNODB;

CREATE TABLE Entity
(
	id int NOT NULL AUTO_INCREMENT, PRIMARY KEY (id),
	FOREIGN KEY stereotypeFK(sterotype) NOT NULL
		REFERENCES Sterotype(id)
		ON DELETE CASCADE,
	name varchar(50) NOT NULL,
	description varchar(200) NOT NULL
) ENGINE = INNODB;

CREATE TABLE EntityCouple
(
	id int NOT NULL AUTO_INCREMENT, PRIMARY KEY (id),
	FOREIGN KEY leftEntityFK(leftEntity) int NOT NULL
		REFERENCES Entity(id)
		ON DELETE CASCADE,
	FOREIGN KEY rightEntityFK(leftEntity) int NOT NULL
		REFERENCES Entity(id)
		ON DELETE CASCADE
) ENGINE = INNODB;

CREATE TABLE EntityConstraint
(
	id int NOT NULL AUTO_INCREMENT, PRIMARY KEY (id),
	FOREIGN KEY entityFK(entity) NOT NULL
		REFERENCES Entity(id)
		ON DELETE CASCADE
) ENGINE = INNODB;

CREATE TABLE Interaction
(
	id int NOT NULL AUTO_INCREMENT, PRIMARY KEY (id),
	FOREIGN KEY entityCoupleFK(entityCouple) NOT NULL
		REFERENCES EntityCouple(id)
		ON DELETE CASCADE
) ENGINE = INNODB;

CREATE TABLE Action
(
	id int NOT NULL AUTO_INCREMENT, PRIMARY KEY (id),
	FOREIGN KEY entityFK(entity) NOT NULL
		REFERENCES Entity(id)
		ON DELETE CASCADE
) ENGINE = INNODB;

CREATE TABLE Event
(
	id int NOT NULL AUTO_INCREMENT, PRIMARY KEY(id),
	FOREIGN KEY interactionFK(interaction)
		REFERENCES Interaction(id)
		ON DELETE CASCADE
) ENGINE = INNODB;

CREATE TABLE EventConstraint
(
	id int NOT NULL AUTO_INCREMENT, PRIMARY KEY (id),
	FOREIGN KEY eventFK(event) NOT NULL
		REFERENCES Event(id)
		ON DELETE CASCADE
) ENGINE = INNODB;

CREATE TABLE Sequence
(
	id int NOT NULL AUTO_INCREMENT, PRIMARY KEY (id),
	FOREIGN KEY eventConstraintFK(eventConstraint) NOT NULL
		REFERENCES EventConstraint(id)
		ON DELETE CASCADE,
	FOREIGN KEY eventFK(event) NOT NULL
		REFERENCES Event(id)
		ON DELETE CASCADE,
	sequence int NOT NULL
) ENGINE = INNODB;

CREATE TABLE Constraint
(
	id int NOT NULL AUTO_INCREMENT, PRIMARY KEY (id),
	FOREIGN KEY entityConstraintFK(entityConstraint)
		REFERENCES EnityConstraint(id)
		ON DELETE CASCADE,
	FOREIGN KEY eventConstraintFK(eventConstraint)
		REFERENCES EventConstraint(id)
		ON DELETE CASCADE,
	name varchar(50),
	description varchar(200)
) ENGINE = INNODB;
