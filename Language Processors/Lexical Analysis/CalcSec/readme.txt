Se quiere realizar un analizador léxico llamado CalcSec.lex que sea capaz de imprimir por pantalla el resultado de diversas operaciones matemáticas: suma (+), resta (-), multiplicación (*) y división (/); sobre números enteros positivos y terminando la secuencia de operaciones en (;). (El resultado puede de la operación puede ser positivop o negativo)
Esta calculadora tiene varias particularidades. La primera es que la calculadora solo es capaz de almacenar el resultado de la operación anterior en memoria. En otras palabras, siempre operará el resultado de la operación anterior con el nuevo número.
Algunos ejemplos de salidas serían las siguientes:

Operación	Resultado
7;		7
4+3+1;		8

Además, la calculadora puede realizar múltiples secuencias de operaciones en un mismo fichero. Cada secuencia de operaciones aparecerá en una única línea terminada en (;) y devolverá su propio resultado. Un posible ejemplo sería:

Operación	Resultado
3+2*5;4/2+1;	25
		3
7+1/4;		2
3*3+3;		9

Otra particularidad de las expresiones secuenciales es que sus operaciones no tienen la precedencia habitual, ya que operan siempre asociando a la izquierda, es decir, 3+2*5; obtiene primero la suma de 3+2 y luego multiplica por 5; lo que no es el resultado habitual en las expresiones de los lengajes de programación. Los resultados obtenidos por estas operaciones serían los siguientes:

Operación	Resultado
3+2*5;		25
2*5+3;		13

Finalmente, para poder realizar algunas operaciones antes de otras se utilizan los paréntesis (). Los paréntesis tienen dos funciones principales.
Permiten saltarse el orden secuencial de operación. Las operaciones que aparezcan dentro de un paréntesis se calcularán antes de operarlas con el resto de operaciones.
Funcionan como una multiplicación con el valor que le preceden, es decir 3(3-1) sería igual a 3*2, ya que 2 es el resultado del paréntesis.
Los parentesis solo funcionan para multiplicar, siendo incorrectas expresiones como 2+(3*5) o 3*(4*5)
Además los paréntesis pueden anidarse entre sí manteniendo sus funcionalidades. Algunos ejemplos del uso de los paréntesis serían los siguientes:

Operación		Resultado
2+5(3*2);		42
(2+5)(3*2);		42
2(7-10)+2(2+3)*7;	-140
6/3(8+2(5*5));		500

Se debe enviar un fichero llamado CalcSec.lex que contenga el analizador léxico en JFLEX que analice los puntos enunciados en el apartado anterior. Para ello se adjunta la clase principal CalcSec.java, la cual NO PUEDE SER MODIFICADA. Así como unos ficheros de ejemplo. ejemplos.zip Los ficheros deben de poder compilarse mediante los comandos:
          > jflex CalcSec.lex
          > javac *.java
y pueda ejecutarse con la instrucción:
          > java CalcSec <fichero>
Por ejemplo, para un fichero prueba.txt que contiene el texto:
8+2(3-1);
Si ejecutamos nuestro programa debe de dar lo siguiente:
         > java CalcSec prueba.txt
         20