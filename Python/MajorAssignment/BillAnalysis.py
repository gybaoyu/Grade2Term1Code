import numpy as np
import pandas as pd
import matplotlib.pyplot as plt

plt.rcParams['font.sans-serif'] = ['SimHei']
plt.rcParams['axes.unicode_minus'] = False

# 数据预处理
def preprocess_data(df):
    df['金额(元)'] = df['金额(元)'].astype(str).str.replace('¥', '').str.replace(',', '').astype(float)
    df['交易时间'] = pd.to_datetime(df['交易时间'])
    df['日期'] = df['交易时间'].dt.date
    df['月份'] = df['交易时间'].dt.to_period('M')
    df['星期'] = df['交易时间'].dt.day_name()
    df['小时'] = df['交易时间'].dt.hour
    return df
def extract_shenzhen_university_data(df):
    szu_data = df[df['交易对方'].str.contains('深圳大学', na=False)].copy()
    def extract_category(commodity):
        commodity = str(commodity)
        if '荔园小巴' in commodity or '小巴' in commodity:
            return '交通'
        elif any(keyword in commodity for keyword in
                 ['荔天', '听山', '实验', '南校区', '粥铺', '餐厅', '大众菜', '烧腊', '风味屋']):
            return '餐饮'
        elif any(keyword in commodity for keyword in ['罗森', 'LAWSON']):
            return '便利店'
        elif any(keyword in commodity for keyword in ['水电费', '乔相阁']):
            return '水电费'
        elif any(keyword in commodity for keyword in ['校医院', '挂号']):
            return '医疗'
        else:
            return '其他消费'
    szu_data['消费类别'] = szu_data['商品'].apply(extract_category)
    return szu_data
def preprocess_product_name(commodity):
    if pd.isna(commodity):
        return '其他'
    commodity = str(commodity)
    if '电费' in commodity:
        return '电费'
    elif '校园卡' in commodity:
        return '校园卡'
    elif '荔园小巴' in commodity or '小巴' in commodity:
        return '荔园小巴'
    return commodity

def monthly_spend(szu_data):
    plt.figure(figsize=(12, 8))
    monthly_spending = szu_data.groupby('月份')['金额(元)'].sum()

    # 绘制折线图，确保使用数据点标记
    plt.plot(monthly_spending.index.astype(str), monthly_spending.values,
             marker='o', color='#2E86AB', markersize=8, linewidth=2)

    plt.title('深圳大学月度消费趋势', fontsize=20, fontweight='bold')
    plt.xlabel('月份', fontsize=16)
    plt.ylabel('消费金额(元)', fontsize=16)
    plt.xticks(rotation=45)
    plt.grid(True, alpha=0.3)
    # 在每个数据点上添加数值标注
    for i, (month, value) in enumerate(monthly_spending.items()):
        plt.text(i, value + monthly_spending.max() * 0.02, f'{value:.2f}元',
                 ha='center', va='bottom', fontsize=14, color='#2E86AB',
                 bbox=dict(boxstyle="round,pad=0.3", facecolor='white', alpha=0.8))

    stats_text = (f"最高: {monthly_spending.idxmax()}, {monthly_spending.max():.2f}元\n"
                  f"最低: {monthly_spending.idxmin()},{monthly_spending.min():.2f}元\n"
                  f"平均每月消费: {monthly_spending.mean():.2f}元")
    plt.annotate(stats_text,xy=(0.98, 0.98),xycoords='axes fraction',ha='left',va='top',fontsize=16,
                 bbox=dict(boxstyle="round,pad=0.5", facecolor='lightblue', alpha=0.8))
    plt.tight_layout()
    plt.show()
def category_spend(szu_data):
    plt.figure(figsize=(12, 10))
    category_spending = szu_data.groupby('消费类别')['金额(元)'].sum().sort_values(ascending=False)
    colors = plt.cm.Set3(np.linspace(0, 1, len(category_spending)))
    wedges, texts, autotexts = plt.pie(
        category_spending.values,
        labels=None,
        autopct='%1.1f%%',
        colors=colors,
        startangle=90,
        pctdistance=1.05,
        textprops={'fontsize': 14, 'fontweight': 'bold'},
        wedgeprops={'width': 0.6, 'linewidth': 1}  # 创建环形
    )
    # 在中心显示总计
    centre_circle = plt.Circle((0, 0), 0.4, fc='white')
    plt.gca().add_artist(centre_circle)
    plt.text(0, 0, f'总计:\n{category_spending.sum():.1f}元',
             ha='center', va='center', fontsize=17, fontweight='bold')
    # 在右侧添加详细图例
    legend_labels = []
    for label, value in zip(category_spending.index, category_spending.values):
        percentage = value / category_spending.sum() * 100
        legend_labels.append(f'{label}\n{value:.1f}元 ({percentage:.1f}%)')
    plt.legend(
        wedges,
        legend_labels,
        loc="center left",
        bbox_to_anchor=(1, 0.5),
        fontsize=14
    )
    plt.title('消费类别分布', fontsize=24, fontweight='bold', pad=20)
    plt.tight_layout(rect=[0, 0, 0.8, 1])
    plt.show()
