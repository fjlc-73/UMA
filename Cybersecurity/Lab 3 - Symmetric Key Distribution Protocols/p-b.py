

from Crypto.Hash import SHA256, HMAC
import base64
import json
import sys
from socket_class import SOCKET_SIMPLE_TCP
import funciones_aes
from Crypto.Random import get_random_bytes

# Paso 0: Inicializacion
########################

# Lee clave KBT
KBT = open("KBT.bin", "rb").read()

# Paso 1) B->T: KBT(Bob, Nb) en AES-GCM
#######################################

# Crear el socket de conexion con T (5551)
print("Creando conexion con T...")
socket = SOCKET_SIMPLE_TCP('127.0.0.1', 5551)
socket.conectar()

# Crea los campos del mensaje
t_n_origen = get_random_bytes(16)

# Codifica el contenido (los campos binarios en una cadena) y contruyo el mensaje JSON
msg_TE = []
msg_TE.append("Bob")
msg_TE.append(t_n_origen.hex())
json_ET = json.dumps(msg_TE)
print("B -> T (descifrado): " + json_ET)

# Cifra los datos con AES GCM
aes_engine = funciones_aes.iniciarAES_GCM(KBT)
cifrado, cifrado_mac, cifrado_nonce = funciones_aes.cifrarAES_GCM(aes_engine,json_ET.encode("utf-8"))

# Envia los datos
socket.enviar(cifrado)
socket.enviar(cifrado_mac)
socket.enviar(cifrado_nonce)

# Paso 2) T->B: KBT(K1, K2, Nb) en AES-GCM
##########################################

print("Esperando a TTP...")

cifrado = socket.recibir()
cifrado_mac = socket.recibir()
cifrado_nonce = socket.recibir()

datos_descifrado_ET = funciones_aes.descifrarAES_GCM(KBT, cifrado_nonce, cifrado, cifrado_mac)

json_ET = datos_descifrado_ET.decode("utf-8" ,"ignore")
print("T->B (descifrado): " + json_ET)
msg_ET = json.loads(json_ET)

K1, K2, t_nb = msg_ET
K1 = bytearray.fromhex(K1)
K2 = bytearray.fromhex(K2)
t_nb = bytearray.fromhex(t_nb)

if t_nb!=t_n_origen:
    sys.exit(1)


# Cerramos el socket entre B y T, no lo utilizaremos mas
socket.cerrar() 

# Paso 5) A->B: KAB(Nombre) en AES-CTR con HMAC
###############################################

print("Esperando a Alice...")
socket = SOCKET_SIMPLE_TCP('127.0.0.1', 5553)
socket.escuchar()
cifrado = socket.recibir()
nonce_origen = socket.recibir()

aes_engine = funciones_aes.iniciarAES_CTR_descifrado(K1, nonce_origen)
datos_descifrado = funciones_aes.descifrarAES_CTR(aes_engine, cifrado)

json_ET = datos_descifrado.decode("utf-8" ,"ignore")
print("A->B (descifrado): " + json_ET)
msg_ET = json.loads(json_ET)

nombre, hash = msg_ET
nombre = bytearray(nombre.encode("utf-8"))
h = HMAC.new(K2, msg=nombre, digestmod=SHA256)

try:
    h.hexverify(hash)
    print("The message is authentic") 
except ValueError:
    print("The message or the key is wrong")
    sys.exit(1)



# Paso 6) B->A: KAB(Apellido) en AES-CTR con HMAC
#################################################

aes_engine, nonce_destino = funciones_aes.iniciarAES_CTR_cifrado(K1)
h = HMAC.new(K2, msg=b"Lopez", digestmod=SHA256 )

msg_TE = []
msg_TE.append("Lopez")
msg_TE.append(h.hexdigest())
json_ET = json.dumps(msg_TE)
print("B -> A (descifrado): " + json_ET)

cifrado = funciones_aes.cifrarAES_CTR(aes_engine,json_ET.encode("utf-8"))

socket.enviar(cifrado)
socket.enviar(nonce_destino)

# Paso 7) A->B: KAB(END) en AES-CTR con HMAC
############################################

print("Esperando a Alice...")

cifrado = socket.recibir()
nonce_origen = socket.recibir()

aes_engine = funciones_aes.iniciarAES_CTR_descifrado(K1, nonce_origen)
datos_descifrado = funciones_aes.descifrarAES_CTR(aes_engine, cifrado)

json_ET = datos_descifrado.decode("utf-8" ,"ignore")
print("A->B (descifrado): " + json_ET)
msg_ET = json.loads(json_ET)

nombre, hash = msg_ET
nombre = bytearray(nombre.encode("utf-8"))
h = HMAC.new(K2, msg=nombre, digestmod=SHA256)

try:
    h.hexverify(hash)
    print("The message is authentic") 
    socket.cerrar()
except ValueError:
    print("The message or the key is wrong")
    sys.exit(1)
