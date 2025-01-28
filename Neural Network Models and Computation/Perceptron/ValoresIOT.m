function [Input,Output,Target]=ValoresIOT(Data,W,i)

Input = Data(i,1:end-1);
Target = Data(i,end);
Output = Signo(Input*W(1:end-1)-W(end));

end 