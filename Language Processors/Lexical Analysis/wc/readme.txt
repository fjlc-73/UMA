Se desea implementar mediante JFLEX un programa que haga exactamente lo mismo que la instruccion Linux wc, que cuenta el numero de lineas, palabras y caracteres que hay en un fichero.
Por ejemplo la ejecucion de la instruccion wc con un fichero de prueba daria el siguiente resultado:

          > wc test.txt
          2       4      23 test.txt

Para implementar este programa se usaran dos ficheros, un fichero wc.java que contenga la clase principal, y un fichero wc.lex que contenga las especificaciones, de manera que el programa pueda compilarse con las siguientes instrcciones:

          > jflex wc.flex
          > javac *.java 

y pueda ejecutarse con la instruccion:

          > java wc test.txt
          2       4      23 test.txt