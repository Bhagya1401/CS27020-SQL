DROP TABLE IF EXISTS reg;
DROP TABLE IF EXISTS student;
DROP TABLE IF EXISTS module;

CREATE TABLE student (
	id character(10) NOT NULL PRIMARY KEY,
	name varchar(40),
	year int
);

CREATE TABLE module (
	code character(7) NOT NULL PRIMARY KEY,
	title varchar(50)
);

CREATE TABLE reg (
	student character(10) REFERENCES student(id),
	module character(7) REFERENCES module(code),
	PRIMARY KEY (student,module)
);
