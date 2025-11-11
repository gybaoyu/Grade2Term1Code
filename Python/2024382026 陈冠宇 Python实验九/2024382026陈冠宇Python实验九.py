print("=====第一题=====")
classA = {1,3,7,15,22,26,27}
classB = {1,4,8,9,15,20,26,30}
lib = {1,8,15,22,29}
print("两个班级都想去图书馆集体看书的日期:{}".format(classA.intersection(classB)))
print("仅有一个班级想去图书馆集体看书的日期:{}".format(classA.symmetric_difference(classB)))
print("classA班想去但classB班不想去图书馆集体看书的日期:{}".format(classA.difference(classB)))
print("两个班级能一起去图书馆集体看书的日期:{}".format(lib.intersection(classA.intersection(classB))))
print("classA班能去但classB班不能去图书馆集体看书的日期:{}".format(classA.intersection(lib)
                                                                   .difference(classA.intersection(classB.intersection(lib)))))
print("=====第二题=====")
str0 = set(input("输入字符串: "))
str1 = {'a','e','i','o','u','A','E','I','O','U'}
print("输入字符串中是否含有元音字母:{}".format(not str0.isdisjoint(str1)))

print("=====第三题=====")
ip = input("输入IP地址: ")
if len(ip) != 32 or set(ip).difference({'1','0'}):
    print("输入不合法")
else:
    for i in range(1,5):
        ip0 = ip[(i-1)*8:i*8].strip("")
        print(int(ip0, 2),end='')
        if i != 4:
            print('.',end='')
#Apple
#10110100111100001011111100001101