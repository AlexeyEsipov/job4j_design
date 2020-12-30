CREATE TABLE type (
                      id serial primary key,
                      name_type varchar(255)
);
CREATE TABLE product (
                         id serial primary key,
                         name varchar(255),
                         type_id int references type(id),
                         expired_date date,
                         price float
);

INSERT INTO type (name_type) VALUES ('Сыр'), ('Молоко'),
                                    ('Мясо'), ('Колбаса'), ('Хлеб');

INSERT INTO product (name, type_id, expired_date, price) VALUES ('Сыр Российский', 1, date '2021-01-20', 150.00),
    ('Сыр Московский', 1, date '2020-12-01', 150.00), ('Сыр Казанский', 1, date '2020-12-01', 150.00),('Сыр Деревенский', 1, date '2020-12-01', 150.00),
    ('Филе мороженное', 3, date '2020-12-01', 150.00),('Куриная грудка', 3, date '2020-12-01', 350.00),('Голень индейки', 3, date '2020-12-01', 150.00),
    ('Суповой набор', 3, date '2020-12-01', 150.00),('Шашлык маринованный', 3, date '2020-12-01', 150.00),('Свинина вырезка', 3,date '2020-12-01', 150.00),
    ('Говядина мороженная', 3, date '2020-12-01', 150.00),('Балык', 3, date '2020-12-01', 100.00),('Баранина на кости', 3, date '2020-12-01', 150.00),
    ('Сервелат', 4, date '2020-12-01', 150.00),('Докторская', 4, date '2020-12-01', 150.00),('Сырокопченая', 4, date '2020-12-01', 150.00),
    ('Молоко деревенское', 2, date '2020-12-01', 150.00),('Домик в деревне', 2, date '2020-12-01', 150.00),
    ('Подовый хлеб', 5, date '2020-12-01', 150.00),('Сладкий батон', 5, date '2020-12-01', 150.00),('Пшеничный хлеб', 5, date '2020-12-01', 150.00);

-- 1. Написать запрос получение всех продуктов с типом "СЫР"
SELECT * FROM product WHERE type_id = 1;

-- 2. Написать запрос получения всех продуктов, у кого в имени есть слово "мороженное"
SELECT * FROM product WHERE name LIKE '%мороженное%';

-- 3. Написать запрос, который выводит все продукты, срок годности которых заканчивается в следующем месяце.
SELECT * FROM product WHERE expired_date between CURRENT_DATE and CURRENT_DATE+30;

-- 4. Написать запрос, который выводит самый дорогой продукт.
SELECT name, price
FROM product
WHERE price = (
    SELECT max(price) from product
);

--5. Написать запрос, который выводит количество всех продуктов определенного типа.
SELECT t.name_type Тип, ct.count Остаток
FROM (
         SELECT count(*), type_id
         FROM product
         GROUP BY type_id
     ) AS ct
         JOIN type AS t
              ON ct.type_id = t.id;

--6. Написать запрос получение всех продуктов с типом "СЫР" и "МОЛОКО"
SELECT * FROM product WHERE type_id = 1 or type_id = 2;

--7. Написать запрос, который выводит тип продуктов, которых осталось меньше 10 штук.
SELECT t.name_type Тип, ct.count Остаток
FROM (
         SELECT count(*), type_id
         FROM product
         GROUP BY type_id
     ) AS ct
         JOIN type AS t
              ON ct.type_id = t.id
WHERE ct.count < 10;

--8. Вывести все продукты и их тип.
SELECT t.name_type Тип, p.name Продукт
FROM type t
         JOIN product p
              ON t.id = p.type_id;
