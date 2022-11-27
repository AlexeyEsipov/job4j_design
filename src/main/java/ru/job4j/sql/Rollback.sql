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
begin transaction;
savepoint first_savepoint;
insert into products (name, producer, count, price)
              values ('milk', 'Pure milk', 33, 12),
                     ('oil', 'Olive oil', 41, 48);
select * from products;
rollback to first_savepoint;
select * from products;
commit;