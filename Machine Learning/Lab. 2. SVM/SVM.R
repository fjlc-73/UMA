#Autor: Fernando Javier López Cerezo

library(kernlab)
library(MASS)
library(e1071)

print_clasificacion <- function(x, w, b) {
  
  valor_clasificacion <- t(w) %*% x + b
  
  if (valor_clasificacion >= 0) {
    clase <- 1
    color <- "blue"
  } else {
    clase <- -1
    color <- "red"
  }
  
  cat("Punto:", sprintf("(%g, %g)", x[1], x[2]), "\n")
  cat("Pertenece a la clase:", clase, "\n")
  
  points(x[1], x[2], col = color, pch = 19)
  text(x[1], x[2], labels = paste("(", x[1], ",", x[2], ")", sep = ""), pos = 3)
}


distancia<- function (x,y) {
  sqrt(sum((x-y)^2))
}

########################################## Apartado A: Manualmente ####################################

# Datos
A = c(0, 0)
B = c(4, 4)
YA = 1
YB = -1

# Creamos el conjunto de datos
dataA <- data.frame(x1 = c(0, 4), x2 = c(0, 4), y = c(1, -1))

# 1. Vectores de soporte
vsA <- matrix(c(A, B), nrow = 2, byrow = TRUE)
cat("1.Vectores de soporte:\n")
print(vsA)

# 2. Calculamos los valores del kernel
KAA = t(A) %*% A
KAB = t(A) %*% B
KBB = t(B) %*% B
cat("2.Valores del kernel:\n")
cat("K(A,A):", KAA, "\nK(A,B):", KAB, "\nK(B,B):", KBB, "\n")

b <- c(0, 1, -1)
AM <- matrix(c(YA, YB, 0, KAA * YA, KAB * YB, 1, KAB * YA, KBB * YB, 1), ncol = 3, byrow = TRUE)
soluciones = qr.solve(AM, b)
ALFA = matrix(c(soluciones[1], soluciones[2]), ncol = 1)
YY = matrix(c(YA, YB), ncol = 1)

# 3. Vector de pesos normal al hiperplano (W)
wA <- t(vsA) %*% (ALFA * YY)
cat("3.Vector de pesos W:\n")
print(wA)

# 4. Calcular ancho del canal
widthA = 2 / (sqrt(sum((wA)^2)))
cat("4.Ancho del canal:", widthA, "\n")

# 5. Calcular vector B
bA <- YA - (t(wA) %*% A)
cat("5.Valor de b:", bA, "\n")

# 6. Ecuación del hiperplano y planos de soporte
cat("6.Ecuación del hiperplano y planos de soporte: \n")
cat("Ecuación del hiperplano: [", wA, "]' * x + [", bA, "] = 0\n")
cat("Plano de soporte positivo: [", wA, "]' * x + [", bA, "] = 1\n")
cat("Plano de soporte negativo: [", wA, "]' * x + [", bA, "] = -1\n")

# Crear un gráfico
par(mfrow = c(1, 1))
plot(dataA[, c("x1", "x2")], col = ifelse(dataA$y == 1, "blue", "red"), pch = 19, xlim = c(-7, 7), ylim = c(-7, 7), 
     xlab = "x1", ylab = "x2", main = "Clasificador SVM Lineal")
# Etiquetar los puntos
for (i in 1:nrow(dataA)) {
  text(dataA$x1[i], dataA$x2[i], labels = paste("(", dataA$x1[i], ",", dataA$x2[i], ")", sep = ""), pos = 3)
}
# Agregar una leyenda
legend("topleft", legend = c("Y = +1", "Y = -1"), col = c("blue", "red"), pch = 19, xpd = TRUE)

# Dibujar el hiperplano
abline(a = -bA / wA[2], b = -wA[1] / wA[2], col = "green")
abline(a = (1 - bA) / wA[2], b = -wA[1] / wA[2], col = "red", lty = 2)
abline(a = (-1 - bA) / wA[2], b = -wA[1] / wA[2], col = "red", lty = 2)

# 7. Determinamos a la clase que pertenece cada uno
# Puntos a clasificar
x1 = c(5, 6)
x2 = c(1, -4)

cat("7.Clasificación A.1:\n")
print_clasificacion(x1, wA, bA)
print_clasificacion(x2, wA, bA)

########################################## Apartado A: Kernlab ####################################

# Datos
A = c(0, 0)
B = c(4, 4)

# Datos de entrenamiento
dataA <- data.frame(x1 = c(0, 4), x2 = c(0, 4), y = c(1, -1))

# Convertimos y en factor
dataA$y <- as.factor(dataA$y)

# Entrenar el modelo SVM con kernlab usando un kernel lineal
svmA <- ksvm(as.matrix(dataA[, c("x1", "x2")]), dataA$y, kernel = "vanilladot", C = 1)

# 1. Vectores de soporte
vsA <- dataA[alphaindex(svmA)[[1]], 1:2]
cat("1.Vectores de soporte:\n")
print(vsA)

# 2. Calcular valores del Kernel
KAA <- t(A) %*% A
KAB <- t(A) %*% B
KBB <- t(B) %*% B
cat("2.Valores del kernel:\n")
cat("K(A,A):", KAA, "\nK(A,B):", KAB, "\nK(B,B):", KBB, "\n")

# 3. Vector de pesos (W)
wA <- colSums(coef(svmA)[[1]] * as.matrix(vsA))
cat("3.Vector de pesos W:\n")
print(wA)

# 4. Ancho del canal
widthA <- 2 / sqrt(sum(wA^2))
cat("4.Ancho del canal:", widthA, "\n")

# 5. Vector B (sesgo)
bA <- -b(svmA)
cat("5.Valor de b:", bA, "\n")

