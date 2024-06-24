# -*- coding: utf-8 -*-
"""
Created on Sun Mar  8 12:09:15 2020

@author: Usuario
"""

from pylab import *
from time import perf_counter

print('\n Práctica 1 \n')


def f(t, y):
    """Funcion que define la ecuacion diferencial"""
    return 0.5*(t**2 - y)

def exacta(t):
    """Solucion exacta del problema de valor inicial"""
    return t**2 - 4*t + 8 - 7*exp(-0.5*t)

print('\n Ejercicio 1 \n')

def euler(a, b, fun, N, y0):
    """Implementacion del metodo de Euler en el intervalo [a, b]
    usando N particiones y condicion inicial y0"""
    
    h = (b-a)/N # paso de malla
    t = zeros(N+1) # inicializacion del vector de nodos
    y = zeros(N+1) # inicializacion del vector de resultados
    t[0] = a # nodo inicial
    y[0] = y0 # valor inicial

    # Metodo de Euler
    for k in range(N):
        y[k+1] = y[k]+h*fun(t[k], y[k])
        t[k+1] = t[k]+h
    
    return (t, y)

print('\n Primera prueba con Euler \n')

# Datos del problema
a = 0. # extremo inferior del intervalo
b = 10. # extremo superior del intervalo
N = 20 # numero de particiones
y0 = 1. # condicion inicial

tini = perf_counter()       #iniciar contador de tiempo

(t, y) = euler(a, b, f, N, y0) # llamada al metodo de Euler

tfin=perf_counter()         #parar contador de tiempo

ye = exacta(t) # calculo de la solucion exacta

# Dibujamos las soluciones
figure('Figura 1 - Euler')
plot(t, y, '-*') # dibuja la solucion aproximada
plot(t, ye, 'k') # dibuja la solucion exacta
xlabel('t')
ylabel('y')
legend(['Euler', 'exacta'])
grid(True)

# Calculo del error cometido
error = max(abs(y-ye))

# Resultados
print('-----')
print('Tiempo CPU: ', str(tfin-tini))
print('Error: ', str(error))
print('Paso de malla: ', str((b-a)/N))
print('-----')



print('\n Ejericio 1.a \n')

Nvarios = [10,20,40,80,160]

figure('Figura 1.a - Euler')
for N in Nvarios:
    
    tini = perf_counter()       #iniciar contador de tiempo
    (t, y) = euler(a, b, f, N, y0) # llamada al metodo de Euler
    tfin=perf_counter()         #parar contador de tiempo
    ye = exacta(t) # calculo de la solucion exacta
    # Dibujamos las soluciones
    plot(t, y, '-*') # dibuja la solucion aproximada
    # Calculo del error cometido
    error = max(abs(y-ye))
    
    # Resultados
    print('-----')
    print('Tiempo CPU: ',tfin-tini)
    print('Error: ', error)
    
    if N!=Nvarios[0]:
        print('Cociente de errores: ', errorold/error)
        
    print('Paso de malla: ', (b-a)/N)
    print('-----')
    errorold = error
    
plot(t, ye, 'k') # dibuja la solucion exacta
xlabel('t')
ylabel('y')
leyenda=['N=' + str(N) for N in Nvarios]
leyenda.append('Exacta')
legend(leyenda)
grid(True)



print('\n Ejericio 1.b \n')


print('\n Ejericio 1.c \n')


print('\n Ejericio 2 - Taylor 3 \n')


def taylor3(a, b, N, y0):
    """Implementacion del metodo de Euler en el intervalo [a, b]
    usando N particiones y condicion inicial y0"""
    
    h = (b-a)/N # paso de malla
    t = zeros(N+1) # inicializacion del vector de nodos
    y = zeros(N+1) # inicializacion del vector de resultados
    t[0] = a # nodo inicial
    y[0] = y0 # valor inicial

    # Metodo de Taylor de orden 3
    for k in range(N):
        fk = 0.5*(t[k]**2 - y[k])
        dfk = t[k] - 0.5*fk
        d2fk = 1 - 0.5*dfk
        
        y[k+1] = y[k]+h*fk + 0.5*h**2*dfk + h**3/6*d2fk 
        t[k+1] = t[k]+h
    
    return (t, y)



