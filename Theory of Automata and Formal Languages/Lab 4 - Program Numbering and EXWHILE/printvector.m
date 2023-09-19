function printvector = printvector(n)


for i=0:n-1
	disp([num2str(i+1), ': (' num2str(godeldecoding(i)) ')']);
	i=i+1;
	end
end
