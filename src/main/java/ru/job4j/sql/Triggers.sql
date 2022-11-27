create table products (
    id serial primary key,
    name varchar,
    producer varchar,
    count integer default 0,
    price integer
);

create or replace function tax_statement()
    returns trigger as
$$
BEGIN
    update products
    set price = price + price * 0.2
    where id in (select id from inserted);
    return new;
END;
$$
    LANGUAGE 'plpgsql';

create trigger tax_trigger_after
    after insert on products
    referencing new table as inserted
    for each statement
execute procedure tax_statement();

insert into products (name, producer, price)
              values ('milk', 'Milk farm', 15),
                     ('bread', 'Bread farm', 9),
                     ('meat', 'Meat farm', 40);

select * from products;

create or replace function tax_row()
    returns trigger as
$$
BEGIN
    NEW.price = NEW.price + NEW.price * 0.2;
    return NEW;
END;
$$
    LANGUAGE 'plpgsql';

create trigger tax_trigger_before
    before insert on products
    for each row
execute procedure tax_row();

insert into products (name, producer, price)
              values ('water', 'Water farm', 5);

select * from products;

create table history_of_price (
    id serial primary key,
    name varchar(50),
    price integer,
    date timestamp
);

create or replace function insert_product_date()
    returns trigger as
$$
BEGIN
    insert into history_of_price(name, price, date)
    values (NEW.name, NEW.price, current_date);
    return NEW;
END;
$$
    LANGUAGE 'plpgsql';

create trigger insert_products
    after insert on products
    for each row
execute procedure insert_product_date();

select * from products;

insert into products (name, producer, price)
              values ('coffe', 'Coffee farm', 49);

select * from products;

select * from history_of_price;