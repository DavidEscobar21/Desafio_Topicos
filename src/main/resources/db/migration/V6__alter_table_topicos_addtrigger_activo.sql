DELIMITER //

CREATE TRIGGER set_default_activo
BEFORE INSERT ON topicos
FOR EACH ROW
BEGIN
        SET NEW.activo = 1;
END;
//

DELIMITER ;