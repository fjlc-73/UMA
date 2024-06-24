--PRIMERA PARTE
--APARTADO 1
/* Usuario lifefit y tablespace ts_lifefit ya est�n creados en las anteriores pr�cticas.*/
--Desde system
grant create table to lifefit;
grant create view to lifefit;
grant create materialized view to lifefit;
grant create sequence to lifefit;
grant create procedure to lifefit;
grant create trigger to lifefit;

create TABLESPACE TS_INDICES datafile 'indices.dbf' size 50M autoextend on;
alter user lifefit quota 25M on ts_indices;
select * from dba_tablespaces where tablespace_name like 'TS%';
select username,default_tablespace from dba_users where username='LIFEFIT';
select * from dba_data_files where tablespace_name like 'TS%';

--APARTADO 2
--Desde lifefit
CREATE TABLE centro (
    id        NUMBER NOT NULL,
    nombre    VARCHAR2(200) NOT NULL,
    direccion VARCHAR2(200),
    cpostal   NUMBER
);

ALTER TABLE centro ADD CONSTRAINT centro_pk PRIMARY KEY ( id );

CREATE TABLE cita (
    fechayhora VARCHAR2(200) NOT NULL,
    id         NUMBER NOT NULL,
    modalidad  VARCHAR2(200),
    cliente_id NUMBER NOT NULL
);

ALTER TABLE cita ADD CONSTRAINT cita_pk PRIMARY KEY ( fechayhora,
                                                      id );

ALTER TABLE cita ADD CONSTRAINT cita_pkv1 UNIQUE ( cliente_id );

CREATE TABLE cliente (
    id           NUMBER NOT NULL,
    objetivo     VARCHAR2(200) NOT NULL,
    preferencias VARCHAR2(200),
    dieta_id     NUMBER,
    centro_id    NUMBER NOT NULL
);

ALTER TABLE cliente ADD CONSTRAINT cliente_pk PRIMARY KEY ( id );

CREATE TABLE conforman (
    series       NUMBER,
    repeticiones NUMBER,
    duracion     NUMBER,
    rutina_id    NUMBER NOT NULL,
    ejercicio_id NUMBER NOT NULL
);

ALTER TABLE conforman ADD CONSTRAINT conforman_pk PRIMARY KEY ( rutina_id,
                                                                ejercicio_id );

CREATE TABLE dieta (
    id          NUMBER NOT NULL,
    nombre      VARCHAR2(200) NOT NULL,
    descripcion VARCHAR2(200),
    tipo        VARCHAR2(200)
);

ALTER TABLE dieta ADD CONSTRAINT dieta_pk PRIMARY KEY ( id );

ALTER TABLE dieta ADD CONSTRAINT dieta_nombre_un UNIQUE ( nombre );

CREATE TABLE ejercicio (
    id          NUMBER NOT NULL,
    nombre      VARCHAR2(30) NOT NULL,
    descripcion VARCHAR2(300),
    video       VARCHAR2(200),
    imagen      VARCHAR2(200)
);

ALTER TABLE ejercicio ADD CONSTRAINT ejercicio_pk PRIMARY KEY ( id );

CREATE TABLE elementocalendario (
    fechayhora    VARCHAR2(200) NOT NULL,
    entrenador_id NUMBER NOT NULL
);

ALTER TABLE elementocalendario ADD CONSTRAINT elementocalendario_pk PRIMARY KEY ( fechayhora,
                                                                                  entrenador_id );

CREATE TABLE entrena (
    especialidad  VARCHAR2(200),
    entrenador_id NUMBER NOT NULL,
    cliente_id    NUMBER NOT NULL
);

ALTER TABLE entrena ADD CONSTRAINT entrena_pk PRIMARY KEY ( entrenador_id,
                                                            cliente_id );

CREATE TABLE entrenador (
    id             NUMBER NOT NULL,
    disponibilidad VARCHAR2(200),
    centro_id      NUMBER NOT NULL
);

ALTER TABLE entrenador ADD CONSTRAINT entrenador_pk PRIMARY KEY ( id );

CREATE TABLE gerente (
    id        NUMBER NOT NULL,
    despacho  VARCHAR2(200),
    horario   VARCHAR2(200),
    centro_id NUMBER NOT NULL
);

CREATE UNIQUE INDEX gerente__idx ON
    gerente (
        centro_id
    ASC );

ALTER TABLE gerente ADD CONSTRAINT gerente_pk PRIMARY KEY ( id );

CREATE TABLE plan (
    inicio                VARCHAR2(200) NOT NULL,
    fin                   VARCHAR2(200),
    entrena_entrenador_id NUMBER NOT NULL,
    entrena_cliente_id    NUMBER NOT NULL,
    rutina_id             NUMBER NOT NULL
);

ALTER TABLE plan
    ADD CONSTRAINT plan_pk PRIMARY KEY ( inicio,
                                         entrena_entrenador_id,
                                         entrena_cliente_id,
                                         rutina_id );

CREATE TABLE rutina (
    id          NUMBER NOT NULL,
    nombre      VARCHAR2(30) NOT NULL,
    descripcion VARCHAR2(200)
);

ALTER TABLE rutina ADD CONSTRAINT rutina_pk PRIMARY KEY ( id );

CREATE TABLE sesion (
    inicio                      VARCHAR2(200) NOT NULL,
    fin                         VARCHAR2(200),
    presencial                  CHAR(1),
    descripcion                 VARCHAR2(200),
    video                       VARCHAR2(200),
    datos_salud                 VARCHAR2(200),
    plan_inicio                 VARCHAR2(200)
     NOT NULL,
    plan_entrena_entrenador_id  NUMBER NOT NULL,
    plan_entrena_cliente_id     NUMBER NOT NULL,
    plan_rutina_id              NUMBER NOT NULL,
    plan_inicio1                VARCHAR2(200 CHAR) NOT NULL,
    plan_entrena_entrenador_id1 NUMBER NOT NULL,
    plan_entrena_cliente_id1    NUMBER NOT NULL,
    plan_rutina_id1             NUMBER NOT NULL
);

ALTER TABLE sesion
    ADD CONSTRAINT sesion_pk PRIMARY KEY ( inicio,
                                           plan_inicio,
                                           plan_entrena_entrenador_id,
                                           plan_entrena_cliente_id,
                                           plan_rutina_id );

CREATE TABLE usuario (
    id            NUMBER NOT NULL,
    nombre        VARCHAR2(200) NOT NULL,
    apellidos     VARCHAR2(200) NOT NULL,
    telefono      VARCHAR2(200) NOT NULL,
    direccion     VARCHAR2(200),
    correoe       VARCHAR2(200),
    usuariooracle VARCHAR2(200)
);

ALTER TABLE usuario ADD CONSTRAINT usuario_pk PRIMARY KEY ( id );

ALTER TABLE cita
    ADD CONSTRAINT cita_cliente_fk FOREIGN KEY ( cliente_id )
        REFERENCES cliente ( id );

ALTER TABLE cita
    ADD CONSTRAINT cita_elementocalendario_fk FOREIGN KEY ( fechayhora,
                                                            id )
        REFERENCES elementocalendario ( fechayhora,
                                        entrenador_id );

ALTER TABLE cliente
    ADD CONSTRAINT cliente_centro_fk FOREIGN KEY ( centro_id )
        REFERENCES centro ( id );

ALTER TABLE cliente
    ADD CONSTRAINT cliente_dieta_fk FOREIGN KEY ( dieta_id )
        REFERENCES dieta ( id );

ALTER TABLE cliente
    ADD CONSTRAINT cliente_usuario_fk FOREIGN KEY ( id )
        REFERENCES usuario ( id );

ALTER TABLE conforman
    ADD CONSTRAINT conforman_ejercicio_fk FOREIGN KEY ( ejercicio_id )
        REFERENCES ejercicio ( id );

