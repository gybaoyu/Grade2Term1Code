#$ ￥
a = input()
b = int(a[:-1])
if "$" == a[-1]:
    print(f"{a} = {b/6}￥")
elif "￥" == a[-1]:
    print(f"{a} = {b*6}$")
else:
    print("invalid input")