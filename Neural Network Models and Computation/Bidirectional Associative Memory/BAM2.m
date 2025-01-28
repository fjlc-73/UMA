clear all;

epocMax = 21;

barco = load('barco.mat');
coche = load('coche.mat');
textoBarco = load('textoBarco.mat');
textoCoche = load('textoCoche.mat');

X(1,:) = reshape(barco.barco,1,size(barco.barco,1)*size(barco.barco,2));
X(2,:) = reshape(coche.coche,1,size(coche.coche,1)*size(coche.coche,2));

Y(1,:) = reshape(textoBarco.textoBarco,1,size(textoBarco.textoBarco,1)*size(textoBarco.textoBarco,2));
Y(2,:) = reshape(textoCoche.textoCoche,1,size(textoCoche.textoCoche,1)*size(textoCoche.textoCoche,2));

w = X'*Y;

S = zeros(size(X,2),epocMax);
S2 = zeros(size(Y,2),epocMax);

sinit = X(1,:);%reshape(barco.barco,1,size(barco.barco,1)*size(barco.barco,2));    %jugamos cambiando distintos valores
s2init = sign(sinit*w);

S(:,1) = sinit;
S2(:,1) = s2init;

imshow(reshape(S(:,1),size(barco.barco,1),size(barco.barco,2)));

for epoc=2:1:epocMax
    S(:,epoc) = sign(w*S2(:,epoc-1));
    S2(:,epoc) = sign(S(:,epoc)'*w);
    if(sum(S(:,epoc)==S(:,epoc-1))==size(X,2))
        subplot(3,1,1);
        imshow(reshape(S(:,1),size(barco.barco,1),size(barco.barco,2)));
        subplot(3,1,2);
        imshow(reshape(S(:,epoc),size(barco.barco,1),size(barco.barco,2)));
        subplot(3,1,3);
        imshow(reshape(S2(:,2),size(textoBarco.textoBarco,1),size(textoBarco.textoBarco,2)));
        return 
    end
end