# -*- coding: utf-8 -*-
from numpy import *
from numpy.linalg import *
from numpy import abs, sum, max, min

def conjugada(A):
    if ndim(A)==1:
        A = array([A])
    return conjugate(transpose(A))

def norma_vec(X, p):
    n=ndim(X)
    q=1
    r=1
    if(n!=1):
        q, r = shape(X)
    if(q!=1 and r!=1):
        print("Primer argumento de entrada no es vector o matriz columna")
        return -1
    elif(p<1):
        print("Norma no definida para indice negativo")
        return -1
    else:
        X=X.astype(complex)
        if p == inf:
            return max(abs(X))
        else:
            return (sum(abs(X)**p))**(1/p)
        
def norma_mat(A, p):
    if(p==inf):
        return max(sum(abs(A),axis=1))
    elif(p==1):
        return max(sum(abs(A),axis=0))
    elif(p==2):
        d, P = eig(conjugada(A)@A)
        return max(abs(d))**(1/2)
    elif(p=="fro"):
        return sum(abs(A)**2)**(1/2)
    else:
        print("Norma matricial no reconocida");
        return -1
        
def descenso(A, B):
    m, n = shape(A)
    p, q = shape(B)
    if m != n or n != p or q < 1:
        return False, "Error descenso: error en las dimensiones."
    if min(abs(diag(A))) < 1e-200:
        return False, "Error descenso: matriz singular."
    if A.dtype == complex or B.dtype == complex:
        X = zeros((n, q), dtype=complex)
    else:
        X = zeros((n, q), dtype=float)
    for i in range(n):
        X[i, :] = B[i, :]
        if i != 0:
            X[i, :] -= A[i, :i]@X[:i, :]
        X[i, :] = X[i, :]/A[i, i]
    return True, X

def remonte(A, B):
    m, n = shape(A)
    p, q = shape(B)
    if m != n or n != p or q < 1:
        return False, "Error remonte: error en las dimensiones."
    if min(abs(diag(A))) < 1e-200:
        return False, "Error remonte: matriz singular."
    if A.dtype == complex or B.dtype == complex:
        X = zeros((n, q), dtype=complex)
    else:
        X = zeros((n, q), dtype=float)
    for i in range(n-1,-1,-1):
        X[i, :] = B[i, :]
        if i != n-1:
            X[i, :] -= A[i, i+1:]@X[i+1:, :]
        X[i, :] = X[i, :]/A[i, i]
    return True, X

def descenso1(A, B):
    m, n = shape(A)
    p, q = shape(B)
    if m != n or n != p or q < 1:
        return False, "Error descenso: error en las dimensiones."
    if A.dtype == complex or B.dtype == complex:
        X = zeros((n, q), dtype=complex)
    else:
        X = zeros((n, q), dtype=float)
    for i in range(n):
        X[i, :] = B[i, :]
        if i != 0:
            X[i, :] -= A[i, :i]@X[:i, :]
    return True, X

def remonte1(A, B):
    m, n = shape(A)
    p, q = shape(B)
    if m != n or n != p or q < 1:
        return False, "Error remonte: error en las dimensiones."
    if A.dtype == complex or B.dtype == complex:
        X = zeros((n, q), dtype=complex)
    else:
        X = zeros((n, q), dtype=float)
    for i in range(n-1,-1,-1):
        X[i, :] = B[i, :]
        if i != n-1:
            X[i, :] -= A[i, i+1:]@X[i+1:, :]
    return True, X

def descenso_1diag(A, B):
    m, n = shape(A)
    p, q = shape(B)
    if m != n or n != p or q < 1:
        return False, "Error descenso: error en las dimensiones."
    if min(abs(diag(A))) < 1e-200:
        return False, "Error descenso: matriz singular."
    if A.dtype == complex or B.dtype == complex:
        X = zeros((n, q), dtype=complex)
    else:
        X = zeros((n, q), dtype=float)
    for i in range(n):
        X[i, :] = B[i, :]
        if i != 0:
            X[i, :] -= A[i, i-1]*X[i-1, :]
        X[i, :] = X[i, :]/A[i, i]
    return True, X

