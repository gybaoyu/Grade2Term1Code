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

def has_duplicates_set(lst):
    return len(lst) != len(set(lst))


def filter_special_words(word_list):
    # 定义元音字母和数字的集合
    forbidden_chars = set('aeiouAEIOU0123456789')
    result = []
    for word in word_list:
        # 将单词转换为小写后的字符集合
        word_chars = set(word.lower())
        # 检查交集是否为空（即没有共同字符）
        if not word_chars.intersection(forbidden_chars):
            result.append(word)
    return result


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

print("=== 第五,六题 ===")
test_lists = [
    [1, 2, 3, 4, 5],
    [1, 2, 3, 2, 1],
    ['a', 'b', 'c', 'a'],
    ['x', 'y', 'z']]
for i, test_list in enumerate(test_lists, 1):
    result1 = has_duplicates_count(test_list)
    result2 = has_duplicates_set(test_list)
    print(f"测试用例{i}: {test_list}")
    print(f"count方法: {result1}, 集合方法: {result2}, 结果一致: {result1 == result2}")

print("=== 第七题 ===")
test_case = ["python", "rhythm", "sky", "fly", "hello", "world123","", "a", "b", "1", "2"]
result = filter_special_words(test_case)
print(f"测试用例{i}:")
print(f"原始列表: {test_case}")
print(f"符合条件的单词: {result}")