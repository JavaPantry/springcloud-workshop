drop table if exists store;
create table store(
                        id bigint not null auto_increment,
                        name varchar(100),
                        description varchar(100),
                        address varchar(100),

                        version bigint,
                        created_date timestamp,
                        last_modified_date timestamp,
                        PRIMARY KEY (`id`)
) engine = InnoDB;

INSERT INTO store (name, description, address, version, created_date, last_modified_date) VALUES ('Walmart Canada', 'Walmart store', 'Walmart Canada address', 0, now(), now());
INSERT INTO store (name, description, address, version, created_date, last_modified_date) VALUES ('Drug store Canada', 'Drug store store', 'Drug store address', 0, now(), now());
INSERT INTO store (name, description, address, version, created_date, last_modified_date) VALUES ('Best Buy Canada', 'Best Buy store', 'Best Buy address', 0, now(), now());
INSERT INTO store (name, description, address, version, created_date, last_modified_date) VALUES ('Viztek', 'Viztek store', 'Viztek address', 0, now(), now());
INSERT INTO store (name, description, address, version, created_date, last_modified_date) VALUES ('Henrys', 'Henrys store1', 'Henrys address', 0, now(), now());
INSERT INTO store (name, description, address, version, created_date, last_modified_date) VALUES ('Canon Canada', 'Canon store', 'address', 0, now(), now());
INSERT INTO store (name, description, address, version, created_date, last_modified_date) VALUES ('Warehouse 1', 'Warehouse 1', 'address', 0, now(), now());
INSERT INTO store (name, description, address, version, created_date, last_modified_date) VALUES ('Warehouse 2', 'Warehouse 2', 'address', 0, now(), now());
INSERT INTO store (name, description, address, version, created_date, last_modified_date) VALUES ('Warehouse 3', 'Warehouse 3', 'address', 0, now(), now());
INSERT INTO store (name, description, address, version, created_date, last_modified_date) VALUES ('Warehouse 4', 'Warehouse 4', '', 0, now(), now());

drop table if exists products_in_store;
create table products_in_store(
                       id bigint not null auto_increment,
                       store_id bigint,
                       product_id bigint,
                       price decimal(10,2),

                       version bigint,
                       created_date timestamp,
                       last_modified_date timestamp,
                       constraint store_fk FOREIGN KEY (store_id) references store (id),
                       constraint store_product UNIQUE (store_id, product_id),
                       PRIMARY KEY (`id`)
) engine = InnoDB;