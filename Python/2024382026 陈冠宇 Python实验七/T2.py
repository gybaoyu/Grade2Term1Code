a = float(input("input a: "))
b = float(input("input b: "))
print("a+b={}".format(a+b))
print("a-b={}".format(a-b))
print("a*b={}".format(a*b))
#用if处理异常
if b==0:
    print("a can not be divided by 0")
else:
    print("a/b={}".format(a/b))

#用try except处理异常
try:
    print("a/b={}".format(a/b))
except ZeroDivisionError:
    print("a can not be divided by 0")