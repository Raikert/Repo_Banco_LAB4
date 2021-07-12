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
Estado_Cli bit not null default (0), 

CONSTRAINT PK_DNI_CLIENTE PRIMARY KEY (DNI_Cli)
);

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

create table CUENTAS
( 
DNI_Cu varchar(20) not null,
Fec_creacion_Cu date default (CURRENT_DATE) null,
TipoCuenta_Cu varchar (20) not null,
Ncuenta_Cu int not null auto_increment,
CBU_Cu varchar (20) not null,
Saldo_Cu decimal(13,2) not null default '10000.00',
CONSTRAINT PK_NCUENTA_CU PRIMARY KEY (Ncuenta_Cu)
);

alter table CUENTAS
add constraint FK_CLIENTE_CUENTAS foreign key (DNI_Cu)
references CLIENTES (DNI_Cli);