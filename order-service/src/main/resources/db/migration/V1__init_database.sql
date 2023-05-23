    drop table if exists customer;
    drop table if exists order_header;
    drop table if exists order_line;

    create table customer (
       id bigint not null auto_increment,
        created_date timestamp,
        last_modified_date timestamp,
        version bigint,
        city varchar(128),
        state varchar(32),
        street_address varchar(255),
        zip_code varchar(10),
        email varchar(255),
        name varchar(255),
        phone varchar(32),
        primary key (id)
    ) engine=InnoDB;

    create table order_header (
       id bigint not null auto_increment,
        created_date timestamp,
        last_modified_date timestamp,
        version bigint,
        bill_to_city varchar(128),
        bill_to_state varchar(32),
        bill_to_address varchar(255),
        bill_to_zip_code varchar(10),
        name varchar(255),
        order_status varchar(32),
        shipping_city varchar(128),
        shipping_state varchar(32),
        shipping_address varchar(255),
        shipping_zip_code varchar(10),
        customer_id bigint,
        constraint order_customer_fk FOREIGN KEY (customer_id) references customer (id),
        primary key (id)
    ) engine=InnoDB;

    create table order_line (
       id bigint not null auto_increment,
        created_date timestamp,
        last_modified_date timestamp,
        version bigint,
        product_id bigint,
        quantity_ordered integer,
        order_header_id bigint,
        constraint order_header_pk FOREIGN KEY (order_header_id) references order_header(id),
#        constraint order_line_product_fk FOREIGN KEY (product_id) references product(id)
        primary key (id)
    ) engine=InnoDB;

INSERT INTO customer (name, version, created_date, last_modified_date) VALUES ('customer1', 0, now(), now());
INSERT INTO customer (name, version, created_date, last_modified_date) VALUES ('customer2', 0, now(), now());

INSERT INTO order_header (name, order_status, version, created_date, last_modified_date, customer_id) VALUES ('order1', 'COMPLETED', 0, now(), now(), 1);
INSERT INTO order_header (name, order_status, version, created_date, last_modified_date, customer_id) VALUES ('order2', 'COMPLETED', 0, now(), now(), 1);
INSERT INTO order_header (name, order_status, version, created_date, last_modified_date, customer_id) VALUES ('order3', 'COMPLETED', 0, now(), now(), 2);
INSERT INTO order_header (name, order_status, version, created_date, last_modified_date, customer_id) VALUES ('order4', 'COMPLETED', 0, now(), now(), 2);

INSERT INTO order_line (quantity_ordered, version, created_date, last_modified_date, order_header_id) VALUES (1, 0, now(), now(), 1);
INSERT INTO order_line (quantity_ordered, version, created_date, last_modified_date, order_header_id) VALUES (2, 0, now(), now(), 1);
INSERT INTO order_line (quantity_ordered, version, created_date, last_modified_date, order_header_id) VALUES (3, 0, now(), now(), 2);
INSERT INTO order_line (quantity_ordered, version, created_date, last_modified_date, order_header_id) VALUES (4, 0, now(), now(), 2);
INSERT INTO order_line (quantity_ordered, version, created_date, last_modified_date, order_header_id) VALUES (5, 0, now(), now(), 3);
INSERT INTO order_line (quantity_ordered, version, created_date, last_modified_date, order_header_id) VALUES (6, 0, now(), now(), 3);
INSERT INTO order_line (quantity_ordered, version, created_date, last_modified_date, order_header_id) VALUES (7, 0, now(), now(), 4);
INSERT INTO order_line (quantity_ordered, version, created_date, last_modified_date, order_header_id) VALUES (8, 0, now(), now(), 4);
