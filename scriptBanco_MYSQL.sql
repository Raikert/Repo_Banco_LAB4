##Ejecutar lo siguiente para dropear la base de datos y cargarla devuelta
##DROP database FINAL_LAB_4;


Create database FINAL_LAB_4;
use FINAL_LAB_4;

create table CLIENTES
(
DNI_Cli varchar(20) not null,
CUIL_Cli varchar(20) not null,
Nombre_Cli varchar(50) not null,
Apellido_Cli varchar (50) not null,
Sexo_Cli varchar(20) not null,
Nacionalidad_Cli varchar(50)not null,
Fecha_Nac_Cli date not null,
Direccion_Cli varchar(50) null,
Localidad_Cli varchar(50) null,
Provincia_Cli varchar(50) null,
CorreoE_Cli varchar (50) null,
Telefono_Cli varchar (20) null,
Usuario_Cli varchar (20) not null,
Contraseña_Cli varchar (20) not null,
Estado_Cli bit not null default 0, 

CONSTRAINT PK_DNI_CLIENTE PRIMARY KEY (DNI_Cli)
);

create table CUENTAS
( 
DNI_Cu varchar(20) not null,
Fec_creacion_Cu date null,
TipoCuenta_Cu varchar (20) not null,
Ncuenta_Cu int not null auto_increment,
CBU_Cu varchar (20) not null,
Saldo_Cu decimal(13,2) not null default '10000.00',
CONSTRAINT PK_NCUENTA_CU PRIMARY KEY (Ncuenta_Cu)
);

alter table CUENTAS
add constraint FK_CLIENTE_CUENTAS foreign key (DNI_Cu)
references CLIENTES (DNI_Cli);

CREATE TABLE PRESTAMOS
( 
	ID_Pr int(10) NOT NULL AUTO_INCREMENT,
    Ncuenta_Pr int NOT NULL,
    DNI_Pr varchar(20) NOT NULL default 0,
    fecha_Pr date NULL,
    importe_Int_Pr decimal(13,2) NOT NULL,
    importe_Ped_Pr decimal(13,2) NOT NULL,
    plazo_pago_Pr date NOT NULL,
    montoxMes_Pr decimal(13,2) NOT NULL,
    cuotas_Pr varchar(43) NOT NULL,
    cuota_pagada_Pr varchar(43) NULL DEFAULT '0',
    estado_Pr varchar(45) NOT NULL DEFAULT 'pendiente',
    CONSTRAINT PK_ID_Pr PRIMARY KEY (ID_Pr)
);

ALTER TABLE PRESTAMOS
ADD CONSTRAINT FK_PRESTAMOS_CUENTAS FOREIGN KEY (Ncuenta_Pr)
REFERENCES CUENTAS (NCuenta_Cu);

##PROCEDIMIENTOS ALMACENADOS

DELIMITER $$
CREATE PROCEDURE `Obtener_DNI_Segun_Cuenta`(IN NCuenta INT(11))
BEGIN
	SELECT DNI_Cu FROM CUENTAS
    WHERE NCuenta_Cu = NCuenta;
END$$

CREATE PROCEDURE `Modificar_Estado_Prestamo`(IN ID INT(10), IN estado VARCHAR(45))
BEGIN
	UPDATE FINAL_LAB_4.PRESTAMOS SET estado_Pr = estado
    WHERE ID_Pr = ID;
END$$

CREATE PROCEDURE `Sumar_Prestamo_A_Saldo`(IN monto DECIMAL(13,2), IN cuenta INT)
BEGIN
	UPDATE FINAL_LAB_4.CUENTAS SET Saldo_Cu = monto + Saldo_Cu
    WHERE Ncuenta_Cu = cuenta;
END$$

CREATE PROCEDURE `Nuevo_Prestamo` (IN NCuenta INT(11), IN importe_Int DECIMAL(13,2), IN importe_Ped DECIMAL(13,2), IN montoxmes DECIMAL(13,2), IN cuotas INT)
BEGIN

	DECLARE DNI varchar(20);
    
	SELECT DNI_Cu INTO DNI
    FROM CUENTAS
    WHERE NCuenta_Cu = NCuenta;
    
	INSERT INTO PRESTAMOS(NCuenta_Pr, DNI_Pr, importe_Int_Pr, importe_Ped_Pr, plazo_pago_Pr, montoxmes_Pr, cuotas_Pr, cuota_pagada_Pr, estado_Pr)
    VALUE (NCuenta, DNI, importe_Int, importe_Ped, DATE_ADD(fecha, INTERVAL cuotas month), montoxmes, cuotas, '0', 'pendiente') ;

END$$

CREATE PROCEDURE `Contar_Cantidad_Prestamos_Cuotas` (IN ini DATE, in fin DATE, IN monmin DECIMAL(13,2), IN monmax DECIMAL(13,2), IN cuotas VARCHAR(43))
BEGIN
	SELECT COUNT(ID_Pr) FROM PRESTAMOS
    WHERE fecha_Pr >= ini 
    AND fecha_Pr <= fin
    AND importe_Int_Pr >= monmin 
    AND importe_Int_Pr < minmax
    AND cuotas_Pr = cuotas;
