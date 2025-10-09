def check_comfort_temperature():
    season = input("请输入当前季节（春/夏/秋/冬）：").strip()
    temperature = float(input("请输入当前温度（℃）："))
    comfort_ranges = {
        '春': (18, 25),
        '夏': (19, 24),
        '秋': (18, 25),
        '冬': (12, 22)
    }
    if season not in comfort_ranges:
        print("季节输入错误！请输入春、夏、秋、冬中的一个")
        return
    min_temp, max_temp = comfort_ranges[season]
    if min_temp <= temperature <= max_temp:
        print("舒适")
    else:
        print("不舒适")


if __name__ == "__main__":
    print("舒适温度标准：")
    print("夏季：19℃ ~ 24℃")
    print("冬季：12℃ ~ 22℃")
    print("春秋季：18℃ ~ 25℃")
    check_comfort_temperature()