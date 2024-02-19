Implementar mediante Jflex, Cup y Java, un programa que haga las funciones de una calculadora con las cuatro operaciones básicas de suma, resta, multiplicación y division entre números enteros (positivos o negativos). Para resolver este ejercicio deben enviarse tres ficheros que se compilaran mediante las instrucciones:

      cup Calculadora.cup
      jflex Calculadora.flex    
      javac Calculadora.java
El programa resultante tendra su entrada desde un fichero de pruebas y su salida se redirigira a otro fichero, por ejemplo, la instruccion:
    java Calculadora exp.in exp.out
transforma el fichero exp.in en el fichero exp.out. En caso de que no se especifique fichero de salida, se utilizará la salida estandar System.out, y si tampoco se especifica fichero de entrada, se usará la entrada estandar System.in. La solucion se evaluará de acuerdo a los casos de prueba descritos a continuación (Nótese que en cada fichero aparece una expresión en cada línea, y algunas líneas pueden estar vacías).

Prueba 1. Operaciones basicas, con solo un operador binario y numeros positivos:

exp.in	exp.out

2+2	4
3*5	15
12*400  4800

Prueba 2. Operaciones basicas, con solo un operador binario, ignorando espacios en blanco y tabuladores. Las lineas en blanco no generan ninguna salida, las lineas que contienen un solo número dan como resultado ese número:

exp.in		exp.out

21 + 12 	33
     11 *22  	242   

  5 -   1	4
  123		123

Prueba 3. Operaciones basicas, con cualquier número de operadores con operandos enteros, positivos o negativos, respetando la prioridad habitual entre los operadores:

exp.in			exp.out

10 + 20 * 30		610
10*   20+   30		230
30  *  20  +10		610
1*2+3*4+5*6+7*8		100

Prueba 4. Incluir el operador menos unario:
exp.in			exp.out

1+-2--3*-4		-13
-11+22-0+4*-1		7
2+32/2/2/2		6
- - - - 3		3
64/-2/2/2/-2*-2*-2	16

Prueba 5. Incluir paréntesis en las expresiones:
exp.in					exp.out

(1+-2)-(-3*-4)				-13
2 * ((-11+22-0)+(4*-1))			14	
2 + (((32/2)/2)/2)			6
- (- - - 3 * (2 + 5))			21
(64/-2/2/2/-2)-(2*-2*-2)		-4
((((((1+2)*3)+4)*((5+6)*7)+8)*9))	9081