# 6. Ecuación del hiperplano y planos de soporte
cat("6.Ecuación del hiperplano y planos de soporte: \n")
cat("Ecuación del hiperplano: [", wA, "]' * x + [", bA, "] = 0\n")
cat("Plano de soporte positivo: [", wA, "]' * x + [", bA, "] = 1\n")
cat("Plano de soporte negativo: [", wA, "]' * x + [", bA, "] = -1\n")

# Graficar los datos y el hiperplano
par(mfrow = c(1, 1))
plot(dataA[, c("x1", "x2")], col = ifelse(dataA$y == 1, "blue", "red"), pch = 19, xlim = c(-7, 7), ylim = c(-7, 7))
for (i in 1:nrow(dataA)) {
  text(dataA$x1[i], dataA$x2[i], labels = paste("(", dataA$x1[i], ",", dataA$x2[i], ")", sep = ""), pos = 3)
}
legend("topleft", legend = c("Y = +1", "Y = -1"), col = c("blue", "red"), pch = 19)

# Dibujar el hiperplano y los planos de soporte
abline(a = -bA / wA[2], b = -wA[1] / wA[2], col = "green")
abline(a = (1 - bA) / wA[2], b = -wA[1] / wA[2], col = "red", lty = 2)
abline(a = (-1 - bA) / wA[2], b = -wA[1] / wA[2], col = "red", lty = 2)

# 7. Determinamos a la clase que pertenece cada uno
# Puntos a clasificar
x1 = c(5, 6)
x2 = c(1, -4)

cat("7.Clasificación A.2:\n")
print_clasificacion(x1, wA, bA)
print_clasificacion(x2, wA, bA)

########################################## Apartado A: IPOP ####################################

# Datos
x <- matrix(c(0, 0, 4, 4), ncol = 2, byrow = TRUE)
y <- c(1, -1)  # Etiquetas

# Calcular matriz H
H <- (y %*% t(y)) * (x %*% t(x))

# Vector c
c <- rep(-1, length(y))

# Restricciones
MA <- t(y)
b <- 0
l <- rep(0, length(y))
u <- rep(1e6, length(y))

# Parámetro de tolerancia para el optimizador
r <- 0.1

# Resolver el problema de optimización con ipop
resultado <- ipop(c = c, H = H, A = MA, b = b, l = l, u = u, r = r)

# Extraer los multiplicadores de Lagrange (alfas)
alphas <- primal(resultado)

# Imprimir los alphas
cat("Multiplicadores de Lagrange (alphas):\n")
print(alphas)

# 1. Vectores de soporte
# Filtrar los vectores de soporte (donde alphas > un pequeño umbral)
support_indices <- which(alphas > 1e-5)
support_vectors <- x[support_indices, ]
cat("1.Vectores de soporte:\n")
print(support_vectors)

# 2. Calcular valores del Kernel
A <- c(0, 0)
B <- c(4, 4)
KAA <- sum(A * A)
KAB <- sum(A * B)
KBB <- sum(B * B)
cat("2.Valores del kernel:\n")
cat("K(A,A):", KAA, "\nK(A,B):", KAB, "\nK(B,B):", KBB, "\n")

# 3. Calcular el vector de pesos w
wA <- t(x) %*% (alphas * y)
cat("3.Vector de pesos W:\n")
print(wA)

# 4. Calcular el ancho del margen (canal)
widthA <- 2 / sqrt(sum(wA^2))
cat("4.Ancho del canal:", widthA, "\n")

# 5. Calcular el sesgo b usando los vectores de soporte
bA <- mean(y[support_indices] - x[support_indices, ] %*% wA)
cat("5.Valor de b:", bA, "\n")

# 6. Ecuación del hiperplano y planos de soporte
cat("6.Ecuación del hiperplano y planos de soporte:\n")
cat("Ecuación del hiperplano: [", wA, "]' * x + [", bA, "] = 0\n")
cat("Plano de soporte positivo: [", wA, "]' * x + [", bA, "] = 1\n")
cat("Plano de soporte negativo: [", wA, "]' * x + [", bA, "] = -1\n")


# Crear un data frame para graficar los puntos y asignar colores a las clases
data_points <- data.frame(x1 = x[, 1], x2 = x[, 2], y = factor(y))
# Graficar puntos de datos
plot(data_points[, c("x1", "x2")], col = ifelse(data_points$y == 1, "blue", "red"), 
     pch = 19, xlim = c(-7, 7), ylim = c(-7, 7), 
     xlab = "x1", ylab = "x2", main = "Hiperplano y márgenes")
# Etiquetas en los puntos de datos
for (i in 1:nrow(data_points)) {
  text(data_points$x1[i], data_points$x2[i], 
       labels = paste("(", data_points$x1[i], ",", data_points$x2[i], ")", sep = ""), 
       pos = 3)
}
# Leyenda para clases
legend("topleft", legend = c("Y = +1", "Y = -1"), col = c("blue", "red"), pch = 19)


# Dibujar el hiperplano y los márgenes usando w y b
abline(a = -b / wA[2], b = -wA[1] / wA[2], col = "green", lwd = 2)  # Hiperplano
abline(a = (1 - b) / wA[2], b = -wA[1] / wA[2], col = "red", lty = 2)  # Margen positivo
abline(a = (-1 - b) / wA[2], b = -wA[1] / wA[2], col = "red", lty = 2)  # Margen negativo


# 7.Determinamos a la clase que pertenece cada uno
# Puntos a clasificar
x1 <- c(5, 6)
x2 <- c(1, -4)

cat("7. Clasificación A.3:\n")
print_clasificacion(x1, wA, bA)
print_clasificacion(x2, wA, bA)

########################################## Apartado B: Manualmente ####################################

# Datos
A = c(2,0)
B = c(0,0)
C = c(1,1)

