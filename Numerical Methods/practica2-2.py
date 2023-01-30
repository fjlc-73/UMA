# -*- coding: utf-8 -*-
"""
Spyder Editor

This is a temporary script file.
"""

from numpy import *
from matplotlib.pyplot import *

#EJERCICIO 1

def puntofijo(g, x0, eps, nmax):
    error=1+eps
    k=0
    while(error>eps and k<nmax):
        x1=g(x0)
        error=abs(x1-x0)
        x0=x1
        k+=1
    if k==nmax:
        print("Se alcanza el numero maximo de iteraciones ", nmax)
        print("El ultimo valor obtenido es", x1)
    else:
        print("Se alcanza la precision requerida en ", k, " iteraciones")
        print("La aproximacion obtenida es ", x1)
    return x1

#EJERCICIO 2

#a 

def g2a(x):
    return exp(-x)

puntofijo(g2a, 0.5, 1e-7, 100)

#b

def g2b(x):
    return x-(x-exp(-x))/(1+exp(-x))

puntofijo(g2b, 0.5, 1e-7, 100)

#EJERCICIO 3

#a

def g3a(x):
    return 2/3 + 0.093*sin(x)

puntofijo(g3a, 0.5, 1e-7, 100)

#b

def g3b(x):
    return x-(x-0.093*sin(x)-2/3)/(1-0.093*cos(x))

puntofijo(g3b, 0.5, 1e-7, 100)


#EJERCICIO 4

#a

puntofijo(cos, 0.5, 1e-7, 100)

#b 

def g4b(x):
    return x -(cos(x)-x)/(-sin(x)-1)

puntofijo(g4b, 0.5, 1e-7, 100)

#EJERCICIO 5

#a 

def g5a(x):
    return x - (x**5 -5*x**3 + 1)/(5*x**4 - 15*x**2)

puntofijo(g5a, 0.5, 1e-7, 100)

#b 
def g5b0(x):
    return x + (x**5 -5*x**3 + 1)

# puntofijo(g5b0, 0.5, 1e-7, 100)
# Este metodo produce overflow

def g5b1(x):
    return x + 0.2*(x**5 -5*x**3 + 1)

puntofijo(g5b1, 0.5, 1e-7, 100)

#EJERCICIO 6

#a 

def puntofijo2(f, g, x0, eps, nmax):
    error=abs(f(x0))
    k=0
    while(error>eps and k<nmax):
        x1=g(x0)
        error=abs(f(x1))
        x0=x1
        k+=1
    if k==nmax:
        print("Se alcanza el numero maximo de iteraciones ", nmax)
        print("El ultimo valor obtenido es", x1)
    else:
        print("Se alcanza la precision requerida en ", k, " iteraciones")
        print("La aproximacion obtenida es ", x1)
    return x1

#b 

x=linspace(0,1,100)
plot(x, x+(x-1)*exp(x))
plot(x, 0*x)

#c 










































