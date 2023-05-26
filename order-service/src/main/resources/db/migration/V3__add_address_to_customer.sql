UPDATE `orderdb`.`customer`
SET
    `city` = 'New York',
    `state` = 'Ontario',
    `street_address` = '260 W 54th St',
    `zip_code` = '12345',
    `email` = 'avp@avp.com',
    `name` = 'Alexey Pashin',
    `phone` = '1234567890'
WHERE `id` = 1;

UPDATE `orderdb`.`customer`
SET
    `city` = 'New York',
    `state` = 'Ontario',
    `street_address` = '260 W 54th St unit 301',
    `zip_code` = '12345',
    `email` = 'avp1@avp.com',
    `name` = 'Alexey Pushhin',
    `phone` = '1234567891'
WHERE `id` = 2;