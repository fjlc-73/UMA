from Crypto.Random import get_random_bytes
from Crypto.Cipher import AES
from Crypto.Util.Padding import pad, unpad
from Crypto.Util import Counter

key = get_random_bytes(32)
IV = get_random_bytes(16)
BLOCK_SIZE_AES = 16
data1 = "Hola amigos de la seguridad".encode("utf-8")
data2 = "Hola amigas de la seguridad".encode("utf-8")
print(data1)
print(data2)

# CIFRADO ##########################################################################

cipher = AES.new(key, AES.MODE_CBC, IV)

ciphertext_data1 = cipher.encrypt(pad(data1, BLOCK_SIZE_AES))
ciphertext_data2 = cipher.encrypt(pad(data2, BLOCK_SIZE_AES))

# Mostramos el cifrado por pantalla
print(ciphertext_data1.decode("utf-8", "ignore"))
print(ciphertext_data2.decode("utf-8", "ignore"))

# DESCIFRADO #######################################################################

decipher = AES.new(key, AES.MODE_CBC, IV)

new_data1 = unpad(decipher.decrypt(ciphertext_data1),
                  BLOCK_SIZE_AES).decode("utf-8", "ignore")

new_data2 = unpad(decipher.decrypt(ciphertext_data2),
                  BLOCK_SIZE_AES).decode("utf-8", "ignore")


# Imprimimos los datos descifrados
print(new_data1)
print(new_data2)
