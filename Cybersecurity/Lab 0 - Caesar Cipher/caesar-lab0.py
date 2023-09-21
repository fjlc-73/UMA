def cifradoCesarAlfabetoInglesMAY(cadena, k):
    """
    Devuelve un cifrado Cesar tradicional (+k)
    """
    # Definir la nueva cadena resultado
    resultado = ''
    # Realizar el "cifrado", sabiendo que A = 65, Z = 90, a = 97, z = 122
    i = 0
    while i < len(cadena):
        # Recoge el caracter a cifrar
        ordenClaro = ord(cadena[i])
        # ordenCifrado = 0 No es necesario inicializarlo aqui, ya que lo hara dentro del if
        # Cambia el caracter a cifrar
        if (ordenClaro >= 65 and ordenClaro <= 90):
            # ==COMPLETAR para guardar en ordenCifrado el cifrado de ordenClaro==
            ordenCifrado = ((ordenClaro + k - 65) % 26) + 65
            resultado = resultado + chr(ordenCifrado)
        elif (ordenClaro >= 97 and ordenClaro <= 122):
            ordenCifrado = ((ordenClaro + k - 97) % 26) + 97
            resultado = resultado + chr(ordenCifrado)
        else:
            resultado = resultado + chr(ordenClaro)
        # Añade el caracter cifrado al resultado
        i = i + 1
    # devuelve el resultado
    return resultado


def descifradoCesarAlfabetoInglesMAY(cadena, k):
    resultado = ''
    i = 0
    while i < len(cadena):
        ordenClaro = ord(cadena[i])
        if (ordenClaro >= 65 and ordenClaro <= 90):
            ordenCifrado = ((ordenClaro - k - 65) % 26) + 65
            resultado = resultado + chr(ordenCifrado)
        elif (ordenClaro >= 97 and ordenClaro <= 122):
            ordenCifrado = ((ordenClaro - k - 97) % 26) + 97
            resultado = resultado + chr(ordenCifrado)
        else:
            resultado = resultado + chr(ordenClaro)
        i = i + 1
    return resultado


claroCESARMAY = '¿¿¿ VENI VIDI VINCI AURIA XYZ veni vidi vinci auria xyz !!!'
print(claroCESARMAY)
cifradoCESARMAY = cifradoCesarAlfabetoInglesMAY(claroCESARMAY, 7)
print(cifradoCESARMAY)
claroCESARMY = descifradoCesarAlfabetoInglesMAY(cifradoCESARMAY, 7)
print(claroCESARMY)
