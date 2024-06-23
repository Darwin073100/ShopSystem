/* DATABASE FOR SERVER: MySQL*/
CREATE DATABASE sy_admin_db;

USE sy_admin_db;

CREATE TABLE product(
    id INT AUTO_INCREMENT NOT NULL,
    name VARCHAR(250) NOT NULL,
    price DOUBLE NOT NULL,
    description TEXT NULL,
    stock INT NOT NULL,
    unit VARCHAR(10) NOT NULL,
    expiration_date DATE NULL,
    active BOOLEAN DEFAULT TRUE NOT NULL,
    var_code VARCHAR(100) NOT NULL,
    CONSTRAINT pk_product_id PRIMARY KEY (id)
);

CREATE TABLE customer(
    id INT AUTO_INCREMENT NOT NULL,
    name VARCHAR(100) NOT NULL,
    surname VARCHAR(150) NOT NULL,
    birthday DATE NOT NULL,
    age INT NULL,
    phone_number VARCHAR(20) NULL,
    email VARCHAR(250) NULL,
    address TEXT NULL,
    no_sales INT NULL,
    active BOOLEAN NOT NULL,
    CONSTRAINT pk_customer_id PRIMARY KEY (id)
);

INSERT INTO customer(`id`, `name`, `suername`, `birthday`, `age`, `phone_number`, `email`, `address`, `no_sales`, `active`) 
VALUES(DEFAULT, 'All', 'Generic', '2000-07-31', null, null, null, null, 0, TRUE);

CREATE TABLE provider(
    id INT AUTO_INCREMENT NOT NULL,
    name VARCHAR(100) NOT NULL,
    surname VARCHAR(150) NOT NULL,
    age INT NULL,
    phone_number VARCHAR(20) NULL,
    email VARCHAR(250) NULL,
    company VARCHAR(250) NULL,
    active BOOLEAN NOT NULL,
    CONSTRAINT pk_employee_id PRIMARY KEY (id)
);

CREATE TABLE user(
    id INT AUTO_INCREMENT NOT NULL,
    user_name VARCHAR(50) NULL,
    user_password VARCHAR(100) NOT NULL,
    type VARCHAR(50) NOT NULL,
    CONSTRAINT pk_user_id PRIMARY KEY (id) 
);

CREATE TABLE employee(
    id INT AUTO_INCREMENT NOT NULL,
    user_id INT NULL,
    name VARCHAR(100) NOT NULL,
    surname VARCHAR(150) NOT NULL,
    salary DOUBLE NOT NULL,
    birthday DATE NOT NULL,
    age INT NULL,
    phone_number VARCHAR(20) NULL,
    email VARCHAR(250) NULL,
    address TEXT NULL,
    active BOOLEAN NOT NULL,
    CONSTRAINT pk_employee_id PRIMARY KEY (id),
    CONSTRAINT fk_employe_user_id FOREIGN KEY (user_id)    
        REFERENCES user(id) 
);

CREATE TABLE pay_method(
    id INT AUTO_INCREMENT NOT NULL,
    method CHAR(1) NOT NULL,
    detail VARCHAR(50) NOT NULL,
    CONSTRAINT pk_pay_method_id PRIMARY KEY (id)
);

INSERT INTO pay_method (`id`, `method`, `detail`) 
VALUES(DEFAULT, 'E', 'Efectivo');
INSERT INTO pay_method (`id`, `method`, `detail`) 
VALUES(DEFAULT, 'D', 'Debito');
INSERT INTO pay_method (`id`, `method`, `detail`) 
VALUES(DEFAULT, 'C', 'Credito');

CREATE TABLE ticket(
    id INT AUTO_INCREMENT NOT NULL,
    method CHAR(1) NOT NULL,
    detail VARCHAR(50) NOT NULL,
    CONSTRAINT pk_ticket_id PRIMARY KEY (id)
);

INSERT INTO ticket (`id`, `method`, `detail`) 
VALUEs(DEFAULT, 'S', 'S/C');
INSERT INTO ticket (`id`, `method`, `detail`) 
VALUEs(DEFAULT, 'I', 'Impreso');
INSERT INTO ticket (`id`, `method`, `detail`) 
VALUEs(DEFAULT, 'E', 'Correo electrónico');

CREATE TABLE sale(
    id INT AUTO_INCREMENT NOT NULL,
    employee_id INT NOT NULL,
    customer_id INT NULL,
    total DOUBLE NOT NULL,
    paymethod_id INT NOT NULL,
    ticket_id INT NOT NULL,
    date DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
    CONSTRAINT pk_sale_id PRIMARY KEY (id),
    CONSTRAINT fk_sale_employee_id FOREIGN KEY (employee_id) 
        REFERENCES employee (id),
    CONSTRAINT fk_sale_customer_id FOREIGN KEY (customer_id)
        REFERENCES customer (id),
	CONSTRAINT fk_sale_pay_method_id FOREIGN KEY (pay_method_id)
		REFERENCES pay_method (id),
	CONSTRAINT fk_sale_ticket_id FOREIGN KEY (ticket_id)
		REFERENCES ticket (id)
);

CREATE TABLE sale_item(
    id INT AUTO_INCREMENT NOT NULL,
    sale_id INT NOT NULL,
    product_id INT NOT NULL,
    quantity INT NULL,
    total DOUBLE NOT NULL,
    CONSTRAINT pk_sale_item_id PRIMARY KEY (id),
    CONSTRAINT fk_sale_item_product_product_id FOREIGN KEY(product_id)
        REFERENCES product (id),
    CONSTRAINT fk_sale_item_sale_sale_id FOREIGN KEY(sale_id)
        REFERENCES  sale(id)
);

CREATE TABLE purchase(
    id INT AUTO_INCREMENT NOT NULL,
    employee_id INT NOT NULL,
    provider_id INT NOT NULL,
    total DOUBLE NOT NULL,
    pay_method CHAR(1) NOT NULL,
    date DATETIME NOT NULL,
    CONSTRAINT pk_purchase_id PRIMARY KEY (id),
    CONSTRAINT fk_purchase_employee_id FOREIGN KEY (employee_id) 
        REFERENCES employee (id),
    CONSTRAINT fk_purchase_provider_id FOREIGN KEY (provider_id)
        REFERENCES provider (id)
);

CREATE TABLE purchase_item(
    id INT AUTO_INCREMENT NOT NULL,
    purchase_id INT NOT NULL,
    product_id INT NOT NULL,
    quantity INT NULL,
    total DOUBLE NOT NULL,
    CONSTRAINT pk_purchase_item_id PRIMARY KEY (id),
    CONSTRAINT fk_purchase_item_product_product_id FOREIGN KEY(product_id) 
        REFERENCES product (id),
    CONSTRAINT fk_purchase_item_purchase_purchase_id FOREIGN KEY(purchase_id)
        REFERENCES purchase(id)
);
