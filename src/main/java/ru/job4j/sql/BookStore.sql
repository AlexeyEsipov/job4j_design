create table buyers (
    id serial primary key,
    name varchar
);

create table authors (
    id serial primary key,
    name varchar
);

create table books (
    id serial primary key,
    name varchar,
    price float,
    amount int,
    author_id integer references authors(id)
);

create table orders (
    id serial primary key,
    book_id integer references books(id),
    buyer_id integer references buyers(id)
);

insert into buyers (name)
            values ('Иван Иванов'),
                   ('Петр Петров'),
                   ('Федор Федоров'),
                   ('Ольга Михайлова'),
                   ('Ирина Белова');

insert into authors (name)
             values ('Александр Пушкин'),
                    ('Николай Гоголь'),
                    ('Сергей Есенин'),
                    ('Лев Толстой'),
                    ('Михаил Булгаков');

insert into books (name, price, amount, author_id)
           values ('Евгений Онегин', 12.5, 7, 1),
                  ('Капитанская дочка', 45.6, 2, 1),
                  ('Дубровский', 33.0, 4, 1),
                  ('Мертвые души', 21.9, 1, 2),
                  ('Мастер и Маргарита', 65.8, 3, 5),
                  ('Сборник стихотворений', 76.8, 7, 3),
                  ('Собачье сердце', 75.9, 2, 5),
                  ('Морфий', 35.3, 2, 5),
                  ('Война и мир', 78.9, 10, 4);

insert into orders (book_id, buyer_id)
            values (1, 1),
                   (3, 1),
                   (5, 2),
                   (4, 1),
                   (1, 5),
                   (6, 4),
                   (9, 4),
                   (8, 2),
                   (2, 2),
                   (7, 2),
                   (3, 3),
                   (1, 4),
                   (6, 4),
                   (8, 5);

create view show_books_amount_more_3_sorted_by_price_desc
as select a.name author, b.name book, br.name buyer, price
   from books b
            left join authors a on b.author_id = a.id
            left join orders o on o.book_id = b.id
            left join buyers br on o.buyer_id = br.id
   where amount > 3
   order by price desc;

select * from show_books_amount_more_3_sorted_by_price_desc;
