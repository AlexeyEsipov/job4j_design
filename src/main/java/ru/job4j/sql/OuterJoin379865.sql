
CREATE TABLE departments (
                             dep int primary key,
                             dep_name varchar(255)
);
CREATE TABLE employees (
                           name varchar(255) primary key,
                           dep int references departments(dep)
);
INSERT INTO departments (dep, dep_name) VALUES (1, 'Dep1'),(2, 'Dep2'),(3, 'Dep3'), (4, 'Dep4');
INSERT INTO employees (name, dep) VALUES ('AA', 1), ('AB', 1), ('CC', 2), ('DD', 3), ('FF', null);

--2. Выполнить запросы с left, right, full, cross соединениями
-- left outer join
SELECT e.name, d.dep_name
    FROM employees e
    LEFT JOIN departments d
    ON e.dep = d.dep;
SELECT * FROM employees e
    LEFT JOIN departments d
    ON e.dep = d.dep;
-- right outer join
SELECT e.name, d.dep_name
    FROM employees e
    RIGHT JOIN departments d
    ON d.dep = e.dep;
SELECT * FROM employees e
    RIGHT JOIN departments d
    ON d.dep = e.dep;
--full join
SELECT e.name, d.dep_name
    FROM employees e
    FULL OUTER JOIN departments d
    ON d.dep = e.dep;
SELECT * FROM employees e
    FULL OUTER JOIN departments d
    ON d.dep = e.dep;
--cross join
SELECT e.name, d.dep_name
    FROM employees e
    CROSS JOIN departments d;
SELECT * FROM employees e
    FULL OUTER JOIN departments d
    ON d.dep = e.dep;

--3. Используя left join найти работников, которые не относятся ни к одну из департаментов
SELECT e.name, d.dep_name
    FROM employees e
    LEFT JOIN departments d
    ON e.dep = d.dep
    WHERE d.dep is null;

--4. Используя left и right join написать запросы, которые давали бы одинаковый результат.
SELECT e.name, d.dep_name
    FROM employees e
    LEFT JOIN departments d
    ON e.dep = d.dep;
SELECT e.name, d.dep_name
    FROM departments d
    RIGHT JOIN employees e
    ON d.dep = e.dep;

--5. Создать таблицу teens с атрибутами name, gender и заполнить ее.
CREATE TABLE teens(
    name   varchar(255) primary key,
    gender boolean
);
INSERT INTO teens (name, gender) VALUES ('AA', true),
    ('AB', true), ('BC', false), ('BD', false), ('BF', false);

-- Используя cross join составить все возможные разнополые пары
SELECT * FROM teens AS t1
    CROSS JOIN teens AS t2
    WHERE t1.gender < t2.gender;
-- аналогичный результат
SELECT * FROM teens AS t1
    CROSS JOIN teens AS t2
    WHERE t1.gender > t2.gender;