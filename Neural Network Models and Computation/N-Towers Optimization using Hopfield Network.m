clear all;
N = 3;
W = zeros(N,N,N,N);
Theta = -ones(N,N);

for i=1:1:N
    for j=1:1:N
        W(i,j,1:N,j) = -2;
        W(i,j,i,1:N) = -2;
        W(i,j,i,j) = 0;
    end
end

epoc = 20;

Shist = zeros(N,N,epoc);
Shist(:,:,1) = [0 0 1;0 1 1; 0 0 0];

for e=2:1:epoc
    cambio = false;
    Shist(:,:,e) = Shist(:,:,e-1);
    for i=1:1:N
        for j=1:1:N  %(i,j) = posicion tabla
            h = 0;
            for l = 1:1:N
                for k = 1:1:N  %(l,k) = conexiones
                    h = h + Shist(l, k, e) * W(i, j, l, k);
                end
            end

            Shist(i, j, e) = int16(h >= Theta(i, j));
            cambio = cambio || Shist(i,j,e)~=Shist(i,j,e-1);
        end
    end
    if ~cambio
        Shist(:,:,e)
        return;
    end
end