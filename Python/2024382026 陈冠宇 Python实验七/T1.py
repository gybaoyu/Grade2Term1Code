import random as rd
X = rd.randint(0,300)
while 1:
    guess = input("input an integer: ")
    try:
        guess = int(guess)
    except ValueError:
        print("illegal input")
        continue
    if guess > X:
        print("too high")
    elif guess < X:
        print("too low")
    else:
        print("correct")
        break