ALTER TABLE conforman
    ADD CONSTRAINT conforman_rutina_fk FOREIGN KEY ( rutina_id )
        REFERENCES rutina ( id );

ALTER TABLE elementocalendario
    ADD CONSTRAINT elemcalen_entren_fk FOREIGN KEY ( entrenador_id )
        REFERENCES entrenador ( id );

ALTER TABLE entrena
    ADD CONSTRAINT entrena_cliente_fk FOREIGN KEY ( cliente_id )
        REFERENCES cliente ( id );

ALTER TABLE entrena
    ADD CONSTRAINT entrena_entrenador_fk FOREIGN KEY ( entrenador_id )
        REFERENCES entrenador ( id );

ALTER TABLE entrenador
    ADD CONSTRAINT entrenador_centro_fk FOREIGN KEY ( centro_id )
        REFERENCES centro ( id );

ALTER TABLE entrenador
    ADD CONSTRAINT entrenador_usuario_fk FOREIGN KEY ( id )
        REFERENCES usuario ( id );

ALTER TABLE gerente
    ADD CONSTRAINT gerente_centro_fk FOREIGN KEY ( centro_id )
        REFERENCES centro ( id );

ALTER TABLE gerente
    ADD CONSTRAINT gerente_usuario_fk FOREIGN KEY ( id )
        REFERENCES usuario ( id );

ALTER TABLE plan
    ADD CONSTRAINT plan_entrena_fk FOREIGN KEY ( entrena_entrenador_id,
                                                 entrena_cliente_id )
        REFERENCES entrena ( entrenador_id,
                             cliente_id );

ALTER TABLE plan
    ADD CONSTRAINT plan_rutina_fk FOREIGN KEY ( rutina_id )
        REFERENCES rutina ( id );

ALTER TABLE sesion
    ADD CONSTRAINT sesion_plan_fk FOREIGN KEY ( plan_inicio1,
                                                plan_entrena_entrenador_id1,
                                                plan_entrena_cliente_id1,
                                                plan_rutina_id1 )
        REFERENCES plan ( inicio,
                          entrena_entrenador_id,
                          entrena_cliente_id,
                          rutina_id );

CREATE OR REPLACE TRIGGER arc_fkarc_1_gerente BEFORE
    INSERT OR UPDATE OF id ON gerente
    FOR EACH ROW
DECLARE
    d NUMBER;
BEGIN
    SELECT
        a.id
    INTO d
    FROM
        usuario a
    WHERE
        a.id = :new.id;

    IF ( d IS NULL OR d <> gerente ) THEN
        raise_application_error(-20223, 'FK Gerente_Usuario_FK in Table Gerente violates Arc constraint on Table Usuario - discriminator column ID doesn''t have value Gerente'
        );
    END IF;

EXCEPTION
    WHEN no_data_found THEN
        NULL;
    WHEN OTHERS THEN
        RAISE;
END;
/

CREATE OR REPLACE TRIGGER arc_fkarc_1_entrenador BEFORE
    INSERT OR UPDATE OF id ON entrenador
    FOR EACH ROW
DECLARE
    d NUMBER;
BEGIN
    SELECT
        a.id
    INTO d
    FROM
        usuario a
    WHERE
        a.id = :new.id;

    IF ( d IS NULL OR d <> entrenador ) THEN
        raise_application_error(-20223, 'FK Entrenador_Usuario_FK in Table Entrenador violates Arc constraint on Table Usuario - discriminator column ID doesn''t have value Entrenador'
        );
    END IF;

EXCEPTION
    WHEN no_data_found THEN
        NULL;
    WHEN OTHERS THEN
        RAISE;
END;
/

CREATE OR REPLACE TRIGGER arc_fkarc_1_cliente BEFORE
    INSERT OR UPDATE OF id ON cliente
    FOR EACH ROW
DECLARE
    d NUMBER;
BEGIN
    SELECT
        a.id
    INTO d
    FROM
        usuario a
    WHERE
        a.id = :new.id;

    IF ( d IS NULL OR d <> cliente ) THEN
        raise_application_error(-20223, 'FK Cliente_Usuario_FK in Table Cliente violates Arc constraint on Table Usuario - discriminator column ID doesn''t have value Cliente'
        );
    END IF;

EXCEPTION
    WHEN no_data_found THEN
        NULL;
    WHEN OTHERS THEN
        RAISE;
END;
/

--A continuaci�n las sentencias por si necesitamos borrar el esquema

DROP TABLE centro CASCADE CONSTRAINTS;

DROP TABLE cita CASCADE CONSTRAINTS;

DROP TABLE cliente CASCADE CONSTRAINTS;

DROP TABLE conforman CASCADE CONSTRAINTS;

DROP TABLE dieta CASCADE CONSTRAINTS;

DROP TABLE ejercicio CASCADE CONSTRAINTS;

DROP TABLE elementocalendario CASCADE CONSTRAINTS;

DROP TABLE entrena CASCADE CONSTRAINTS;

DROP TABLE entrenador CASCADE CONSTRAINTS;

DROP TABLE gerente CASCADE CONSTRAINTS;

DROP TABLE plan CASCADE CONSTRAINTS;

DROP TABLE rutina CASCADE CONSTRAINTS;

DROP TABLE sesion CASCADE CONSTRAINTS;

DROP TABLE usuario CASCADE CONSTRAINTS;

--Cambiamos el tablespace de los �ndices

select * from dba_indexes where table_owner='LIFEFIT';
select 'alter index ' || index_name || ' rebuild tablespace ts_indices;' from dba_indexes where table_owner='LIFEFIT';

alter index CENTRO_PK rebuild tablespace ts_indices;
alter index CITA_PK rebuild tablespace ts_indices;
alter index CITA_PKV1 rebuild tablespace ts_indices;
alter index CLIENTE_PK rebuild tablespace ts_indices;
alter index CONFORMAN_PK rebuild tablespace ts_indices;
alter index DIETA_PK rebuild tablespace ts_indices;
alter index DIETA_NOMBRE_UN rebuild tablespace ts_indices;
alter index EJERCICIO_PK rebuild tablespace ts_indices;
alter index ELEMENTOCALENDARIO_PK rebuild tablespace ts_indices;
alter index ENTRENA_PK rebuild tablespace ts_indices;
alter index ENTRENADOR_PK rebuild tablespace ts_indices;
alter index GERENTE__IDX rebuild tablespace ts_indices;
alter index GERENTE_PK rebuild tablespace ts_indices;
alter index PLAN_PK rebuild tablespace ts_indices;
alter index RUTINA_PK rebuild tablespace ts_indices;
alter index SESION_PK rebuild tablespace ts_indices
alter index USUARIO_PK rebuild tablespace ts_indices

--APARTADO 3
--Datos importados con �xito

--APARTADO 4
--Desde system
create or replace directory directorio_ext as 'C:\app\alumnos\admin\orcl\dpdump';
grant read, write on directory directorio_ext to LIFEFIT;

--Desde lifefit
create table ejercicios_ext
 (nombre VARCHAR(300), descripcion VARCHAR(300),video VARCHAR(300))
ORGANIZATION EXTERNAL (
 TYPE ORACLE_LOADER
 DEFAULT DIRECTORY directorio_ext
 ACCESS PARAMETERS (
 RECORDS DELIMITED BY NEWLINE
 skip 1
 CHARACTERSET UTF8
 FIELDS TERMINATED BY ';'
 OPTIONALLY ENCLOSED BY '"'
 MISSING FIELD VALUES ARE NULL
 (nombre, descripcion, video)
 )
 LOCATION ('Ejercicios.csv')
 );
 
 SELECT * FROM ejercicios_ext;
 
 --APARTADO 5
--Desde lifefit
select * from user_constraints where table_name='USUARIO' and constraint_type='P';//Hay clave primaria

