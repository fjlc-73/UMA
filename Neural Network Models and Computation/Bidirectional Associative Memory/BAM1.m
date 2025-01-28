clear all;

epocMax = 21;

X(1,:) = [1 1 1 -1 1 -1 -1 1 -1];
X(2,:) = [1 -1 -1 1 -1 -1 1 1 1];
Y(1,:) = [1 -1 -1];
Y(2,:) = [-1 -1 1];

w = X'*Y; %Regla Hebb

S = zeros(size(X,2),epocMax);
S2 = zeros(size(Y,2),epocMax);

sinit = [1 1 1 -1 1 -1 -1 1 -1];    %usar X1 o X2
s2init = sign(sinit*w);

S(:,1) = sinit;
S2(:,1) = s2init;

for epoc=2:1:epocMax
    S(:,epoc) = sign(w*S2(:,epoc-1));
    S2(:,epoc) = sign(S(:,epoc)'*w);
    if(sum(S(:,epoc)==S(:,epoc-1))==size(X,2))
        S(:,epoc)
        S2(:,epoc)
        return 
    end
end