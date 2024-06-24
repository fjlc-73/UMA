SET SERVEROUTPUT ON SIZE;

-- Ejercicio 1:

DECLARE
    v_table_name USER_TABLES.TABLE_NAME%TYPE;
    v_schema_name USER_TABLES.TABLESPACE_NAME%TYPE;
    
    CURSOR table_cursor IS
        SELECT TABLE_NAME, TABLESPACE_NAME
        FROM USER_TABLES;
BEGIN
    OPEN table_cursor;
    
    LOOP
        FETCH table_cursor INTO v_table_name, v_schema_name;
        EXIT WHEN table_cursor%NOTFOUND;
        
        DBMS_OUTPUT.PUT_LINE('La tabla ' || v_table_name || ' pertenece al esquema ' || v_schema_name);
    END LOOP;
    
    CLOSE table_cursor;
END;
/

-------------------------------------------------------------------
-- Ejercicio 2:

DECLARE
    v_table_name ALL_TABLES.TABLE_NAME%TYPE;
    v_schema_name ALL_TABLES.OWNER%TYPE;
    
    CURSOR table_cursor IS
        SELECT TABLE_NAME, OWNER
        FROM ALL_TABLES;
BEGIN
    OPEN table_cursor;
    
    LOOP
        FETCH table_cursor INTO v_table_name, v_schema_name;
        EXIT WHEN table_cursor%NOTFOUND;
        
        DBMS_OUTPUT.PUT_LINE('La tabla ' || v_table_name || ' pertenece al esquema ' || v_schema_name);
    END LOOP;
    
    CLOSE table_cursor;
END;
/


-------------------------------------------------------------------
-- Ejercicio 3:
/*Dado que USER_TABLES solo muestra las tablas en su propio esquema, debemos usar la vista ALL_TABLES en lugar de USER_TABLES para obtener las tablas a las que tiene permiso en otros esquemas de usuario. 


Este script utiliza la vista ALL_TABLES para filtrar las tablas cuyo propietario no sea el mismo que el usuario actual.
 Como resultado, también tuvimos que filtrar para obtener tablas de otros esquemas, modificando la acción y especificando con el elemento OWNER.*/

-------------------------------------------------------------------
-- Ejercicio 4:

DECLARE
    v_table_name ALL_TABLES.TABLE_NAME%TYPE;
    v_schema_name ALL_TABLES.OWNER%TYPE;
    
    CURSOR table_cursor IS
        SELECT TABLE_NAME, OWNER
        FROM ALL_TABLES
        WHERE OWNER=USER;
BEGIN
    OPEN table_cursor;
    
    LOOP
        FETCH table_cursor INTO v_table_name, v_schema_name;
        EXIT WHEN table_cursor%NOTFOUND;
        
        DBMS_OUTPUT.PUT_LINE('La tabla ' || v_table_name || ' pertenece al esquema ' || v_schema_name);
    END LOOP;
    
    CLOSE table_cursor;
END;
/

/*La principal distinción entre los dos script es la fuente de los datos:

El primer script que se ejecuta con USER_TABLES:

Este script utiliza la vista USER_TABLES, que solo muestra las tablas del esquema del usuario que está ejecutando el script.
Incluso si el usuario actual tiene permisos para acceder a las tablas de otros esquemas, no las incluye.

El script número dos, utilizando ALL_TABLES:

Este script utiliza la vista ALL_TABLES, que proporciona datos sobre todas las tablas que el usuario actual puede ver.
Siempre que el usuario tenga los permisos adecuados para acceder a las tablas, incluye tablas del esquema del usuario y tablas de otros esquemas.


El primer script tiene un alcance más limitado porque solo muestra las tablas dentro del esquema del usuario, mientras que el segundo script muestra todas las tablas a las que el usuario tiene acceso, independientemente del propietario.*/

-------------------------------------------------------------------
-- Ejercicio 5:

CREATE OR REPLACE PROCEDURE RECORRE_TABLAS(P_MODE IN NUMBER DEFAULT NULL) IS
    v_table_name ALL_TABLES.TABLE_NAME%TYPE;
    v_schema_name ALL_TABLES.OWNER%TYPE;
    v_owner_condition VARCHAR2(100);
    
    CURSOR table_cursor IS
        SELECT TABLE_NAME, OWNER
        FROM ALL_TABLES
        WHERE NVL(DECODE(P_MODE, 0, NULL, OWNER), USER) = USER;
BEGIN
    IF P_MODE IS NULL THEN
        DBMS_OUTPUT.PUT_LINE('Este procedimiento lista todas las tablas a las que tienes permiso.');
        DBMS_OUTPUT.PUT_LINE('Para listar solo las propias del usuario, proporciona 0 como valor de P_MODE.');
        RETURN;
    END IF;
    
    IF P_MODE = 0 THEN
        DBMS_OUTPUT.PUT_LINE('Listando todas las tablas a las que tienes permiso:');
    ELSE
        DBMS_OUTPUT.PUT_LINE('Listando tus propias tablas:');
    END IF;
    
    OPEN table_cursor;
    
    LOOP
        FETCH table_cursor INTO v_table_name, v_schema_name;
        EXIT WHEN table_cursor%NOTFOUND;
        
        DBMS_OUTPUT.PUT_LINE('La tabla ' || v_table_name || ' pertenece al esquema ' || v_schema_name);
    END LOOP;
    
    CLOSE table_cursor;
END RECORRE_TABLAS;
/

BEGIN
    RECORRE_TABLAS(0);
END;
/

BEGIN
    RECORRE_TABLAS(NULL);
END;
/

BEGIN
    RECORRE_TABLAS(1);
END;
/
