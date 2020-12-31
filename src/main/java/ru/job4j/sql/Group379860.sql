CREATE TABLE devices(
                        id serial primary key,
                        name varchar(255),
                        price float
);

CREATE TABLE people(
                       id serial primary key,
                       name varchar(255)
);

CREATE TABLE devices_people(
                               id serial primary key,
                               device_id int references devices(id),
                               people_id int references people(id)
);

INSERT INTO devices (name, price) VALUES ('d1', 1000), ('d2', 11000),
                                         ('d3', 6120), ('d4', 4130), ('d5', 5140), ('d6', 1500);

INSERT INTO people (name) VALUES ('Иван'), ('Алексей'), ('Саша');

INSERT INTO devices_people (device_id, people_id) VALUES (1,1), (2,1), (3,1),
                                                         (4,2), (5,2), (6,2), (1,3), (2,3);

-- средняя цена устройств
SELECT avg(d.price)
FROM devices AS d
         JOIN devices_people AS dp
              ON d.id = dp.device_id;

-- средняя цена устройств для каждого человека
SELECT p.name, w.avg
FROM (
         SELECT dp.people_id, avg(d.price)
         FROM devices AS d
                  JOIN devices_people AS dp
                       ON d.id = dp.device_id
         GROUP BY dp.people_id
         HAVING avg(d.price) > 5000
     ) AS w
         JOIN people AS p
              ON w.people_id = p.id;