# Distancia entre A y B
distanceAB = distancia(A,B)
# Distancia entre A y C
distanceAC = distancia(A,C)

# Como la distancia entre A y C es la menor, A y C son los vectores soporte.

# 1. Vectores de soporte
vsB <- matrix(c(A, C), nrow = 2, byrow = TRUE)
cat("1. Vectores de soporte:\n")
print(vsB)

# 2. Calcular los valores del kernel
KAA <- t(A) %*% A
KAC <- t(A) %*% C
KCC <- t(C) %*% C
cat("2. Valores del kernel:\n")
cat("K(A,A):", KAA, "\nK(A,C):", KAC, "\nK(C,C):", KCC, "\n")

YA = 1
YC = -1

b <- c(0, 1, -1)
AM <- matrix(c(YA, YC, 0, KAA * YA, KAC * YC, 1, KAC * YA, KCC * YC, 1), ncol = 3, byrow = TRUE)
soluciones = qr.solve(AM, b)
ALFA = matrix(c(soluciones[1], soluciones[2]), ncol = 1)
YY = matrix(c(YA, YC), ncol = 1)

# 3. Vector de pesos normal al hiperplano (W)
wB <- t(vsB) %*% (ALFA * YY)
cat("3. Vector de pesos W:\n")
print(wB)

# 4. Calcular ancho del canal
widthB = 2 / (sqrt(sum(wB^2)))
cat("4. Ancho del canal:", widthB, "\n")

# 5. Calcular vector B
bB <- YA - (t(wB) %*% A)
cat("5. Valor de b:", bB, "\n")

# 6. Ecuación del hiperplano y planos de soporte
cat("6. Ecuación del hiperplano y planos de soporte: \n")
cat("Ecuación del hiperplano: [", wB, "]' * x + [", bB, "] = 0\n")
cat("Plano de soporte positivo: [", wB, "]' * x + [", bB, "] = 1\n")
cat("Plano de soporte negativo: [", wB, "]' * x + [", bB, "] = -1\n")

# Crear gráfico
par(mfrow = c(1, 1))
plot(dataB[, c("x1", "x2")], col = ifelse(dataB$y == 1, "blue", "red"), pch = 19, xlim = c(-7, 7), ylim = c(-7, 7))
for (i in 1:nrow(dataB)) {
  text(dataB$x1[i], dataB$x2[i], labels = paste("(", dataB$x1[i], ",", dataB$x2[i], ")", sep = ""), pos = 3)
}
legend("topleft", legend = c("Y = +1", "Y = -1"), col = c("blue", "red"), pch = 19)

# Dibujar hiperplano y márgenes
abline(a = -bB / wB[2], b = -wB[1] / wB[2], col = "green")
abline(a = (1 - bB) / wB[2], b = -wB[1] / wB[2], col = "red", lty = 2)
abline(a = (-1 - bB) / wB[2], b = -wB[1] / wB[2], col = "red", lty = 2)

# 7. Determinamos a la clase que pertenece cada uno
# Puntos a clasificar
x1 = c(5, 6)
x2 = c(1, -4)

cat("7. Clasificación B.1:\n")
print_clasificacion(x1, wB, bB)
print_clasificacion(x2, wB, bB)

########################################## Apartado B: Kernlab ####################################

# Datos
A = c(2, 0)
B = c(0, 0)
C = c(1, 1)

# Distancia entre A y B
distanceAB = distancia(A, B)
# Distancia entre A y C
distanceAC = distancia(A, C)

# Como la distancia entre A y C es la menor, A y C son los vectores soporte..

# Convertimos y en factor
dataB$y <- as.factor(dataB$y)

# Entrenar el modelo SVM con kernlab usando un kernel lineal
svmB <- ksvm(as.matrix(dataB[, c("x1", "x2")]), dataB$y, kernel = "vanilladot", C = 1)

# 1. Vectores de soporte
vsB <- dataB[alphaindex(svmB)[[1]], 1:2]
cat("1. Vectores de soporte:\n")
print(vsB)

# 2. Calcular los valores del kernel
KAA <- t(A) %*% A
KAC <- t(A) %*% C
KCC <- t(C) %*% C
cat("2. Valores del kernel:\n")
cat("K(A,A):", KAA, "\nK(A,C):", KAC, "\nK(C,C):", KCC, "\n")

# 3. Vector de pesos normal al hiperplano (W)
wB <- colSums(coef(svmB)[[1]] * as.matrix(vsB))
cat("3. Vector de pesos W:\n")
print(wB)

# 4. Calcular el ancho del canal
widthB <- 2 / sqrt(sum(wB^2))
cat("4. Ancho del canal:", widthB, "\n")

# 5. Calcular el valor de b
bB <- -b(svmB)
cat("5. Valor de b:", bB, "\n")

# 6. Ecuación del hiperplano y planos de soporte
cat("6. Ecuación del hiperplano y planos de soporte: \n")
cat("Ecuación del hiperplano: [", wB, "]' * x + [", bB, "] = 0\n")
cat("Plano de soporte positivo: [", wB, "]' * x + [", bB, "] = 1\n")
cat("Plano de soporte negativo: [", wB, "]' * x + [", bB, "] = -1\n")

# Crear gráfico
par(mfrow = c(1, 1))
plot(dataB[, c("x1", "x2")], col = ifelse(dataB$y == 1, "blue", "red"), pch = 19, xlim = c(-7, 7), ylim = c(-7, 7))
for (i in 1:nrow(dataB)) {
  text(dataB$x1[i], dataB$x2[i], labels = paste("(", dataB$x1[i], ",", dataB$x2[i], ")", sep = ""), pos = 3)
}
legend("topleft", legend = c("Y = +1", "Y = -1"), col = c("blue", "red"), pch = 19)

