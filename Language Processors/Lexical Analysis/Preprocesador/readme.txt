Preprocesador de texto


Implementar un analizar léxico con jflex que permita realizar operaciones de sustitución de variables y eliminación de bloques de texto.

El analizador leerá un texto de entrada y generará un texto de salida siguiendo las siguientes reglas:

El texto de entrada que no sea una definición de variable, sustitución de variable o control de bloque se copiará tal cual a la salida.

En el texto pueden aparece definiciones de variables de la forma #define id valor. Donde id es un identificador válido (comienza por un letra y puede contener letras, dígitos y subrayado. Las letras mayúsculas y minúsculas se consideran diferentes). Valor es cualquier cadena a partir de los espacios en blanco después de id y hasta el final de la línea (puede incluir espacios en blanco o estar vacía). La instrucción #define debe aparecer siempre al principio de la línea y sin ningún espacio en blanco delante. En otro caso serán ignoradas y se considerarán texto normal. El programa almacenará valor asociado a la variable id y eliminará totalmente esta línea de la salida. Si la variable id ya hubiese sido definida entonces se sustituirá su valor por el nuevo. Por ejemplo, la entrada:

Esto es

#define ejemplo texto de ejemplo

un ejemplo

Generaría la salida

Esto es

un ejemplo

En el texto pueden aparecer sustitución de variables. No se tendrán en cuenta las sustituciones de variables en las instrucciones #define ni #ifdef. Una sustitución de variable tendrá la forma ${variable}. En el texto de salida se sustituirá ${variable} por el valor almacenado en la variable variable. Por ejemplo, la entrada:

Esto es

#define ejemplo texto de ejemplo

un ${ejemplo}

Generaría la salida
Esto es

un texto de ejemplo

Si la variable variable no estuviese definida entonces se sustituiría por la cadena ID_NO_DEFINIDO. Por ejemplo, la entrada:

Esto es

#define ejemplo texto de ejemploç

un ${valor}

Generaría la salida

Esto es

un ID NO DEFINIDO

En el texto pueden aparecer bloque delimitados por instrucciones #ifdef id y #endif. Estas instrucciones deberán aparecer siempre al comienzo de una línea y si ningún espacio en blanco delante. En otro caso serán ignoradas y se considerarán texto normal. Cualquier línea que comience por #ifdef o #endif no se mostrará en la salida incluyendo cualquier texto a la derecha de #ifdef id o #endif hasta el final de línea. Si la variable id no está definida entonces el texto entre la línea que comienza con #ifdef y la línea que comienza con #endif no se mostrará en la salida. Si id está definida entonces el texto entre la línea que comienza con #ifdef y la línea que comienza con #endif se mostrará en la salida. Por ejemplo, la entrada:

Esto es

#define ejemplo texto de ejemplo

#ifdef variable texto no mostrado

un ${ejemplo}

#endif

Generaría la salida

Esto es

La entrada:

Esto es

#define ejemplo texto de ejemplo

#ifdef ejemplo texto no mostrado

un ${ejemplo}

#endif

Generaría la salida

Esto es

un texto de ejemplo




NOTAS:

En la instrucción #ifdef id, si id no es un identificador válido entonces la línea se ignorará (no se mostrará en la salida ni tendrá efecto ninguno) y se mostrará en la salida el texto ERROR_IFDEF.

En la instrucción #define id, si id no es un identificador válido entonces la línea se ignorará (no se mostrará en la salida ni tendrá efecto ninguno) y se mostrará en la salida el texto ERROR_DEFINICION.

Las instrucciones #ifdef/#endif no se pueden anidar. Si así fuese entonces se mostraría en la salida el texto ERROR_IF_ANIDADO y se ignoraría la línea con la instrucción #ifdef anidada. Esto se tendrá en cuenta incluso si el identificador correspondiente a la instrucción #ifdef no está definido.

No se permiten instrucciones #define dentro de un bloque #ifdef/#endif. Si se encontrase una entonces se ignoraría la línea y en la salida se mostraría el texto ERROR_DEFINE_EN_IF. Esto se tendrá en cuenta incluso si el identificador asociado a la instrucción #ifdef no está definido.

Si se encontrase una instrucción #endif sin el #ifdef correspondiente entonces la línea sería ignorada y en la salida se mostraría el texto ERROR_ENDIF_SIN_IF.

Para implementar este programa se usaran dos ficheros, un fichero Prep.java que contenga la clase principal, y un fichero Prep.lex que contenga las especificaciones, de manera que el programa pueda compilarse con las siguientes instrcciones:

          > jflex Prep.lex
          > javac *.java
y pueda ejecutarse con la instruccion:

          > java Prep texto.pre
El programa debe podre ejecutarse sin parámetros (y escribirá en la consola y leerá la entrada del teclado); con un solo parámetro (que indicará el fichero de donde leerá la entrada y escribirá la salida a la consola); o con dos parámetros (el primero será el fichero desde donde leerá la entrada y el segundo el fichero donde guardará la salida del programa).