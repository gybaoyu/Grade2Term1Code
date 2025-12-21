import numpy as np
import matplotlib.pyplot as plt

plt.rcParams['font.sans-serif'] = ['SimHei']
plt.rcParams['axes.unicode_minus'] = False

x = np.arange(0, 2 * np.pi, 0.1)
y_sin = np.sin(x)
y_cos = np.cos(x)
plt.figure(figsize=(10, 8))
plt.subplot(2, 1, 1)
plt.plot(x, y_sin, 'b-', linewidth=2, label='sin(x)')
plt.title('正弦函数 Sine Function')
plt.xlabel('x (弧度)')
plt.ylabel('sin(x)')
plt.grid(True, alpha=0.3)
plt.legend()
plt.subplot(2, 1, 2)
plt.plot(x, y_cos, 'r-', linewidth=2, label='cos(x)')
plt.title('余弦函数 Cosine Function')
plt.xlabel('x (弧度)')
plt.ylabel('cos(x)')
plt.grid(True, alpha=0.3)
plt.legend()
plt.subplots_adjust(hspace=0.5)  # 垂直间距
plt.show()

plt.axes([.1,.1,0.8,0.8])
plt.plot(range(7),[3,4,7,6,2,8,9],color='r',marker='o')
plt.axes([.3,.15,0.4,0.3])
plt.plot(range(7),[5,1,8,2,6,9,4],color='green',marker='o')
plt.show()

x = np.linspace(0, 4, 50)
y1 = x
y2 = x + 2
y3 = x**2
plt.figure(figsize=(8, 6))
plt.plot(x, y1, 'r', linewidth=2, label='y = x')
plt.plot(x, y2, 'g*', linewidth=2, label='y = x + 2')
plt.plot(x, y3, 'b+', linewidth=2, label='y = x²')

plt.xlim(0, 4)
plt.ylim(0, 14)

plt.title("函数曲线对比图", fontsize=14)
plt.xlabel("x", fontsize=12)
plt.ylabel("y", fontsize=12)

plt.legend(loc='upper left')

plt.tight_layout()
plt.show()


countries = ['Country A', 'Country B', 'Country C', 'Country D', 'Country E']
gnp = [1, 2, 3, 5, 8]
poverty_ratio = [0.11, 0.12, 0.13, 0.15, 0.18]
plt.figure(figsize=(10, 6))

plt.scatter(gnp, poverty_ratio, color='blue', s=100, alpha=0.7, edgecolors='black', linewidth=1.5)

plt.xlim(0, 9)
plt.ylim(0.10, 0.20)

plt.xticks(np.arange(0, 9, 1))
plt.yticks(np.arange(0.10, 0.21, 0.01))

for i, country in enumerate(countries):
    plt.annotate(country, (gnp[i], poverty_ratio[i]),
                 xytext=(5, 5), textcoords='offset points',
                 fontsize=9, fontweight='bold')

for i, (x, y) in enumerate(zip(gnp, poverty_ratio)):
    plt.text(x+0.1, y-0.002, f'({x}, {y*100}%)', fontsize=9, fontstyle='italic')
plt.legend()

plt.tight_layout()
plt.show()