# Dibujar hiperplano y márgenes
abline(a = -bB / wB[2], b = -wB[1] / wB[2], col = "green")
abline(a = (1 - bB) / wB[2], b = -wB[1] / wB[2], col = "red", lty = 2)
abline(a = (-1 - bB) / wB[2], b = -wB[1] / wB[2], col = "red", lty = 2)

# 7. Determinamos a la clase que pertenece cada uno
# Puntos a clasificar
x1 = c(5, 6)
x2 = c(1, -4)

cat("7. Clasificación B.2:\n")
print_clasificacion(x1, wB, bB)
print_clasificacion(x2, wB, bB)

########################################## Apartado B: IPOP ####################################

x <- matrix(c(2, 0, 1, 1), ncol = 2, byrow = TRUE)
y <- c(1, -1)  # Etiquetas

# Calcular matriz H
H <- (y %*% t(y)) * (x %*% t(x))

# Vector c
c <- rep(-1, length(y))

# Restricciones
MA <- t(y)
b <- 0
l <- rep(0, length(y))
u <- rep(1e6, length(y))

# Parámetro de tolerancia para el optimizador
r <- 0.1

# Resolver el problema de optimización con ipop
resultado <- ipop(c = c, H = H, A = MA, b = b, l = l, u = u, r = r)

# Extraer los multiplicadores de Lagrange (alfas)
alphas <- primal(resultado)

# Imprimir los alphas
cat("Multiplicadores de Lagrange (alphas):\n")
print(alphas)

# 1. Vectores de soporte
# Filtrar los vectores de soporte (donde alphas > un pequeño umbral)
support_indices <- which(alphas > 1e-5)
support_vectors <- x[support_indices, ]
cat("1.Vectores de soporte:\n")
print(support_vectors)

# 2. Calcular los valores del kernel
A = c(2,0) 
C = c(1,1) 
KAA <- t(A) %*% A
KAC <- t(A) %*% C
KCC <- t(C) %*% C
cat("2.Valores del kernel:\n")
cat("K(A,A):", KAA, "\nK(A,C):", KAC, "\nK(C,C):", KCC, "\n")

# 3. Calcular el vector de pesos w
wB <- t(x) %*% (alphas * y)
cat("3.Vector de pesos W:\n")
print(wB)

# 4. Calcular el ancho del margen (canal)
widthB <- 2 / sqrt(sum(wB^2))
cat("4.Ancho del canal:", widthB, "\n")


# 5. Calcular el sesgo b usando los vectores de soporte
bB <- mean(y[support_indices] - x[support_indices, ] %*% wB)
cat("5.Valor de b:", bB, "\n")

# 6. Ecuación del hiperplano y planos de soporte
cat("6.Ecuación del hiperplano y planos de soporte:\n")
cat("Ecuación del hiperplano: [", wB, "]' * x + [", bB, "] = 0\n")
cat("Plano de soporte positivo: [", wB, "]' * x + [", bB, "] = 1\n")
cat("Plano de soporte negativo: [", wB, "]' * x + [", bB, "] = -1\n")


# Crear un data frame para graficar los puntos y asignar colores a las clases
data_points <- data.frame(x1 = x[, 1], x2 = x[, 2], y = factor(y))
# Graficar puntos de datos
plot(data_points[, c("x1", "x2")], col = ifelse(data_points$y == 1, "blue", "red"), 
     pch = 19, xlim = c(-7, 7), ylim = c(-7, 7), 
     xlab = "x1", ylab = "x2", main = "Hiperplano y márgenes")
# Etiquetas en los puntos de datos
for (i in 1:nrow(data_points)) {
  text(data_points$x1[i], data_points$x2[i], 
       labels = paste("(", data_points$x1[i], ",", data_points$x2[i], ")", sep = ""), 
       pos = 3)
}
# Leyenda para clases
legend("topleft", legend = c("Y = +1", "Y = -1"), col = c("blue", "red"), pch = 19)


# Dibujar el hiperplano y los márgenes usando w y b
abline(a = -b / wB[2], b = -wB[1] / wB[2], col = "green", lwd = 2)  # Hiperplano
abline(a = (1 - b) / wB[2], b = -wB[1] / wB[2], col = "red", lty = 2)  # Margen positivo
abline(a = (-1 - b) / wB[2], b = -wB[1] / wB[2], col = "red", lty = 2)  # Margen negativo


# 7.Determinamos a la clase que pertenece cada uno
# Puntos a clasificar
x1 <- c(5, 6)
x2 <- c(1, -4)

cat("7. Clasificación B.3:\n")
print_clasificacion(x1,wB, bB)
print_clasificacion(x2,wB, bB)

########################################## Apartado C: Manualmente ####################################

# Definir puntos A, E y otros
A = c(2, 2)
E = c(1, 1)

dataC <- data.frame(
  x1 = c(2, 2, -2, -2, 1, 1, -1, -1),
  x2 = c(2, -2, -2, 2, 1, -1, -1, 1),
  y = c(1, 1, 1, 1, -1, -1, -1, -1)
)

# 1. Vectores de soporte
vsC <- matrix(c(A, E), nrow = 2, byrow = TRUE)
cat("1. Vectores de soporte:\n")
print(vsC)

# 2. Calcular los valores del kernel
KAA <- t(A) %*% A
KAE <- t(A) %*% E
KEE <- t(E) %*% E
cat("2. Valores del kernel:\n")
cat("K(A,A):", KAA, "\nK(A,E):", KAE, "\nK(E,E):", KEE, "\n")

# Etiquetas para los vectores de soporte
YA = 1
YE = -1

# Resolver el sistema de ecuaciones para los multiplicadores de Lagrange (alfa)
b <- c(0, 1, -1)
AM <- matrix(c(YA, YE, 0, KAA * YA, KAE * YE, 1, KAE * YA, KEE * YE, 1), ncol = 3, byrow = TRUE)
soluciones = qr.solve(AM, b)
ALFA = matrix(c(soluciones[1], soluciones[2]), ncol = 1)
YY = matrix(c(YA, YE), ncol = 1)

