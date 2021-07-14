CREATE DEFINER=`root`@`localhost` TRIGGER `cuentas_BEFORE_INSERT` BEFORE INSERT ON `cuentas` FOR EACH ROW BEGIN
 if((select COUNT(DNI_Cu)  from cuentas where DNI_Cu = new.DNI_Cu) >=3)then
 SIGNAL SQLSTATE '45000'
 SET MESSAGE_TEXT = 'Error';
 END if;
END