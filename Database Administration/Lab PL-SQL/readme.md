1. Te pido escribir un script de PL/SQL que recorre todas las tablas que hay en tu PROPIO esquema usando un cursor sobre la tabla USER_TABLES. El script produce una salida al buffer de E/S (usando la librería DBMS_OUTPUT) y para cada tabla se escribe una fila con el formato: La tabla ... pertenece al esquema ....

2. Modifica el script anterior para que salgan las tablas a las que tienes permiso en otros esquemas de usuario.

3. ¿Has tenido que modificar algo más además de la vista del diccionario?.

4. El segundo script cubre ambos casos si limitamos en el primer caso a que el OWNER de la vista ALL_TABLES coincide con el usuario que ejecuta el script. Compara ambas sentencias y extrae conclusiones.
   
5. Crea un procedimiento llamado RECORRE_TABLAS(P_MODE IN NUMBER) que recorre las tablas y produce la salida antes mencionada. Si llamamos al procedimiento con valor 0 en P_MODE lista todas las tablas a las que tenemos permiso y si le damos un valor distinto de cero, lista las propias del usuario. Si no recibe valor en el parámetro, sale un mensaje a modo de manual del propio procedimiento explicando lo que hace y los posibles valores de P_MODE.
Para crear el procedimiento, puedes usar un solo cursor sobre la vista ALL_TABLES con un parámetro que permite limitar la búsqueda sobre la vista. Es decir si el parámetro tiene valor, entonces el atributo OWNER se compara con el nombre de usuario. Para mejorar la definición del cursor, recuerda el uso de las funciones NVL y DECODE.