# 3. Calcular el vector de pesos W
wC <- t(vsC) %*% (ALFA * YY)
cat("3. Vector de pesos W:\n")
print(wC)

# 4. Calcular el ancho del canal
widthC <- 2 / sqrt(sum(wC^2))
cat("4. Ancho del canal:", widthC, "\n")

# 5. Calcular el valor de b
bC <- YA - (t(wC) %*% A)
cat("5. Valor de b:", bC, "\n")

# 6. Ecuación del hiperplano y planos de soporte
if (!all(wC == 0)) {
  cat("6. Ecuación del hiperplano y planos de soporte:\n")
  cat("Ecuación del hiperplano: [", wC, "]' * x + [", bC, "] = 0\n")
  cat("Plano de soporte positivo: [", wC, "]' * x + [", bC, "] = 1\n")
  cat("Plano de soporte negativo: [", wC, "]' * x + [", bC, "] = -1\n")
}

# Crear gráfico
par(mfrow = c(1, 1))
plot(dataC[, c("x1", "x2")], col = ifelse(dataC$y == 1, "blue", "red"), pch = 19, xlim = c(-7, 7), ylim = c(-7, 7))
for (i in 1:nrow(dataC)) {
  text(dataC$x1[i], dataC$x2[i], labels = paste("(", dataC$x1[i], ",", dataC$x2[i], ")", sep = ""), pos = 3)
}
legend("topleft", legend = c("Y = +1", "Y = -1"), col = c("blue", "red"), pch = 19)

# Dibujar el hiperplano y los márgenes
if (!all(wC == 0)) {
  abline(a = -bC / wC[2], b = -wC[1] / wC[2], col = "green")
  abline(a = (1 - bC) / wC[2], b = -wC[1] / wC[2], col = "red", lty = 2)
  abline(a = (-1 - bC) / wC[2], b = -wC[1] / wC[2], col = "red", lty = 2)
}

# Puntos a clasificar
x1 = c(5, 6)
x2 = c(1, -4)

cat("7. Clasificación C.1:\n")
print_clasificacion(x1, wC, bC)
print_clasificacion(x2, wC, bC)

cat("\nEl punto [5, 6] se clasifica positivamente.")
cat("\nEl punto [1, -4] se clasifica negativamente.")

########################################## Apartado C: Kernlab ####################################

dataC <- data.frame(
  x1 = c(2, 2, -2, -2, 1, 1, -1, -1),
  x2 = c(2, -2, -2, 2, 1, -1, -1, 1),
  y = c(1, 1, 1, 1, -1, -1, -1, -1)
)

# Convertimos y en factor
dataC$y <- as.factor(dataC$y) 

# Entrenar el modelo SVM con kernlab usando un kernel lineal
svmC <- ksvm(as.matrix(dataC[, c("x1", "x2")]), dataC$y, kernel = "vanilladot", C = 1)

# 1. Vectores de soporte
vsC <- dataC[alphaindex(svmC)[[1]], 1:2]
cat("1.Vectores de soporte:\n")
print(vsC)

# 2. Calcular los valores del kernel
# Matriz de características (sin la columna 'y')
X <- as.matrix(dataC[, c("x1", "x2")])
y <- dataC$y

# Calcular la matriz del kernel (producto punto)
K <- X %*% t(X)

cat("2.Valores del kernel:\n")
print(K)

# 3. Vector de pesos normal al hiperplano (W)
wC <- colSums(coef(svmC)[[1]] * as.matrix(vsC))
cat("3.Vector de pesos W:\n")
print(wC)

# 4. Calcular el ancho del canal
widthC <- 2 / sqrt(sum(wC^2))
cat("4.Ancho del canal:", widthC, "\n")

# 5. Calcular el valor de b
bC <- -b(svmC)
cat("5.Valor de b:", bC, "\n")

# Es posible (como en este caso) que el problema no sea linealmente separable
# Verificar si w es nulo
if (all(wC == 0)) {
  cat("El vector w es nulo. Prueba con un kernel no lineal o ajusta los parámetros.\n")
  
  # Entrenar con un kernel RBF en caso de no separabilidad lineal
  svmC <- ksvm(X, y, kernel = "rbfdot", C = 1)
  
  # Recalcular vectores de soporte, w y b con el nuevo modelo
  vsC <- dataC[alphaindex(svmC)[[1]], 1:2]
  wC <- colSums(coef(svmC)[[1]] * as.matrix(vsC))
  bC <- -b(svmC)
  
  cat("Modelo ajustado con kernel RBF.\n")
  cat("3.Vector de pesos W:\n")
  print(wC)
  widthC <- 2 / sqrt(sum(wC^2))
  cat("4.Ancho del canal:", widthC, "\n")
  cat("5.Valor de b:", bC, "\n")
} else {
  # Calcular el ancho del margen solo si w no es nulo
  widthC <- 2 / sqrt(sum(wC^2))
  cat("4.Ancho del canal:", widthC, "\n")
}

# 6. Ecuación del hiperplano y planos de soporte
# Mostrar ecuaciones del hiperplano y márgenes si w no es nulo
if (!all(wC == 0)) {
  cat("6.Ecuación del hiperplano y planos de soporte: \n")
  cat("Ecuación del hiperplano: [", wC, "]' * x + [", bC, "] = 0\n")
  cat("Plano de soporte positivo: [", wC, "]' * x + [", bC, "] = 1\n")
  cat("Plano de soporte negativo: [", wC, "]' * x + [", bC, "] = -1\n")
}

