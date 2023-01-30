import Test.QuickCheck
import Data.List

allDifferent :: (Eq a) => [a] -> Bool
allDifferent [_] = True
allDifferent (x:xs) = not (elem x xs) && allDifferent xs

replicate' :: Int -> a -> [a]
replicate' 0 y = []
replicate' x y = y : (replicate' (x-1) y)


p_replicate' n x = (n >= 0) && (n <= 1000) ==>
    (length (filter (==x) xs) == n) 
    && (length (filter (/=x) xs) == 0)
        where xs = replicate' n x 

p_prop x y = (x + y) == (y - x)

divisors :: Integer -> [Integer]
divisors n = [x | x <- [1..n] , mod n x == 0]

divisors' :: Integer -> [Integer]
divisors' n = [x | x <- [-n..n] , x/=0 ,mod n x == 0]

gcdiv :: Integer -> Integer -> Integer
gcdiv a b  | ((a == 0) && (b == 0)) = error "both values can't be zero"
           | (a == 0) = b
           | (b == 0) = a
           | otherwise = maximum (filter (\x -> elem x (divisors a)) (divisors b))

p_gcdiv x y z = ((x>0) && (y>0) && (z>0)) ==> ((gcdiv (z*x) (z*y)) == (z * (gcdiv x y))) 

lcmul :: Integer -> Integer -> Integer
lcmul x y = div (x*y) (gcdiv x y)

isPrime :: Integer -> Bool
isPrime x = (divisors x) == [1,x] 

primesUpto :: Integer -> [Integer]
primesUpto a = [x | x <- [1..a], isPrime x == True]

primesUpto' :: Integer -> [Integer]
primesUpto' a = filter isPrime [0..a]

p1_primes x = primesUpto x == primesUpto' x

take' :: Int -> [a] -> [a]
take' n xs = [ x | (p,x) <- zip [0..n] xs, p<n]

drop' :: Int -> [a] -> [a]
drop' n xs = [ x | (p,x) <- zip [0..(length xs)] xs, p>=n]

p_conc n xs = (n>=0) ==> ((take' n xs) ++ (drop' n xs)) == xs

concat' :: [[a]] -> [a]
concat' x = foldr (++) [] x

concat'' :: [[a]] -> [a]
concat'' b = [x | c <- b, x <- c]

unknown :: (Ord a) => [a] -> Bool                         --return True is list is ordered
unknown xs = and [ x<=y | (x,y) <- zip xs (tail xs) ]

insert' :: (Ord a) => a -> [a] -> [a]
insert' x a = (takeWhile (<x) a) ++ [x] ++ (dropWhile (<x) a)

p1_insert' x xs = unknown xs ==> unknown (insert x xs)

isort :: (Ord a) => [a] -> [a]
isort x = foldr insert [] x

p_isort xs = unknown (isort xs)

geometric :: Integer -> Integer -> [Integer]
geometric a b = iterate (*b) a

p1_geometric x r = x>0 && r>0 ==>
    and [ div z y == r | (y,z) <- zip xs (tail xs) ]
        where 
            xs = take 100 (geometric x r)

multiplesOf :: Integer -> [Integer]
multiplesOf a = iterate (+a) 0

powersOf :: Integer -> [Integer]
powersOf a = iterate (*a) 1

p_rightUnit xs = xs ++ [] == xs

p_associative xs ys zs = ((xs ++ ys) ++ zs) == (xs ++ (ys ++ zs))

nub' :: (Eq a) => [a] -> [a]
nub' [] = []
nub' (x:xs) | elem x xs = nub' xs
            | otherwise = [x] ++ (nub' xs)

p_nub' xs = nub xs == nub' xs

p_necessary xs = allDifferent (nub' xs)

allIn :: (Eq a) => [a] -> [a] -> Bool
ys `allIn` xs = all (`elem` xs) ys

p_noRepetition xs = xs `allIn` (nub' xs) && allDifferent (nub' xs)

bin :: Integer -> [[Char]]
bin x | x<0 = error "only natural numbers allowed"
      | x == 0 = [""]
      | otherwise = (map(\x -> x ++ "0") binnie) ++ (map(\x -> x ++ "1") binnie) where 
          binnie = bin(x-1)

varRep :: Integer -> [Char] -> [[Char]]
varRep x xs | x<0 = error "only natural numbers allowed"
            | x == 0 = [""]
            | otherwise = [x : y | x <- xs, y <- eppie] where 
                eppie = varRep (x-1) xs 

p_varRep m xs = m>=0 && m<=5 && n<=5 && allDifferent xs ==>
                    len vss == n^m
                    && allDifferent vss
                    && all (`allIn` xs) vss
        where
            vss = varRep m xs
            n = len xs
            len :: [a] -> Integer
            len xs = fromIntegral (length xs)

facts :: [Integer]
facts = [y | x <- [0..], let y = aux x] where
    aux 0 = 1
    aux n | n>0 = fromIntegral(n* (fromIntegral(facts !! (n-1))))




          