END$$

CREATE PROCEDURE `Contar_Cantidad_Prestamos` (IN ini DATE, in fin DATE, IN monmin DECIMAL(13,2), IN monmax DECIMAL(13,2))
BEGIN
	SELECT COUNT(ID_Pr) FROM prestamos
    WHERE fecha_Pr >= ini 
    AND fecha_Pr <= fin
    AND importe_Int_Pr >= monmin
    AND importe_Int_Pr < minmax;
END$$

CREATE PROCEDURE `Contar_Cantidad_Prestamos_Pagos_Cuotas` (IN ini DATE, in fin DATE, IN monmin DECIMAL(13,2), IN monmax DECIMAL(13,2), IN cuotas VARCHAR(43))
BEGIN

	SELECT COUNT(ID_Pr) FROM prestamos
    WHERE fecha_Pr >= ini AND fecha_Pr <= fin AND importe_Int_Pr >= monmin AND importe_Int_Pr < minmax AND cuotas_Pr = cuotas AND estado_Pr = 'pago';

END$$

CREATE PROCEDURE `Contar_Cantidad_Prestamos_Pagos` (IN ini DATE, in fin DATE, IN monmin DECIMAL(13,2), IN monmax DECIMAL(13,2))
BEGIN

	SELECT COUNT(ID_Pr) FROM prestamos
    WHERE fecha_Pr >= ini AND fecha_Pr <= fin AND importe_Int_Pr >= monmin AND importe_Int_Pr < minmax AND estado_Pr = 'pago';

END$$

CREATE PROCEDURE `Contar_Cantidad_Prestamos_Pendientes_Cuotas` (IN ini DATE, in fin DATE, IN monmin DECIMAL(13,2), IN monmax DECIMAL(13,2), IN cuotas VARCHAR(43))
BEGIN

	SELECT COUNT(ID_Pr) FROM prestamos
    WHERE fecha_Pr >= ini AND fecha_Pr <= fin AND importe_Int_Pr >= monmin AND importe_Int_Pr < minmax AND cuotas_Pr = cuotas AND estado_Pr = 'pendiente';

END$$

CREATE PROCEDURE `Contar_Cantidad_Prestamos_Pendientes` (IN ini DATE, in fin DATE, IN monmin DECIMAL(13,2), IN monmax DECIMAL(13,2))
BEGIN

	SELECT COUNT(ID_Pr) FROM prestamos
    WHERE fecha_Pr >= ini AND fecha_Pr <= fin AND importe_Int_Pr >= monmin AND importe_Int_Pr < minmax AND estado_Pr = 'pendiente';

END$$

CREATE PROCEDURE `Contar_Cantidad_Prestamos_Rechazados_Cuotas` (IN ini DATE, in fin DATE, IN monmin DECIMAL(13,2), IN monmax DECIMAL(13,2), IN cuotas VARCHAR(43))
BEGIN

	SELECT COUNT(ID_Pr) FROM prestamos
    WHERE fecha_Pr >= ini AND fecha_Pr <= fin AND importe_Int_Pr >= monmin AND importe_Int_Pr < minmax AND cuotas_Pr = cuotas AND estado_Pr = 'rechazado';

END$$

CREATE PROCEDURE `Contar_Cantidad_Prestamos_Rechazados` (IN ini DATE, in fin DATE, IN monmin DECIMAL(13,2), IN monmax DECIMAL(13,2))
BEGIN

	SELECT COUNT(ID_Pr) FROM prestamos
    WHERE fecha_Pr >= ini AND fecha_Pr <= fin AND importe_Int_Pr >= monmin AND importe_Int_Pr < minmax AND estado_Pr = 'rechazado';

END$$

CREATE PROCEDURE `Contar_Cantidad_Prestamos_Aprobados_Cuotas` (IN ini DATE, in fin DATE, IN monmin DECIMAL(13,2), IN monmax DECIMAL(13,2), IN cuotas VARCHAR(43))
BEGIN

	SELECT COUNT(ID_Pr) FROM prestamos
    WHERE fecha_Pr >= ini AND fecha_Pr <= fin AND importe_Int_Pr >= monmin AND importe_Int_Pr < minmax AND cuotas_Pr = cuotas AND estado_Pr = 'aprobado';

END$$

CREATE PROCEDURE `Contar_Cantidad_Prestamos_Aprobados` (IN ini DATE, in fin DATE, IN monmin DECIMAL(13,2), IN monmax DECIMAL(13,2))
BEGIN

	SELECT COUNT(ID_Pr) FROM prestamos
    WHERE fecha_Pr >= ini AND fecha_Pr <= fin AND importe_Int_Pr >= monmin AND importe_Int_Pr < minmax AND estado_Pr = 'aprobado';

END$$

CREATE PROCEDURE `Obtener_Promedio_Prestamos_Cuotas` (IN ini DATE, in fin DATE, IN monmin DECIMAL(13,2), IN monmax DECIMAL(13,2), IN cuotas VARCHAR(43))
BEGIN

	SELECT importe_Int_Pr FROM prestamos
    WHERE fecha_Pr >= ini AND fecha_Pr <= fin AND importe_Int_Pr >= monmin AND importe_Int_Pr < minmax AND cuotas_Pr = cuotas;

