CREATE TABLE users (
                      id SERIAL PRIMARY KEY,
                      name TEXT NOT NULL ,
                      email TEXT NOT NULL ,
                      password TEXT NOT NULL

);

CREATE TABLE car (
                       id SERIAL PRIMARY KEY,
                       description TEXT NOT NULL
);

CREATE TABLE markAuto (
                      id SERIAL PRIMARY KEY,
                      name TEXT NOT NULL
);

CREATE TABLE carBody (
                          id SERIAL PRIMARY KEY,
                          name TEXT NOT NULL
);

CREATE TABLE photo (
                         id SERIAL PRIMARY KEY,
                         name TEXT NOT NULL
);

-- DELETE from users where id > 3;
-- DELETE from item where id > 1;
-- DELETE from item_category where item_category.item_id> 0;




CREATE TABLE mark (
                      id SERIAL PRIMARY KEY,
                      name TEXT NOT NULL
);

CREATE TABLE model (
                       id SERIAL PRIMARY KEY,
                       name TEXT NOT NULL

);

CREATE TABLE authors (
                       id SERIAL PRIMARY KEY,
                       name TEXT NOT NULL

);


CREATE TABLE books(
                      id SERIAL PRIMARY KEY,
                      name TEXT NOT NULL
);

CREATE TABLE post(
                      id SERIAL PRIMARY KEY,
                      description TEXT NOT NULL ,
                      created TIMESTAMP NOT NUll
);

-- INSERT INTO category (name) values ('Долгосрочные');
-- INSERT INTO category (name) values ('Краткосрочные');
-- INSERT INTO category (name) values ('Высокая срочность');
-- INSERT INTO category (name) values ('Не срочные');
ALTER table users add COLUMN  phone TEXT;
ALTER table post add COLUMN  cost TEXT;
ALTER table post add COLUMN  run TEXT;
update users  Set phone = '+79818082715' where id > 0;

DELETE from post where id > 1;
DELETE from car where id > 1;
DELETE from photo where id > 1;
DELETE from carBody where id > 1;
DELETE from markAuto where id > 1;

INSERT INTO markAuto (name) values ('BMW');
INSERT INTO markAuto (name) values ('Toyota');
INSERT INTO markAuto (name) values ('Lada');
INSERT INTO markAuto (name) values ('Ford');
INSERT INTO markAuto (name) values ('Mercedes');

INSERT INTO carBody (name) values ('Седан');
INSERT INTO carBody (name) values ('Универсал');
INSERT INTO carBody (name) values ('Хетчбек');
INSERT INTO carBody (name) values ('Купэ');