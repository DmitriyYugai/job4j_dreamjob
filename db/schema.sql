DROP TABLE IF EXISTS post;
DROP TABLE IF EXISTS candidate;
DROP TABLE IF EXISTS photo;


CREATE TABLE IF NOT EXISTS post (
    id SERIAL PRIMARY KEY,
    name TEXT
);

CREATE TABLE IF NOT EXISTS photo (
     id SERIAL PRIMARY KEY,
     image bytea
);

CREATE TABLE IF NOT EXISTS candidate (
    id SERIAL PRIMARY KEY,
    name TEXT,
    photo_id INTEGER REFERENCES photo(id)
);

SELECT * FROM photo;

SELECT * FROM candidate;