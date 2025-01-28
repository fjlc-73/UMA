clear all;
s = [-1 -1 1 1 1];%Patrón que memorizamos
w = zeros(size(s,2),size(s,2));
w = (1/size(s,2))*(s'*s);
w = w - diag(diag(w));
S = zeros(size(s,2),21);
S(:,1) = [1 1 -1 -1 1];%Valor de entrada a la red

for t=2:21
    cambio=false;
    S(:,t) = S(:,t-1);
    for i=1:size(s,2)
        h = w(i,:)*S(:,t);
        S(i,t)=(h>0)*2-1;
        cambio = cambio || S(i,t) ~= S(i,t-1);
    end
    if ~cambio
        S(:,t-1)
        return
    end
end
