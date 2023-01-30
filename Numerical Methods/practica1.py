# -*- coding: utf-8 -*-
"""
Spyder Editor

This is a temporary script file.
"""

from numpy import *
from matplotlib.pyplot import *

print("Ejercicio 1")

def sumanveces(a,n):
    s=0
    for i in range(n):
        s+=a
    return s

def sumanveces2(a,n):
    s=0
    cont=0
    while(cont<n):
        s+=a
        cont+=1
    return s

a=0.1
n=10
print(sumanveces(a, n), sumanveces2(a, n))
n=100      
print(sumanveces(a, n), sumanveces2(a, n))
n=10000000
print(sumanveces(a, n), sumanveces2(a, n))


print("Ejercicio 2")

def epsilon():
    eps=1
    while(1+eps>1):
        eps=eps/2
    return eps*2

print(epsilon())


print("Ejercicio 3")

x = linspace(-2,2,100)
y = x-exp(-x)
plot(x,y)
plot(x,0*x,'k')
show()

print("Ejercicio 4")


def aproxe(n):
    aprox=(1+1/n)**n
    error=abs(aprox-exp(1))
    print('n=', n, 'aproxe=', aprox, 'error=', error)
    return aprox, error

aproxe(10)
aproxe(100)
aproxe(1000)
aproxe(100000000000000000)


print("Ejercicio 5")

def sumaparcial(n):
    Sn=0
    for k in range(1,n+1):
        Sn+=1/sqrt(k)
    #print('n=',n,'Sn=',Sn)
    return Sn

sumaparcial(10)
sumaparcial(100)
sumaparcial(1000)

n=1
while(sumaparcial(n)<50):
    n+=1
print('La suma parcial es mayor a 50 para n=',n,'y vale',sumaparcial(n))


print("Ejercicio 6")

#De una forma
ns=zeros(100)
Sns=zeros(100)
for n in range(1,101):
    ns[n-1]=n
    Sns[n-1]=sumaparcial(n)
plot(ns,Sns)
show()

#De otra forma
for n in range(1,101):
    plot(n,sumaparcial(n),'o')



print("Ejercicio 7")

def sumaparcial2(n):
    Sn=0
    for k in range(1,n+1):
        Sn+=1/(k*(k+1))
    print('n=',n,'Sn=',Sn,'error=',abs(1-Sn))
    return Sn

sumaparcial2(10)
sumaparcial2(100)
sumaparcial2(1000)

def sumaparcial3(n):
    Sn=(1 - 1/(n+1))
    print('n=',n,'Sn=',Sn,'error=',abs(1-Sn))
    return Sn

sumaparcial3(10)
sumaparcial3(100)
sumaparcial3(1000)




print("Ejercicio 8")

def factorial(k):
    fact=1
    for i in range(2,k+1):
        fact*=i
    return fact

def sumaparciale(n):
    Sn=0
    for k in range(0,n+1):
        Sn+= 1/factorial(k)
    print('n=',n,'Sn=',Sn,'error=',abs(1-Sn))
    return Sn

sumaparciale(10)
sumaparciale(100)
sumaparciale(1000)



print("Ejercicio 10")

def sumean(ar):
    l = size(ar)
    suma = 0;
    for i in range(l):
        suma+=ar[i]
    return suma, suma/l
    
print(sumean([1,2,3]))
print(sumean([1,2,3, -6, 9, 44]))
























































