create table products (
    id serial primary key,
    name varchar,
    producer varchar,
    count integer default 0,
    price integer
);

insert into products (name, producer, count, price)
              values ('milk', 'Milk farm', 10, 15),
                     ('bread', 'Bread farm', 2, 9),
                     ('meat', 'Meat farm', 5, 40),
                     ('water', 'Water farm', 13, 5),
                     ('coffe', 'Coffee farm', 28, 49);

create or replace procedure p_delete_from_products (p_count int)
    language 'plpgsql'
as
$$
BEGIN
    delete from products row where count < p_count;
END;
$$;

call p_delete_from_products (8);

create or replace function f_delete_from_products (id_from int, id_to int)
    returns void
    language 'plpgsql'
as
$$
BEGIN
    delete from products row where id between id_from and id_to;
END;
$$;

select f_delete_from_products(1, 3);