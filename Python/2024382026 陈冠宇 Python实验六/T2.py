pi_over_4 = 0.0
denominator = 1
sign = 1
term_count = 0

while True:
    term = sign * (1.0 / denominator)
    pi_over_4 += term

    # 检查最后一项的绝对值是否小于10^-8
    if abs(term) < 1e-8:
        break

    denominator += 2
    sign = -sign
    term_count += 1

pi_value = pi_over_4 * 4
print(f"计算项数：{term_count + 1}")
print(f"π的近似值：{pi_value:.10f}")