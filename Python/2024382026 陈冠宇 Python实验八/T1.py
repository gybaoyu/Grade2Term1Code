# T1 连乘计算
# 实现multi()函数，参数个数不限。返回所有参数的乘积。
import math


def multi(*nums):
    result = 1
    for num in nums:
        result *= num
    return result
print("multi(1,2,3,4) =",multi(1,2,3,4))

# T2质数判断
# 实现isPrime()函数，参数为整数。如果实参是整数且为质数，返回True，否则返回False。
def isPrime(num):
    for i in range(2,int(math.sqrt(num))+1):
        if num % i == 0:
            return False
    return True
x = int(input("Enter a number: "))
if isPrime(x):
    print("The number is prime")
else:
    print("The number is not prime")

# T3寻找水仙花数
# 实现isNarcissus(x)函数判断x是否是水仙花数，如果是，则输出x。利用isNarcissus()函数找出[100，999]范围内所有水仙花数。
def isNarcissus(num):
    if  100<=num<=999 and type(num)==int:
        a = num//100
        b = (num//10)%10
        c = num%10
        if a**3+b**3+c**3 == num:
            return True
    return False
x = int(input("Enter a number: "))
if isNarcissus(x):
    print("The number is Narcissus")
else:
    print("The number is not Narcissus")

# T4设计一个简单计算器的程序，可以实现加、减、乘、除、整除和幂运算。
def add(a,b):
    return a+b
def subtract(a, b):
    return a-b
def multiply(a,b):
    return a*b
def divide(a,b):
    if b!=0:
        return a/b
def floorDivide(a,b):
    if b!=0:
        return a//b
def power(a,b):
    res = a
    for i in range(1,b+1):
        res*=a
    return res


m=eval(input('请输入第一个操作数:'))       #输入整数、浮点数或复数
sign=input('输入运算符号:')             #输入运算符号'+'、'-'、'*'、'/'、'//'、'**'
n=eval(input('请输入第二个操作数:'))
if sign=='+':
    print(add(m,n))
elif sign=='-':
    print(subtract(m, n))
elif sign=='*':
    print(multiply(m,n))
elif sign=='/':
    print(divide(m,n))
elif sign=='//':
    print(floorDivide(m,n))
elif sign=='**':
    print(power(m,n))
else:
    print("运算符号只能是'+'、'-'、'*'、'/'、'//'、'**'之一")

#T5
# 以函数的方式，实现绘制五星红旗。利用turtle模块。
import turtle
import math

def draw_polygon(aTurtle, size=50, n=3):
    ''' 绘制正多边形
    args:
        aTurtle: turtle对象实例
        size: int类型，正多边形的边长
        n: int类型，是几边形
    '''
    for i in range(n):
        aTurtle.forward(size)
        aTurtle.left(360.0 / n)


def draw_n_angle(aTurtle, size=50, num=5, color=None):
    ''' 绘制正n角形，默认为黄色
    args:
        aTurtle: turtle对象实例
        size: int类型，正多角形的边长
        n: int类型，是几角形
        color: str， 图形颜色，默认不填色
    '''
    if color:
        aTurtle.begin_fill()
        aTurtle.fillcolor(color)
    for i in range(num):
        aTurtle.forward(size)
        aTurtle.left(360.0 / num)
        aTurtle.forward(size)
        aTurtle.right(2 * 360.0 / num)
    if color:
        aTurtle.end_fill()


def draw_5_angle(aTurtle=None, start_pos=(0, 0), end_pos=(0, 10), radius=100, color=None):
    ''' 根据起始位置、结束位置和外接圆半径画五角星

    args:
        aTurtle: turtle对象实例
        start_pos: int的二元tuple，要画的五角星的外接圆圆心
        end_pos: int的二元tuple，圆心指向的位置坐标点
        radius: 五角星外接圆半径
        color: str， 图形颜色，默认不填色
    '''
    aTurtle = aTurtle or turtle.Turtle()
    # size = radius * math.sin(math.pi/5)/math.sin(math.pi*2/5)计算公式错误，但不影响显示
    size = radius * math.sin(math.pi / 5) / math.sin(math.pi * 3 / 10)  # 修正
    angle = math.degrees(math.atan2(end_pos[1] - start_pos[1], end_pos[0] - start_pos[0]))
    print(angle)
    aTurtle.pencolor("yellow")
    aTurtle.penup()
    aTurtle.goto(start_pos)
    aTurtle.setheading(0)
    aTurtle.left(angle)
    aTurtle.fd(radius)
    aTurtle.pendown()
    aTurtle.right(math.degrees(math.pi * 9 / 10))
    draw_n_angle(aTurtle, size, 5, color)


def draw_5_star_flag(times=20.0):
    width, height = 30 * times, 20 * times
    # 初始化屏幕
    window = turtle.Screen()
    aTurtle = turtle.Turtle()
    aTurtle.hideturtle()
    aTurtle.speed(10)
    # 画红旗
    aTurtle.penup()
    aTurtle.goto(-width / 2, height / 2)
    aTurtle.pencolor("red")
    aTurtle.pendown()
    aTurtle.begin_fill()
    aTurtle.fillcolor('red')
    aTurtle.fd(width)
    aTurtle.right(90)
    aTurtle.fd(height)
    aTurtle.right(90)
    aTurtle.fd(width)
    aTurtle.right(90)
    aTurtle.fd(height)
    aTurtle.right(90)
    aTurtle.end_fill()
    # 画大星星
    draw_5_angle(aTurtle, start_pos=(-10 * times, 5 * times), end_pos=(-10 * times, 8 * times), radius=3 * times,color='yellow')
    # 画四个小星星
    stars_start_pos = [(-5, 8), (-3, 6), (-3, 3), (-5, 1)]
    for pos in stars_start_pos:
        draw_5_angle(aTurtle, start_pos=(pos[0] * times, pos[1] * times), end_pos=(-10 * times, 5 * times),radius=1 * times, color='yellow')
        # 点击关闭窗口
    window.exitonclick()
draw_5_star_flag()