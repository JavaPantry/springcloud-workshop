-- orderdb database
DROP DATABASE IF EXISTS orderdb;
DROP USER IF EXISTS `orderadmin`@`%`;
DROP USER IF EXISTS `orderuser`@`%`;
CREATE DATABASE IF NOT EXISTS orderdb CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

CREATE USER IF NOT EXISTS `orderadmin`@`%` IDENTIFIED WITH mysql_native_password BY 'password';
GRANT SELECT, INSERT, UPDATE, DELETE, CREATE, DROP, REFERENCES, INDEX, ALTER, EXECUTE, CREATE VIEW, SHOW VIEW, CREATE ROUTINE, ALTER ROUTINE, EVENT, TRIGGER ON `orderdb`.* TO `orderadmin`@`%`;

CREATE USER IF NOT EXISTS `orderuser`@`%` IDENTIFIED WITH mysql_native_password BY 'password';
GRANT SELECT, INSERT, UPDATE, DELETE, EXECUTE, SHOW VIEW ON `orderdb`.* TO `orderuser`@`%`;

FLUSH PRIVILEGES;

-- inventorydb database
DROP DATABASE IF EXISTS inventorydb;
DROP USER IF EXISTS `inventoryadmin`@`%`;
DROP USER IF EXISTS `inventoryuser`@`%`;
CREATE DATABASE IF NOT EXISTS inventorydb CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

CREATE USER IF NOT EXISTS `inventoryadmin`@`%` IDENTIFIED WITH mysql_native_password BY 'password';
GRANT SELECT, INSERT, UPDATE, DELETE, CREATE, DROP, REFERENCES, INDEX, ALTER, EXECUTE, CREATE VIEW, SHOW VIEW, CREATE ROUTINE, ALTER ROUTINE, EVENT, TRIGGER ON `inventorydb`.* TO `inventoryadmin`@`%`;

CREATE USER IF NOT EXISTS `inventoryuser`@`%` IDENTIFIED WITH mysql_native_password BY 'password';
GRANT SELECT, INSERT, UPDATE, DELETE, EXECUTE, SHOW VIEW ON `inventorydb`.* TO `inventoryuser`@`%`;

FLUSH PRIVILEGES;

-- productdb database
DROP DATABASE IF EXISTS productdb;
DROP USER IF EXISTS `productadmin`@`%`;
DROP USER IF EXISTS `productuser`@`%`;
CREATE DATABASE IF NOT EXISTS productdb CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

CREATE USER IF NOT EXISTS `productadmin`@`%` IDENTIFIED WITH mysql_native_password BY 'password';
GRANT SELECT, INSERT, UPDATE, DELETE, CREATE, DROP, REFERENCES, INDEX, ALTER, EXECUTE, CREATE VIEW, SHOW VIEW, CREATE ROUTINE, ALTER ROUTINE, EVENT, TRIGGER ON `productdb`.* TO `productadmin`@`%`;

CREATE USER IF NOT EXISTS `productuser`@`%` IDENTIFIED WITH mysql_native_password BY 'password';
GRANT SELECT, INSERT, UPDATE, DELETE, EXECUTE, SHOW VIEW ON `productdb`.* TO `productuser`@`%`;

FLUSH PRIVILEGES;

DROP DATABASE IF EXISTS storedb;
DROP USER IF EXISTS `storeadmin`@`%`;
DROP USER IF EXISTS `storeuser`@`%`;
CREATE DATABASE IF NOT EXISTS storedb CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

CREATE USER IF NOT EXISTS `storeadmin`@`%` IDENTIFIED WITH mysql_native_password BY 'password';
GRANT SELECT, INSERT, UPDATE, DELETE, CREATE, DROP, REFERENCES, INDEX, ALTER, EXECUTE, CREATE VIEW, SHOW VIEW, CREATE ROUTINE, ALTER ROUTINE, EVENT, TRIGGER ON `storedb`.* TO `storeadmin`@`%`;

CREATE USER IF NOT EXISTS `storeuser`@`%` IDENTIFIED WITH mysql_native_password BY 'password';
GRANT SELECT, INSERT, UPDATE, DELETE, EXECUTE, SHOW VIEW ON `storedb`.* TO `storeuser`@`%`;

FLUSH PRIVILEGES;