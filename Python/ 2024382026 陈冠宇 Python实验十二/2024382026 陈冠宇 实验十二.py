# T1：写入新文件 scompanies.txt
with open('7_companies.txt', 'r') as source_file, open('scompanies.txt', 'w') as target_file:
    for index, line in enumerate(source_file):
        new_line = f"{index + 1}. {line.strip()}\n"
        target_file.write(new_line)

# T2：直接修改原文件
with open('7_companies.txt', 'r') as file:
    lines = file.readlines()
with open('7_companies.txt', 'w') as file:
    for index, line in enumerate(lines):
        new_line = f"{index + 1}. {line.strip()}\n"
        file.write(new_line)

# T3：唐诗三百首
import re
from collections import Counter
with open('7_宋词三百首.txt', 'r', encoding='utf-8') as f:
    text = f.read()
words = re.findall(r'[\u4e00-\u9fa5]+', text)  # 匹配所有中文字符
word_count = Counter(words)
most_common_10 = word_count.most_common(10)
for word, count in most_common_10:
    print(f"词：{word}，出现次数：{count}")

# T4：众数
import random
with open('random.txt', 'w') as f:
    for _ in range(500):
        num = random.randint(1, 100)
        f.write(f"{num}\n")

count_dict = {}
with open('random.txt', 'r') as f:
    for line in f:
        num = int(line.strip())
        count_dict[num] = count_dict.get(num, 0) + 1

max_count = max(count_dict.values())
modes = [num for num, count in count_dict.items() if count == max_count]

print("众数：", modes)
print("出现次数：", max_count)