Nvarios = [10,20,40,80,160]

figure('Figura 2 - Taylor 3')
for N in Nvarios:
    
    tini = perf_counter()       #iniciar contador de tiempo
    (t, y) = taylor3(a, b, N, y0) # llamada al metodo de Euler
    tfin=perf_counter()         #parar contador de tiempo
    ye = exacta(t) # calculo de la solucion exacta
    # Dibujamos las soluciones
    plot(t, y, '-*') # dibuja la solucion aproximada
    # Calculo del error cometido
    error = max(abs(y-ye))
    
    # Resultados
    print('-----')
    print('Tiempo CPU: ',tfin-tini)
    print('Error: ', error)
    
    if N!=Nvarios[0]:
        print('Cociente de errores: ', errorold/error)
        
    print('Paso de malla: ', (b-a)/N)
    print('-----')
    errorold = error
    
plot(t, ye, 'k') # dibuja la solucion exacta
xlabel('t')
ylabel('y')
leyenda=['N=' + str(N) for N in Nvarios]
leyenda.append('Exacta')
legend(leyenda)
grid(True)



print('\n Ejericio 3 \n')

#   Método de Heun

def heun(a, b, fun, N, y0):
    """Implementacion del metodo de Heun en el intervalo [a, b]
    usando N particiones y condicion inicial y0"""
    
    h = (b-a)/N # paso de malla
    t = zeros(N+1) # inicializacion del vector de nodos
    y = zeros(N+1) # inicializacion del vector de resultados
    t[0] = a # nodo inicial
    y[0] = y0 # valor inicial

    # Metodo de Heun
    for k in range(N):
        t[k+1] = t[k]+h
        fk=fun(t[k],y[k])
        ykmas1p = y[k]+ h*fk
        y[k+1] = y[k]+0.5*h*(fk + fun(t[k+1],ykmas1p))
        
    return (t, y)

print('\n Ejercicio 3.a (Heun) \n')

Nvarios = [10,20,40,80,160]

figure('Figura 3a - Heun')
for N in Nvarios:
    
    tini = perf_counter()       #iniciar contador de tiempo
    (t, y) = heun(a, b, f, N, y0) # llamada al metodo de Euler
    tfin=perf_counter()         #parar contador de tiempo
    ye = exacta(t) # calculo de la solucion exacta
    # Dibujamos las soluciones
    plot(t, y, '-*') # dibuja la solucion aproximada
    # Calculo del error cometido
    error = max(abs(y-ye))
    
    # Resultados
    print('-----')
    print('Tiempo CPU: ',tfin-tini)
    print('Error: ', error)
    
    if N!=Nvarios[0]:
        print('Cociente de errores: ', errorold/error)
        
    print('Paso de malla: ', (b-a)/N)
    print('-----')
    errorold = error
    
plot(t, ye, 'k') # dibuja la solucion exacta
xlabel('t')
ylabel('y')
leyenda=['N=' + str(N) for N in Nvarios]
leyenda.append('Exacta')
legend(leyenda)
grid(True)


# Al dividir h por 2, el error se divide aproximadamente por 4
# Esto corresponde al comportamiento de un método de orden 2



#   Método RK4

def rk4(a, b, fun, N, y0):
    """Implementacion del metodo de RK4 en el intervalo [a, b]
    usando N particiones y condicion inicial y0"""
    
    h = (b-a)/N # paso de malla
    t = zeros(N+1) # inicializacion del vector de nodos
    y = zeros(N+1) # inicializacion del vector de resultados
    t[0] = a # nodo inicial
    y[0] = y0 # valor inicial

    # Metodo de RK4
    for k in range(N):
        t[k+1] = t[k]+h
        k1=fun(t[k],y[k])
        k2=fun(t[k] + 0.5*h,y[k] + 0.5*h*k1)
        k3=fun(t[k] + 0.5*h,y[k] + 0.5*h*k2)
        k4=fun(t[k+1], y[k] + h*k3)
        y[k+1] = y[k]+(h/6)*(k1 + 2*k2 + 2*k3 + k4)
        
    return (t, y)

