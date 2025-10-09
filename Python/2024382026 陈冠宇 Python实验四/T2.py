a = int(input())
b = int(input())
c = int(input())
max_value = a if a > b else b
max_value = max_value if max_value > c else c
print(f"{a}, {b}, {c} 中的最大值是：{max_value}")