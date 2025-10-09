def score_to_grade(score):
    if score < 0 or score > 100:
        return "无效成绩"
    elif score < 60:
        return "F"
    elif score < 70:
        return "D"
    elif score < 80:
        return "C"
    elif score < 90:
        return "B"
    else:
        return "A"

score = int(input("input score: "))
grade = score_to_grade(score)
print(f"成绩 {score} -> {grade}")