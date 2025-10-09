print("枚举法")
solution_count = 0
for cock in range(1, 20):
    for hen in range(1, 33):
        chick = 100 - cock - hen
        if chick <= 0:
            continue
        total_money = cock * 5 + hen * 3 + chick / 3
        if total_money == 100 and chick % 3 == 0:
            solution_count += 1
            print(f"方案{solution_count}: 鸡翁{cock}只, 鸡母{hen}只, 鸡雏{chick}只")

if solution_count == 0:
    print("无解")

print("解方程法")


solution_count = 0
for x in range(1, 20):
    if (100 - 7 * x) % 4 == 0:
        y = (100 - 7 * x) // 4
        z = 100 - x - y

        if y > 0 and z > 0 and z % 3 == 0:
            solution_count += 1
            print(f"方案{solution_count}: 鸡翁{x}只, 鸡母{y}只, 鸡雏{z}只")

if solution_count == 0:
    print("无解")