create database task379857;
create table aircrafts(
    id serial primary key,
    cod char(3) NOT NULL ,
    model text NOT NULL ,
    range integer NOT NULL,
    CHECK ( range > 0 )
);
insert into aircrafts (cod, model, range) VALUES
             ('SU9', 'Sukhoi SuperJet-100', 3000);
insert into aircrafts (cod, model, range) VALUES
('CN1', 'Cessna 208', 1200);
select * from aircrafts;
update aircrafts set model = 'SSJ-100' where id = 1;
select * from aircrafts ;
delete from aircrafts where id = 1;
select * from aircrafts;