create index index_correo on usuario(correoe);
alter index index_correo rebuild tablespace ts_indices;
create index index_telefono on usuario(telefono);
alter index index_telefono rebuild tablespace ts_indices;
create index index_apellidos on usuario (UPPER(apellidos));
alter index index_apellidos rebuild tablespace ts_indices;
create index index_apellido_nombre on usuario (apellidos,nombre);
alter index index_apellido_nombre rebuild tablespace ts_indices;

select * from user_indexes where table_name='USUARIO';//�ndices creados correctamente
select table_name,tablespace_name from user_tables where table_name='USUARIO';//En ts_lifefit
select index_name,tablespace_name from user_indexes where table_name='USUARIO';//En ts_indices

create bitmap index bindex_centroid on cliente(centro_id);
alter index bindex_centroid rebuild tablespace ts_indices;
select index_name, index_type from user_indexes where index_name='BINDEX_CENTROID'; //Es de tipo bitmap

--APARTADO 6
--Desde lifefit
create materialized view VM_EJERCICIOS refresh force
start with sysdate next trunc(sysdate)+1 as (select * from ejercicios_ext);

--APARTADO 7
--Desde system
grant create public synonym to lifefit;
--Desde lifefit
create public synonym S_EJERCICIOS for VM_EJERCICIOS;

--APARTADO 8
--Desde system
grant create sequence to lifefit;
--Desde lifefit
create sequence seq_ejercicios;

create or replace trigger tr_EJERCICIOS
before insert on EJERCICIO for each row
begin
if :new.ID is null then
 :new.ID := SEQ_EJERCICIOS.NEXTVAL;
end if;
END tr_EJERCICIOS;
/

insert into ejercicio (nombre,descripcion,video)
SELECT
 nombre,descripcion,video
FROM S_EJERCICIOS;

--SEGUNDA PARTE-----------------------------------------------------------------------------------------------------
--APARTADO 1
--desde lifefit
grant select on ejercicio to entrenador_deporte_fitness;
grant insert on ejercicio to entrenador_deporte_fitness;
grant update on ejercicio to entrenador_deporte_fitness;
grant delete on ejercicio to entrenador_deporte_fitness;

alter table ejercicio add (PUBLICO CHAR(1) DEFAULT 'S');

create view VEJERCICIO as (select * from ejercicio where PUBLICO='S');

--Ya se insertaron los datos de ejercicio_ext en ejercicio en la anterior pr�ctica

--APARTADO 2
--desde lifefit
grant select on rutina to entrenador_deporte_fitness;
grant insert on rutina to entrenador_deporte_fitness;
grant update on rutina to entrenador_deporte_fitness;
grant delete on rutina to entrenador_deporte_fitness;

grant select on conforman to entrenador_deporte_fitness;
grant insert on conforman to entrenador_deporte_fitness;
grant update on conforman to entrenador_deporte_fitness;
grant delete on conforman to entrenador_deporte_fitness;

--APARTADO 3
--desde lifefit
grant select on entrena to gerente;
grant insert on entrena to gerente;
grant update on entrena to gerente;
grant delete on entrena to gerente;

--APARTADO 4
--desde lifefit
grant select on plan to entrenador_deporte_fitness;
grant insert on plan to entrenador_deporte_fitness;
grant update on plan to entrenador_deporte_fitness;
grant delete on plan to entrenador_deporte_fitness;

grant select on sesion to entrenador_deporte_fitness;
grant insert on sesion to entrenador_deporte_fitness;
grant update on sesion to entrenador_deporte_fitness;
grant delete on sesion to entrenador_deporte_fitness;

--PRACTICA 2:-----------------------------------------------------------------------------------------------------------------
/*1. RF5. Control del cliente de sus sesiones de
entrenamiento

- Visualizaci�n de su sesi�n de entrenamiento actual. Utilizar alg�n mecanismo de seguridad
de los vistos
- Gesti�n de estado personal del cliente (responsabilidad del cliente)
- informar sobre entrenamientos hechos / parciales / saltados
- Cliente puede subir a la plataforma un enlace en Youtube a un v�deo con una
grabaci�n de un entrenamiento suyo (y borrarlo cuando quiera). Por simplicidad se
trata solo de eso, de guardar la url del video (modificaci�n del atributo video de la
tabla SESION, pero s�lo de las sesiones del cliente conectado).
- Actualizaci�n del perfil:
- actualizar datos sobre indicadores de salud
- cambiar objetivos y preferencias (las suyas)*/

CREATE OR REPLACE VIEW v_sesioncliente AS
SELECT s.inicio, s.fin, s.presencial, s.descripcion, s.video, s.datos_salud, p.inicio as inicio_plan, e.id as entrenador_id, u.id as cliente_id, r.id as rutina_id
FROM sesion s
JOIN usuario u ON s.plan_entrena_cliente_id = u.id
JOIN plan p ON s.plan_inicio = p.inicio
JOIN entrenador e ON s.plan_entrena_entrenador_id=e.id
JOIN rutina r ON s.plan_rutina_id=r.id
WHERE u.usuariooracle = USER;

grant update, delete, insert, select on v_sesioncliente to cliente;

create or replace TRIGGER Control_videos
INSTEAD OF INSERT OR UPDATE OR DELETE ON v_sesioncliente
FOR EACH ROW
BEGIN
    IF INSERTING THEN
        INSERT INTO SESION VALUES (:NEW.INICIO,:NEW.FIN, 
        :NEW.PRESENCIAL, :NEW.DESCRIPCION,:NEW.VIDEO, :NEW.datos_salud, 
        :NEW.INICIO_PLAN, :NEW.ENTRENADOR_ID, :NEW.CLIENTE_ID, :NEW.RUTINA_ID);
    ELSIF UPDATING THEN
        UPDATE SESION SET 
        INICIO=:NEW.INICIO, PRESENCIAL=:NEW.PRESENCIAL, DESCRIPCION=:NEW.DESCRIPCION,VIDEO=:NEW.VIDEO, 
        DATOS_SALUD=:NEW.datos_salud, PLAN_INICIO=:NEW.INICIO_PLAN, PLAN_ENTRENA_ENTRENADOR_ID=:NEW.ENTRENADOR_ID, 
        PLAN_ENTRENA_CLIENTE_ID=:NEW.CLIENTE_ID, PLAN_RUTINA_ID=:NEW.RUTINA_ID
        WHERE UPPER(INICIO) = UPPER(:OLD.INICIO)
        and sesion.plan_entrena_cliente_id=:OLD.CLIENTE_ID 
        and sesion.plan_entrena_entrenador_id=:OLD.ENTRENADOR_ID
        and UPPER(PLAN_INICIO) = UPPER(:OLD.INICIO_PLAN)
        and sesion.plan_rutina_id=:OLD.RUTINA_ID;
    ELSIF DELETING THEN
        DELETE FROM SESION WHERE UPPER(INICIO) = UPPER(:OLD.INICIO) 
        and sesion.plan_entrena_cliente_id=:OLD.CLIENTE_ID 
        and sesion.plan_entrena_entrenador_id=:OLD.ENTRENADOR_ID
        and UPPER(PLAN_INICIO) = UPPER(:OLD.INICIO_PLAN)
        and sesion.plan_rutina_id=:OLD.RUTINA_ID;
    END IF;
END Control_videos;
/

SELECT * FROM SESION;
SELECT * FROM V_SESIONCLIENTE;
select * from v_datoscliente;

create or replace view v_datoscliente as
select c.id,c.objetivo,c.preferencias,c.dieta_id,c.centro_id
from cliente c
join usuario u on c.id=u.id
where u.usuariooracle=user;

grant update(OBJETIVO, PREFERENCIAS) on v_datoscliente to cliente;

