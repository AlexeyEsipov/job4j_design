CREATE TABLE IF NOT EXISTS films (
    id serial primary key,
    film_name varchar
);

CREATE TABLE IF NOT EXISTS gender (
    id serial primary key,
    sex varchar
);

CREATE TABLE IF NOT EXISTS customers (
    id serial primary key,
    last_name varchar,
    first_name varchar,
    address varchar,
    gender_id int references gender(id),
    UNIQUE (last_name, first_name, address)
);

CREATE TABLE IF NOT EXISTS films_customer (
    id serial primary key,
    film_id int references films(id),
    customer_id int references customers(id)
);

