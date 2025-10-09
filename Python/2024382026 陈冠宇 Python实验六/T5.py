import random

target = random.randint(0, 300)
guess_count = 0

while True:
    user_input = input(f"第{guess_count + 1}次猜测，请输入数字：")

    # 检查是否退出
    if user_input.lower() == 'quit':
        print(f"再见！正确答案是：{target}")
        break

    guess = int(user_input)
    guess_count += 1
    if guess == target:
        print(f"恭喜你！猜中了！用了{guess_count}次")
        break
    elif guess > target:
        print("太大了")
    else:
        print("太小了")