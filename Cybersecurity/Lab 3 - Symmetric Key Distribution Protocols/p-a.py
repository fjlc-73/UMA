
from Crypto.Hash import SHA256, HMAC
import base64
import json
import sys
from socket_class import SOCKET_SIMPLE_TCP
import funciones_aes
from Crypto.Random import get_random_bytes

# Paso 0: Inicializacion
########################

KAT = open("KAT.bin", "rb").read()

# Paso 3) A->T: KAT(Alice, Na) en AES-GCM
#########################################

print("Creando conexion con T...")
socket = SOCKET_SIMPLE_TCP('127.0.0.1', 5552)
socket.conectar()

t_n_origen = get_random_bytes(16)

msg_TE = []
msg_TE.append("Alice")
msg_TE.append(t_n_origen.hex())
json_ET = json.dumps(msg_TE)
print("A -> T (descifrado): " + json_ET)

aes_engine = funciones_aes.iniciarAES_GCM(KAT)
cifrado, cifrado_mac, cifrado_nonce = funciones_aes.cifrarAES_GCM(aes_engine,json_ET.encode("utf-8"))

socket.enviar(cifrado)
socket.enviar(cifrado_mac)
socket.enviar(cifrado_nonce)

# Paso 4) T->A: KAT(K1, K2, Na) en AES-GCM
##########################################

print("Esperando a TTP...")

cifrado = socket.recibir()
cifrado_mac = socket.recibir()
cifrado_nonce = socket.recibir()

datos_descifrado_ET = funciones_aes.descifrarAES_GCM(KAT, cifrado_nonce, cifrado, cifrado_mac)

json_ET = datos_descifrado_ET.decode("utf-8" ,"ignore")
print("T->A (descifrado): " + json_ET)
msg_ET = json.loads(json_ET)

K1, K2, t_na = msg_ET
K1 = bytearray.fromhex(K1)
K2 = bytearray.fromhex(K2)
t_na = bytearray.fromhex(t_na)

if t_na!=t_n_origen:
    sys.exit(1)

socket.cerrar() 
# Paso 5) A->B: KAB(Nombre) en AES-CTR con HMAC
###############################################

aes_engine, nonce_origen = funciones_aes.iniciarAES_CTR_cifrado(K1)
h = HMAC.new(K2, msg=b"Javier", digestmod=SHA256 )

msg_TE = []
msg_TE.append("Javier")
msg_TE.append(h.hexdigest())
json_ET = json.dumps(msg_TE)
print("A -> B (descifrado): " + json_ET)

cifrado = funciones_aes.cifrarAES_CTR(aes_engine,json_ET.encode("utf-8"))

socket = SOCKET_SIMPLE_TCP('127.0.0.1', 5553)
socket.conectar()
socket.enviar(cifrado)
socket.enviar(nonce_origen)

# Paso 6) B->A: KAB(Apellido) en AES-CTR con HMAC
#################################################

print("Esperando a Bob...")

cifrado = socket.recibir()
nonce_destino = socket.recibir()

aes_engine = funciones_aes.iniciarAES_CTR_descifrado(K1, nonce_destino)
datos_descifrado = funciones_aes.descifrarAES_CTR(aes_engine, cifrado)

json_ET = datos_descifrado.decode("utf-8" ,"ignore")
print("B->A (descifrado): " + json_ET)
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

# Paso 7) A->B: KAB(END) en AES-CTR con HMAC
############################################

aes_engine, nonce_origen = funciones_aes.iniciarAES_CTR_cifrado(K1)
h = HMAC.new(K2, msg=b"END", digestmod=SHA256 )

msg_TE = []
msg_TE.append("END")
msg_TE.append(h.hexdigest())
json_ET = json.dumps(msg_TE)
print("A -> B (descifrado): " + json_ET)

cifrado = funciones_aes.cifrarAES_CTR(aes_engine,json_ET.encode("utf-8"))

socket.enviar(cifrado)
socket.enviar(nonce_origen)

socket.cerrar()
