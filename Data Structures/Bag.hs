data Bag a = Empty | Node a Int (Bag a) deriving Show

bag1 :: Bag Int
bag1 = Node 1 4 (Node 3 5 (Node 7 1 (Node 9 4Empty)))

bag2 :: Bag Int
bag2 = Node 1 2 (Node 4 8 (Node 7 1 (Node 9 3 Empty)))

empty :: Bag a 
empty = Empty

isEmpty :: Bag a -> Bool
isEmpty Empty = True
isEmpty _     = False


insert :: (Ord a) => a -> Bag a -> Bag a
insert x Empty                    = Node x 1 (Empty)
insert x (Node y i b) | y < x     = Node y i (insert x b)
                      | y == x    = Node y (i+1) b
                      | otherwise = Node x 1 (Node y i b)

occurrences :: (Ord a) => a -> Bag a -> Int
occurrences x Empty                    = 0
occurrences x (Node y i b) | y < x     = occurrences x b
                           | y == x    = i
                           | otherwise = 0

delete :: (Ord a) => a -> Bag a -> Bag a
delete x Empty                    = Empty
delete x (Node y i b) | y < x     = Node y i (delete x b)
                      | y == x    = if i==1 then b else Node y (i-1) b
                      | otherwise = (Node y i b)

union :: (Ord a) => Bag a -> Bag a -> Bag a
union b1 Empty                                = b1
union Empty b2                                = b2
union (Node y i b1) (Node z j b2) | y < z     = Node y i (union (b1) (Node z j b2))
                                  | y == z    = Node y (max i j) (union b1 b2)
                                  | otherwise = Node z j (union (Node y i b1) (b2))

intersection :: (Ord a) => Bag a -> Bag a -> Bag a
intersection b1 Empty                                = Empty
intersection Empty b2                                = Empty
intersection (Node y i b1) (Node z j b2) | y < z     = intersection (b1) (Node z j b2)
                                         | y == z    = Node y (min i j) (intersection b1 b2)
                                         | otherwise = intersection (Node y i b1) (b2)

difference :: (Ord a) => Bag a -> Bag a -> Bag a
difference b1 Empty                                = b1
difference Empty b2                                = Empty
difference (Node y i b1) (Node z j b2) | y < z     = Node y i (difference (b1) (Node z j b2))
                                       | y == z    = if i==j then difference b1 b2 else Node y (abs(i-j)) (difference b1 b2)
                                       | otherwise = difference (Node y i b1) (b2)