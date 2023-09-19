function printwhile = printwhile(n)


for i=0:n-1
	disp([num2str(i+1), ': (' num2str(N2WHILE(i)) ')']);
	i=i+1;
	end
end
