n = int(input("请输入一个正整数："))

if n <= 1:
    print(f"{n}不是质数")
elif n == 2:
    print(f"{n}是质数")
else:
    is_prime = True

    for i in range(2, int(n ** 0.5) + 1):
        if n % i == 0:
            is_prime = False
            break

    if is_prime:
        print(f"{n}是质数")
    else:
        print(f"{n}不是质数")