def daily_spend(szu_data):
    plt.figure(figsize=(14, 8))
    daily_spending = szu_data.groupby('日期')['金额(元)'].sum()
    bins = list(range(0, int(daily_spending.max()) + 6, 5))  # 每5元一个区间
    labels = [f'{bins[i]}-{bins[i + 1]}元' for i in range(len(bins) - 1)]
    daily_spending_binned = pd.cut(daily_spending, bins=bins, labels=labels, right=False)
    frequency_distribution = daily_spending_binned.value_counts().sort_index()
    frequency_distribution = frequency_distribution[frequency_distribution > 0]# 剔除频次为0的点
    frequency_sorted = frequency_distribution.sort_index(key=lambda x: x.str.split('-').str[0].astype(float))# 按区间起始值排序
    plt.scatter(range(len(frequency_sorted)), frequency_sorted.values,
                color='#A23B72', s=100, alpha=0.7, edgecolors='black', linewidths=1)
    plt.plot(range(len(frequency_sorted)), frequency_sorted.values,
             color='#A23B72', linewidth=2, alpha=0.5, linestyle='-', marker='')
    plt.title('每日消费金额分布（5元区间）', fontsize=16, fontweight='bold')
    plt.xlabel('消费金额区间（元）', fontsize=12)
    plt.ylabel('出现频次', fontsize=12)
    plt.grid(True, alpha=0.3)
    # 设置x轴刻度（确保按价格区间从小到大均匀排列）
    plt.xticks(range(len(frequency_sorted)),
               frequency_sorted.index.astype(str),
               rotation=45, fontsize=10)
    # 在散点上添加频次标注
    for i, (price_range, count) in enumerate(frequency_sorted.items()):
        plt.text(i, count + max(frequency_sorted.values) * 0.01, f'{count}次',
                 ha='center', va='bottom', fontsize=10, fontweight='bold',
                 bbox=dict(boxstyle="round,pad=0.3", facecolor='white', alpha=0.8))
    # 添加统计信息框
    stats_text = f"总天数: {len(daily_spending)}天\n有效区间数: {len(frequency_sorted)}个\n金额区间: {bins[0]}-{bins[-1]}元\n最高日消费: {daily_spending.max():.2f}元\n最低日消费: {daily_spending.min():.2f}元\n最频繁区间: {frequency_sorted.idxmax()} ({frequency_sorted.max()}天)\n"
    plt.annotate(stats_text,xy=(0.98, 0.98),xycoords='axes fraction',ha='left',va='top',fontsize=16,bbox=dict(boxstyle="round,pad=0.5", facecolor='lightblue', alpha=0.8))
    plt.tight_layout()
    plt.show()
def hour_spend(szu_data):
    plt.figure(figsize=(16, 8))
    def get_half_hour(hour, minute):
        half_hour = hour * 2
        if minute >= 30:
            half_hour += 1
        return half_hour
    def get_half_hour_label(half_hour):
        hour = half_hour // 2
        minute = 0 if half_hour % 2 == 0 else 30
        return f"{hour:02d}:{minute:02d}"
    # 提取每半小时的交易数据
    half_hour_data = []
    for _, row in szu_data.iterrows():
        hour = row['交易时间'].hour
        minute = row['交易时间'].minute
        half_hour = get_half_hour(hour, minute)
        half_hour_data.append(half_hour)
    # 统计每半小时的交易次数
    half_hour_series = pd.Series(half_hour_data)
    half_hour_count = half_hour_series.value_counts().sort_index()
    # 只保留有交易的时间段
    half_hour_count = half_hour_count[half_hour_count > 0]
    half_hour_labels = [get_half_hour_label(h) for h in half_hour_count.index]
    bars = plt.bar(range(len(half_hour_count)), half_hour_count.values,
                   color='#C73E1D', alpha=0.8, width=0.6)
    plt.title('消费时间分布（半小时分段）', fontsize=16, fontweight='bold')
    plt.xlabel('半小时区间', fontsize=12)
    plt.ylabel('交易次数', fontsize=12)
    plt.grid(True, alpha=0.3, axis='y')
    plt.xticks(range(len(half_hour_labels)), half_hour_labels, rotation=45, fontsize=10)
    for i, (half_hour, count) in enumerate(half_hour_count.items()):
        label = get_half_hour_label(half_hour)
        plt.text(i, count + max(half_hour_count.values) * 0.01, f'{count}次',
                 ha='center', va='bottom', fontsize=10, fontweight='bold',
                 bbox=dict(boxstyle="round,pad=0.2", facecolor='white', alpha=0.8))
    stats_text = f"总交易次数: {len(szu_data)}次\n半小时分段数: {len(half_hour_count)}个\n最活跃时段: {half_hour_count.idxmax()}时 ({half_hour_count.max()}次)\n最不活跃时段: {half_hour_count.idxmin()}时 ({half_hour_count.min()}次)"
    plt.annotate(stats_text,
                 xy=(0.98, 0.98),
                 xycoords='axes fraction',
                 ha='left', va='top', fontsize=16,
                 bbox=dict(boxstyle="round,pad=0.5", facecolor='lightblue', alpha=0.8))
    # 添加高峰时段分析
    peak_hours = half_hour_count.nlargest(3)
    peak_text = "消费高峰时段:\n"
    for i, (half_hour, count) in enumerate(peak_hours.items(), 1):
        label = get_half_hour_label(half_hour)
        peak_text += f"{i}. {label}-{get_half_hour_label(half_hour + 1)}: {count}次\n"
    plt.annotate(peak_text,
                 xy=(0.02, 0.98),
                 xycoords='axes fraction',
                 ha='left', va='top', fontsize=16,
                 bbox=dict(boxstyle="round,pad=0.4", facecolor='lightyellow', alpha=0.8))

    plt.tight_layout()
    plt.show()
