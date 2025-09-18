import math
a,b,c = input("input a,b,c: ").split()
a = int(a)
b = int(b)
c = int(c)
delta = math.sqrt(pow(b,2)-4*a*c)
x1 = (-b+delta)/2*a
x2 = (-b-delta)/2*a

print(f"the solutions of {a}x^2+{b}x+{c} is {x1} and {x2}")