delimiter $$

CREATE DEFINER=`root`@`localhost` TRIGGER `PAGADO_AFTER_UPDATE` BEFORE UPDATE ON `prestamos` FOR EACH ROW BEGIN
IF (NEW.cuota_pagada_Pr >= NEW.cuotas_Pr )
THEN
SET NEW.estado_Pr = 'Pagado' ;
end if;
end;$$

delimiter ;