END$$

CREATE PROCEDURE `Obtener_Promedio_Prestamos` (IN ini DATE, in fin DATE, IN monmin DECIMAL(13,2), IN monmax DECIMAL(13,2))
BEGIN

	SELECT importe_Int_Pr FROM prestamos
    WHERE fecha_Pr >= ini AND fecha_Pr <= fin AND importe_Int_Pr >= monmin AND importe_Int_Pr < minmax;

END$$

##TRIGGERS

CREATE TRIGGER `FINAL_LAB_4`.`PRESTAMOS_AFTER_UPDATE` AFTER UPDATE ON `PRESTAMOS` FOR EACH ROW
BEGIN
	IF NEW.estado_Pr = 'aprobado'
		THEN
			CALL Sumar_Prestamo_A_Saldo(NEW.importe_Ped_Pr, NEW.Ncuenta_Pr);
            ##Agregar procedimiento para que al mismo tiempo que se ingresa la suma de dinero
            ##se crean los registros para la tabla cuotas paga
	END IF;
END$$

CREATE TRIGGER `FINAL_LAB_4`.`CUENTAS_SET_FECHA` BEFORE INSERT ON `CUENTAS` 
FOR EACH ROW 
SET NEW.Fec_creacion_Cu = IFNULL(NEW.Fec_creacion_Cu,NOW());

CREATE TRIGGER `FINAL_LAB_4`.`PRESTAMOS_SET_FECHA` BEFORE INSERT ON `PRESTAMOS` 
FOR EACH ROW 
SET NEW.fecha_Pr = IFNULL(NEW.Fecha_Pr,NOW());

##INSERTS
insert into Clientes (DNI_Cli, CUIL_Cli, Nombre_Cli, Apellido_Cli, Sexo_Cli, Nacionalidad_Cli, 
Fecha_Nac_Cli, Direccion_Cli, Localidad_Cli, Provincia_Cli, CorreoE_Cli, Telefono_Cli, 
Usuario_Cli, Contraseña_Cli, Estado_Cli) 

Select '32078320', '20320783203', 'Raul', 'Gimenez', 'Masculino', 'Bolivia', 
'1978-03-28', 'Parque de los Cultos 778', 'San isidro', 'Ciudad de Buenos Aires', 
'RaulGimenz@yahoo.com.ar', '1154678932', 'RGimenez01', 'RG3220', 1 UNION
Select '45678920', '20456789203', 'Romina', 'Guemez', 'Femenino', 'Argentina', 
'1989-07-19', 'Av. de los Condecorados 443', 'La matanza', 'Ciudad de Buenos Aires', 
'RomiGZ@gmail.com', '1122398866', 'RGuemez01', 'RG4520', 1 UNION
Select '40230980', '20402309803', 'Gimena', 'Estevez', 'Otro', 'Colombia', 
'1985-05-05', 'Av. Rivadavia 998', 'Palermo', 'Buenos Aires', 
'GimenaEz@outlook.com', '1111234567', 'GEstevez01', 'GE4080', 0 UNION
Select '35654890', '20356548903', 'Nicolas', 'Garcia', 'Masculino', 'Brasil', 
'1990-07-16', 'Camino de los cocos 4323', 'Lomas de Zamora', 'Cordoba', 
'Nico55Garcia@gmail.com', '1198765475', 'NGarcia01', 'NG3590', 0 UNION
Select '27543278', '20275432783', 'Nicolas', 'Augustus', 'Masculino', 'Brasil', 
'1964-02-18', 'Calle Florida 9998', 'Villa Carlos Paz', 'Cordoba',
'AugustusColas@outlook.com', '1165789012', 'NAugustuz01', 'NA2778', 1 UNION
Select '34877655', '20348776553', 'Raul', 'Gimenez', 'Otro', 'Bolivia', 
'1980-08-22', 'Parque de los Cultos 1400', 'La Boca', 'Buenos Aires', 
'GimenezConstrucciones@hotmail.com', '1145678900', 'RGimenez02', 'RG3455', 1;

INSERT INTO CUENTAS (DNI_Cu, tipoCuenta_Cu, CBU_Cu)
VALUES ('32078320','Cuenta corriente','275485725845'), ('32078320','Caja de ahorro','2468569347')
,('32078320','Caja de ahorro','34213434'), ('40230980','Cuenta corriente','45415454')
,('40230980','Cuenta corriente','676736767'), ('40230980','Caja de ahorro','1434345');

INSERT INTO PRESTAMOS(Ncuenta_Pr,DNI_Pr, importe_Int_Pr, importe_Ped_Pr, plazo_pago_Pr, montoxMes_Pr, cuotas_Pr)
VALUES ('1','32078320','260000','200000','2022-1-12','43333','6'),('2','32078320','65000','50000','2022-7-12','5416.33','12')
,('3','32078320','97500','75000','2022-7-12','8125','12');