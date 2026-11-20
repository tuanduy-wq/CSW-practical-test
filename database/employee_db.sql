CREATE DATABASE IF NOT EXISTS fpt_employee_db
  CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE fpt_employee_db;

CREATE TABLE IF NOT EXISTS employees (
                                         id     INT AUTO_INCREMENT PRIMARY KEY,
                                         name   VARCHAR(100) NOT NULL,
    salary DOUBLE       NOT NULL
    );

INSERT INTO employees (name, salary) VALUES
                                         ('Nguyen Van An',  1500.00),
                                         ('Tran Thi Binh',  1800.50),
                                         ('Le Minh Cuong',  2200.00);