def remonte_1diag(A, B):
    m, n = shape(A)
    p, q = shape(B)
    if m != n or n != p or q < 1:
        return False, "Error remonte: error en las dimensiones."
    if min(abs(diag(A))) < 1e-200:
        return False, "Error remonte: matriz singular."
    if A.dtype == complex or B.dtype == complex:
        X = zeros((n, q), dtype=complex)
    else:
        X = zeros((n, q), dtype=float)
    for i in range(n-1,-1,-1):
        X[i, :] = B[i, :]
        if i != n-1:
            X[i, :] -= A[i, i+1]*X[i+1, :]
        X[i, :] = X[i, :]/A[i, i]
    return True, X

def gauss_pp(A, B):
    m, n = shape(A)
    p, q = shape(B)
    if m != n or n != p or q < 1:
        return False, "Error gauss_pp: error en las dimensiones."
    if A.dtype == complex or B.dtype == complex:
        gaussA = array(A, dtype=complex)
        gaussB = array(B, dtype=complex)
    else:
        gaussA = array(A, dtype=float)
        gaussB = array(B, dtype=float)
    for k in range(n-1):
        pos = argmax(abs(gaussA[k:, k]))
        ik = pos+k
        if ik != k:
            gaussA[[ik, k], :] = gaussA[[k, ik], :]
            gaussB[[ik, k], :] = gaussB[[k, ik], :]
        if abs(gaussA[k, k]) >= 1e-200:
            for i in range(k+1, n):
                gaussA[i, k] = gaussA[i, k]/gaussA[k, k]
                gaussA[i, k+1:] -= gaussA[i, k]*gaussA[k, k+1:]
                gaussB[i, :] -= gaussA[i, k]*gaussB[k, :]
    exito, X = remonte(gaussA, gaussB)
    return exito, X

def gaussjordan_pp(A, B):
    m, n = shape(A)
    p, q = shape(B)
    if m != n or n != p or q < 1:
        return False, "Error gaussjordan_pp: error en las dimensiones."
    if A.dtype == complex or B.dtype == complex:
        gaussA = array(A, dtype=complex)
        gaussB = array(B, dtype=complex)
    else:
        gaussA = array(A, dtype=float)
        gaussB = array(B, dtype=float)
    for k in range(n):
        pos = argmax(abs(gaussA[k:, k]))
        ik = pos+k
        if ik != k:
            gaussA[[ik, k], :] = gaussA[[k, ik], :]
            gaussB[[ik, k], :] = gaussB[[k, ik], :]
        if abs(gaussA[k, k]) >= 1e-200:
            for i in range(k):
                gaussA[i, k] = gaussA[i, k]/gaussA[k, k]
                gaussA[i, k+1:] -= gaussA[i, k]*gaussA[k, k+1:]
                gaussB[i, :] -= gaussA[i, k]*gaussB[k, :]
            for i in range(k+1, n):
                gaussA[i, k] = gaussA[i, k]/gaussA[k, k]
                gaussA[i, k+1:] -= gaussA[i, k]*gaussA[k, k+1:]
                gaussB[i, :] -= gaussA[i, k]*gaussB[k, :]
    if(min(abs(diag(gaussA)))) < 1e-200:
        return False, "Error gaussjordan_pp matriz singular"
    if A.dtype == complex or B.dtype == complex:
        X = zeros((n,q), dtype=complex)
    else:
        X = zeros((n,q), dtype=float)
    for i in range(n):
        X[i, :] = gaussB[i, :]/gaussA[i,i]
    return True, X


