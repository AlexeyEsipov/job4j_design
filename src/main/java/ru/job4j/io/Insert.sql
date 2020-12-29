create table rules (
    id serial primary key,
    rules text
);

create table role (
    id serial primary key,
    name_role varchar(2000)
);

create table cross_role_rules (
    id serial primary key,
    role_id int references role(id),
    rules_id int references rules(id)
);

create table username (
    id serial primary key,
    name varchar(2000),
    role int references role (id)
);

create table category (
    id serial primary key,
    category varchar(2000)
);

create table state (
    id serial primary key,
    state varchar(2000)
);

create table item (
    id serial primary key,
    text_item text,
    user_id int references username (id),
    category_id int references category (id),
    state_id int references state (id)
);

create table comments (
    id serial primary key,
    comment text,
    item_id int references item (id)
);

create table attachments (
    id serial primary key,
    file varchar(2000),
    item_id int references item (id)
);

insert into rules (rules) VALUES ('text1');
insert into rules (rules) VALUES ('text2');

insert into role (name_role) VALUES ('role1');
insert into role (name_role) VALUES ('role2');
insert into role (name_role) VALUES ('role3');

insert into cross_role_rules (role_id, rules_id) VALUES (1,1);
insert into cross_role_rules (role_id, rules_id) VALUES (2,1);
insert into cross_role_rules (role_id, rules_id) VALUES (2,2);
insert into cross_role_rules (role_id, rules_id) VALUES (2,3);
insert into cross_role_rules (role_id, rules_id) VALUES (1,2);

insert into username (name, role) VALUES ('AB', 1);
insert into username (name, role) VALUES ('BC', 2);
insert into username (name, role) VALUES ('CD', 3);
insert into username (name, role) VALUES ('DE', 2);

insert into category (category) VALUES ('cat1');
insert into category (category) VALUES ('cat2');
insert into category (category) VALUES ('cat3');

insert into state (state) VALUES ('state1');
insert into state (state) VALUES ('state2');
insert into state (state) VALUES ('state3');

insert into item (text_item, user_id, category_id, state_id) VALUES ('a', 1, 1, 1);
insert into item (text_item, user_id, category_id, state_id) VALUES ('b', 1, 3, 2);
insert into item (text_item, user_id, category_id, state_id) VALUES ('c', 4, 2, 2);

insert into comments (comment, item_id) VALUES ('commentA', 1);
insert into comments (comment, item_id) VALUES ('commentB', 2);
insert into comments (comment, item_id) VALUES ('commentC', 3);
insert into comments (comment, item_id) VALUES ('commentD', 2);

insert into attachments (file, item_id) VALUES ('file1', 1);
insert into attachments (file, item_id) VALUES ('file2', 3);
insert into attachments (file, item_id) VALUES ('file3', 2);
insert into attachments (file, item_id) VALUES ('file4', 1);
insert into attachments (file, item_id) VALUES ('file1', 3);


