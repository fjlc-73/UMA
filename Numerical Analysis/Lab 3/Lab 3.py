# -*- coding: utf-8 -*-
"""
Created on Sun May 17 11:38:44 2020

@author: Usuario
"""

from pylab import *
    
print('Ejercicio 1\n')

def locfron(rho, sigma):
# Dibuja la frontera de la region de estabilidad absoluta 
# de un metodo multipaso.
# rho y sigma son los coeficientes de los polinomios caracteristicos
# ordenados de mayor a menor grado '''
    theta = arange(0, 2.*pi, 0.01)
    numer = polyval(rho, exp(theta*1j)) # rho(e^{theta*i})
    denom = polyval(sigma, exp(theta*1j)) # sigma(e^{theta*i})
    mu = numer/denom
    x = real(mu)
    y = imag(mu)
    plot(x, y)
    grid(True)
    axis('equal')

figure('AB')
# AB 1 paso
rho = array([1.,-1])
sigma = array([0,1.])
locfron(rho,sigma)
# AB 2 pasos
rho = array([1.,-1, 0])
sigma = array([0,3/2., -1/2.])
locfron(rho,sigma)
# Ejemplo: AB3 y_{k+1} - y_k  = h/12*(23*f_k - 16*f_{k-1} + 5*f_{k-2})
rho = array([1., -1., 0.,0.]) # primero
sigma = array([0., 23., -16., 5.])/12. # segundo
locfron(rho,sigma)
# AB 4 pasos 
rho = array([1.,-1, 0., 0., 0.])
sigma = array([0,55., -59., 37., -9.])/24
locfron(rho,sigma)

legend(['AB1','AB2','AB3','AB4'])

figure('AM')
# AM 1 paso
rho = array([1.,-1])
sigma = array([0.5,0.5])
locfron(rho,sigma)
# AM 2 pasos
rho = array([1.,-1, 0])
sigma = array([5/12.,2/3., -1/12.])
locfron(rho,sigma)
# AM 3 pasos
rho = array([1., -1., 0.,0.]) # primero
sigma = array([3/8., 19/24., -5/24., 1/24.]) # segundo
locfron(rho,sigma)
# AM 4 pasos 
rho = array([1.,-1, 0., 0., 0.])
sigma = array([251., 646., -264.,106., -19.])/720   #no me ha dado tiempo a copiarlo
locfron(rho,sigma)

axis([-8,1,-4,4])

legend(['AM1','AM2','AM3','AM4'])

#################  region estabilidad RK





def locfronRK(dR, N):
# Localizacion de la frontera de un metodo RK
#  Derividada de la funcion R
    Npoints = 5000
    T = 2*N*pi
    h = 2*N*pi/Npoints
    z = zeros(Npoints +1 , dtype = complex)
    z[0] = 0.
    t = 0
    for k in range(len(z)-1):
        k1 = 1j*exp(1j*t)/dR(z[k])
        k2 = 1j*exp(1j*(t+0.5*h))/dR(z[k] + 0.5*h*k1)
        k3 = 1j*exp(1j*(t+0.5*h))/dR(z[k] + 0.5*h*k2)
        k4 = 1j*exp(1j*(t+h))/dR(z[k] + h*k3)
        z[k+1] = z[k]+ h*(k1+ 2*k2+ 2*k3 + k4)/6
        t = t + h
    x = real(z)
    y = imag(z)
    plot(x,y)
    grid(True)
    axis('equal')

figure('RK explicitos')

# Euler: función de estabilidad R = 1 + z

def dREuler(z):
    return 1.
locfronRK(dREuler, 1.)

# RK2 explicitos: función de estabilidad R = 1 + z + z**2/2

def dRK2exp(z):
    return 1. + z
locfronRK(dRK2exp, 2.)

def dRK3exp(z):
    return 1. + z + z**2/2.
locfronRK(dRK3exp,3.)

def dRK4exp(z):
    return 1. + z + z**2/2+z**3/6.
locfronRK(dRK4exp,4.)

legend(['RK1','RK2','RK3','RK4'])

print('Ejercicio 2\n')

print('Apartado a\n')

print('-2<-1200h<0 => 0<h<1/600')
print('N=4/h=2400')

def f(t, y):
    """Funcion que define la ecuacion diferencial"""
    return -1200*y +2000 -1500*exp(-t)

def exacta(t):
    """Solucion exacta del problema de valor inicial"""
    return 5/3. -1495/3579*exp(-1200*t)-1500/1199*exp(-t)


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

a=0.
b=4.
y0=0.

figure('Ejercicio 2a')

malla=[0.001,1/600,0.0017]

for h in malla:
    N=int((b-a)/h)+1
    (t,y)=euler(a,b,f,N,y0)
    plot(t,y)
    
tex=linspace(a,b,200)
yex=exacta(tex)
plot(tex,yex)
legend(['h=0.001','h=1/600','h=0.0017','exacta'])
axis([0,4,-10,10])

print('Apartado b/n')

def eulerimp2a(a, b, N, y0):
    """Implementacion del metodo de Euler en el intervalo [a, b]
    usando N particiones y condicion inicial y0"""
    
    h = (b-a)/N # paso de malla
    t = zeros(N+1) # inicializacion del vector de nodos
    y = zeros(N+1) # inicializacion del vector de resultados
    t[0] = a # nodo inicial
    y[0] = y0 # valor inicial

    # Metodo de Euler
    for k in range(N):
        t[k+1] = t[k]+h
        y[k+1] = (y[k]+h*(2000-1500*exp(-t[k+1])))/(1+1200*h)
       
    
    return (t, y)

figure('Ejercicio 2b')

malla=[0.001,0.01,0.1,1.]

for h in malla:
    N=int((b-a)/h)+1
    (t,y)=eulerimp2a(a,b,N,y0)
    plot(t,y)
    
tex=linspace(a,b,200)
yex=exacta(tex)
plot(tex,yex)
legend(['h=0.001','h=0.01','h=0.1','h=1','exacta'])

print('Apartado c\n')

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

print('Apartado d\n')

print('Ejercicio 3\n')

figure('Ejercicio 3')

def dREuler(z):
    return 1.
locfronRK(dREuler, 1.)

def fun3(t,y):
    return array([y[1],-101*y[0]-20*y[1]])

def exacta3(t):
    return exp(-10*t)*cos(t)


re=-10.
im=1.
plot([re,re],[im,-im],'*')
plot([0,re],[0,im],'--',[0,re],[0,-im],'--')

print('lambda = -10+-i')
print('h=20/101')

hcrit=20/101

plot([-10*hcrit,-10*hcrit],[-hcrit,hcrit],'o')

a=0.
b=7.
y0=array([1,-10])

figure('Ejercicio 3 - otra cosa')

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

N=int((b-a)/hcrit)+1

malla=[N-5,N,N+5]
leyenda=[]

for N in malla:
    (t,y)=eulerSis(a,b,fun3,N,y0)
    plot(t,y[0])
    leyenda.append('N = '+str(N))
    
tex=linspace(a,b,200)
yex=exacta3(tex)
plot(tex,yex)
leyenda.append('exacta')
legend(leyenda)
axis([0,7,-10,10])

















































