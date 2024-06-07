/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  edwin
 * Created: 6 jun 2024
 */

-- Crea la estructura inicial para ejecutar una venta.
DELIMITER //
CREATE PROCEDURE created_sale_car()
BEGIN
INSERT INTO sale(id, employee_id, customer_id,total, pay_method, date) 
	VALUES(DEFAULT, 1, 1, 0, 'E', DEFAULT);
END //
DELIMITER;

