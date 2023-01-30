data Tree a = Empty | Node a [Tree a] deriving Show

tree1 :: Tree Int
tree1 = Node 1 [Node 2 [Node 4 [], Node 5 [], Node 6 []], Node 3 [Node 7 []]]

sumT :: (Num a) => Tree a -> a
sumT Empty       = 0
sumT (Node x ts) = x + sum (map sumT ts) 
-- sumT (Node x ts) = x + sum [sumT t | t <- ts]

sizeT :: Tree a -> Int
sizeT Empty       = 0
sizeT (Node x ts) = 1 + sum (map sizeT ts) 

maximumT :: (Ord a) => Tree a -> a
maximumT Empty        = error "no maximum"
maximumT (Node x [])  = x
maximumT (Node x ts)  = max x (maximum (map maximumT ts))

leavesT :: Tree a -> [a]
leavesT Empty       = []
leavesT (Node x []) = [x]
leavesT (Node x ts) = concat (map leavesT ts)

heightT :: Tree a -> Int
heightT Empty       = 0
heightT (Node x []) = 1
heightT (Node x ts) = 1 + maximum (map heightT ts)


data TreeB a = EmptyB | NodeB a (TreeB a) (TreeB a) deriving Show

tree2 :: TreeB Int 
tree2 = NodeB 1 (NodeB 2 (NodeB 4 (EmptyB) (EmptyB)) (NodeB 5 (EmptyB) (EmptyB))) 
                (NodeB 3 (NodeB 6 (EmptyB) (EmptyB)) (EmptyB))

sumB :: Num a => TreeB a -> a
sumB EmptyB          = 0
sumB (NodeB x lt rt) = x + sumB lt + sumB rt

heightTB :: TreeB a -> Int
heightTB EmptyB = 0
heightTB (NodeB x lt rt) = 1 + max (heightTB lt) (heightTB rt)

pathsToB :: (Eq a) => a -> TreeB a -> [[a]] 
pathsToB x EmptyB = []
pathsToB x (NodeB y lt rt)
    | x==y = [y] : ps 
    | otherwise = ps
        where
            ps = map (y:) (pathsToB x lt ++ pathsToB x rt)

tree3 :: TreeB Int 
tree3 = NodeB 1 (NodeB 2 (NodeB 4 (EmptyB) (EmptyB)) (NodeB 5 (EmptyB) (EmptyB))) 
                (NodeB 3 (NodeB 6 (EmptyB) (EmptyB)) (NodeB 2 (EmptyB) (EmptyB)))