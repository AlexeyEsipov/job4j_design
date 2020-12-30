
CREATE TABLE body (
                      id int primary key,
                      num varchar(255) unique
);

CREATE TABLE engine (
                        id int primary key,
                        num varchar(255) unique
);

CREATE TABLE gearbox (
                         id int primary key,
                         num varchar(255)
);

CREATE TABLE auto (
                      id int primary key,
                      model varchar(255),
                      body_num int references body(id),
                      engine_num int references engine(id),
                      gearbox_num int references gearbox(id)
);

INSERT INTO body (id, num) VALUES (1, 'body1'), (2, 'body2'), (3, 'body3');

INSERT INTO engine (id, num) VALUES (1, 'en1'), (2, 'en2'), (3, 'en3');

INSERT INTO gearbox (id, num) VALUES (1, 'gear1'), (2, 'gear2'), (3, 'gear3');

insert into auto (id, model, body_num, engine_num, gearbox_num) VALUES (1, 'model1', 1, 1, 1),
                                                                       (2, 'model2', 2, 2, 2);

--1. Вывести список всех машин и все привязанные к ним детали.
SELECT a.model, b.num, e.num, g.num
from auto as a
         left outer join body b
                         on a.body_num = b.id
         left outer join engine e
                         on e.id = a.engine_num
         left outer join gearbox g
                         on g.id = a.gearbox_num;

--2. Вывести отдельно детали, которые не используются в машине, кузова, двигатели, коробки передач.
SELECT b.num Body, e.num Engine, g.num Gearbox
from auto as a
         full join body b
                   on a.body_num = b.id
         full join engine e
                   on e.id = a.engine_num
         full join gearbox g
                   on g.id = a.gearbox_num
where a.model is null ;