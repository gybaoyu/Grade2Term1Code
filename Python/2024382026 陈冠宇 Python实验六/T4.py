total_money = int(input("请输入总钱数："))
total_chickens = int(input("请输入想买的鸡的总数："))

solutions = []

for cocks in range(0, total_money // 5 + 1):
    for hens in range(0, (total_money - cocks * 5) // 3 + 1):
        chicks = total_chickens - cocks - hens

        if chicks < 0:
            continue
        if cocks * 5 + hens * 3 + chicks / 3 == total_money and chicks % 3 == 0:
            solutions.append((cocks, hens, chicks))

if solutions:
    # 按鸡翁最少、鸡雏最多排序
    solutions.sort(key=lambda x: (x[0], -x[2]))
    best_solution = solutions[0]
    print(f"鸡翁{best_solution[0]}只, 鸡母{best_solution[1]}只, 鸡雏{best_solution[2]}只")
else:
    print("无解")