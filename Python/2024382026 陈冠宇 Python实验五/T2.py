for i in range(1, 10):
    for j in range(1, i + 1):
        # 格式化输出，保持对齐
        result = i * j
        print(f"{j}×{i}={result}", end="\t")
    print()  # 换行