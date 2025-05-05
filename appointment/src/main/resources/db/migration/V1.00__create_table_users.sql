CREATE SEQUENCE IF NOT EXISTS user_seq START WITH 1 INCREMENT BY 1;

CREATE TABLE IF NOT EXISTS users
(
    id           INTEGER NOT NULL,
    name         VARCHAR(255) NOT NULL,
    email        VARCHAR(255) NOT NULL,
    access_level VARCHAR(50)  NOT NULL,
    updated_at TIMESTAMP,
    CONSTRAINT pk_user_id PRIMARY KEY (id)
);