# Crear gráfico
par(mfrow = c(1, 1))
plot(dataC[, c("x1", "x2")], col = ifelse(dataC$y == 1, "blue", "red"), pch = 19, xlim = c(-7, 7), ylim = c(-7, 7))
for (i in 1:nrow(dataC)) {
  text(dataC$x1[i], dataC$x2[i], labels = paste("(", dataC$x1[i], ",", dataC$x2[i], ")", sep = ""), pos = 3)
}
legend("topleft", legend = c("Y = +1", "Y = -1"), col = c("blue", "red"), pch = 19)

# Dibujar hiperplano y márgenes
# Graficar el hiperplano y los márgenes si w no es nulo
if (!all(wC == 0)) {
  abline(a = -bC / wC[2], b = -wC[1] / wC[2], col = "green")
  abline(a = (1 - bC) / wC[2], b = -wC[1] / wC[2], col = "red", lty = 2)
  abline(a = (-1 - bC) / wC[2], b = -wC[1] / wC[2], col = "red", lty = 2)
}

# 7.Determinamos a la clase que pertenece cada uno
# Puntos a clasificar
x1 = c(5, 6)
x2 = c(1, -4)

cat("7.Clasificación C.3:\n")
print_clasificacion(x1, wC, bC)
print_clasificacion(x2, wC, bC)

cat("\nEl punto [5, 6] se clasifica positivamente.")
cat("\nEl punto [1, -4] se clasifica positivamente. \n")
cat("\nNo tiene sentido. Debemos aplicar la función de transformacion.\n")

########################################## Apartado C: IPOP ####################################

x <- matrix(c(2, 2, 1, 1), ncol = 2, byrow = TRUE)
y <- c(1, -1)  # Etiquetas

# Calcular matriz H
H <- (y %*% t(y)) * (x %*% t(x))

# Vector c
c <- rep(-1, length(y))

# Restricciones
MA <- t(y)
b <- 0
l <- rep(0, length(y))
u <- rep(1e6, length(y))

# Parámetro de tolerancia para el optimizador
r <- 0.1

# Resolver el problema de optimización con ipop
resultado <- ipop(c = c, H = H, A = MA, b = b, l = l, u = u, r = r)

# Extraer los multiplicadores de Lagrange (alfas)
alphas <- primal(resultado)

# Imprimir los alphas
cat("Multiplicadores de Lagrange (alphas):\n")
print(alphas)


# 1. Vectores de soporte
# Filtrar los vectores de soporte (donde alphas > un pequeño umbral)
support_indices <- which(alphas > 1e-5)
support_vectors <- x[support_indices, ]
cat("1.Vectores de soporte:\n")
print(support_vectors)

# 2. Calcular los valores del kernel
A = c(2,2) 
E = c(1,1) 
KAA <- t(A) %*% A
KAE <- t(A) %*% E
KEE <- t(E) %*% E
cat("2.Valores del kernel:\n")
cat("K(A,A):", KAA, "\nK(A,E):", KAE, "\nK(E,E):", KEE, "\n")

# 3. Calcular el vector de pesos w
wC <- t(x) %*% (alphas * y)
cat("3.Vector de pesos W:\n")
print(wC)

# 4. Calcular el ancho del margen (canal)
widthC <- 2 / sqrt(sum(wC^2))
cat("4.Ancho del canal:", widthC, "\n")

# 5. Calcular el sesgo b usando los vectores de soporte
bC <- mean(y[support_indices] - x[support_indices, ] %*% wC)
cat("5.Valor de b:", bC, "\n")

# 6. Ecuación del hiperplano y planos de soporte
# Mostrar ecuaciones del hiperplano y márgenes si w no es nulo
if (!all(wC == 0)) {
  cat("6.Ecuación del hiperplano y planos de soporte: \n")
  cat("Ecuación del hiperplano: [", wC, "]' * x + [", bC, "] = 0\n")
  cat("Plano de soporte positivo: [", wC, "]' * x + [", bC, "] = 1\n")
  cat("Plano de soporte negativo: [", wC, "]' * x + [", bC, "] = -1\n")
}

# Crear gráfico
par(mfrow = c(1, 1))
plot(dataC[, c("x1", "x2")], col = ifelse(dataC$y == 1, "blue", "red"), pch = 19, xlim = c(-7, 7), ylim = c(-7, 7))
for (i in 1:nrow(dataC)) {
  text(dataC$x1[i], dataC$x2[i], labels = paste("(", dataC$x1[i], ",", dataC$x2[i], ")", sep = ""), pos = 3)
}
legend("topleft", legend = c("Y = +1", "Y = -1"), col = c("blue", "red"), pch = 19)

# Dibujar hiperplano y márgenes
# Graficar el hiperplano y los márgenes si w no es nulo
if (!all(wC == 0)) {
  abline(a = -bC / wC[2], b = -wC[1] / wC[2], col = "green")
  abline(a = (1 - bC) / wC[2], b = -wC[1] / wC[2], col = "red", lty = 2)
  abline(a = (-1 - bC) / wC[2], b = -wC[1] / wC[2], col = "red", lty = 2)
}

# 7.Determinamos a la clase que pertenece cada uno
# Puntos a clasificar
x1 = c(5, 6)
x2 = c(1, -4)

cat("7.Clasificación C.3:\n")
print_clasificacion(x1, wC, bC)
print_clasificacion(x2, wC, bC)

cat("\nEl punto [5, 6] se clasifica positivamente.")
cat("\nEl punto [1, -4] se clasifica negativamente.")

########################################## APARTADO D ###########################################

# Función de transformación
funcion_transformacion <- function(a) {
  if(sqrt(a[1]^2 + a[2]^2) > 2) {
    a[1] <- 4 - a[2] + abs(a[1] - a[2])
    a[2] <- 4 - a[1] + abs(a[1] - a[2])
  }
  a
}

