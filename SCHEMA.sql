/* DATABASE FOR SERVER: MySQL*/
-- CREATE DATABASE sy_admin_db;

-- USE sy_admin_db;
/*----Nueva al 06/08/2024---*/
CREATE TABLE administration(
	`id` INT AUTO_INCREMENT NOT NULL,
    `enterprise` VARCHAR(250) NOT NULL,
    `capital_mount` DOUBLE NOT NULL,
    `business_line` VARCHAR(250) NOT NULL,
    `mission` TEXT NULL,
    `vision` TEXT NULL,
    `history` TEXT NULL,
	`date` DATETIME NOT NULL,
    CONSTRAINT pk_id_administration PRIMARY KEY(`id`)
);

/*----Nueva al 06/08/2024---*/
CREATE TABLE branch(
	`id` INT AUTO_INCREMENT NOT NULL,
    `administration_id` INT NOT NULL,
    `capital_mount` DOUBLE NOT NULL,
    `phone_number` VARCHAR(50) NULL,
    `email` VARCHAR(200) NULL,
    `state` VARCHAR(100) NOT NULL,
    `city` VARCHAR(250) NOT NULL,
    `municipality` VARCHAR(100) NOT NULL,
    `street` TEXT NULL,
	`out_number` VARCHAR(10) NULL,
    `in_number` VARCHAR(10) NULL,
    `date` DATETIME NOT NULL,
    CONSTRAINT pk_id_branch PRIMARY KEY(`id`),
    CONSTRAINT fk_administration_id_branch FOREIGN KEY(`administration_id`)
		REFERENCES administration(`id`)
);

CREATE TABLE product(
    `id` INT AUTO_INCREMENT NOT NULL,
    `branch_id` INT NOT NULL,
    `name` VARCHAR(250) NOT NULL,
    `in_price` DOUBLE NOT NULL,
    `out_price` DOUBLE NOT NULL,
    `wholesale_price` DOUBLE NOT NULL,
    `description` TEXT NULL,
    `stock` INT NOT NULL,
    `min_stock` INT NULL,
    `unit` VARCHAR(10) NOT NULL,
    `expiration_date` DATE NULL,
    `active` BOOLEAN DEFAULT TRUE NOT NULL,
    `var_code` VARCHAR(100) NOT NULL,
    CONSTRAINT `pk_product_id` PRIMARY KEY (`id`),
    CONSTRAINT `fk_branch_id_product` FOREIGN KEY(`branch_id`) 
		REFERENCES branch(`id`)
);

CREATE TABLE customer(
    `id` INT AUTO_INCREMENT NOT NULL,
    `branch_id` INT NOT NULL,
    `name` VARCHAR(100) NOT NULL,
    `surname` VARCHAR(150) NOT NULL,
    `birthday` DATE NOT NULL,
    `age` INT NULL,
    `phone_number` VARCHAR(20) NULL,
    `email` VARCHAR(250) NULL,
    `address` TEXT NULL,
    `no_sales` INT NULL,
    active BOOLEAN DEFAULT TRUE NOT NULL,
    CONSTRAINT `pk_customer_id` PRIMARY KEY (`id`),
    CONSTRAINT `fk_branch_id_customer` FOREIGN KEY(`branch_id`) 
		REFERENCES branch(`id`)
);

INSERT INTO customer(`id`, `name`, `surname`, `birthday`, `age`, `phone_number`, `email`, `address`, `no_sales`, `active`) 
VALUES(DEFAULT, 'All', 'Generic', '2000-07-31', null, null, null, null, 0, DEFAULT);

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

/*----Nueva al 06/08/2024---*/
CREATE TABLE user_type(
	`id` INT AUTO_INCREMENT NOT NULL,
    `type` VARCHAR(100) NOT NULL,
    `description` VARCHAR(250) NOT NULL,
    `create_at` DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
    CONSTRAINT `pk_id_user_type` PRIMARY KEY(`id`)
);

INSERT INTO user_type(`id`, `type`, `description`, `create_at`)
	VALUES(DEFAULT, 'SUPERADMIN', 'Administrador de sistema', DEFAULT);
