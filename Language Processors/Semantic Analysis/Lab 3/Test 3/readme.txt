Escribir un programa en CUP que aplane una lista de numeros escrita al estilo Prolog. Por ejemplo la lista [1,[2,3],[[4,[5]]]] se debe aplanar como [1,2,3,4,5]. Si la lista de entrada contiene la lista vacia [], se traducira por la secuencia NULL, como por ejemplo, la lista [1,[],[2,[]],3] que debe traducirse a [1,NULL,2,NULL,3]. En caso de que la lista de entrada no este correctamente estructurada la salida debe ser "Syntax error"
Los siguientes son ejemplos de entradas y sus correspondientes salidas. (Cada fichero de entrada contiene una sola línea)

Entrada				Salida
[1,[2,3],[[4,[5]]]]		[1,2,3,4,5]
[5,[4,[3,2]],[[],[]],1]		[5,4,3,2,NULL,NULL,1]
[1,2,3,[[1],[2],[3]],[1,2,3]]	[1,2,3,1,2,3,1,2,3]