/*RF6 Seguimiento de entrenamientos (entrenador)
- Entrenador personal puede ver los v�deos de entrenamiento que le han enviado los
clientes (los que tiene asignados).
- Gesti�n del estado del cliente (visualizaci�n de datos hist�ricos):
- Trabajo realizado,
- datos f�sicos
- comprobaci�n de objetivos
*/

create or replace view v_sesiones_clientes_entrenador AS
SELECT U_CLIENTE.NOMBRE, S.VIDEO, S.DESCRIPCION, S.DATOS_SALUD, C.OBJETIVO
FROM SESION S 
JOIN USUARIO U_ENTRENADOR ON S.PLAN_ENTRENA_ENTRENADOR_ID = U_ENTRENADOR.ID
JOIN USUARIO U_CLIENTE ON S.PLAN_ENTRENA_CLIENTE_ID = U_CLIENTE.ID
JOIN CLIENTE C ON S.PLAN_ENTRENA_CLIENTE_ID = C.ID
WHERE U_ENTRENADOR.USUARIOORACLE = USER;

GRANT SELECT ON V_SESIONES_CLIENTES_ENTRENADOR TO ENTRENADOR_DEPORTE_FITNESS;

select * from v_sesiones_clientes_entrenador;

/*RF7 Gesti�n de citas:
- Cliente puede pedir / anular / cambiar cita con su entrenador (solo una petici�n activa a la vez).
- Entrenador puede ver la lista de citas (confirmadas, pendientes).
- Entrenador puede cambiar estado de cita (confirmar, anular, cambiar fecha u hora.*/

create or replace view v_citascliente as 
SELECT U_ENTRENADOR.NOMBRE AS NOMBRE_ENTRENADOR, U_ENTRENADOR.ID AS ENTRENADOR_ID, U_CLIENTE.ID AS CLIENTE_ID, C.FECHAYHORA, C.MODALIDAD
FROM CITA C
JOIN USUARIO U_CLIENTE ON U_CLIENTE.ID = C.CLIENTE_ID
JOIN USUARIO U_ENTRENADOR ON C.ENTRENADOR_ID = U_ENTRENADOR.ID
WHERE U_CLIENTE.USUARIOORACLE = USER;

SELECT * FROM V_CITASCLIENTE;

GRANT SELECT, DELETE, UPDATE, INSERT ON V_CITASCLIENTE TO CLIENTE;
grant select, update(FECHAYHORA) on v_citascliente to ENTRENADOR_DEPORTE_FITNESS;
grant select, update(FECHAYHORA) on v_citascliente to ENTRENADOR_NUTRICION;

CREATE OR REPLACE TRIGGER Control_cita
INSTEAD OF INSERT OR UPDATE OR DELETE ON v_citascliente
FOR EACH ROW
DECLARE
    numfilas NUMBER;
BEGIN
    IF INSERTING THEN
        SELECT COUNT(*)
        INTO numfilas
        FROM CITA
        WHERE ENTRENADOR_ID = :NEW.ENTRENADOR_ID
        AND CLIENTE_ID = :NEW.CLIENTE_ID;

        IF numfilas < 1 THEN
            INSERT INTO CITA VALUES(:NEW.FECHAYHORA, :NEW.ENTRENADOR_ID, :NEW.MODALIDAD, :NEW.CLIENTE_ID);
        ELSE
            RAISE_APPLICATION_ERROR(-20001, 'La inserci�n no se realiz� porque ya existe una cita para este entrenador y cliente.');
        END IF;
    
    ELSIF UPDATING THEN
        UPDATE CITA SET 
        FECHAYHORA = :NEW.FECHAYHORA, 
        ENTRENADOR_ID = :NEW.ENTRENADOR_ID, 
        MODALIDAD = :NEW.MODALIDAD,
        CLIENTE_ID = :NEW.CLIENTE_ID
        WHERE FECHAYHORA = :OLD.FECHAYHORA
        AND ENTRENADOR_ID = :OLD.ENTRENADOR_ID
        AND MODALIDAD = :OLD.MODALIDAD
        AND CLIENTE_ID = :OLD.CLIENTE_ID;

    ELSIF DELETING THEN
        DELETE FROM CITA WHERE fechayhora = :OLD.FECHAYHORA
        AND entrenador_id = :OLD.ENTRENADOR_ID;
        
        -- Otros procesos o acciones que desees realizar en conjunto con la eliminaci�n de la cita.
        -- Puedes a�adirlos aqu�.
    END IF;
END Control_cita
/


--PRACTICA PL-SQL:
--package BASE:
create or replace PACKAGE         BASE AS

    TYPE TCLIENTE IS RECORD (
        NOMBRE USUARIO.NOMBRE%TYPE,
        APELLIDOS USUARIO.APELLIDOS%TYPE,
        TELEFONO USUARIO.TELEFONO%TYPE,
        DIRECCION USUARIO.DIRECCION%TYPE,
        CORREOE USUARIO.CORREOE%TYPE,
        OBJETIVOS CLIENTE.OBJETIVO%TYPE,
        DIETA CLIENTE.DIETA_ID%TYPE,
        PREFERENCIAS CLIENTE.PREFERENCIAS%TYPE,
        CENTRO CLIENTE.CENTRO_ID%TYPE
    );

    PROCEDURE CREA_CLIENTE(
        P_DATOS IN TCLIENTE,
        P_USERPASS IN VARCHAR2,
        P_USUARIO OUT USUARIO%ROWTYPE,
        P_CLIENTE OUT CLIENTE%ROWTYPE
    );

    TYPE TENTRENADOR IS RECORD (
        NOMBRE USUARIO.NOMBRE%TYPE,
        APELLIDOS USUARIO.APELLIDOS%TYPE,
        TELEFONO USUARIO.TELEFONO%TYPE,
        DIRECCION USUARIO.DIRECCION%TYPE,
        CORREOE USUARIO.CORREOE%TYPE,
        DISPONIBILIDAD ENTRENADOR.DISPONIBILIDAD%TYPE,
        CENTRO ENTRENADOR.CENTRO_ID%TYPE
    );

    PROCEDURE CREA_ENTRENADOR(
        P_DATOS IN TENTRENADOR,
        P_USERPASS IN VARCHAR2,
        P_USUARIO OUT USUARIO%ROWTYPE,
        P_ENTRENADOR OUT ENTRENADOR%ROWTYPE
    );

    TYPE TGERENTE IS RECORD (
        NOMBRE USUARIO.NOMBRE%TYPE,
        APELLIDOS USUARIO.APELLIDOS%TYPE,
        TELEFONO USUARIO.TELEFONO%TYPE,
        DIRECCION USUARIO.DIRECCION%TYPE,
        CORREOE USUARIO.CORREOE%TYPE,
        DESPACHO GERENTE.DESPACHO%TYPE,
        HORARIO GERENTE.HORARIO%TYPE,
        CENTRO GERENTE.CENTRO_ID%TYPE
        );
    PROCEDURE CREA_GERENTE(
        P_DATOS IN TGERENTE,
        P_USERPASS IN VARCHAR2,
        P_USUARIO OUT USUARIO%ROWTYPE,
        P_GERENTE OUT GERENTE%ROWTYPE
        );

    PROCEDURE ELIMINA_USER(
        P_ID USUARIO.ID%TYPE
        );

    PROCEDURE ELIMINA_CLIENTE(
        P_ID USUARIO.ID%TYPE
        );

    PROCEDURE ELIMINA_GERENTE(
        P_ID USUARIO.ID%TYPE
        );

    PROCEDURE ELIMINA_ENTRENADOR(
        P_ID USUARIO.ID%TYPE
        );

    PROCEDURE ELIMINA_CENTRO(
         P_ID CENTRO.ID%TYPE
         );

    EXCEPCION_CREACION EXCEPTION;
    EXCEPCION_MODIFICACION EXCEPTION;
    EXCEPCION_ELIMINACION EXCEPTION;
    EXCEPCION_LECTURA EXCEPTION;