A = c(2,2) 
B = c(2,-2)
C = c(-2,-2)
D = c(-2,2)
E = c(1,1) 
FF = c(1,-1)
G = c(-1,-1)
H = c(-1,1)

# Aplicar la función de transformación a los vectores X e Y
A2 <- funcion_transformacion(A)
B2 <- funcion_transformacion(B)
C2 <- funcion_transformacion(C)
D2 <- funcion_transformacion(D)

E2 <- funcion_transformacion(E)
FF2 <- funcion_transformacion(FF)
G2 <- funcion_transformacion(G)
H2 <- funcion_transformacion(H)

dataDTrans <- data.frame(
  x1 = c(A2[1], B2[1], C2[1], D2[1], E2[1], FF2[1], G2[1], H2[1]),
  x2 = c(A2[2], B2[2], C2[2], D2[2], E2[2], FF2[2], G2[2], H2[2]),
  y = c(1, 1, 1, 1, -1, -1, -1, -1)
)

# Convertimos y en factor
dataDTrans$y <- as.factor(dataDTrans$y) 

# Entrenar el modelo SVM con kernlab usando un kernel lineal
svmD <- ksvm(as.matrix(dataDTrans[, c("x1", "x2")]), dataDTrans$y, kernel = "vanilladot", C = 1)

# 1. Vectores de soporte
vsD <- dataDTrans[alphaindex(svmD)[[1]], 1:2]
cat("1. Vectores de soporte:\n")
print(vsD)

# 2. Calcular los valores del kernel
# Matriz de características (sin la columna 'y')
X <- as.matrix(dataDTrans[, c("x1", "x2")])

# Calcular la matriz del kernel (producto punto)
K <- X %*% t(X)

cat("2. Valores del kernel:\n")
print(K)

# 3. Vector de pesos normal al hiperplano (W)
wD <- colSums(coef(svmD)[[1]] * as.matrix(vsD))
cat("3. Vector de pesos W:\n")
print(wD)

# 4. Calcular el ancho del canal
widthD <- 2 / sqrt(sum(wD^2))
cat("4. Ancho del canal:", widthD, "\n")

# 5. Calcular el valor de b
bD <- -b(svmD)
cat("5. Valor de b:", bD, "\n")

# 6. Ecuación del hiperplano y planos de soporte
cat("6. Ecuación del hiperplano y planos de soporte:\n")
cat("Ecuación del hiperplano: [", wD, "]' * x + [", bD, "] = 0\n")
cat("Plano de soporte positivo: [", wD, "]' * x + [", bD, "] = 1\n")
cat("Plano de soporte negativo: [", wD, "]' * x + [", bD, "] = -1\n")

# Crear gráfico
par(mfrow = c(1, 1))
plot(dataDTrans[, c("x1", "x2")], col = ifelse(dataDTrans$y == 1, "blue", "red"), pch = 19, xlim = c(-7, 7), ylim = c(-7, 7))
for (i in 1:nrow(dataDTrans)) {
  text(dataDTrans$x1[i], dataDTrans$x2[i], labels = paste("(", dataDTrans$x1[i], ",", dataDTrans$x2[i], ")", sep = ""), pos = 3)
}
legend("topleft", legend = c("Y = +1", "Y = -1"), col = c("blue", "red"), pch = 19)

# Dibujar hiperplano y márgenes
abline(a = -bD / wD[2], b = -wD[1] / wD[2], col = "green")
abline(a = (1 - bD) / wD[2], b = -wD[1] / wD[2], col = "red", lty = 2)
abline(a = (-1 - bD) / wD[2], b = -wD[1] / wD[2], col = "red", lty = 2)

# 7. Determinamos a la clase que pertenece cada uno
x1 = c(5, 6)
x2 = c(1, -4)

cat("7. Clasificación D:\n")
print_clasificacion(x1, wD, bD)
print_clasificacion(x2, wD, bD)

cat("\nEl punto [5, 6] se clasifica positivamente.\n")
cat("El punto [1, -4] se clasifica negativamente.\n")

########################################## APARTADO E ###########################################


dataE <- data.frame( 
  x1 = c(3,3,6,6,1,0,0,-1), 
  x2 = c(1,-1,1,-1,0,1,-1,0), 
  y = c(1,1,1,1,-1,-1,-1,-1) 
)

A = c(3,1) 
B = c(3,-1) 
C = c(6,1) 
D = c(6,-1) 
E = c(1,0) 
FF = c(0,1) 
G = c(0,-1) 
H = c(-1,0)

# Convertimos y en factor
dataE$y <- as.factor(dataE$y) 

# Entrenar el modelo SVM con kernlab usando un kernel lineal
svmE <- ksvm(as.matrix(dataE[, c("x1", "x2")]), dataE$y, kernel = "vanilladot", C = 1)

# 1. Vectores de soporte
vsE <- dataE[alphaindex(svmE)[[1]], 1:2]
cat("1.Vectores de soporte:\n")
print(vsE)

# 2. Calcular los valores del kernel
# Matriz de características (sin la columna 'y')
X <- as.matrix(dataE[, c("x1", "x2")])

# Calcular la matriz del kernel (producto punto)
K <- X %*% t(X)

cat("2.Valores del kernel:\n")
print(K)

# 3. Vector de pesos normal al hiperplano (W)
wE <- colSums(coef(svmE)[[1]] * as.matrix(vsE))
cat("3.Vector de pesos W:\n")
print(wE)

# 4. Calcular el ancho del canal
widthE <- 2 / sqrt(sum(wE^2))
cat("4.Ancho del canal:", widthE, "\n")

# 5. Calcular el valor de b
bE <- -b(svmE)
cat("5.Valor de b:", bE, "\n")

