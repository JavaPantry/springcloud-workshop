drop table if exists product;
create table inventory(
                        id bigint not null auto_increment,
                        product_id bigint,
                        store_id bigint,
                        quantity int,

                        version bigint,
                        created_date timestamp,
                        last_modified_date timestamp,
                        PRIMARY KEY (`id`)
) engine = InnoDB;

INSERT INTO inventory (product_id, quantity, store_id, version, created_date, last_modified_date) VALUES (1, 10, 1, 0, now(), now());
INSERT INTO inventory (product_id, quantity, store_id, version, created_date, last_modified_date) VALUES (2, 10, 1, 0, now(), now());
INSERT INTO inventory (product_id, quantity, store_id, version, created_date, last_modified_date) VALUES (3, 10, 1, 0, now(), now());
INSERT INTO inventory (product_id, quantity, store_id, version, created_date, last_modified_date) VALUES (4, 10, 1, 0, now(), now());
INSERT INTO inventory (product_id, quantity, store_id, version, created_date, last_modified_date) VALUES (5, 10, 1, 0, now(), now());

INSERT INTO inventory (product_id, quantity, store_id, version, created_date, last_modified_date) VALUES (1, 10, 2, 0, now(), now());
INSERT INTO inventory (product_id, quantity, store_id, version, created_date, last_modified_date) VALUES (2, 10, 2, 0, now(), now());
INSERT INTO inventory (product_id, quantity, store_id, version, created_date, last_modified_date) VALUES (3, 10, 2, 0, now(), now());
INSERT INTO inventory (product_id, quantity, store_id, version, created_date, last_modified_date) VALUES (4, 10, 2, 0, now(), now());
INSERT INTO inventory (product_id, quantity, store_id, version, created_date, last_modified_date) VALUES (5, 10, 2, 0, now(), now());

INSERT INTO inventory (product_id, quantity, store_id, version, created_date, last_modified_date) VALUES (1, 10, 3, 0, now(), now());
INSERT INTO inventory (product_id, quantity, store_id, version, created_date, last_modified_date) VALUES (2, 10, 3, 0, now(), now());
INSERT INTO inventory (product_id, quantity, store_id, version, created_date, last_modified_date) VALUES (3, 10, 3, 0, now(), now());
INSERT INTO inventory (product_id, quantity, store_id, version, created_date, last_modified_date) VALUES (4, 10, 3, 0, now(), now());
INSERT INTO inventory (product_id, quantity, store_id, version, created_date, last_modified_date) VALUES (5, 10, 3, 0, now(), now());

INSERT INTO inventory (product_id, quantity, store_id, version, created_date, last_modified_date) VALUES (1, 10, 4, 0, now(), now());
INSERT INTO inventory (product_id, quantity, store_id, version, created_date, last_modified_date) VALUES (2, 10, 4, 0, now(), now());
INSERT INTO inventory (product_id, quantity, store_id, version, created_date, last_modified_date) VALUES (3, 10, 4, 0, now(), now());
INSERT INTO inventory (product_id, quantity, store_id, version, created_date, last_modified_date) VALUES (4, 10, 4, 0, now(), now());
INSERT INTO inventory (product_id, quantity, store_id, version, created_date, last_modified_date) VALUES (5, 10, 4, 0, now(), now());

INSERT INTO inventory (product_id, quantity, store_id, version, created_date, last_modified_date) VALUES (1, 10, 5, 0, now(), now());
INSERT INTO inventory (product_id, quantity, store_id, version, created_date, last_modified_date) VALUES (2, 10, 5, 0, now(), now());
INSERT INTO inventory (product_id, quantity, store_id, version, created_date, last_modified_date) VALUES (3, 10, 5, 0, now(), now());
INSERT INTO inventory (product_id, quantity, store_id, version, created_date, last_modified_date) VALUES (4, 10, 5, 0, now(), now());
INSERT INTO inventory (product_id, quantity, store_id, version, created_date, last_modified_date) VALUES (5, 10, 5, 0, now(), now());
