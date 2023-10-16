import funciones_rsa

claves_Alice = funciones_rsa.crear_RSAKey()

funciones_rsa.guardar_RSAKey_Publica(
    "clave_publica_Alice.txt", claves_Alice)
funciones_rsa.guardar_RSAKey_Privada(
    "clave_privada_Alice.txt", claves_Alice, "superposition")

claves_Bob = funciones_rsa.crear_RSAKey()

funciones_rsa.guardar_RSAKey_Publica(
    "clave_publica_Bob.txt", claves_Bob)
funciones_rsa.guardar_RSAKey_Privada(
    "clave_privada_Bob.txt", claves_Bob, "interference")
