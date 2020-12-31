create table categories (
    number int primary key ,
    name varchar(255),
    price numeric(7,2)--,
);

create table parts (
    part_id serial primary key,
    part varchar(255),
    cat int references categories(number)
);

insert into categories (number, name, price) VALUES (10, 'Стройматериалы', 105.50);
insert into categories (number, name, price) VALUES (505, 'Недвижимость', 201.00);
insert into categories (number, name, price) VALUES (205, 'Транспорт', 160.00);
insert into categories (number, name, price) VALUES (30, 'Мебель', 77.70);
insert into categories (number, name, price) VALUES (45, 'Техника', 65.10);

insert into parts (part, cat) VALUES ('Квартиры', 505);
insert into parts (part, cat) VALUES ('Автомашины', 205);
insert into parts (part, cat) VALUES ('Доски', 10);
insert into parts (part, cat) VALUES ('Шкафы', 30);
insert into parts (part) VALUES ('Книги');

SELECT p.Part as Item, c.number as Cat, c.price as Price
FROM parts p INNER JOIN categories c on c.number = p.cat where c.price < 70;
SELECT p.Part as Item, c.price as Price
FROM parts p INNER JOIN categories c on c.number = p.cat where p.cat <100;
SELECT p.Part as Item, c.number as Cat
FROM parts p INNER JOIN categories c on c.number = p.cat where c.price > 100;