Escribir un programa en CUP que calcule un cierto valor ponderado de una lista de números. El valor ponderado se calcula según la profuncidad que el número tiene en la lista, La evalaucion se hace de la siguiente manera: La lista vacia [] se evalua a cero. La lista con un solo numero se eavlua al valor de ese numero [1], se evalua a 1, La lista de varios números se evalua mediante la suma, por ejemplo [1,2,3,4] se evalúa a 10. Cuando hay anidamiento los valores cuentan dobble, cuadruple, etc, por ejemplo [1,[2],[[3,4]],5], se evalúa como 1 + 21*2 + 22*(3+4) + 5 = 38. En caso de que la lista de entrada no este correctamente estructurada la salida debe ser "Syntax error"
Los siguientes son ejemplos de entradas y sus correspondientes salidas. (Cada fichero de entrada contiene una sola línea)

Entrada				Salida
[1,[2],[[3,4]],5]		38
[1,[1,[1,2]],[[],[]],1]		16
[[ [], [1], [[], [1,1]] ]]	20