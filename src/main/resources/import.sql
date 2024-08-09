insert into category (id, description) values (1, 'Massas');
insert into category (id, description) values (2, 'Carnes');
insert into category (id, description) values (3, 'Peixes');

insert into supplier (id, name) values (1, 'CEASA');
insert into supplier (id, name) values (2, 'SUPERMERCADO');

insert into product (id, name, fk_supplier, fk_category, quantity_avaliable) values (1, 'Espaguete', 2, 1, 11);

insert into product (id, name, fk_supplier, fk_category, quantity_avaliable) values (2, 'Salmao', 1, 3, 11);