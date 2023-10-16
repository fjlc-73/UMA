from Crypto.Random import get_random_bytes
from Crypto.Cipher import AES
from Crypto.Util.Padding import pad, unpad
from Crypto.Util import Counter

BLOCK_SIZE_AES = 16
key = get_random_bytes(32)
IV = get_random_bytes(16)
nonce = get_random_bytes(8)
mac_size = 16

data = "Hola Amigos de Seguridad".encode("utf-8")
print(data)

# CIFRADO ##########################################################################

cipher_ECB = AES.new(key, AES.MODE_ECB)
ciphertext_data_ECB = cipher_ECB.encrypt(pad(data, BLOCK_SIZE_AES))

cipher_CTR = AES.new(key, AES.MODE_CTR, nonce=nonce)
ciphertext_data_CTR = cipher_CTR.encrypt(pad(data, BLOCK_SIZE_AES))

cipher_OFB = AES.new(key, AES.MODE_OFB, IV)
ciphertext_data_OFB = cipher_OFB.encrypt(pad(data, BLOCK_SIZE_AES))

cipher_CFB = AES.new(key, AES.MODE_CFB, IV)
ciphertext_data_CFB = cipher_CFB.encrypt(pad(data, BLOCK_SIZE_AES))

cipher_GCM = AES.new(key, AES.MODE_GCM, nonce=nonce, mac_len=mac_size)
ciphertext_data_GCM, mac_cifrado = cipher_GCM.encrypt_and_digest(
    pad(data, BLOCK_SIZE_AES))

# Mostramos el cifrado por pantalla
print("ECB " + ciphertext_data_ECB.decode("utf-8", "ignore"))
print("CTR " + ciphertext_data_CTR.decode("utf-8", "ignore"))
print("OFB " + ciphertext_data_OFB.decode("utf-8", "ignore"))
print("CFB " + ciphertext_data_CFB.decode("utf-8", "ignore"))
print("GCM " + ciphertext_data_GCM.decode("utf-8", "ignore"))


# DESCIFRADO #######################################################################

decipher_ECB = AES.new(key, AES.MODE_ECB)
decipher_CTR = AES.new(key, AES.MODE_CTR, nonce=nonce)
decipher_OFB = AES.new(key, AES.MODE_OFB, IV)
decipher_CFB = AES.new(key, AES.MODE_CFB, IV)

try:
    decipher_GCM = AES.new(key, AES.MODE_GCM, nonce=nonce)
    text = unpad(decipher_GCM.decrypt_and_verify(
        ciphertext_data_GCM, mac_cifrado), BLOCK_SIZE_AES).decode("utf-8", "ignore")
    print("GCM " + text)
except (ValueError, KeyError) as e:
    print("Error de Integridad - Enviar mensaje de nuevo")

# Imprimimos los datos descifrados

new_data = unpad(decipher_ECB.decrypt(ciphertext_data_ECB),
                 BLOCK_SIZE_AES).decode("utf-8", "ignore")
print("ECB " + new_data)

new_data = unpad(decipher_CTR.decrypt(ciphertext_data_CTR),
                 BLOCK_SIZE_AES).decode("utf-8", "ignore")
print("CTR " + new_data)

new_data = unpad(decipher_OFB.decrypt(ciphertext_data_OFB),
                 BLOCK_SIZE_AES).decode("utf-8", "ignore")
print("OFB " + new_data)

new_data = unpad(decipher_CFB.decrypt(ciphertext_data_CFB),
                 BLOCK_SIZE_AES).decode("utf-8", "ignore")
print("CFB " + new_data)
