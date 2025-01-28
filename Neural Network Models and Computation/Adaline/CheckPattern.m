function correct=CheckPattern(Data,W) 

correct = true;

for i=1:size(Data,1)
    
    
    [Input,Output,Target]=ValoresIOT(Data,W,i)
    
    if(Signo(Output)~=Target)
        correct = false;
        break;
    end

end 

end