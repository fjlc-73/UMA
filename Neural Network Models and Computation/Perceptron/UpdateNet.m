function W=UpdateNet(W,LR,Output,Target,Input)

diffW = LR*(Target-Output)*[Input,-1]';
W = W + diffW;

end 