# 6. Ecuación del hiperplano y planos de soporte
cat("6.Ecuación del hiperplano y planos de soporte: \n")
cat("Ecuación del hiperplano: [", wE, "]' * x + [", bE, "] = 0\n")
cat("Plano de soporte positivo: [", wE, "]' * x + [", bE, "] = 1\n")
cat("Plano de soporte negativo: [", wE, "]' * x + [", bE, "] = -1\n")

# Crear gráfico
par(mfrow = c(1, 1))
plot(dataE[, c("x1", "x2")], col = ifelse(dataE$y == 1, "blue", "red"), pch = 19, xlim = c(-7, 7), ylim = c(-7, 7))
for (i in 1:nrow(dataE)) {
  text(dataE$x1[i], dataE$x2[i], labels = paste("(", dataE$x1[i], ",", dataE$x2[i], ")", sep = ""), pos = 3)
}
legend("topleft", legend = c("Y = +1", "Y = -1"), col = c("blue", "red"), pch = 19)

# Dibujar hiperplano y márgenes
abline(a = -bE / wE[2], b = -wE[1] / wE[2], col = "green")
abline(a = (1 - bE) / wE[2], b = -wE[1] / wE[2], col = "red", lty = 2)
abline(a = (-1 - bE) / wE[2], b = -wE[1] / wE[2], col = "red", lty = 2)

# 7.Determinamos a la clase que pertenece cada uno
# Puntos a clasificar
x1 = c(4, 5)

cat("7.Clasificación E:\n")
print_clasificacion(x1, wE, bE)

cat("\nEl punto [4, 5] se clasifica positivamente.")

########################################## APARTADO F ###########################################

data(iris)
dataF <- iris

# Entrenar el modelo SVM con kernlab usando un kernel lineal
svmF <- ksvm(Species ~ ., data = iris, kernel = "vanilladot", C = 1)

# 1. Vectores de soporte
vsF <- dataF[alphaindex(svmF)[[1]], 1:2]
cat("1.Vectores de soporte:\n")
print(vsF)

# 2. Calcular los valores del kernel (producto punto)
X <- as.matrix(dataF[, c("Sepal.Length", "Sepal.Width")])
K <- X %*% t(X)

cat("2.Valores del kernel:\n")
print(K)

# 3. Vector de pesos normal al hiperplano (W)
wF <- colSums(coef(svmF)[[1]] * as.matrix(vsF))
cat("3.Vector de pesos W:\n")
print(wF)

# 4. Calcular el ancho del canal (margen)
widthF <- 2 / sqrt(sum(wF^2))
cat("4.Ancho del canal:", widthF, "\n")

# 5. Calcular el valor de b
bF <- -b(svmF)
cat("5.Valor de b:", bF, "\n")

# 6. Mostrar las ecuaciones del hiperplano y planos de soporte para cada clase
cat("6.Ecuaciones del hiperplano y planos de soporte para cada clase:\n")
for (i in 1:length(bF)) {
  cat(paste("Para la clase", levels(iris$Species)[i], ":\n"))
  cat("Ecuación del hiperplano: ", wF, "* x + ", bF[i], " = 0\n")
  cat("Plano de soporte positivo ", i, ": [", wF, "]' * x + [", bF[i], "] = 1\n")
  cat("Plano de soporte negativo ", i, ": [", wF, "]' * x + [", bF[i], "] = -1\n")
}

# Crear gráfico de los datos de entrenamiento y las clases
par(mfrow = c(1, 1))
plot(dataF[, 1:2], col = as.factor(dataF$Species), pch = 19, xlim = c(-8, 8), ylim = c(-8, 8))
legend("topleft", legend = levels(dataF$Species), col = 1:length(levels(dataF$Species)), pch = 19)

# Dibujar márgenes de separación (uno para cada clase)
# Este paso es solo para ilustración y es más complejo en un escenario multiclase
for (i in 1:length(bF)) {
  abline(a = -bF[i] / wF[2], b = -wF[1] / wF[2], col = i)  # Hiperplano de cada clase
  abline(a = (1 - bF[i]) / wF[2], b = -wF[1] / wF[2], col = i, lty = 2)  # Margen positivo
  abline(a = (-1 - bF[i]) / wF[2], b = -wF[1] / wF[2], col = i, lty = 2)  # Margen negativo
}

# Función para clasificar puntos
print_clasificacion <- function(x, w, b) {
  # Calculamos el valor para cada clase
  resultados <- sapply(1:length(b), function(i) {
    t(w) %*% x + b[i]
  })
  
  # Determinamos la clase de acuerdo al valor más grande
  clase_predicha <- which.max(resultados)  # La clase con el valor máximo
  
  cat("Punto:", sprintf("(%g, %g)", x[1], x[2]), "\n")
  cat("Pertenece a la clase:", levels(iris$Species)[clase_predicha], "\n")
  
  # Visualización de los puntos
  points(x[1], x[2], col = ifelse(clase_predicha == 1, "blue", ifelse(clase_predicha == 2, "green", "red")), pch = 19)
  text(x[1], x[2], labels = paste("(", x[1], ",", x[2], ")", sep = ""), pos = 3)
}

# 7. Determinamos a la clase que pertenece cada uno
# Puntos a clasificar
x1 <- c(5, 6)
x2 <- c(1, -4)

cat("\n7.Clasificación E:\n")
print_clasificacion(x1, wF, bF)
print_clasificacion(x2, wF, bF)

cat("\nEl punto [5, 6] se clasifica como clase", levels(iris$Species)[which.max(sapply(1:length(bF), function(i) t(wF) %*% x1 + bF[i]))], ".\n")
cat("\nEl punto [1, -4] se clasifica como clase", levels(iris$Species)[which.max(sapply(1:length(bF), function(i) t(wF) %*% x2 + bF[i]))], ".\n")
