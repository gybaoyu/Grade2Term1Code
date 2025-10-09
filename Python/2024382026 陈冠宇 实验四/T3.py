def sign_function_if(x):
    if x > 0:
        return 1
    elif x == 0:
        return 0
    else:
        return -1
def sign_function_nested(x):
    if x >= 0:
        if x == 0:
            return 0
        else:
            return 1
    else:
        return -1

# 测试符号函数
x = int(input("input x: "))
result1 = sign_function_if(x)
result2 = sign_function_nested(x)
print(f"sign({x}) = {result1} (if-elif-else), {result2} (嵌套if)")