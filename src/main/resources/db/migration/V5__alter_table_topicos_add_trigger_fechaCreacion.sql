DELIMITER //

CREATE OR REPLACE TRIGGER set_default_fecha_creacion
BEFORE INSERT ON topicos
FOR EACH ROW
BEGIN
        SET NEW.fecha_creacion = NOW();
END;
//

DELIMITER ;