print('\n Ejercicio 3.b (RK4) \n')

Nvarios = [10,20,40,80,160]

figure('Figura 3b - RK4')
for N in Nvarios:
    
    tini = perf_counter()       #iniciar contador de tiempo
    (t, y) = rk4(a, b, f, N, y0) # llamada al metodo de EulerRK4
    tfin=perf_counter()         #parar contador de tiempo
    ye = exacta(t) # calculo de la solucion exacta
    # Dibujamos las soluciones
    plot(t, y, '-*') # dibuja la solucion aproximada
    # Calculo del error cometido
    error = max(abs(y-ye))
    
    # Resultados
    print('-----')
    print('Tiempo CPU: ',tfin-tini)
    print('Error: ', error)
    
    if N!=Nvarios[0]:
        print('Cociente de errores: ', errorold/error)
        
    print('Paso de malla: ', (b-a)/N)
    print('-----')
    errorold = error
    
plot(t, ye, 'k') # dibuja la solucion exacta
xlabel('t')
ylabel('y')
leyenda=['N=' + str(N) for N in Nvarios]
leyenda.append('Exacta')
legend(leyenda)
grid(True)

# Al dividir h por 2, el error se divide aproximadamente por 16
# Esto corresponde al comportamiento de un método de orden 4

###################
print('\n Ejericio 4 \n')
###################

# PRUEBAS CON ARRAYS

x=[1,2,3]
y=[4,5,6]
x+y

xx=array([1,2,3])
yy=array([4,5,6])
xx+yy

def fsis(t,y):
    fsis1=y[0]*cos(t)
    fsis2=y[1]*sin(t)
    return array([fsis1,fsis2])


fsis(pi, array([1,1]))

A=array([[1,2,3],[4,5,6]])
print(A[0,0])
print(A[:,1])
print(A[0,:])


# Método de Euler para sistemas

def eulerSis(a, b, fun, N, y0):
    """Implementacion del metodo de Euler en el intervalo [a, b]
    usando N particiones y condicion inicial y0"""
    
    h = (b-a)/N # paso de malla
    t = zeros(N+1) # inicializacion del vector de nodos
    y = zeros([len(y0), N+1]) # inicializacion del vector de resultados
    t[0] = a # nodo inicial
    y[:,0] = y0 # valor inicial

    # Metodo de Euler
    for k in range(N):
        y[:, k+1] = y[:, k]+h*fun(t[k], y[:, k])
        t[k+1] = t[k]+h
    
    return (t, y)


def f4(t,y):
    dx=0.25*y[0]-0.01*y[0]*y[1]
    dy=-y[1]+0.01*y[0]*y[1]
    return array([dx,dy])


a=0.
b=20.
N=20
y0=array([80,30])

tini = perf_counter()

(t,y) = eulerSis(a,b,f4,N,y0)

tfin=perf_counter()

# Dibujamos las soluciones
figure('Ejercicio 4 - EulerSis')

subplot(121)
plot(t, y[0,:], t, y[1,:]) # dibuja la solucion aproximada
xlabel('t')
ylabel('x,y')
legend(['Presa', 'Depredador'])

subplot(122)
plot(y[0,:],y[1,:])
xlabel('x')
ylabel('y')
legend(['Trayectorias'])

grid(False)

###################
print('\n Ejericio 4a \n')
###################

a=0.
b=20.
N=20
y0=array([80,30])

malla=[20,40,80,160,320,640]

figure('Ejercicio 4a - EulerSis')

for N in malla:
    tini = perf_counter()

    (t,y) = eulerSis(a,b,f4,N,y0)

    tfin=perf_counter()

    plot(y[0,:],y[1,:])
    
    print('-----')
    print('Tiempo CPU: ', str(tfin-tini))
    print('Paso de malla: ', str((b-a)/N))
    print('-----')
    
