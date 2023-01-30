data Seq a = Empty | Node a (Seq a) deriving Show

testSeq1 :: Seq Int
testSeq1 = Node 10(Node 40(Node 30 Empty))

empty :: Seq a
empty = Empty

size :: Seq a -> Integer
size Empty      = 0
size (Node y s) = 1 + size s

insert :: Integer -> a -> Seq a -> Seq a
insert i x s = insertcount i x 0 s

insertcount :: Integer -> a -> Integer -> Seq a -> Seq a
insertcount i x cont Empty | (i == cont) = Node x (Empty)
                           | otherwise = error "invalid index"
insertcount i x cont (Node y s) | (i == cont) = Node x (Node y (s))
                                | otherwise = Node y (insertcount i x (cont+1) s)

get :: Integer -> Seq a -> a
get k Empty = error "Wrong index"
get 0 (Node x s1) = x
get k (Node x s1) = get (k-1) s1

invert :: Seq a -> Seq a
invert Empty = Empty
invert (s) = invertcount s 0 (size s)

invertcount :: Seq a -> Integer -> Integer -> Seq a
invertcount (s) x sz | (x == sz) = Empty
                     | otherwise = Node (get (sz-x-1) s) (invertcount s (x+1) sz)