def gauss_pp_verbose(A, B):
    m, n = shape(A)
    p, q = shape(B)
    if m != n or n != p or q < 1:
        return False, "Error gauss_pp: error en las dimensiones."
    if A.dtype == complex or B.dtype == complex:
        gaussA = array(A, dtype=complex)
        gaussB = array(B, dtype=complex)
    else:
        gaussA = array(A, dtype=float)
        gaussB = array(B, dtype=float)
    for k in range(n-1):
        print("Iteración: k = ", k+1)
        pos = argmax(abs(gaussA[k:, k]))
        ik = pos+k
        print("Posicion pivot: ik = ", ik+1)
        if ik != k:
            gaussA[[ik, k], :] = gaussA[[k, ik], :]
            gaussB[[ik, k], :] = gaussB[[k, ik], :]
        if abs(gaussA[k, k]) >= 1e-200:
            for i in range(k+1, n):
                gaussA[i, k] = gaussA[i, k]/gaussA[k, k]
                gaussA[i, k+1:] -= gaussA[i, k]*gaussA[k, k+1:]
                gaussB[i, :] -= gaussA[i, k]*gaussB[k, :]
        print("Matriz: A_k+1 = ", gaussA)
    print("Matriz triangular: ", tril(triu(gaussA)))
    print("Segundo miembro: ", gaussB)
    exito, X = remonte(gaussA, gaussB)
    return exito, X

def gaussjordan_pp_verbose(A, B):
    m, n = shape(A)
    p, q = shape(B)
    if m != n or n != p or q < 1:
        return False, "Error gaussjordan_pp: error en las dimensiones."
    if A.dtype == complex or B.dtype == complex:
        gaussA = array(A, dtype=complex)
        gaussB = array(B, dtype=complex)
    else:
        gaussA = array(A, dtype=float)
        gaussB = array(B, dtype=float)
    for k in range(n):
        pos = argmax(abs(gaussA[k:, k]))
        ik = pos+k
        if ik != k:
            gaussA[[ik, k], :] = gaussA[[k, ik], :]
            gaussB[[ik, k], :] = gaussB[[k, ik], :]
        if abs(gaussA[k, k]) >= 1e-200:
            for i in range(k):
                gaussA[i, k] = gaussA[i, k]/gaussA[k, k]
                gaussA[i, k+1:] -= gaussA[i, k]*gaussA[k, k+1:]
                gaussB[i, :] -= gaussA[i, k]*gaussB[k, :]
            for i in range(k+1, n):
                gaussA[i, k] = gaussA[i, k]/gaussA[k, k]
                gaussA[i, k+1:] -= gaussA[i, k]*gaussA[k, k+1:]
                gaussB[i, :] -= gaussA[i, k]*gaussB[k, :]
        print("Matriz: A_k+1 = ", gaussA)
    print("Matriz diagonal: ", tril(triu(gaussA)))
    print("Segundo miembro: ", gaussB)
    if(min(abs(diag(gaussA)))) < 1e-200:
        return False, "Error gaussjordan_pp matriz singular"
    if A.dtype == complex or B.dtype == complex:
        X = zeros((n,q), dtype=complex)
    else:
        X = zeros((n,q), dtype=float)
    for i in range(n):
        X[i, :] = gaussB[i, :]/gaussA[i,i]
    return True, X

def gauss_1p(A, B):
    m, n = shape(A)
    p, q = shape(B)
    if m != n or n != p or q < 1:
        return False, "Error gauss_pp: error en las dimensiones."
    if A.dtype == complex or B.dtype == complex:
        gaussA = array(A, dtype=complex)
        gaussB = array(B, dtype=complex)
    else:
        gaussA = array(A, dtype=float)
        gaussB = array(B, dtype=float)
    for k in range(n-1):
        pos=(gaussA[k:, k]!=0).argmax()
        ik = pos+k
        if ik != k:
            gaussA[[ik, k], :] = gaussA[[k, ik], :]
            gaussB[[ik, k], :] = gaussB[[k, ik], :]
        if abs(gaussA[k, k]) >= 1e-200:
            for i in range(k+1, n):
                gaussA[i, k] = gaussA[i, k]/gaussA[k, k]
                gaussA[i, k+1:] -= gaussA[i, k]*gaussA[k, k+1:]
                gaussB[i, :] -= gaussA[i, k]*gaussB[k, :]
    exito, X = remonte(gaussA, gaussB)
    return exito, X

