CREATE TABLE MENSAJES (
    Codigo NUMBER(20) PRIMARY KEY,
    Texto VARCHAR2(200)
);

CREATE TABLE AUDITA_MENSAJES (
    Quien VARCHAR2(20),
    Como VARCHAR2(20),
    Cuando DATE
);

CREATE OR REPLACE TRIGGER trg_audita_mensajes
AFTER INSERT OR UPDATE OR DELETE ON MENSAJES
DECLARE
    v_quien AUDITA_MENSAJES.quien%TYPE;
    v_como AUDITA_MENSAJES.como%TYPE;
    v_cuando AUDITA_MENSAJES.cuando%TYPE;
BEGIN

    SELECT USER INTO v_quien FROM DUAL;
    
    IF INSERTING THEN
        v_como := 'INSERT';
    ELSIF UPDATING THEN
        v_como := 'UPDATE';
    ELSIF DELETING THEN
        v_como := 'DELETE';
    END IF;
    
    v_cuando := SYSDATE;
    
    INSERT INTO AUDITA_MENSAJES (Quien, Como, Cuando)
    VALUES (v_quien, v_como, v_cuando);
END;
/

INSERT INTO MENSAJES (Codigo, Texto) VALUES (1, 'lalala');

ALTER TABLE MENSAJES
ADD (TIPO VARCHAR2(20)
     CONSTRAINT tipo_valores CHECK (TIPO IN ('INFORMACION', 'RESTRICCION', 'ERROR', 'AVISO', 'AYUDA'))
);

INSERT INTO MENSAJES (Codigo, Texto, Tipo) VALUES (1, 'Este es un mensaje de información', 'INFORMACION');
INSERT INTO MENSAJES (Codigo, Texto, Tipo) VALUES (2, 'Este es otro mensaje de información', 'INFORMACION');

INSERT INTO MENSAJES (Codigo, Texto, Tipo) VALUES (3, 'Este es un mensaje de restricción', 'RESTRICCION');
INSERT INTO MENSAJES (Codigo, Texto, Tipo) VALUES (4, 'Este es otro mensaje de restricción', 'RESTRICCION');

INSERT INTO MENSAJES (Codigo, Texto, Tipo) VALUES (5, 'Este es un mensaje de error', 'ERROR');
INSERT INTO MENSAJES (Codigo, Texto, Tipo) VALUES (6, 'Este es otro mensaje de error', 'ERROR');

INSERT INTO MENSAJES (Codigo, Texto, Tipo) VALUES (7, 'Este es un mensaje de aviso', 'AVISO');
INSERT INTO MENSAJES (Codigo, Texto, Tipo) VALUES (8, 'Este es otro mensaje de aviso', 'AVISO');

INSERT INTO MENSAJES (Codigo, Texto, Tipo) VALUES (9, 'Este es un mensaje de ayuda', 'AYUDA');
INSERT INTO MENSAJES (Codigo, Texto, Tipo) VALUES (10, 'Este es otro mensaje de ayuda', 'AYUDA');

CREATE TABLE MENSAJES_Info (
    Tipo VARCHAR2(30) PRIMARY KEY,
    Cuantos_Mensajes NUMBER(2),
    Ultimo VARCHAR2(200)
);

INSERT INTO MENSAJES_Info (Tipo, Cuantos_Mensajes, Ultimo)
SELECT Tipo, COUNT(*), NULL
FROM MENSAJES
GROUP BY Tipo, NULL;

CREATE OR REPLACE TRIGGER trg_insertar_mensaje
AFTER INSERT ON MENSAJES
FOR EACH ROW
BEGIN
    UPDATE MENSAJES_Info
    SET Cuantos_Mensajes = Cuantos_Mensajes + 1,
        Ultimo = :NEW.Texto
    WHERE Tipo = :NEW.Tipo;
END;
/

CREATE OR REPLACE TRIGGER trg_actualizar_mensaje
AFTER UPDATE OF Tipo, Texto ON MENSAJES
FOR EACH ROW
BEGIN
    UPDATE MENSAJES_Info
    SET Cuantos_Mensajes = Cuantos_Mensajes + 1,
        Ultimo = :NEW.Texto
    WHERE Tipo = :NEW.Tipo;

    UPDATE MENSAJES_Info
    SET Cuantos_Mensajes = Cuantos_Mensajes - 1,
        Ultimo = NULL
    WHERE Tipo = :OLD.Tipo;
END;
/

CREATE OR REPLACE TRIGGER trg_eliminar_mensaje
BEFORE DELETE ON MENSAJES
FOR EACH ROW
BEGIN
    UPDATE MENSAJES_Info
    SET Cuantos_Mensajes = Cuantos_Mensajes - 1,
        Ultimo = NULL
    WHERE Tipo = :OLD.Tipo;
END;
/

CREATE TABLE MENSAJES_TEXTO (
    Codigo NUMBER PRIMARY KEY,
    Texto VARCHAR2(200)
);

CREATE TABLE MENSAJES_TIPO (
    Codigo NUMBER PRIMARY KEY,
    Tipo VARCHAR2(30)
);

INSERT INTO MENSAJES_TEXTO (Codigo, Texto)
SELECT Codigo, Texto
FROM MENSAJES;

INSERT INTO MENSAJES_TIPO (Codigo, Tipo)
SELECT Codigo, Tipo
FROM MENSAJES;

DROP TABLE MENSAJES;

CREATE OR REPLACE VIEW MENSAJES AS
SELECT MT.Codigo, MT.Texto, MTI.Tipo
FROM MENSAJES_TEXTO MT
JOIN MENSAJES_TIPO MTI ON MT.Codigo = MTI.Codigo;

SELECT * FROM MENSAJES;

INSERT INTO MENSAJES (Codigo, Texto, Tipo) VALUES (20, 'lalala', 'AVISO');

CREATE OR REPLACE TRIGGER trg_insertar_mensaje_vista
INSTEAD OF INSERT ON MENSAJES
FOR EACH ROW
BEGIN
    INSERT INTO MENSAJES_TEXTO (Codigo, Texto) VALUES (:NEW.Codigo, :NEW.Texto);
    INSERT INTO MENSAJES_TIPO (Codigo, Tipo) VALUES (:NEW.Codigo, :NEW.Tipo);
END;
/

CREATE TABLE MENSAJES_BORRADOS AS SELECT * FROM MENSAJES_TEXTO WHERE 1=0;

CREATE OR REPLACE TRIGGER trg_mensajes_borrados
AFTER DELETE ON MENSAJES_TEXTO
FOR EACH ROW
BEGIN
    INSERT INTO MENSAJES_BORRADOS VALUES (:OLD.Codigo, :OLD.Texto);
END;
/

BEGIN
    DBMS_SCHEDULER.create_job (
        job_name          => 'BORRAR_MENSAJES_BORRADOS',
        job_type          => 'PLSQL_BLOCK',
        job_action        => 'BEGIN DELETE FROM MENSAJES_BORRADOS; END;',
        start_date        => SYSTIMESTAMP,
        repeat_interval   => 'FREQ=MINUTELY; INTERVAL=2',
        enabled           => TRUE
    );
END;
/








