import Test.QuickCheck

isTriple :: Integer -> Integer -> Integer -> Bool
isTriple a b c = (a*a + b*b == c*c)

triple :: Integer -> Integer -> (Integer, Integer, Integer)
triple a b = (a*a - b*b, 2*a*b, a*a + b*b)

p_triples x y = x>0 && y>0 && x>y ==> isTriple l1 l2  h
    where
        (l1,l2,h) = triple x y

swap :: (a,b) -> (b,a)
swap (x,y) = (y,x)

sort2 :: Ord a => (a,a) -> (a,a)
sort2 (x,y) = if y<x then (y,x) else (x,y)

p1_sort2 x y = sorted (sort2(x,y))
    where 
        sorted (x,y) = x<=y

p2_sort2 x y = sameElements (x,y) (sort2 (x,y))
    where
        sameElements (x,y) (x',y') = (x==x' && y==y') || (x==y' && y==x')

test_sort2 = do
    quickCheck (p1_sort2 :: Integer -> Integer -> Bool)
    quickCheck (p2_sort2 :: Integer -> Integer -> Bool)

sort3 :: Ord a => (a,a,a) -> (a,a,a)
sort3 (x,y,z)  | z <= y && y <= x = (z,y,x)
               | z <= x && x <= y = (z,x,y)
               | z >= y && z <= x = (y,z,x)
               | x >= y && x <= z = (y,x,z)
               | x <= z && z <= y = (x,z,y)
               | otherwise      = (x,y,z)

p1_sort3 x y z = sorted (sort3 (x,y,z))
    where 
        sorted (x,y,z) = x<=y && y<=z

p2_sort3 x y z = sameElements (x,y,z) (sort3 (x,y,z))
    where
        sameElements (x,y,z) (x',y',z') = (x==x' && y==y' && z==z') || (x==x' && y==z' && z==y') || (x==y' && y==x' && z==z') || (x==y' && y==z' && z==x') || (x==z' && y==y' && z==x') || (x==z' && y==x' && z==y')

test_sort3 = do
    quickCheck (p1_sort3 :: Integer -> Integer -> Integer -> Bool)
    quickCheck (p2_sort3 :: Integer -> Integer -> Integer -> Bool)

max2 :: Ord a => a -> a -> a
x `max2` y = max x y 

p1_max2 x y = (max2 x y == x) || (max2 x y == y)

p2_max2 x y = (max2 x y >= x) && (max2 x y >= y)

p3_max2 x y = x>=y ==> max2 x y == x

p4_max2 x y = y>=x ==> max2 x y == y

between :: Ord a => a -> (a,a) -> Bool
between x (y,z) = (x>=y && x<=z) 

equals3 :: Eq a => (a,a,a) -> Bool
equals3 (x,y,z) = (x==y && y==z)

type Hour      = Integer
type Minute    = Integer
type Second    = Integer
decompose :: Second -> (Hour,Minute,Second) 
decompose x = (hours, minutes, seconds)
    where
        hours = div x 3600
        minutes = div (mod x 3600) 60
        seconds = mod (mod x 3600) 60

p_decompose x = x>=0 ==> h*3600 + m*60 + s == x && between m (0,59) && between s (0,59)
    where 
        (h,m,s) = decompose x

oneEuro :: Double
oneEuro = 166.386

pesetasToEuros :: Double -> Double
pesetasToEuros x = x/oneEuro

eurosToPesetas :: Double -> Double
eurosToPesetas x = x*oneEuro

p_inverse x = eurosToPesetas (pesetasToEuros x) ~= x

infix 4 ~=
(~=) :: Double -> Double -> Bool 
x ~= y = abs (x-y) < epsilon
    where 
        epsilon = 1/1000

roots :: (Real a, Floating a) => a -> a -> a -> (a,a)
roots x y z | disc > 0 = (r1,r2)
             | disc == 0 = (r1,r1)
             | otherwise = error "Non real roots"
                where
                    disc = (y^2)-4*x*z
                    r1   = (-y + sqrt disc)/(2*x)
                    r2   = (-y - sqrt disc)/(2*x)

p1_roots a b c = isRoot r1 && isRoot r2
    where
        (r1,r2) = roots a b c 
        isRoot r = a*r^2 + b*r + c ~= 0

p2_roots a b c = a>0 && (b^2)-4*a*c >=0 ==> isRoot r1 && isRoot r2
    where
        (r1,r2) = roots a b c 
        isRoot r = a*r^2 + b*r + c ~= 0

isMultiple :: (Integral a) => a -> a -> Bool
isMultiple x y = mod x y == 0

infixl 1 ==>>
(==>>) :: Bool -> Bool -> Bool
x ==>> y = not x || y

isLeap :: Integer -> Bool
isLeap x = (mod x 4 == 0) && ((mod x 100 == 0) ==>> (mod x 400 == 0))

power :: Integer -> Integer -> Integer
power x y | y == 0 = 1
          | y > 0 = x*(power x (y-1))
          | y < 0 = error "negative exponent not allowed"

power' :: Integer -> Integer -> Integer
power' x y | y == 0 = 1
           | mod y 2 == 0 = (power' x (div y 2))^2 
           | mod y 2 /= 0 = x*(power' x (y-1))
           | y < 0 = error "negative exponent not allowed"

p_power b n = n>=0 ==> (power b n == sol) && (power' b n == sol)
    where
        sol=b^n

factorial :: Integer -> Integer
factorial 1 = 1
factorial x = x * (factorial (x-1))

divides :: Integer -> Integer -> Bool
divides x y = mod y x == 0

p1_divides x y = y/=0 && y `divides` x ==> div x y * y == x

p2_divides x y z = z/= 0 && z `divides` y && z `divides` x ==> z `divides` (x+y)

median :: Ord a => (a,a,a,a,a) -> a
median (x,y,z,t,u)
    | x > z = median (z,y,x,t,u) 
    | y > z = median (x,z,y,t,u)
    | t < z = median (x,y,t,z,u)
    | u < z = median (x,y,u,t,z)
    | otherwise = z