END BASE;


--cuerpo del paquete BASE:
create or replace PACKAGE BODY         BASE AS

    PROCEDURE ejecutar_dml (
        p_dml IN VARCHAR2,
        p_exitoso IN OUT BOOLEAN
    ) AS
    PRAGMA AUTONOMOUS_TRANSACTION;
    BEGIN
        -- Intentar ejecutar la sentencia DML
        BEGIN
            EXECUTE IMMEDIATE p_dml;
            -- p_exitoso permanece con el mismo valor que al entrar.
        EXCEPTION
            WHEN OTHERS THEN
                p_exitoso := FALSE;  -- Error
        END;
    END  ejecutar_dml;

    PROCEDURE CrearUsuario(
        p_Nombre IN VARCHAR2,
        p_Apellidos IN VARCHAR2,
        p_Telefono IN VARCHAR2,
        p_Direccion IN VARCHAR2,
        p_Correoe IN VARCHAR2,
        p_UserPass IN VARCHAR2,
        p_Usuario OUT USUARIO%ROWTYPE
    ) IS
        PRAGMA AUTONOMOUS_TRANSACTION;
        v_Id USUARIO.ID%TYPE;
    BEGIN
    BEGIN
        INSERT INTO USUARIO 
        VALUES (seq_usuario.nextval, p_Nombre, p_Apellidos, p_Telefono, p_Direccion, p_Correoe, p_Nombre||p_Apellidos)
        RETURNING ID INTO v_Id;
        
        EXCEPTION
        WHEN OTHERS THEN
            ROLLBACK;
            RAISE EXCEPCION_CREACION;
            
    END;

    BEGIN
        SELECT * INTO p_Usuario
        FROM USUARIO
        WHERE ID = v_Id;
        
        EXCEPTION
        WHEN OTHERS THEN
            ROLLBACK;
            RAISE EXCEPCION_LECTURA;
    END;
        
    BEGIN

        EXECUTE IMMEDIATE 'CREATE USER ' || p_Usuario.USUARIOORACLE || ' IDENTIFIED BY ' || p_UserPass;
        
        EXCEPTION
            WHEN OTHERS THEN
                ROLLBACK;
                RAISE EXCEPCION_CREACION;
    END;
    END CrearUsuario;

    PROCEDURE CREA_CLIENTE(
        P_DATOS IN TCLIENTE,
        P_USERPASS IN VARCHAR2,
        P_USUARIO OUT USUARIO%ROWTYPE,
        P_CLIENTE OUT CLIENTE%ROWTYPE
    ) IS
        v_SavepointName VARCHAR2(30) := 'sp_creacliente';
        v_exitoso BOOLEAN := TRUE;
        v_Id USUARIO.ID%TYPE;
    BEGIN
        SAVEPOINT v_SavepointName;
        
        BEGIN
            INSERT INTO USUARIO 
            VALUES (seq_usuario.nextval, P_DATOS.nombre, P_DATOS.apellidos, P_DATOS.telefono, P_DATOS.direccion, P_DATOS.correoe, P_DATOS.nombre||P_DATOS.apellidos)
            RETURNING ID INTO v_Id;
        
            EXCEPTION
            WHEN OTHERS THEN
                ROLLBACK TO v_SavepointName;
                RAISE EXCEPCION_CREACION;           
        END;
        
        BEGIN
            SELECT * INTO P_USUARIO
            FROM USUARIO
            WHERE ID = v_Id;
        
            EXCEPTION
            WHEN OTHERS THEN
                ROLLBACK TO v_SavepointName;
                RAISE EXCEPCION_LECTURA;
        END;
        
        BEGIN
            INSERT INTO CLIENTE
            VALUES (v_Id, P_DATOS.objetivos, P_DATOS.preferencias, P_DATOS.DIETA, P_DATOS.CENTRO);

            EXCEPTION
            WHEN OTHERS THEN
                ROLLBACK TO v_SavepointName;
                RAISE EXCEPCION_CREACION;
        END;

        BEGIN

            SELECT * INTO P_CLIENTE
            FROM CLIENTE
            WHERE ID = v_Id;

            EXCEPTION
            WHEN OTHERS THEN
                ROLLBACK TO v_SavepointName;
                RAISE EXCEPCION_LECTURA;
        END;
        
        BEGIN
            ejecutar_dml('CREATE USER ' || P_USUARIO.USUARIOORACLE || ' IDENTIFIED BY ' || P_USERPASS, v_exitoso);
            ejecutar_dml('GRANT CONNECT, RESOURCE TO ' || P_USUARIO.USUARIOORACLE, v_exitoso);
            ejecutar_dml('GRANT CLIENTE TO ' || P_USUARIO.USUARIOORACLE, v_exitoso);
            ejecutar_dml('CREATE SYNONYM ' || P_USUARIO.USUARIOORACLE || '.CLIENTE FOR LIFEFIT.V_DATOSCLIENTE', v_exitoso);
            ejecutar_dml('CREATE SYNONYM ' || P_USUARIO.USUARIOORACLE || '.SESION FOR LIFEFIT.V_SESIONCLIENTE', v_exitoso);
            ejecutar_dml('CREATE SYNONYM ' || P_USUARIO.USUARIOORACLE || '.CITA FOR LIFEFIT.V_CITASCLIENTE', v_exitoso);
            ejecutar_dml('CREATE SYNONYM ' || P_USUARIO.USUARIOORACLE || '.EJERCICIO FOR LIFEFIT.VEJERCICIO', v_exitoso);
            ejecutar_dml('CREATE SYNONYM ' || P_USUARIO.USUARIOORACLE || '.DIETA FOR LIFEFIT.V_DIETACLIENTE', v_exitoso);
			ejecutar_dml('CREATE SYNONYM ' || P_USUARIO.USUARIOORACLE || '.USUARIO FOR LIFEFIT.USUARIO', v_exitoso);
            
            IF not v_exitoso then
                ejecutar_dml('DROP USER ' || P_USUARIO.USUARIOORACLE || ' CASCADE',v_exitoso);
                ROLLBACK TO v_SavepointName;
                RAISE EXCEPCION_CREACION;
            END IF;
        END;

        COMMIT;
    END CREA_CLIENTE;


    PROCEDURE CREA_ENTRENADOR(
        P_DATOS IN TENTRENADOR,
        P_USERPASS IN VARCHAR2,
        P_USUARIO OUT USUARIO%ROWTYPE,
        P_ENTRENADOR OUT ENTRENADOR%ROWTYPE
    ) IS
        v_SavepointName VARCHAR2(30) := 'spEntrenador';
        v_exitoso BOOLEAN := TRUE;
        v_Id USUARIO.ID%TYPE;
    BEGIN
        SAVEPOINT v_SavepointName;

    BEGIN
            INSERT INTO USUARIO 
            VALUES (seq_usuario.nextval, P_DATOS.nombre, P_DATOS.apellidos, P_DATOS.telefono, P_DATOS.direccion, P_DATOS.correoe, P_DATOS.nombre||P_DATOS.apellidos)
            RETURNING ID INTO v_Id;
        
            EXCEPTION
            WHEN OTHERS THEN
                ROLLBACK TO v_SavepointName;
                RAISE EXCEPCION_CREACION;           
    END;
        
    BEGIN
            SELECT * INTO P_USUARIO
            FROM USUARIO
            WHERE ID = v_Id;
        
            EXCEPTION
            WHEN OTHERS THEN
                ROLLBACK TO v_SavepointName;
                RAISE EXCEPCION_LECTURA;
    END;
        
    BEGIN
            INSERT INTO ENTRENADOR 
            VALUES (v_Id, P_DATOS.disponibilidad, P_DATOS.centro);

            EXCEPTION
            WHEN OTHERS THEN
            ROLLBACK TO v_SavepointName;
            RAISE EXCEPCION_CREACION;
    END;

    BEGIN

        SELECT * INTO p_Entrenador
        FROM ENTRENADOR
        WHERE ID = v_Id;

        EXCEPTION
        WHEN OTHERS THEN
            ROLLBACK TO v_SavepointName;
            RAISE EXCEPCION_LECTURA;

    END;
        
    BEGIN
            ejecutar_dml('CREATE USER ' || P_USUARIO.USUARIOORACLE || ' IDENTIFIED BY ' || P_USERPASS, v_exitoso);
            ejecutar_dml('GRANT CONNECT, RESOURCE TO ' || P_USUARIO.USUARIOORACLE, v_exitoso);
            ejecutar_dml('GRANT ENTRENADOR_DEPORTE_FITNESS TO ' || P_USUARIO.USUARIOORACLE, v_exitoso);
            ejecutar_dml('CREATE SYNONYM ' || P_USUARIO.USUARIOORACLE || '.SESION FOR LIFEFIT.V_SESIONES_CLIENTES_ENTRENADOR', v_exitoso);
            ejecutar_dml('CREATE SYNONYM ' || P_USUARIO.USUARIOORACLE || '.CITA FOR LIFEFIT.V_CITASCLIENTE', v_exitoso);
            ejecutar_dml('CREATE SYNONYM ' || P_USUARIO.USUARIOORACLE || '.EJERCICIO FOR LIFEFIT.EJERCICIO', v_exitoso);
			ejecutar_dml('CREATE SYNONYM ' || P_USUARIO.USUARIOORACLE || '.RUTINA FOR LIFEFIT.RUTINA', v_exitoso);
            
            IF not v_exitoso then
                ejecutar_dml('DROP USER ' || P_USUARIO.USUARIOORACLE || ' CASCADE',v_exitoso);
                ROLLBACK TO v_SavepointName;
                RAISE EXCEPCION_CREACION;
            END IF;
        END;
    COMMIT;  
    
    END CREA_ENTRENADOR;


    PROCEDURE CREA_GERENTE(
        P_DATOS IN TGERENTE,
        P_USERPASS IN VARCHAR2,
        P_USUARIO OUT USUARIO%ROWTYPE,
        P_GERENTE OUT GERENTE%ROWTYPE
    ) IS
         v_SavepointName VARCHAR2(30) := 'spGerente';
        v_exitoso BOOLEAN := TRUE;
        v_Id USUARIO.ID%TYPE;
    BEGIN
        SAVEPOINT v_SavepointName;

    BEGIN
            INSERT INTO USUARIO 
            VALUES (seq_usuario.nextval, P_DATOS.nombre, P_DATOS.apellidos, P_DATOS.telefono, P_DATOS.direccion, P_DATOS.correoe, P_DATOS.nombre||P_DATOS.apellidos)
            RETURNING ID INTO v_Id;
        
            EXCEPTION
            WHEN OTHERS THEN
                ROLLBACK TO v_SavepointName;
                RAISE EXCEPCION_CREACION;           
    END;
        
    BEGIN
            SELECT * INTO P_USUARIO
            FROM USUARIO
            WHERE ID = v_Id;
        
            EXCEPTION
            WHEN OTHERS THEN
                ROLLBACK TO v_SavepointName;
                RAISE EXCEPCION_LECTURA;
    END;
        
    BEGIN
            INSERT INTO GERENTE 
            VALUES (v_Id, P_DATOS.despacho, P_DATOS.horario, P_DATOS.centro);

            EXCEPTION
            WHEN OTHERS THEN
            ROLLBACK TO v_SavepointName;
            RAISE EXCEPCION_CREACION;
    END;

    BEGIN

        SELECT * INTO p_gerente
        FROM GERENTE
        WHERE ID = v_Id;

        EXCEPTION
        WHEN OTHERS THEN
            ROLLBACK TO v_SavepointName;
            RAISE EXCEPCION_LECTURA;

    END;
        
    BEGIN
            ejecutar_dml('CREATE USER ' || P_USUARIO.USUARIOORACLE || ' IDENTIFIED BY ' || P_USERPASS, v_exitoso);
            ejecutar_dml('GRANT CONNECT, RESOURCE TO ' || P_USUARIO.USUARIOORACLE, v_exitoso);
            ejecutar_dml('GRANT GERENTE TO ' || P_USUARIO.USUARIOORACLE, v_exitoso);
            ejecutar_dml('CREATE SYNONYM ' || P_USUARIO.USUARIOORACLE || '.ENTRENA FOR LIFEFIT.ENTRENA', v_exitoso);
            ejecutar_dml('CREATE SYNONYM ' || P_USUARIO.USUARIOORACLE || '.CLIENTE FOR LIFEFIT.CLIENTE', v_exitoso);
			ejecutar_dml('CREATE SYNONYM ' || P_USUARIO.USUARIOORACLE || '.ENTRENADOR FOR LIFEFIT.ENTRENADOR', v_exitoso);
            
            IF not v_exitoso then
                ejecutar_dml('DROP USER ' || P_USUARIO.USUARIOORACLE || ' CASCADE',v_exitoso);
                ROLLBACK TO v_SavepointName;
                RAISE EXCEPCION_CREACION;
            END IF;
        END;
    COMMIT;       
    END CREA_GERENTE;

    PROCEDURE ELIMINA_USER(P_ID USUARIO.ID%TYPE) IS
        PRAGMA AUTONOMOUS_TRANSACTION;
        v_UsuarioOracle Usuario.USUARIOORACLE%TYPE;
        v_SavepointName VARCHAR2(30) := 'sp_eliminauser'; 
        v_exitoso BOOLEAN := TRUE;

    BEGIN
        SAVEPOINT v_SavepointName;
    BEGIN
        SELECT USUARIOORACLE INTO v_UsuarioOracle
        FROM USUARIO
        WHERE id = p_id;

        EXCEPTION
        WHEN OTHERS THEN
            --No hace falta rollback, solo hemos hecho un select
            RAISE EXCEPCION_LECTURA;

    END;

    BEGIN
        UPDATE USUARIO
        SET USUARIOORACLE = NULL
        WHERE ID = P_ID;

        EXCEPTION
        WHEN OTHERS THEN
            ROLLBACK TO v_SavepointName;
            RAISE EXCEPCION_MODIFICACION;
    END;

    BEGIN
        IF V_UsuarioOracle is not null then
            ejecutar_dml('DROP USER ' || V_UsuarioOracle || ' CASCADE',v_exitoso);
            IF not v_exitoso then
                ROLLBACK TO v_SavepointName;
                RAISE EXCEPCION_ELIMINACION;
            END IF;
        END IF;
    END;

    
    COMMIT;

    END ELIMINA_USER;

    PROCEDURE ELIMINA_CLIENTE(P_ID USUARIO.ID%TYPE) AS
        v_UsuarioOracle Usuario.USUARIOORACLE%TYPE;
        v_SavepointName VARCHAR2(30) := 'sp_eliminacliente';
        v_exitoso BOOLEAN := TRUE;

    BEGIN
        SAVEPOINT v_SavepointName;
    BEGIN
        SELECT USUARIOORACLE INTO v_UsuarioOracle
        FROM USUARIO
        WHERE id = p_id;

        EXCEPTION
        WHEN OTHERS THEN
            --No hace falta rollback, solo hemos hecho select
            RAISE EXCEPCION_LECTURA;

    END;

    BEGIN
        DELETE FROM SESION
        WHERE plan_entrena_cliente_id = p_id;

        DELETE FROM PLAN
        WHERE entrena_cliente_id = p_id;

        DELETE FROM ENTRENA
        WHERE cliente_id = p_id;

        INSERT INTO CITAS_TEMP (FECHAYHORA, ENTRENADOR_ID) SELECT 
        FECHAYHORA, ENTRENADOR_ID FROM CITA WHERE CLIENTE_ID = p_id;

        DELETE FROM CITA
        WHERE CLIENTE_ID = p_id;

        DELETE FROM ELEMENTOCALENDARIO E
        WHERE EXISTS (SELECT * FROM CITAS_TEMP C WHERE E.ENTRENADOR_ID=C.ENTRENADOR_ID AND E.FECHAYHORA=C.FECHAYHORA);

        DELETE FROM DIETA
        WHERE ID = p_id;

        DELETE FROM CLIENTE
        WHERE ID = p_id;

        DELETE FROM USUARIO
        WHERE ID = p_id;
        
        EXCEPTION
        WHEN OTHERS THEN
            ROLLBACK TO v_SavepointName;
            RAISE EXCEPCION_ELIMINACION;
    END;
    
    BEGIN
        IF V_UsuarioOracle is not null then
            ejecutar_dml('DROP USER ' || V_UsuarioOracle || ' CASCADE',v_exitoso);
            IF not v_exitoso then
                ROLLBACK TO v_SavepointName;
                RAISE EXCEPCION_ELIMINACION;
            END IF;
        END IF;
    END;

    COMMIT;
    
    END ELIMINA_CLIENTE;

    PROCEDURE ELIMINA_GERENTE(P_ID USUARIO.ID%TYPE) AS
        v_UsuarioOracle Usuario.USUARIOORACLE%TYPE;
        v_SavepointName VARCHAR2(30) := 'sp_eliminaGerente';
        v_exitoso BOOLEAN := TRUE;
    BEGIN
        SAVEPOINT v_SavepointName;
    BEGIN
        SELECT USUARIOORACLE INTO v_UsuarioOracle
        FROM USUARIO
        WHERE id = p_id;

        EXCEPTION
        WHEN OTHERS THEN
            --No hace falta rollback, solo hemos hecho un select
            RAISE EXCEPCION_LECTURA;

    END;


    BEGIN
        DELETE FROM GERENTE
        WHERE ID = p_id;

        DELETE FROM USUARIO
        WHERE ID = p_id;

        COMMIT;


        EXCEPTION
        WHEN OTHERS THEN
            ROLLBACK TO v_SavepointName;
            RAISE EXCEPCION_ELIMINACION;
    END;
    
    BEGIN
        IF V_UsuarioOracle is not null then
            ejecutar_dml('DROP USER ' || V_UsuarioOracle || ' CASCADE',v_exitoso);
            IF not v_exitoso then
                ROLLBACK TO v_SavepointName;
                RAISE EXCEPCION_ELIMINACION;
            END IF;
        END IF;
    END;

    COMMIT;

    END ELIMINA_GERENTE;

      PROCEDURE ELIMINA_ENTRENADOR(P_ID USUARIO.ID%TYPE) AS
        v_UsuarioOracle Usuario.USUARIOORACLE%TYPE;
        v_SavepointName VARCHAR2(30) := 'sp_eliminaEntrenador';
        v_exitoso BOOLEAN := TRUE;
    BEGIN
        SAVEPOINT v_SavepointName;
    BEGIN
        SELECT USUARIOORACLE INTO v_UsuarioOracle
        FROM USUARIO
        WHERE id = p_id;

        EXCEPTION
        WHEN OTHERS THEN
            --No hace falta rollback, solo hemos hecho select
            RAISE EXCEPCION_LECTURA;

    END;

    BEGIN
        DELETE FROM SESION
        WHERE plan_entrena_entrenador_id = p_id;

        DELETE FROM PLAN
        WHERE entrena_entrenador_id = p_id;

        DELETE FROM ENTRENA
        WHERE entrenador_id = p_id;

        DELETE FROM CITA
        WHERE ENTRENADOR_ID = p_id;

        DELETE FROM ELEMENTOCALENDARIO
        WHERE ENTRENADOR_ID = p_id;

        DELETE FROM ENTRENADOR
        WHERE ID = p_id;

        DELETE FROM USUARIO
        WHERE ID = p_id;


        EXCEPTION
        WHEN OTHERS THEN
            ROLLBACK TO v_SavepointName;
            RAISE EXCEPCION_ELIMINACION;
    END;
    
     BEGIN
        IF V_UsuarioOracle is not null then
            ejecutar_dml('DROP USER ' || V_UsuarioOracle || ' CASCADE',v_exitoso);
            IF not v_exitoso then
                ROLLBACK TO v_SavepointName;
                RAISE EXCEPCION_ELIMINACION;
            END IF;
        END IF;
    END;

    COMMIT;


    END ELIMINA_ENTRENADOR;

     PROCEDURE ELIMINA_CENTRO(P_ID CENTRO.ID%TYPE) AS
     v_SavepointName VARCHAR2(30) := 'sp6';

      CURSOR cliente_cursor IS
        SELECT ID
        FROM CLIENTE
        WHERE CENTRO_ID = p_id;

    v_cliente_id CLIENTE.ID%TYPE;

    CURSOR entrenador_cursor IS
        SELECT ID
        FROM ENTRENADOR
        WHERE CENTRO_ID = p_id;

    v_entrenador_id ENTRENADOR.ID%TYPE;

    CURSOR gerente_cursor IS
        SELECT ID
        FROM GERENTE
        WHERE CENTRO_ID = p_id;

    v_gerente_id GERENTE.ID%TYPE;

    BEGIN
    SAVEPOINT v_SavepointName;
    BEGIN

    OPEN cliente_cursor;
        LOOP
         FETCH cliente_cursor INTO v_cliente_id;
          EXIT WHEN cliente_cursor%NOTFOUND;

         ELIMINA_CLIENTE(v_cliente_id);
        END LOOP;
     CLOSE cliente_cursor;

      OPEN entrenador_cursor;
        LOOP
         FETCH entrenador_cursor INTO v_entrenador_id;
          EXIT WHEN entrenador_cursor%NOTFOUND;

         ELIMINA_ENTRENADOR(v_entrenador_id);
     END LOOP;
     CLOSE entrenador_cursor;

     OPEN gerente_cursor;
        LOOP
         FETCH gerente_cursor INTO v_gerente_id;
          EXIT WHEN gerente_cursor%NOTFOUND;

         ELIMINA_GERENTE(v_gerente_id);
     END LOOP;
     CLOSE gerente_cursor;

     DELETE FROM CENTRO
     WHERE ID = p_id;

    COMMIT;

   EXCEPTION
        WHEN OTHERS THEN
            ROLLBACK TO v_SavepointName;
            RAISE EXCEPCION_ELIMINACION;

    END;

    END ELIMINA_CENTRO;


