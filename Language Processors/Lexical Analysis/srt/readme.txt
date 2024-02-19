Estadísticas de Ficheros SRT

En los últimos años, la popularidad de las diferentes plataformas de streaming ha aumentado el consumo de películas y series alrededor del globo. Ya no se ve únicamente la producción nacional o las grandes producciones de Hollywood. Es común ver series francesas, turcas, koreanas, japonesas, etc. las cuales no han sido dobladas al castellano. Estas series suelen verse subtituladas, y el formato más común para la declaración de subtítulos es el SRT.

Se pide crear mediante JFlex y Java un analizador de ficheros SRT que extraigan diversas estadísticas sobre un fichero de subtítulos.

Un fichero SRT se compone de diversos bloques de subtítulos. Cada uno de ellos se compone de varias líneas contínuas (sin líneas en blanco).

La primera es el identificador numérico del subtítulo.

A continuación se declaran las marcas de tiempo inicial y final para mostrar el subtítulo. Se sigue el formato HH:mm:ss,ZZZ donde HH es la hora, mm los minutos, ss los segundos y ZZZ los milisegundos.

Es necesario que existan ambos dígitos, por ejemplo, las una y un minúto y un segundo sería 01:01:01:000. Cualquier otro formato de fechas se considerará incorrecto e invalidará el subtítulo.

Cada marca de tiempo se separa utilizando una flecha -->.

Se supondrá que todas las marcas de tiempo iniciales y finales son secuenciales, es decir, la marca de tiempo inicial siempre será anterior a la final. Esto no evita que pueda haber errores de formato.

Finalmente aparecen una o más líneas de subtítulos. Estas pueden contener cualquier cadena alfanumérica, así como los diversos signos de puntuación.

Se considera que un bloque de subtítulos termina y comienza el siguiente cuando aparece una línea en blanco.

Un ejemplo de fichero SRT sería el siguiente:
1
00:00:22,708 --> 00:00:24,708
Nada es malvado al principio.

2
00:00:24,791 --> 00:00:26,500
¡Aquí!

3
00:00:29,833 --> 00:00:33,916
Y hubo un tiempo en
que el mundo era tan joven

4
00:00:34,041 --> 00:00:37,041
que no había amanecido aún.

5
00:00:37,125 --> 00:00:41,416
Pero aun así había luz.

6
00:01:02,958 --> 00:01:05,875
¿Qué? ¿Ya lo has acabado?

7
00:01:17,666 --> 00:01:21,208
Ni siquiera tú te crees
que ese trasto pueda flotar.

8
00:01:22,666 --> 00:01:24,208
No va a flotar.

9
00:01:25,958 --> 00:01:29,000
Va a navegar.

Se deberá crear un programa utilizando Java y JFlex que procese un archivo .srt y utilizando el resto de ficheros suministrados muestre la siguiente información por pantalla:

Número total de subtítulos
Tiempo total en segundos que se muestran subtítulos en pantalla. No se tendrán en cuenta los milisegundos de las marcas de tiempo, ni el solapamiento de subtítulos (se considerarán independientes).
Número de líneas de texto de subtítulos.
Número de palabras mostradas.
El programa debe poder compilar y ejecutar correctamente con las instrucciones:
jflex srt.lex
javac srt.java
java srt ejemplo.srt
En donde ejemplo.srt puede ser cualquier nombre de fichero que se quiera procesar, y la salida estará formateada de la siguiente manera:

NUM_SUBTITULOS 9
TIEMPO_TOTAL 28
NUM_LINEAS 11
NUM_PALABRAS 49

Consideraciones importantes:
Si se incumple alguna condición de formato en algún bloque de subtítulos, este será ignorado completamente.
Todo subtítulo debe contener al menos un carácter visible (carácter distinto de un separador).
Los espacios, salvo en las líneas de texto de subtítulos, son puramente decorativos y pueden aparecer o no.

Casos de prueba:
prueba.srt - Prueba del enunciado
e1.srt - Tiene una línea vacía al comienzo y dos líneas en blanco en lugar de una separando subtítulos
e2.srt - Añade información tras las marcas de horas en dos subtítulos y, por tanto, no se deben contar
e3.srt - No tienes ningún error de formato. Finaliza con tres líneas en blanco en lugar de una
e4.srt - Añade texto a la línea con el número de índice en dos subtítulos. En uno antes y en otro después. Y, por tanto, no se deben contar
e5.srt - Los números de inicio o fin en algunos subtítulos tienen caracteres erróneos
e6.srt - Tiene un subtítulo que es una línea vacía y otro que no tiene ninguna línea de subtítulo (solo la separación).
e7.srt - Falta la línea de separación y el siguiente subtítulo se debe considerar como parte del texto del anterior
e8.srt - En un subtítulo falta la flecha y otro la tiene doble
e9.srt - A una marca de tiempo le faltan los milisegundos.
e10.srt - Este no tiene nada mal.
e11.srt - Hay líneas en blanco donde no debería.
e12.srt - Faltan los espacios alrededor de la flecha de tiempo.

Versiones compiladas
Se proporcionan versiones compiladas de la solución a este ejercicio para Linux, Mac y Windows.


Prueba del enunciado
Tiene una línea vacía al comienzo y dos líneas en blanco en lugar de una separando subtítulos
Añade información tras las marcas de horas en dos subtítulos y, por tanto, no se deben contar
No tienes ningún error de formato. Finaliza con tres líneas en blanco en lugar de una
Añade texto a la línea con el número de índice en dos subtítulos. En uno antes y en otro después. Y, por tanto, no se deben contar
Los números de inicio o fin en algunos subtítulos tienen caracteres erróneos
Tiene un subtítulo que es una línea vacía y otro que no tiene ninguna línea de subtítulo (solo la separación).
Falta la línea de separación y el siguiente subtítulo se debe considerar como parte del texto del anterior
En un subtítulo falta la flecha y otro la tiene doble
A una marca de tiempo le faltan los milisegundos
Este no tiene nada mal.
Hay líneas en blanco donde no debería.
Faltan los espacios alrededor de la flecha de tiempo.
Números extra en las marcas de tiempo
Números extra en las marcas de tiempo