xlabel('x')
ylabel('y')
legend(['N = ' + str(N) for N in malla])

grid(False)

# Método RK4 para sistemas

def rk4sis(a, b, fun, N, y0):
    """Implementacion del metodo de RK4 en el intervalo [a, b]
    usando N particiones y condicion inicial y0"""
    
    h = (b-a)/N # paso de malla
    t = zeros(N+1) # inicializacion del vector de nodos
    y = zeros([len(y0), N+1]) # inicializacion del vector de resultados
    t[0] = a # nodo inicial
    y[:,0] = y0 # valor inicial

    # Metodo de RK4
    for k in range(N):
        t[k+1] = t[k]+h
        k1=fun(t[k],y[:,k])
        k2=fun(t[k] + 0.5*h,y[:,k] + 0.5*h*k1)
        k3=fun(t[k] + 0.5*h,y[:,k] + 0.5*h*k2)
        k4=fun(t[k+1], y[:,k] + h*k3)
        y[:,k+1] = y[:,k]+(h/6)*(k1 + 2*k2 + 2*k3 + k4)
        
    return (t, y)

def f4(t,y):
    dx=0.25*y[0]-0.01*y[0]*y[1]
    dy=-y[1]+0.01*y[0]*y[1]
    return array([dx,dy])

a=0.
b=20.
N=20
y0=array([80,30])

malla=[20,40,80,160,320,640]

figure('Ejercicio 4a - Rk4Sis')

for N in malla:
    tini = perf_counter()

    (t,y) = rk4sis(a,b,f4,N,y0)

    tfin=perf_counter()

    plot(y[0,:],y[1,:])
    
    print('-----')
    print('Tiempo CPU: ', str(tfin-tini))
    print('Paso de malla: ', str((b-a)/N))
    print('-----')
    
xlabel('x')
ylabel('y')
legend(['N = ' + str(N) for N in malla])

grid(False)

###################
print('\n Ejericio 5 \n')
###################
def f5(t,y):
    dx=y[1]
    dy=-20.*y[1]-101.*y[0]
    return array([dx,dy])

def exacta5(t):
    return exp(-10*t)*cos(t)

a=0.
b=7.
y0=array([1,-10])

malla=[20,40,80,160,320,640]

figure('Ejercicio 5 - EulerSis')

for N in malla:
    tini = perf_counter()

    (t,y) = eulerSis(a,b,f5,N,y0)

    tfin=perf_counter()

    plot(t,y[0,:])
    
    ye = exacta5(t)
    
    error = max(abs(y[0,:]-ye))
    
    print('-----')
    print('Tiempo CPU: ', str(tfin-tini))
    print('Error: ' + str(error))
    print('Paso de malla: ', str((b-a)/N))
    print('-----')
    
plot(t,ye,'k')
    
xlabel('x')
ylabel('y')
leyenda = ['N = ' + str(N) for N in malla]
leyenda.append('exacta')
legend(leyenda)

figure('Ejercicio 5 - RK4Sis')

for N in malla:
    tini = perf_counter()

    (t,y) = rk4sis(a,b,f5,N,y0)

    tfin=perf_counter()

    plot(t,y[0,:])
    
    ye = exacta5(t)
    
    error = max(abs(y[0,:]-ye))
    
    print('-----')
    print('Tiempo CPU: ', str(tfin-tini))
    print('Error: ' + str(error))
    print('Paso de malla: ', str((b-a)/N))
    print('-----')
    
plot(t,ye,'k')
    
xlabel('x')
ylabel('y')
leyenda = ['N = ' + str(N) for N in malla]
leyenda.append('exacta')
legend(leyenda)

###################
print('\n Ejericio 7 \n')
###################
g=9.81
alpha=0.02
M=7.5

