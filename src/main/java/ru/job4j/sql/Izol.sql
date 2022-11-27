create table products (
    id serial primary key,
    name varchar,
    producer varchar,
    count integer default 0,
    price integer
);

insert into products (name, producer, count, price)
              values ('water', 'Water farm', 13, 5),
                     ('coffe', 'Coffee farm', 28, 49);

begin transaction isolation level serializable;
select * from products;
select sum(count) from products;
update products set count = 25 where name = 'coffe';
commit;