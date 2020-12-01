DROP TABLE IF EXISTS post;

CREATE TABLE IF NOT EXISTS post (
    id SERIAL PRIMARY KEY,
    name TEXT
);

DROP TABLE IF EXISTS candidate;

CREATE TABLE IF NOT EXISTS candidate (
                                    id SERIAL PRIMARY KEY,
                                    name TEXT
);