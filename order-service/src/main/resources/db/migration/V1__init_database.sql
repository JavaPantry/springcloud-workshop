drop table if exists product;
create table order_header(
                        id bigint not null auto_increment,
                        name varchar(100),

                        version bigint,
                        created_date timestamp,
                        last_modified_date timestamp,
                        PRIMARY KEY (`id`)
) engine = InnoDB;

INSERT INTO order_header (name, version, created_date, last_modified_date) VALUES ('order1', 0, now(), now());
INSERT INTO order_header (name, version, created_date, last_modified_date) VALUES ('order2', 0, now(), now());
INSERT INTO order_header (name, version, created_date, last_modified_date) VALUES ('order3', 0, now(), now());
INSERT INTO order_header (name, version, created_date, last_modified_date) VALUES ('order4', 0, now(), now());
INSERT INTO order_header (name, version, created_date, last_modified_date) VALUES ('order5', 0, now(), now());