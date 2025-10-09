# 测试题目要求的年份
test_year = (input("input year: "))
if (test_year % 4 == 0 and test_year % 100 != 0) or (test_year % 400 == 0):
    result = "是闰年"
else:
    result = "不是闰年"
print(f"{test_year}年{result}")