def top10_spend(szu_data):
    plt.figure(figsize=(16, 10))
    # 应用预处理，将荔园小巴统一归类，并识别电费、校园卡
    temp_data = szu_data.copy()
    temp_data['处理后的商品'] = temp_data['商品'].apply(preprocess_product_name)
    filtered_data = temp_data[~temp_data['处理后的商品'].isin(['电费', '校园卡','荔园小巴'])]
    # 按处理后的商品统计消费金额和交易次数（使用剔除后的数据）
    location_stats = (filtered_data.groupby('处理后的商品').agg({'金额(元)': 'sum','交易单号': 'count'})
                      .rename(columns={'交易单号': '交易次数'}))
    # 按消费金额排序，取前10
    top10_by_amount = location_stats.nlargest(10, '金额(元)')
    colors = plt.cm.viridis(np.linspace(0.3, 0.9, len(top10_by_amount)))
    bars = plt.barh(range(len(top10_by_amount)),top10_by_amount['金额(元)'].values,color=colors,alpha=0.8,edgecolor='black',linewidth=1)
    plt.title('消费地点TOP10', fontsize=24, fontweight='bold', pad=20)
    plt.xlabel('消费金额（元）', fontsize=15)
    plt.ylabel('消费地点', fontsize=16)
    plt.yticks(range(len(top10_by_amount)), top10_by_amount.index, fontsize=11)
    for i, (location, row) in enumerate(top10_by_amount.iterrows()):
        amount = row['金额(元)']
        count = row['交易次数']
        avg_per_transaction = amount / count
        plt.text(amount + max(top10_by_amount['金额(元)']) * 0.005, i,f'{amount:.1f}元\n({count}次\n平均{avg_per_transaction:.1f}元一次)',ha='left', va='center', fontsize=12, fontweight='bold',bbox=dict(boxstyle="round,pad=0.2", facecolor='white', alpha=0.8))

    top10_by_amount['平均每笔'] = top10_by_amount['金额(元)'] / top10_by_amount['交易次数']
    stats_text = (f"平均单笔消费最高: {top10_by_amount['平均每笔'].idxmax()} ({top10_by_amount.loc[top10_by_amount['平均每笔'].idxmax()]['平均每笔']:.1f}元/次)\n"
                  f"平均单笔消费最低: {top10_by_amount['平均每笔'].idxmin()} ({top10_by_amount.loc[top10_by_amount['平均每笔'].idxmin()]['平均每笔']:.1f}元/次)")
    plt.annotate(stats_text,xy=(0.98, 0.98),xycoords='axes fraction',ha='left', va='top', fontsize=14,bbox=dict(boxstyle="round,pad=0.6", facecolor='lightblue', alpha=0.9))
    plt.grid(True, axis='x', alpha=0.3, linestyle='--')
    plt.tight_layout()
    plt.show()

def create_visualizations(szu_data):
    monthly_spend(szu_data)# 1. 月度消费趋势（折线图）
    category_spend(szu_data)# 2. 消费类别分布（饼图）
    daily_spend(szu_data)# 3. 每日消费金额分布（散点图连线）
    hour_spend(szu_data)# 4. 消费时间分布（半小时柱状图）
    top10_spend(szu_data)# 5. top10 消费地点

def main():
    df = pd.read_excel('微信账单总表.xlsx', sheet_name='Sheet1')
    df_processed = preprocess_data(df)
    szu_data = extract_shenzhen_university_data(df_processed)
    create_visualizations(szu_data)
    szu_data.to_excel('深圳大学消费分析结果.xlsx', index=False)

if __name__ == "__main__":
    main()