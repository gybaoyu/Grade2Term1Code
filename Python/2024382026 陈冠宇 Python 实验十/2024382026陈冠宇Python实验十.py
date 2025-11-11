def sort_string_unicode():
    original_str = "python程序设计"
    print(f"原字符串: {original_str}")
    sorted_str1 = ''.join(sorted(original_str))
    print(f"排序结果: {sorted_str1}")

def sort_by_absolute_value():
    input_str = input("输入一组整数: ")
    numbers = [eval(x) for x in input_str.split()]
    sorted_numbers = sorted(numbers, key=lambda x: abs(x), reverse=True)
    print(f"原始数据: {numbers}")
    print(f"按绝对值从大到小排序: {sorted_numbers}")
    return sorted_numbers

def swap_max_min():
    input_str = input("输入一个列表: ")
    lst = [int(x.strip()) for x in input_str.strip('[]').split(',')]
    print(f"原始列表: {lst}")
    result = lst.copy()
    max_index = result.index(max(result))
    min_index = result.index(min(result))
    result[0], result[max_index] = result[max_index], result[0]
    min_index = result.index(min(result))
    result[-1], result[min_index] = result[min_index], result[-1]
    print(f"交换后列表: {result}")
    return result

def cafeteria_queue():
    queue = ["weiming", "zhanghua", "lijian", "caoyun"]
    print(f"初始队伍: {queue}")
    queue.insert(0, "chenhao")
    print(f"chenhao插队后: {queue}")
    queue.append("mabin")
    print(f"mabin排队后: {queue}")
    print("打饭顺序:")
    for i, student in enumerate(queue, 1):
        print(f"第{i}个: {student}")
    return queue


def has_duplicates_count(lst):
    for item in lst:
        if lst.count(item) > 1:
            return True
    return False
# 测试第五题
def test_duplicate_detection():
    print("=== 第五题 ===")
    # 测试用例
    test_lists = [
        [1, 2, 3, 4, 5],  # 无重复
        [1, 2, 3, 2, 1],  # 有重复
        ['a', 'b', 'c', 'a'],  # 有重复
        ['x', 'y', 'z'],  # 无重复
        [],  # 空列表
        [5]  # 单个元素
    ]
    for i, test_list in enumerate(test_lists, 1):
        original = test_list.copy()
        result = has_duplicates_count(test_list)
        print(f"测试用例{i}: {original} -> 存在重复元素: {result}")
        print(f"原列表未被修改: {test_list == original}")  # 验证原列表未被修改
    return True


print("=== 第一题 ===")
sort_string_unicode()
print("=== 第二题 ===")
# -9 10 -3 8 -1
sort_by_absolute_value()
print("=== 第三题 ===")
# [1,2,3,7,9,8]
swap_max_min()
print("=== 第四题 ===")
cafeteria_queue()
print("=== 第五题 ===")
test_duplicate_detection()