def gaussjordan_1p(A, B):
    m, n = shape(A)
    p, q = shape(B)
    if m != n or n != p or q < 1:
        return False, "Error gaussjordan_pp: error en las dimensiones."
    if A.dtype == complex or B.dtype == complex:
        gaussA = array(A, dtype=complex)
        gaussB = array(B, dtype=complex)
    else:
        gaussA = array(A, dtype=float)
        gaussB = array(B, dtype=float)
    for k in range(n):
        pos=(gaussA[k:, k]!=0).argmax()
        ik = pos+k
        if ik != k:
            gaussA[[ik, k], :] = gaussA[[k, ik], :]
            gaussB[[ik, k], :] = gaussB[[k, ik], :]
        if abs(gaussA[k, k]) >= 1e-200:
            for i in range(k):
                gaussA[i, k] = gaussA[i, k]/gaussA[k, k]
                gaussA[i, k+1:] -= gaussA[i, k]*gaussA[k, k+1:]
                gaussB[i, :] -= gaussA[i, k]*gaussB[k, :]
            for i in range(k+1, n):
                gaussA[i, k] = gaussA[i, k]/gaussA[k, k]
                gaussA[i, k+1:] -= gaussA[i, k]*gaussA[k, k+1:]
                gaussB[i, :] -= gaussA[i, k]*gaussB[k, :]
    if(min(abs(diag(gaussA)))) < 1e-200:
        return False, "Error gaussjordan_pp matriz singular"
    if A.dtype == complex or B.dtype == complex:
        X = zeros((n,q), dtype=complex)
    else:
        X = zeros((n,q), dtype=float)
    for i in range(n):
        X[i, :] = gaussB[i, :]/gaussA[i,i]
    return True, X

def facto_lu(A):
    m, n = shape(A)
    if m != n:
        return False, "Error factorizacion LU: error en las dimensiones."
    if A.dtype == complex:
        LU = array(A, dtype=complex)
    else:
        LU = array(A, dtype=float)
    for k in range(n-1):
        if abs(LU[k, k]) >= 1e-200:
            for i in range(k+1, n):
                LU[i, k] = LU[i, k]/LU[k, k]
                LU[i, k+1:] -= LU[i, k]*LU[k, k+1:]
        else:
            return False, "No existe la factorizacion LU"
    if abs(LU[n-1, n-1])>= 1e-200:
        return True, LU
    else:
        return False, "No existe la factorizacion LU inversible"
    
def metodo_lu(A, B):
    m, n = shape(A)
    p, q = shape(B)
    if m!=n or n!=p or q < 1:
        return False, "Tamaños erróneos"
    exito, lu = facto_lu(A)
    if exito: 
        ex, Y = descenso1(lu,B)
        ex, X = remonte(lu, Y)
        return True, X
    else:
        return False, "Error metodo LU: error en la resolución"
    
def metodo_cholesky(A, B):
    m, n = shape(A)
    p, q = shape(B)
    if m!=n or n!=p or q < 1:
        return False, "Tamaños erróneos"
    exito, chol = facto_cholesky(A)
    if exito: 
        ex, Y = descenso(chol,B)
        ex, X = remonte(chol, Y)
        return True, X
    else:
        return False, "Error metodo Cholesky: error en la resolución"
    
def facto_cholesky(A):
    m, n = shape(A)
    if m != n:
        return False, "Error facto_cholesky: error en las dimensiones."
    if A.dtype == complex:
        return False, "Error facto_cholesky: matriz compleja."
    else:
        chol = array(A, dtype=float)
    for i in range(n):
        chol[i, i] -= sum(power(chol[i, 0:i], 2))
        if chol[i, i] >= 1e-100:
            chol[i, i] = sqrt(chol[i, i])
        else:
            return False, "Error facto_cholesky: no se factoriza la matriz"
        for j in range(i+1, n):
            chol[j, i] -= sum(chol[i, 0:i]*chol[j, 0:i])
            chol[j, i] = chol[j, i]/chol[i, i]
            chol[i, j] = chol[j, i]
    return True, chol

