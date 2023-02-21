DROP TABLE IF EXISTS employees;

CREATE TABLE Employees (
                           EmployeeID INT AUTO_INCREMENT PRIMARY KEY,
                           EmployeeName VARCHAR(50) NOT NULL,
                           EmployeeAge INT NOT NULL,
                           EmployeeDepartment VARCHAR(50) NOT NULL,
                           EmployeeGender VARCHAR(10) NOT NULL,
                           EmployeeSalary DOUBLE NOT NULL
);