CREATE TABLE Messages(
id INT(11) PRIMARY KEY,
message VARCHAR(100) NOT NULL,
version INT(11) NOT NULL DEFAULT '0',
timestamp DATE
);