def householder(X):
    n, m = shape(X)
    if m!= 1:
        return False, "Error, calcular matriz householder: necesitamos un vector"
    I = eye(n)
    H = I - (2/(transpose(X)@X))*(X@transpose(X))
    return True, H

def jacobi(A, B, XOLD, itermax, tol):
    m, n = shape(A)
    p, q = shape(B)
    r, s = shape(XOLD)
    if m != n or n != p or q != 1 or n != r or s != 1 or min(abs(diag(A))) < 1e-200:
        return False, 'ERROR jacobi: no se resuelve el sistema.'
    k = 0
    error = 1.
    while k < itermax and error >= tol:
        k = k+1
        XNEW = array(B)
        for i in range(n):
            if i != 0:
                XNEW[i, 0] -= A[i, :i]@XOLD[:i, 0]
            if i != n-1:
                XNEW[i, 0] -= A[i, i+1:]@XOLD[i+1:, 0]
            XNEW[i, 0] = XNEW[i, 0]/A[i, i]
        error = norma_vec(XNEW - XOLD, inf)
        XOLD = array(XNEW)
    print('Iteración: k = ', k)
    print('Error absoluto: error = ', error)
    if k == itermax and error >= tol:
        return False, 'ERROR jacobi: no se alcanza convergencia.'
    else:
        print('Convergencia numérica alcanzada: jacobi.')
        return True, XNEW
    
def gauss_seidel(A, B, XOLD, itermax, tol):
    m, n = shape(A)
    p, q = shape(B)
    r, s = shape(XOLD)
    if m != n or n != p or q != 1 or n != r or s != 1 or min(abs(diag(A))) < 1e-200:
        return False, 'ERROR gauss_seidel: no se resuelve el sistema.'
    k = 0
    error = 1.
    while k < itermax and error >= tol:
        k = k+1
        XNEW = array(B)
        for i in range(n):
            if i != 0:
                XNEW[i, 0] -= A[i, :i]@XNEW[:i, 0]
            if i != n-1:
                XNEW[i, 0] -= A[i, i+1:]@XOLD[i+1:, 0]
            XNEW[i, 0] = XNEW[i, 0]/A[i, i]
        error = norma_vec(XNEW - XOLD, inf)
        XOLD = array(XNEW)
    print('Iteración: k = ', k)
    print('Error absoluto: error = ', error)
    if k == itermax and error >= tol:
        return False, 'ERROR gauss_seidel: no se alcanza convergencia.'
    else:
        print('Convergencia numérica alcanzada: gauss_seidel.')
        return True, XNEW
    
def relajacion(A, B, XOLD, omega, itermax, tol):
    m, n = shape(A)
    p, q = shape(B)
    r, s = shape(XOLD)
    if m != n or n != p or q != 1 or n != r or s != 1 or min(abs(diag(A))) < 1e-200:
        return False, 'ERROR relajacion: no se resuelve el sistema.'
    k = 0
    error = 1.
    while k < itermax and error >= tol:
        k = k+1
        XNEW = array(B)
        for i in range(n):
            if i != 0:
                XNEW[i, 0] -= A[i, :i]@XNEW[:i, 0]
            if i != n-1:
                XNEW[i, 0] -= A[i, i+1:]@XOLD[i+1:, 0]
            XNEW[i, 0] +=((1-omega)/omega)*A[i, i]*XOLD[i, 0]
            XNEW[i, 0] = omega*XNEW[i, 0]/A[i, i]
        error = norma_vec(XNEW - XOLD, inf)
        XOLD = array(XNEW)
    print('Iteración: k = ', k)
    print('Error absoluto: error = ', error)
    if k == itermax and error >= tol:
        return False, 'ERROR relajacion: no se alcanza convergencia.'
    else:
        print('Convergencia numérica alcanzada: relajacion.')
        return True, XNEW
    
