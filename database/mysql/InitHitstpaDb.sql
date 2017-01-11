DROP DATABASE IF EXISTS hitstpa;

CREATE DATABASE hitstpa; 
GRANT ALL ON hitstpa.* TO 'hitstpa'@'localhost' IDENTIFIED BY 'hitstpa';

USE hitstpa;

CREATE TABLE Stereotype
(
	id int NOT NULL AUTO_INCREMENT, PRIMARY KEY (id),
	name varchar(50) NOT NULL,
	description varchar(200),
	fake int
)ENGINE=INNODB;

CREATE TABLE Entity
(
	id int NOT NULL AUTO_INCREMENT, PRIMARY KEY (id),
	stereotype int NOT NULL,
	FOREIGN KEY stereotypeFK(stereotype) 
		REFERENCES Stereotype(id)
		ON DELETE CASCADE,
	name varchar(50) NOT NULL,
	description varchar(200) NOT NULL
) ENGINE=INNODB;

CREATE TABLE EntityCouple
(
	id int NOT NULL AUTO_INCREMENT, PRIMARY KEY (id),
	leftEntity int NOT NULL,
	FOREIGN KEY leftEntityFK(leftEntity)
		REFERENCES Entity(id)
		ON DELETE CASCADE,
	rightEntity int NOT NULL,
	FOREIGN KEY rightEntityFK(leftEntity)
		REFERENCES Entity(id)
		ON DELETE CASCADE
) ENGINE=INNODB;

CREATE TABLE EntitySafetyConstraint
(
	id int NOT NULL AUTO_INCREMENT, PRIMARY KEY (id),
	entity int NOT NULL,
	FOREIGN KEY entityFK(entity)
		REFERENCES Entity(id)
		ON DELETE CASCADE
) ENGINE=INNODB;

CREATE TABLE Interaction
(
	id int NOT NULL AUTO_INCREMENT, PRIMARY KEY (id),
	entityCouple int NOT NULL,
	FOREIGN KEY entityCoupleFK(entityCouple)
		REFERENCES EntityCouple(id)
		ON DELETE CASCADE
) ENGINE=INNODB;

CREATE TABLE IndividualAction
(
	id int NOT NULL AUTO_INCREMENT, PRIMARY KEY (id),
	entity int NOT NULL,
	FOREIGN KEY entityFK(entity)
		REFERENCES Entity(id)
		ON DELETE CASCADE
) ENGINE=INNODB;

CREATE TABLE Event
(
	id int NOT NULL AUTO_INCREMENT, PRIMARY KEY(id),
	individualAction int,
	FOREIGN KEY IndividualActionFK(individualAction)
		REFERENCES IndividualAction(id)
		ON DELETE CASCADE,
	interaction int,
	FOREIGN KEY interactionFK(interaction)
		REFERENCES Interaction(id)
		ON DELETE CASCADE
) ENGINE=INNODB;

CREATE TABLE EventSafetyConstraint
(
	id int NOT NULL AUTO_INCREMENT, PRIMARY KEY (id),
	event int NOT NULL,
	FOREIGN KEY eventFK(event)
		REFERENCES Event(id)
		ON DELETE CASCADE
) ENGINE=INNODB;

CREATE TABLE Sequence
(
	id int NOT NULL AUTO_INCREMENT, PRIMARY KEY (id),
	eventConstraint int NOT NULL,
	FOREIGN KEY eventSafetyConstraintFK(eventConstraint)
		REFERENCES EventSafetyConstraint(id)
		ON DELETE CASCADE,
	event int NOT NULL,
	FOREIGN KEY eventFK(event)
		REFERENCES Event(id)
		ON DELETE CASCADE,
	sequence int NOT NULL
) ENGINE=INNODB;

CREATE TABLE SafetyConstraint
(
	id int NOT NULL AUTO_INCREMENT, PRIMARY KEY (id),
	entitySafetyConstraint int,
	FOREIGN KEY entitySafetyConstraintFK(entitySafetyConstraint)
		REFERENCES EntitySafetyConstraint(id)
		ON DELETE CASCADE,
	eventSafetyConstraint int,
	FOREIGN KEY eventSafetyConstraintFK(eventSafetyConstraint)
		REFERENCES EventSafetyConstraint(id)
		ON DELETE CASCADE,
	name varchar(50) NOT NULL,
	description varchar(200) NOT NULL
) ENGINE=INNODB;


CREATE TABLE Incident
(
	id int NOT NULL AUTO_INCREMENT, PRIMARY KEY(id),
	name varchar(50) NOT NULL,
	description varchar(200) NOT NULL,
	outcome varchar(50) NOT NULL,
	startTime datetime,
	endTime datetime,
	vendorProductModel varchar(100)
) ENGINE=INNODB;


CREATE TABLE IncidentXSafetyConstraint
(
	id int NOT NULL AUTO_INCREMENT, PRIMARY KEY(id),
	safetyConstraint int NOT NULL,
	FOREIGN KEY safetyConstraintFK(safetyConstraint)
		REFERENCES SafetyConstraint(id)
		ON DELETE CASCADE,
	incident int NOT NULL,
	FOREIGN KEY incidentFK(incident)
		REFERENCES Incident(id)
		ON DELETE CASCADE
) ENGINE=INNODB;


CREATE TABLE Narrative
(
	id int NOT NULL AUTO_INCREMENT, PRIMARY KEY(id),
	incident int NOT NULL,
	FOREIGN KEY incidentFK(incident)
		REFERENCES Incident(id)
		ON DELETE CASCADE,
	narrative text NOT NULL,
	reporter varchar(500) NOT NULL
) ENGINE=INNODB;