def f7(t,y):
    m=M+y[2]
    T=T0*(y[2]>0)
    
    dz= y[1]
    dv= -g + T/m - C/m*abs(y[1])*y[1] + alpha*T/m*y[1]
    dmf= -alpha*T
    return array([dz,dv,dmf])

a=0.
b=20.
h=0.05
N=int((b-a)/h)
y0=array([0,50,7.5])

#Sin motor ni rozamiento
T0=0.
C=0.

figure('Ejercicio 7 - Cohete')

tini = perf_counter()

(ti,yi)=rk4sis(a,b,f7,N,y0)

tfin=perf_counter()


#Sin motor ni rozamiento
T0=0.
C=0.02


tini = perf_counter()

(tii,yii)=rk4sis(a,b,f7,N,y0)

tfin=perf_counter()



#Sin motor ni rozamiento
T0=50
C=0.02


tini = perf_counter()

(tiii,yiii)=rk4sis(a,b,f7,N,y0)

tfin=perf_counter()

plot(ti,yi[0],tii,yii[0],tiii,yiii[0])
xlabel('t')
ylabel('z')
legend(['T=0,C=0','T=0,C=0.02','T=50,C=0.02'])



def rk4siscohete(a, h, fun, y0):
    """Implementacion del metodo de RK4 en el intervalo [a, b]
    usando N particiones y condicion inicial y0"""
    
    t = zeros(1) # inicializacion del vector de nodos
    y = zeros([len(y0), 1]) # inicializacion del vector de resultados
    t[0] = a # nodo inicial
    y[:,0] = y0 # valor inicial
    k=0

    # Metodo de RK4
    while(y[0,k]>=0):
        k1=fun(t[k],y[:,k])
        k2=fun(t[k] + 0.5*h,y[:,k] + 0.5*h*k1)
        k3=fun(t[k] + 0.5*h,y[:,k] + 0.5*h*k2)
        k4=fun(t[k]+h, y[:,k] + h*k3)
        
        ykmas1 = y[:,k]+(h/6)*(k1 + 2*k2 + 2*k3 + k4)
        t=append(t,t[k]+h)
        y=column_stack((y,ykmas1))
        k=k+1;
        
        
    return (t, y)

def f7(t,y):
    m=M+y[2]
    T=T0*(y[2]>0)
    
    dz= y[1]
    dv= -g + T/m - C/m*abs(y[1])*y[1] + alpha*T/m*y[1]
    dmf= -alpha*T
    return array([dz,dv,dmf])

a=0.
h=0.05
y0=array([0,50,7.5])

#Sin motor ni rozamiento
T0=0.
C=0.

figure('Ejercicio 7a - Cohete')

tini = perf_counter()

(ti,yi)=rk4siscohete(a,h,f7,y0)

tfin=perf_counter()


#Sin motor ni rozamiento
T0=0.
C=0.02


tini = perf_counter()

(tii,yii)=rk4siscohete(a,h,f7,y0)

tfin=perf_counter()



#Sin motor ni rozamiento
T0=50
C=0.02


tini = perf_counter()

(tiii,yiii)=rk4siscohete(a,h,f7,y0)

tfin=perf_counter()

plot(ti,yi[0],tii,yii[0],tiii,yiii[0])
xlabel('t')
ylabel('z')
legend(['T=0,C=0','T=0,C=0.02','T=50,C=0.02'])

show()

#Calculamos la altura maxima en cada caso
print('C=0, T0=0')
print('Maxima altura: ' + str(max(yi[0,:])))
print('Tiempo de caida: ' + str(ti[-1]))
print('\n')

print('C=0.02, T0=0')
print('Maxima altura: ' + str(max(yii[0,:])))
print('Tiempo de caida: ' + str(tii[-1]))
print('\n')

print('C=0.02, T0=50')
print('Maxima altura: ' + str(max(yiii[0,:])))
print('Tiempo de caida: ' + str(tiii[-1]))
print('\n')

figure('Figure 7 - Combustible')
plot(tiii,yiii[2])
xlabel('t')
ylabel('mf')
legend(['T=50,C=0.02'])
grid(False)

