CREATE TABLE Merchant (
    id int(10) unsigned NOT NULL AUTO_INCREMENT,
    name varchar(64) NOT NULL COMMENT 'Merchant name',
    logo_url varchar(256) NOT NULL COMMENT 'Merchant logo',
    business_license_url varchar(256)  NOT NULL COMMENT 'Merchant license',
    phone varchar(64) NOT NULL COMMENT 'Merchant phone number',
    address varchar(64) NOT NULL COMMENT 'Merchant address',
    is_audit BOOLEAN NOT NULL COMMENT 'whether audit passes',
    PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;