import funciones_rsa
from Crypto.Random import get_random_bytes
import socket_class

clave_privada_Alice = funciones_rsa.cargar_RSAKey_Privada(
    "clave_privada_Alice.txt", "superposition")

clave_publica_Bob = funciones_rsa.cargar_RSAKey_Publica(
    "clave_publica_Bob.txt")

k1 = get_random_bytes(16)
k1_encrypted = funciones_rsa.cifrarRSA_OAEP(k1, clave_publica_Bob)
k1_firma = funciones_rsa.firmarRSA_PSS(k1, clave_privada_Alice)

s = socket_class.SOCKET_SIMPLE_TCP('127.0.0.1', 3333)
s.conectar()
s.enviar(k1_encrypted)
s.enviar(k1_firma)
s.cerrar()
