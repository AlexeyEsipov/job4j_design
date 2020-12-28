create table rules (
                       id serial primary key,
                       rules text
);

create table role (
                      id serial primary key,
                      name_role varchar(2000),
                      rule int references rules (id)
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
