drop table if exists product;
create table product(
                        id bigint not null auto_increment,
                        name varchar(100),
                        description varchar(100),

                        version bigint,
                        created_date timestamp,
                        last_modified_date timestamp,
                        PRIMARY KEY (`id`)
) engine = InnoDB;

INSERT INTO product (name, description, version, created_date, last_modified_date) VALUES ('Product 1', 'Product 1 description', 1, now(), now());
INSERT INTO product (name, description, version, created_date, last_modified_date) VALUES ('Product 2', 'Product 2 description', 1, now(), now());
INSERT INTO product (name, description, version, created_date, last_modified_date) VALUES ('Product 3', 'Product 3 description', 1, now(), now());
INSERT INTO product (name, description, version, created_date, last_modified_date) VALUES ('Product 4', 'Product 4 description', 1, now(), now());
INSERT INTO product (name, description, version, created_date, last_modified_date) VALUES ('Product 5', 'Product 5 description', 1, now(), now());
