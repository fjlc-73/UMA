clear;
clc;
close all;

%load DatosAND
%load DatosLS5
%load DatosLS10
load DatosLS50
%load DatosOR
%load DatosXOR

x=Data(:,1:end-1);
y=Data(:,end);
k=size(Data,2)-1;
N=size(Data,1);
Xext=[x,-ones(N,1)];
w = inv(Xext' * Xext) * Xext' * y;
predicted = Xext * w; %y gorrito
label = Signo(predicted);
ccr = sum(label==y)/N  %label==y vector con 1 si coinciden 0 si no
%correct classification rate
ECM = norm(predicted-y,2)



