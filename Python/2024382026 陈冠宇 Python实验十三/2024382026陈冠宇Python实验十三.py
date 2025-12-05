'''
1、身体质量指数BMI(Body Mass Index）是国际上常用的衡量人体肥胖程度和是否健康的重要标准。
BMI = 体重（kg）÷身高²（m²）
请定义BMI类，将身高、体重作为__init__()方法的参数，在方法中计算BMI指数，并使用printBMI()方法输出BMI指数（保留一位小数），使用本人身高、体重数据实例化。
'''


class BMI():
    def __init__(self, height, weight):
        self.height = height
        self.weight = weight
        self.bmi = weight / (height ** 2)

    def printBMI(self):
        print(f'BMI指数: {self.bmi:.1f}')


x = BMI(1.65, 55)
x.printBMI()
del x

'''
2、在第1题的基础上定义ChinaBMI子类，根据BMI指数的中国参考标准重写printBMI()方法，在输出BMI指数（保留一位小数）后输出BMI分类和相关疾病发病的危险性信息。

BMI分类	中国参考标准	相关疾病发病的危险性
偏瘦	< 18.5	低（但其他疾病危险性增加）
正常	18.5 ~ 23.9	平均水平
偏胖	24 ~ 26.9	增加
肥胖	27 ~ 29.9	中度增加
重度肥胖	>=30	严重增加
'''


class ChinaBMI(BMI):
    '''定义子类ChinaBMI'''

    def printBMI(self):
        print(f'BMI指数: {self.bmi:.1f}')

        if self.bmi < 18.5:
            category = '偏瘦'
            risk = '低（但其他疾病危险性增加）'
        elif 18.5 <= self.bmi < 24:
            category = '正常'
            risk = '平均水平'
        elif 24 <= self.bmi < 27:
            category = '偏胖'
            risk = '增加'
        elif 27 <= self.bmi < 30:
            category = '肥胖'
            risk = '中度增加'
        else:
            category = '重度肥胖'
            risk = '严重增加'

        print(f'BMI分类: {category}')
        print(f'相关疾病发病的危险性: {risk}')


if __name__ == '__main__':
    x = ChinaBMI(1.65, 55)
    x.printBMI()
    del x

'''
3、定义平角直角坐标系的坐标点类Point，使用横坐标值和纵坐标值初始化，重载"-"运算，计算两个坐标点之间直线的欧几里得距离。
两点之间的欧几里得距离公式：d=√((x₁-x₂)²+(y₁-y₂)²) 
例：Point(3,4)-Point(6,0)得5.0。
'''


class Point:
    def __init__(self, x, y):
        self.x = x
        self.y = y

    def __sub__(self, obj):
        if self.__class__ != obj.__class__:
            print('The classes of the two instances are different.')
            return None

        distance = ((self.x - obj.x) ** 2 + (self.y - obj.y) ** 2) ** 0.5
        return distance


if __name__ == '__main__':
    p1 = Point(3, 4)
    p2 = Point(6, 0)
    d = p1 - p2
    print('欧几里得距离:', d)

    # 测试不同类的情况
    # p2 = BMI(1.58, 55) # p1与p2不同类，输出提示信息及None
    # d = p1 - p2
    # print('欧几里得距离:', d)