def potencia(A, X, norma, itermax, tol):
    m, n = shape(A)
    r, s = shape(X)
    if m != n or n != r or s != 1:
        return False, 'ERROR potencia: no se ejecuta el programa.', 0, 0
    k = 0
    error = 1.
    normaold = 0.
    if A.dtype == complex or X.dtype == complex:
        lambdas = zeros(n, dtype=complex)
    else:
        lambdas = zeros(n, dtype=float)
    while k < itermax and error >= tol:
        k = k+1
        Y = A@X
        normanew = norm(Y, ord=norma)
        error = abs(normanew - normaold)
        for i in range(n):
            if abs(X[i, 0]) >= 1.e-100:
                lambdas[i] = Y[i, 0]/X[i, 0]
            else:
                lambdas[i] = 0.
        X = Y/normanew
        print('Iteración: k = ', k)
        print('Norma: ||A*X_k|| = ', normanew)
#        print('Lambdas: lambdas_k = \n', lambdas)
#        print('Vectores: X_k = \n', X)
        normaold = normanew
    if k == itermax and error >= tol:
        return False, 'ERROR potencia: no se alcanza convergencia.', 0, 0
    else:
        print('Método de la potencia: convergencia numérica alcanzada.')
        return True, normanew, lambdas, X
    
def potenciainv(A, X, norma, itermax, tol):
    m, n = shape(A)
    r, s = shape(X)
    if m != n or n != r or s != 1:
        return False, 'ERROR potenciainv: no se ejecuta el programa.', 0, 0
    exito, LU = facto_lu(A)
    if not exito:
        return False, 'ERROR potenciainv: sin factorización LU.', 0, 0
    k = 0
    error = 1.
    normaold = 0.
    if A.dtype == complex or X.dtype == complex:
        lambdas = zeros(n, dtype=complex)
    else:
        lambdas = zeros(n, dtype=float)
    while k < itermax and error >= tol:
        k = k+1
        exito, Y = descenso1(LU, X)
        exito, Y = remonte(LU, Y)
        if not exito:
            return False, 'ERROR potenciainv: sin factorización LU.', 0, 0
        normanew = norm(Y, ord=norma)
        error = abs(normanew - normaold)
        for i in range(n):
            if abs(X[i, 0]) >= 1e-100:
                lambdas[i] = Y[i, 0]/X[i, 0]
            else:
                lambdas[i] = 0.
        X = Y/normanew
        print('Iteración: k = ', k)
        print('Norma: ||A-1*X_k|| = ', normanew)
#        print('Lambdas: lambdas_k = ', lambdas)
#        print('Vectores: X_k = ', X)
        normaold = normanew
    if k == itermax and error >= tol:
        return False, 'ERROR potenciainv: no se alcanza convergencia.', 0, 0
    else:
        print('Método de la potencia inversa: convergencia numérica alcanzada.')
        return True, normanew, lambdas, X
    
def potenciades(A, X, des, norma, itermax, tol):
    m, n = shape(A)
    r, s = shape(X)
    if m != n or n!= r or s != 1:
        return False, 'ERROR potenciades: no se ejecuta el programa.', 0, 0
    B = A - des*eye(n)
    exito, normanew, lambdas, X = potencia(B, X, norma, itermax, tol)
    return exito, normanew, lambdas, X 

def potenciadesinv(A, X, des, norma, itermax, tol):
    m, n = shape(A)
    r, s = shape(X)
    if m != n or n!= r or s != 1:
        return False, 'ERROR potenciadesinv: no se ejecuta el programa.', 0, 0
    B = A - des*eye(n)
    exito, normanew, lambdas, X = potenciainv(B, X, norma, itermax, tol)
    return exito, normanew, lambdas, X

