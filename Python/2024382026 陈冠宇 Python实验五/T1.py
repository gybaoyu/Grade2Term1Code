import random

# 初始化游戏参数
X = random.randint(0, 300)  # 随机生成0-300之间的整数
N = 10  # 最多猜测次数
guess_count = 0

print(f"0-300之间的数字，你有{N}次机会")

while guess_count < N:
    guess_count += 1
    guess = int(input(f"第{guess_count}次猜测，请输入数字："))

    if guess == X:
        print("猜中了")
        break
    elif guess > X:
        print("太大了")
    else:
        print("太小了")

    # 显示剩余次数
    remaining = N - guess_count
    if remaining > 0:
        print(f"还有{remaining}次机会")
    else:
        print(f"游戏结束！正确答案是：{X}")