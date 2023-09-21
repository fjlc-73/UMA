# -*- coding: utf-8 -*-
"""
Spyder Editor

This is a temporary script file.

Práctica 4
"""

from numpy import *
from matplotlib.pyplot import *
from scipy.integrate import quad

#Ejercicio 0

def funexp(x):
    return exp(-x*x)

(iquad,equad) = quad(funexp,0,1)
print('La aproximación obtenida con quad es', iquad, 'y la cota de error es', equad)



#Ejercicio 1

def puntomedio(f,a,b):
    c=0.5*(a+b)
    return (b-a)*f(c)

ipuntomedio=puntomedio(funexp,0,1)
epuntomedio=abs(ipuntomedio-iquad)

print('La aproximación obtenida con puntomedio es', ipuntomedio, 'y el error es', epuntomedio)




#Ejercicio 2

def trapecio(f,a,b):
    return 0.5*(b-a)*(f(a)+f(b))

itrapecio=trapecio(funexp,0,1)
etrapecio=abs(itrapecio-iquad)

print('La aproximación obtenida con trapecio es', itrapecio, 'y el error es', etrapecio)



#Ejercicio 3

def simpson(f,a,b):
    c=0.5*(a+b)
    return ((b-a)/6)*(f(a)+4*f(c)+f(b))

isimpson=simpson(funexp,0,1)
esimpson=abs(isimpson-iquad)

print('La aproximación obtenida con trapecio es', isimpson, 'y el error es', esimpson)



#Ejercicio 4

def puntomedioc(f,a,b,N):
    x = linspace(a,b,N+1)
    c = 0.5*(x[0:N] + x[1:N+1])
    #c = x[0:N]+0.5*(b-a)/N       otra opción
    return (b-a)/N*sum(f(c))
    
ipuntomedioc10=puntomedioc(funexp,0,1,10)
epuntomedioc10=abs(ipuntomedioc10-iquad)

print('La aproximación obtenida con puntomedio compuesta con N=10 es', ipuntomedioc10, 'y el error es', epuntomedioc10)

ipuntomedioc20=puntomedioc(funexp,0,1,20)
epuntomedioc20=abs(ipuntomedioc20-iquad)

print('La aproximación obtenida con puntomedio compuesta con N=20 es', ipuntomedioc20, 'y el error es', epuntomedioc20, 'cociente de errores', epuntomedioc20/epuntomedioc10)

ipuntomedioc40=puntomedioc(funexp,0,1,40)
epuntomedioc40=abs(ipuntomedioc40-iquad)

print('La aproximación obtenida con puntomedio compuesta con N=40 es', ipuntomedioc40, 'y el error es', epuntomedioc40, 'cociente de errores', epuntomedioc40/epuntomedioc20)

ipuntomedioc80=puntomedioc(funexp,0,1,80)
epuntomedioc80=abs(ipuntomedioc80-iquad)

print('La aproximación obtenida con puntomedio compuesta con N=80 es', ipuntomedioc80, 'y el error es', epuntomedioc80, 'cociente de errores', epuntomedioc80/epuntomedioc40)

print('El cociente de errores obtenidos al dividir h=(b-a)/N por 2 es aproximadamente 1/4')
print('Esto ocurre por ser un metodo de orden 2')



#Ejercicio 5

def trapecioc(f,a,b,N):
    x = linspace(a,b,N+1)
    return (b-a)/(2*N)*(f(a)+f(b) + 2*sum(f(x[1:N])))
    
itrapecioc10=trapecioc(funexp,0,1,10)
etrapecioc10=abs(itrapecioc10-iquad)

print('La aproximación obtenida con trapecio compuesta con N=10 es', itrapecioc10, 'y el error es', etrapecioc10)

itrapecioc20=trapecioc(funexp,0,1,20)
etrapecioc20=abs(itrapecioc20-iquad)

print('La aproximación obtenida con trapecio compuesta con N=20 es', itrapecioc20, 'y el error es', etrapecioc20, 'cociente de errores', etrapecioc20/etrapecioc10)

itrapecioc40=trapecioc(funexp,0,1,40)
etrapecioc40=abs(itrapecioc40-iquad)

print('La aproximación obtenida con trapecio compuesta con N=40 es', itrapecioc40, 'y el error es', etrapecioc40, 'cociente de errores', etrapecioc40/etrapecioc20)

itrapecioc80=trapecioc(funexp,0,1,80)
etrapecioc80=abs(itrapecioc80-iquad)

print('La aproximación obtenida con trapecio compuesta con N=80 es', itrapecioc80, 'y el error es', etrapecioc80, 'cociente de errores', etrapecioc80/etrapecioc40)

print('El cociente de errores obtenidos al dividir h=(b-a)/N por 2 es aproximadamente 1/4')
print('Esto ocurre por ser un metodo de orden 2')



#Ejercicio 6

def simpsonc(f,a,b,N):
    x = linspace(a,b,N+1)
    c = 0.5*(x[0:N] + x[1:N+1])
    return (b-a)/(6*N)*(f(a)+f(b) + 2*sum(f(x[1:N]))+ 4*sum(f(c)))
    
isimpsonc10=simpsonc(funexp,0,1,10)
esimpsonc10=abs(isimpsonc10-iquad)

print('La aproximación obtenida con simpson compuesta con N=10 es', isimpsonc10, 'y el error es', esimpsonc10)

isimpsonc20=simpsonc(funexp,0,1,20)
esimpsonc20=abs(isimpsonc20-iquad)

print('La aproximación obtenida con simpson compuesta con N=20 es', isimpsonc20, 'y el error es', esimpsonc20, 'cociente de errores', esimpsonc20/esimpsonc10)

isimpsonc40=simpsonc(funexp,0,1,40)
esimpsonc40=abs(isimpsonc40-iquad)

print('La aproximación obtenida con simpson compuesta con N=40 es', isimpsonc40, 'y el error es', esimpsonc40, 'cociente de errores', esimpsonc40/esimpsonc20)

isimpsonc80=simpsonc(funexp,0,1,80)
esimpsonc80=abs(isimpsonc80-iquad)

print('La aproximación obtenida con simpson compuesta con N=80 es', isimpsonc80, 'y el error es', esimpsonc80, 'cociente de errores', esimpsonc80/esimpsonc40)

print('El cociente de errores obtenidos al dividir h=(b-a)/N por 2 es aproximadamente 1/16')
print('Esto ocurre por ser un metodo de orden 4')



#Ejercicio 7

def gauss3(f,a,b):
    t = array([-sqrt(3/5), 0, sqrt(3/5)])
    alfatilde = array([5/9, 8/9, 5/9])
    x = a+(b-a)/2*(t+1)
    alfa = (b-a)/2*alfatilde
    return sum(alfa*f(x))

igauss3=gauss3(funexp,0,1)
egauss3=abs(igauss3-iquad)
print('La aproximación obtenida es', igauss3, 'y el error es', egauss3)







