INSERT INTO user_type(`id`, `type`, `create_at`)
	VALUES(DEFAULT, 'ADMIN','Administrador de sucursal', DEFAULT);
INSERT INTO user_type(`id`, `type`, `create_at`)
	VALUES(DEFAULT, 'REGISTER','Cajero', DEFAULT);


CREATE TABLE user(
    `id` INT AUTO_INCREMENT NOT NULL,
    `user_type_id` INT NOT NULL,
    `user_name` VARCHAR(50) NULL,
    `user_password` VARCHAR(100) NOT NULL,
    CONSTRAINT pk_user_id PRIMARY KEY (id), 
    CONSTRAINT `fk_user_type_id_user` FOREIGN KEY(`user_type_id`) 
		REFERENCES user_type(`id`)
);

CREATE TABLE employee(
    `id` INT AUTO_INCREMENT NOT NULL,
    `user_id` INT NULL,
    `branch_id` INT NOT NULL,
    `name` VARCHAR(100) NOT NULL,
    `surname` VARCHAR(150) NOT NULL,
    `salary` DOUBLE NOT NULL,
    `birthday` DATE NOT NULL,
    `age` INT NULL,
    `phone_number` VARCHAR(20) NULL,
    `email` VARCHAR(250) NULL,
    `address` TEXT NULL,
    active BOOLEAN NOT NULL,
    CONSTRAINT `pk_employee_id` PRIMARY KEY (`id`),
    CONSTRAINT `fk_employe_user_id` FOREIGN KEY (`user_id`)    
        REFERENCES user(`id`),
	CONSTRAINT `fk_branch_id_employee` FOREIGN KEY(`branch_id`) 
		REFERENCES branch(`id`)
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

/*----Nueva al 06/08/2024---*/
CREATE TABLE register(
    `id` INT AUTO_INCREMENT NOT NULL,
    `branch_id` INT NOT NULL,
    `initial_mount` DOUBLE NOT NULL,
    `initial_date` DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
    `close_mount` DOUBLE NOT NULL,
    `close_date` DATETIME NOT NULL,
    CONSTRAINT `pk_id_register` PRIMARY KEY(`id`), 
    CONSTRAINT `fk_branch_id_register` FOREIGN KEY(`branch_id`)
		REFERENCES branch(`id`)
);

CREATE TABLE sale(
    `id` INT AUTO_INCREMENT NOT NULL,
    `sub_total` DOUBLE NULL,
    `iva` DOUBLE NULL,
    `pay_quantity` DOUBLE NULL,
    `change_amount` DOUBLE NULL,
    `total` DOUBLE NOT NULL,
    `employee_id` INT NOT NULL,
    `customer_id` INT NULL,
    `paymethod_id` INT NOT NULL,
    `ticket_id` INT NOT NULL,
    `date` DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
    CONSTRAINT pk_sale_id PRIMARY KEY (id),
    CONSTRAINT fk_sale_employee_id FOREIGN KEY (employee_id) 
        REFERENCES employee (id),
    CONSTRAINT fk_sale_customer_id FOREIGN KEY (customer_id)
        REFERENCES customer (id),
	CONSTRAINT fk_sale_pay_method_id FOREIGN KEY (paymethod_id)
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
    `id` INT AUTO_INCREMENT NOT NULL,
    `employee_id` INT NOT NULL,
    `provider_id` INT NOT NULL,
    `branch_id` INT NOT NULL,
    `total` DOUBLE NOT NULL,
    `pay_method` CHAR(1) NOT NULL,
    date DATETIME NOT NULL,
    CONSTRAINT pk_purchase_id PRIMARY KEY (id),
    CONSTRAINT fk_purchase_employee_id FOREIGN KEY (employee_id) 
        REFERENCES employee (id),
    CONSTRAINT fk_purchase_provider_id FOREIGN KEY (provider_id)
        REFERENCES provider (id),
	CONSTRAINT `fk_branch_id_purchase` FOREIGN KEY(`branch_id`) 
		REFERENCES branch(`id`)
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
