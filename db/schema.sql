DROP TABLE IF EXISTS post;
DROP TABLE IF EXISTS candidate;
DROP TABLE IF EXISTS photo;
DROP TABLE IF EXISTS city;
DROP TABLE IF EXISTS users;

CREATE TABLE IF NOT EXISTS post (
    id SERIAL PRIMARY KEY,
    name TEXT
);

CREATE TABLE IF NOT EXISTS photo (
     id SERIAL PRIMARY KEY,
     image bytea
);

CREATE TABLE IF NOT EXISTS city (
    id SERIAL PRIMARY KEY,
    name text
);

INSERT INTO city(name) VALUES ('Moscow');
INSERT INTO city(name) VALUES ('SPB');
INSERT INTO city(name) VALUES ('Novosibirsk');

CREATE TABLE IF NOT EXISTS candidate (
    id SERIAL PRIMARY KEY,
    name TEXT,
    photo_id INTEGER REFERENCES photo(id),
    city_id INTEGER REFERENCES city(id)
);

CREATE TABLE IF NOT EXISTS users (
    id SERIAL PRIMARY KEY,
    name TEXT,
    email TEXT,
    password TEXT
);

SELECT * FROM post;

SELECT * FROM photo;

SELECT * FROM candidate;

SELECT * FROM users;

SELECT * FROM city;

