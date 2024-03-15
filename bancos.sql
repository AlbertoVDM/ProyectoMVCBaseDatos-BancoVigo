DROP DATABASE IF EXISTS BancoVigo;
CREATE DATABASE BancoVigo;

USE BancoVigo;


CREATE TABLE Cliente (
    clDni VARCHAR(9) PRIMARY KEY,
    clNombre VARCHAR(20),
    clApellido VARCHAR(50),
    clTelefono INT
);

CREATE TABLE Sucursales (
    suCodSucursal INT PRIMARY KEY,
    suCiudad VARCHAR(30),
    suActivo DECIMAL(12, 2)
);

CREATE TABLE Cuenta (
    cuCodCuenta INT AUTO_INCREMENT PRIMARY KEY,
    cuCodSucursal INT ,
    cuFechaCreacion DATE,
    CuSaldo INT,
    FOREIGN KEY (cuCodSucursal) REFERENCES Sucursales(suCodSucursal)
);

CREATE TABLE CuentasCliente (
    ccDNI CHAR(9),
    ccCodCuenta INT,
    PRIMARY KEY (ccDNI, ccCodCuenta),
    FOREIGN KEY (ccDNI) REFERENCES Cliente(clDni),
    FOREIGN KEY (ccCodCuenta) REFERENCES Cuenta(cuCodCuenta)
);

CREATE TABLE Transacciones (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    trCodCuenta INT,
    trFechaTransaccion DATE,
    trTipo ENUM('I', 'R'),
    trCantidad INT,
    FOREIGN KEY (trCodCuenta) REFERENCES Cuenta(cuCodCuenta)
);


