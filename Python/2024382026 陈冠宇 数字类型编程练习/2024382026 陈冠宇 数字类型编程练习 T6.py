#365%7=1
a = 365//7

print("T1\n")
for i in range(10):
    ans = 1+a*4*(i+1)*0.001+(i+1)*0.001
    print(f"N={(i+1)*0.001:.3f} ans={ans:.3f}\n")

print("T2\n")
for i in range(10):
    ans = 1+a*5*(i+1)*0.001+(i+1)*0.001
    print(f"N={(i+1)*0.001:.3f} ans={ans:.3f}\n")

print("T3\n")
for i in range(10):
    ans = 1+a*6*(i+1)*0.001+(i+1)*0.001
    print(f"N={(i+1)*0.001:.3f} ans={ans:.3f}\n")