END BASE;

--package ICALC:

create or replace PACKAGE         ICALC AS
 
END ICALC;


--ENTREGAS OPCIONALES DE LA RÚBRICA:
CREATE OR REPLACE VIEW V_DIETACLIENTE AS
SELECT  c.id AS cliente_id,
        d.id AS dieta_id, d.nombre AS nombre_dieta, d.descripcion AS descripcion_dieta, d.tipo
FROM cliente c
JOIN dieta d ON c.dieta_id = d.id
JOIN usuario u ON c.id = u.id
WHERE UPPER(u.usuariooracle)= user;

GRANT SELECT ON V_DIETACLIENTE TO cliente;

CREATE OR REPLACE VIEW V_DIETAENTRENADOR AS 
  SELECT D.ID AS DIETA_ID, D.NOMBRE, D.DESCRIPCION, D.TIPO, C.ID AS CLIENTE_ID
FROM DIETA D 
JOIN CLIENTE C ON D.ID = C.DIETA_ID
JOIN ENTRENA E1 ON C.ID = E1.CLIENTE_ID
JOIN ENTRENADOR E2 ON E2.ID = E1.ENTRENADOR_ID
JOIN USUARIO U ON U.ID = E2.ID
WHERE UPPER(U.USUARIOORACLE) = USER;


