CREATE SEQUENCE IF NOT EXISTS appointment_seq START WITH 1 INCREMENT BY 1;

CREATE TABLE IF NOT EXISTS appointments
(
    id                 INTEGER NOT NULL,
    user_id            INTEGER NOT NULL,
    status VARCHAR,
    appointment_date   TIMESTAMP,
    updated_at       TIMESTAMP,
    CONSTRAINT pk_appointment_id PRIMARY KEY (id)
);

ALTER TABLE appointments ADD CONSTRAINT fk_appointment_user_id FOREIGN KEY (user_id) REFERENCES users (id);