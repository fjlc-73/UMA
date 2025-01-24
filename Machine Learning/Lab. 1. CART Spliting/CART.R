#Autor: Fernando Javier López Cerezo

########################################## Apartado A ####################################

datos <- data.frame("x1"=c(
  2.771244718,
  1.728571309,
  3.678319846,
  3.961043357,
  2.999208922,
  7.497545867,
  9.00220326,
  7.444542326,
  10.12493903,
  6.642287351
),
"x2"=c(
  1.784783929,
  1.169761413,
  2.81281357,
  2.61995032,
  2.209014212,
  3.162953546,
  3.339047188,
  0.476683375,
  3.234550982,
  3.319983761
),
"y"=c(
  0,
  0,
  0,
  0,
  0,
  1,
  1,
  1,
  1,
  1
))

mejor_xj_s <- function(data){
  numColDatos <- 2
  mejorRSS <- Inf
  mejorxj <- NULL
  mejorS <- NULL
  
  for (col in 1:numColDatos){
    xj <- colnames(data)[col]
    for(fila in 1:nrow(data)){
      s<-data[fila,col]
      R1<-data[data[xj]<s,]
      R2<-data[data[xj]>=s,]
      RSS <- sum((R1$y-mean(R1$y))^2)+sum((R2$y-mean(R2$y))^2)
      if(RSS < mejorRSS){
        mejorRSS <- RSS
        mejorxj <- xj
        mejorS <- s
      }
    }
  }
  return (list(mejorxj=mejorxj, mejorS=mejorS, mejorRSS=mejorRSS))
}

print(mejor_xj_s(datos))

################################ Apartado B ################################################

arbol_nivel_2 <- function(data, xj, s) {
  R1 <- data[data[[xj]] < s, ]
  R2 <- data[data[[xj]] >= s, ]
  
  # Construir Árbol T1 
  mejorT1 <- mejor_xj_s(R2)
  T1 <- list(
    Nivel_0 = paste(xj, "<", s),
    Nivel_1_Derecha = paste(mejorT1$mejorxj, "<", mejorT1$mejorS),
    Nivel_1_Izquierda = paste("Etiqueta:", mean(R2$y[R2[[mejorT1$mejorxj]] >= mejorT1$mejorS]))
  )
  
  # Construir Árbol T2 
  mejorT2 <- mejor_xj_s(R1)
  T2 <- list(
    Nivel_0 = paste(xj, "<", s),
    Nivel_1_Derecha = paste("Etiqueta:", mean(R1$y[R1[[mejorT2$mejorxj]] >= mejorT2$mejorS])),
    Nivel_1_Izquierda = paste(mejorT2$mejorxj, "<", mejorT2$mejorS)
  )
  
  return(list(T1 = T1, T2 = T2))
}

evaluar_arbol <- function(data, arbol) {
  predicciones <- numeric(nrow(data)) 
  
  for (i in 1:nrow(data)) {
    if (data[[substr(arbol$Nivel_0, 1, 2)]][i] < as.numeric(strsplit(arbol$Nivel_0, "<")[[1]][2])) {
      if (startsWith(arbol$Nivel_1_Izquierda, "Etiqueta")) {
        predicciones[i] <- as.numeric(strsplit(arbol$Nivel_1_Izquierda, ":")[[1]][2])
      } else {
        if (data[[substr(arbol$Nivel_1_Izquierda, 1, 2)]][i] < as.numeric(strsplit(arbol$Nivel_1_Izquierda, "<")[[1]][2])) {
          predicciones[i] <- 0
        } else {
          predicciones[i] <- 1
        }
      }
    } else {
      if (startsWith(arbol$Nivel_1_Derecha, "Etiqueta")) {
        predicciones[i] <- as.numeric(strsplit(arbol$Nivel_1_Derecha, ":")[[1]][2])
      } else {
        if (data[[substr(arbol$Nivel_1_Derecha, 1, 2)]][i] < as.numeric(strsplit(arbol$Nivel_1_Derecha, "<")[[1]][2])) {
          predicciones[i] <- 0
        } else {
          predicciones[i] <- 1
        }
      }
    }
  }
  accuracy <- mean(predicciones == data$y)
  return(accuracy)
}

# Ejecutar el apartado B
result <- mejor_xj_s(datos)
xj <- result$mejorxj
s <- result$mejorS

arboles <- arbol_nivel_2(datos, xj, s)

# Evaluar T1 y T2
accuracy_T1 <- evaluar_arbol(datos, arboles$T1)
accuracy_T2 <- evaluar_arbol(datos, arboles$T2)

print(list(T1 = arboles$T1, T2 = arboles$T2, Accuracy_T1 = accuracy_T1, Accuracy_T2 = accuracy_T2))