GRANT SELECT,UPDATE,DELETE ON V_DIETAENTRENADOR TO ENTRENADOR_NUTRICION;

create or replace TRIGGER Control_dietas
INSTEAD OF INSERT OR UPDATE OR DELETE ON v_dietaentrenador
FOR EACH ROW
BEGIN
    IF INSERTING THEN
        INSERT INTO DIETA VALUES (:NEW.DIETA_ID,:NEW.NOMBRE, 
        :NEW.DESCRIPCION, :NEW.TIPO);
        UPDATE CLIENTE SET DIETA_ID=:NEW.DIETA_ID WHERE ID=:NEW.CLIENTE_ID;
    ELSIF UPDATING THEN
        UPDATE DIETA SET ID=:NEW.DIETA_ID, NOMBRE=:NEW.NOMBRE, DESCRIPCION=:NEW.DESCRIPCION,TIPO=:NEW.TIPO WHERE ID=:OLD.DIETA_ID;
        UPDATE CLIENTE SET DIETA_ID=NULL WHERE ID=:OLD.CLIENTE_ID;
        UPDATE CLIENTE SET DIETA_ID=:NEW.DIETA_ID WHERE ID=:NEW.CLIENTE_ID;
    ELSIF DELETING THEN
        UPDATE CLIENTE SET DIETA_ID=NULL;
    END IF;
