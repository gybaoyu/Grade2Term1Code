unrelated_numbers = []

for num in range(1, 91):
    if num % 3 == 0:
        continue

    num_str = str(num)
    has_three = False
    for digit in num_str:
        if digit == '3':
            has_three = True
            break

    if not has_three:
        unrelated_numbers.append(num)

print("与3无关的整数：")
for i, num in enumerate(unrelated_numbers):
    print(num, end=' ')
    if (i + 1) % 10 == 0:  # 每10个数字换一行
        print()
print(f"\n共计{len(unrelated_numbers)}个")