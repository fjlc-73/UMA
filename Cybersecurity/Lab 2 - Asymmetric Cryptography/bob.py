import funciones_rsa
import socket_class

clave_privada_Bob = funciones_rsa.cargar_RSAKey_Privada(
    "clave_privada_Bob.txt", "interference")

clave_publica_Alice = funciones_rsa.cargar_RSAKey_Publica(
    "clave_publica_Alice.txt")

s = socket_class.SOCKET_SIMPLE_TCP('127.0.0.1', 3333)
s.escuchar()
k1_encrypted = s.recibir()
k1_firma = s.recibir()
s.cerrar()

k1 = funciones_rsa.descifrarRSA_OAEP(k1_encrypted, clave_privada_Bob)
print(k1)
correcto = funciones_rsa.comprobarRSA_PSS(k1, k1_firma, clave_publica_Alice)
print(correcto)