END Control_dietas;

CREATE OR REPLACE FUNCTION vpd_function(p_schema VARCHAR2, p_obj VARCHAR2)
RETURN VARCHAR2
IS
  Vusuario VARCHAR2(100);
BEGIN
  Vusuario := SYS_CONTEXT('userenv', 'SESSION_USER');
  
  IF UPPER(Vusuario) = 'LIFEFIT' THEN
    RETURN '1=1';  
  ELSE
    RETURN 'UPPER(usuariooracle) = UPPER(''' || Vusuario || ''')';
  END IF;
END;
/


begin dbms_rls.add_policy (object_schema =>'lifefit',
object_name =>'USUARIO',
policy_name =>'autorization_policy',
function_schema =>'lifefit',
policy_function => 'vpd_function',
statement_types => 'SELECT, UPDATE, DELETE' ); 
end;

--Gestión de contraseñas:
alter profile default limit failed_login_attempts 4;
alter profile default limit password_grace_time 5;
alter profile default limit password_life_time 200;

--auditorias:
SHOW PARAMETER audit_trail;
AUDIT INSERT, UPDATE, DELETE ON CITA BY ACCESS;
AUDIT INSERT, UPDATE, DELETE ON SESION BY ACCESS;

--encriptación de los campos direccion y datos_salud:
select * from dba_encrypted_columns;

alter table usuario modify (DIRECCION VARCHAR(200) ENCRYPT);
alter table sesion modify (DATOS_SALUD VARCHAR(200) ENCRYPT);

--restricciones:
ALTER TABLE ejercicio
ADD CONSTRAINT unique_nombre UNIQUE (nombre);
ALTER TABLE cita ADD CONSTRAINT check_modalidad CHECK (modalidad IN ('Online', 'Presencial'));
alter table conforman add constraint check_repeticiones CHECK (repeticiones>=0);

--probar procedimientos
DECLARE
    -- Definir una variable para almacenar los datos del cliente
    datos_cliente BASE.TCLIENTE;
    -- Definir variables para almacenar los resultados
    usuario_resultado USUARIO%ROWTYPE;
    cliente_resultado CLIENTE%ROWTYPE;
BEGIN
    -- Asignar valores a la variable de datos del cliente
    datos_cliente.NOMBRE := 'David';
    datos_cliente.APELLIDOS := 'Bueno';
    datos_cliente.TELEFONO := '123456789';
    datos_cliente.DIRECCION := 'C/ Oracle';
    datos_cliente.CORREOE := 'david@gmail.com';
    datos_cliente.OBJETIVOS := 'Estar en forma';
    datos_cliente.DIETA := 100;
    datos_cliente.CENTRO := 1000;

    -- Llamar al procedimiento CREA_CLIENTE con los datos de muestra
    BASE.CREA_CLIENTE(datos_cliente, '1234', usuario_resultado, cliente_resultado);

    -- Mostrar los resultados
    DBMS_OUTPUT.PUT_LINE('Usuario creado: ' || usuario_resultado.NOMBRE || ' ' || usuario_resultado.APELLIDOS);
    DBMS_OUTPUT.PUT_LINE('Cliente creado con ID: ' || cliente_resultado.ID);
END;
/

DECLARE
    -- Definir una variable para almacenar los datos del cliente
    datos_entrenador BASE.TENTRENADOR;
    -- Definir variables para almacenar los resultados
    usuario_resultado USUARIO%ROWTYPE;
    entrenador_resultado ENTRENADOR%ROWTYPE;
BEGIN
    -- Asignar valores a la variable de datos del cliente
    datos_entrenador.NOMBRE := 'David';
    datos_entrenador.APELLIDOS := 'Malo';
    datos_entrenador.TELEFONO := '123456789';
    datos_entrenador.DIRECCION := 'C/ Oracle';
    datos_entrenador.CORREOE := 'david@gmail.com';
    datos_entrenador.DISPONIBILIDAD := 'Tiempo Parcial';
    datos_entrenador.CENTRO := 1000;

    -- Llamar al procedimiento CREA_CLIENTE con los datos de muestra
    BASE.CREA_ENTRENADOR(datos_entrenador, '1234', usuario_resultado, entrenador_resultado);

    -- Mostrar los resultados
    DBMS_OUTPUT.PUT_LINE('Usuario creado: ' || usuario_resultado.NOMBRE || ' ' || usuario_resultado.APELLIDOS);
    DBMS_OUTPUT.PUT_LINE('Entrenador creado con ID: ' || entrenador_resultado.ID);
END;
/
DECLARE
    -- Definir una variable para almacenar los datos del cliente
    datos_gerente BASE.TGERENTE;
    -- Definir variables para almacenar los resultados
    usuario_resultado USUARIO%ROWTYPE;
    gerente_resultado GERENTE%ROWTYPE;
BEGIN
    -- Asignar valores a la variable de datos del cliente
    datos_gerente.NOMBRE := 'David';
    datos_gerente.APELLIDOS := 'Neutral';
    datos_gerente.TELEFONO := '123456789';
    datos_gerente.DIRECCION := 'C/ Oracle';
    datos_gerente.CORREOE := 'david@gmail.com';
    datos_gerente.DESPACHO := 'Tiempo Parcial';
    datos_gerente.HORARIO := 'Solo miércoles de 9 a 12 y si además es día impar.';
    datos_gerente.CENTRO := 10;

    BASE.CREA_GERENTE(datos_gerente, '1234', usuario_resultado, gerente_resultado);

    -- Mostrar los resultados
    DBMS_OUTPUT.PUT_LINE('Usuario creado: ' || usuario_resultado.NOMBRE || ' ' || usuario_resultado.APELLIDOS);
    DBMS_OUTPUT.PUT_LINE('Gerente creado con ID: ' || gerente_resultado.ID);
END;
/
BEGIN
    BASE.ELIMINA_GERENTE(331);
END;
/
BEGIN
    BASE.ELIMINA_USER(201);
END;
/

BEGIN
    BASE.ELIMINA_CLIENTE(201);
END;
/
BEGIN
    BASE.ELIMINA_ENTRENADOR(201);
END;
/
BEGIN
    BASE.ELIMINA_CENTRO(1002);
END;
/
