-- PostgreSQL SELECT DISTINCT

CREATE TABLE t1 (
 id serial NOT NULL PRIMARY KEY,
 bcolor VARCHAR,
 fcolor VARCHAR
);


INSERT INTO t1 (bcolor, fcolor)
VALUES
 ('red', 'red'),
 ('red', 'red'),
 ('red', NULL),
 (NULL, 'red'),
 ('red', 'green'),
 ('red', 'blue'),
 ('green', 'red'),
 ('green', 'blue'),
 ('green', 'green'),
 ('blue', 'red'),
 ('blue', 'green'),
 ('blue', 'blue');

 -- PostgreSQL INSERT

CREATE TABLE link (
ID serial PRIMARY KEY,
url VARCHAR (255) NOT NULL,
name VARCHAR (255) NOT NULL,
description VARCHAR (255),
rel VARCHAR (50)
)

ALTER TABLE link ADD COLUMN last_update DATE;
 
ALTER TABLE link ALTER COLUMN last_update
SET DEFAULT CURRENT_DATE;
