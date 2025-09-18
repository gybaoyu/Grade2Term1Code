#include <iostream>
#include <string>
#include <utility>

using namespace std;
#define LIST_INIT_SIZE 10 //线性表存储空间的初始分配量
#define LISTINCREMENT 20 //线性表存储空间的分配增量
#define OK 1
#define ERROR 0
#define YES 1
#define NO 0
#define MALE 1
#define FEMALE 0
typedef int Status;

struct Student {
	int id;
	string name;
	int sex;
};

struct List {
	Student *elem;  //存储空间基址 elem[0]是第一个
	int length;//当前长度  如果只有elem[0]有值，则length=1
	int listsize;//当前分配的存储容量（以sizeof（Student）为单位），顺序表中可存放元素的最大个数
};

Status initList(List &l) {
//	l.elem = (Student *) malloc(LIST_INIT_SIZE * sizeof(Student));
	l.elem = new Student[LIST_INIT_SIZE];
//	if (!l.elem)exit(OVERFLOW);//InitList_Sq//存储分配失败
	l.length = 0;
	l.listsize = LIST_INIT_SIZE;
	return OK;
}

Status insertList(List &l, int pos, Student stu) {
	if (pos < 0 || pos > l.length)return ERROR;
	if (l.length >= l.listsize) {
		auto *newBase = new Student[l.listsize + LISTINCREMENT];
		for (int i = 0; i < l.length; i++) {
			newBase[i] = l.elem[i];
		}
		l.elem = newBase;
		l.listsize += LISTINCREMENT;
	}
	auto insertPos = &(l.elem[pos]);
	for (Student *p = &(l.elem[l.length]); p > insertPos; --p) {
		*p = *(p - 1);
	}
	l.elem[pos] = stu;
	l.length++;
	return OK;
}

Status deleteList(List &l, int i, Student &e) {
	if (i < 0 || i >= l.length)return ERROR;
	Student *p = &(l.elem[i]);
	e = *p;//记录被删除的元素的值
	Student *lastElem = l.elem + l.length;
	for (p++; p <= lastElem; p++) {
		*(p - 1) = *p;
	}
	--l.length;
	return OK;
}

void printList(List &list) {
	for (int i = 0; i < list.length; ++i) {
		Student stu = list.elem[i];
		cout << "id=" << stu.id << " name=" << stu.name << " sex=";
		if (stu.sex == MALE)cout << "MALE" << endl;
		else cout << "FEMALE" << endl;
	}
	cout << endl;
}

Status isEqual(int A, int B) {
	if (A == B)return YES;
	else return NO;
}

Student locateList(List l, int id, Status(*compare)(int, int)) {
	for (int i = 0; i < l.length; ++i) {
		if (compare(l.elem[i].id, id) == YES) {
			return l.elem[i];
		}
	}
	Student stuNull = {-1, "null", 0};
	return stuNull;//由于我这里从下标0开始储存数组，所以没查到时不返回0
}

bool compareById(Student A, Student B) {
	if (A.id >= B.id)return true;
	else return false;
}

bool compareBySex(Student A, Student B) {
	if (A.sex >= B.sex)return true;
	else return false;
}

void autoInsert(List &myList, int n) {
	Student stu{};
	string sex;
	for (int i = 0; i < n; ++i) {
		cin >> stu.id >> stu.name >> sex;
		if (sex == "MALE")stu.sex = MALE;
		else stu.sex = FEMALE;
		insertList(myList, i, stu);
	}
}

void sortList(List &l, bool(*compare)(Student, Student)) {
	bool swapped;
	for (int i = 0; i < l.length - 1; i++) {
		swapped = false;
		for (int j = 0; j < l.length - i - 1; j++) {
			if (compare(l.elem[j], l.elem[j + 1])) {
				swap(l.elem[j], l.elem[j + 1]);
				swapped = true;
			}
		}
		if (!swapped) {
			break;
		}
	}
}

int main() {
	system("chcp 65001 > nul"); // 将控制台代码页设置为 UTF-8
	List stuList{};
	initList(stuList);
	int n;
	cin >> n;
	cout << "数据录入" << endl;
	autoInsert(stuList, n);
	printList(stuList);

	cout << "插入" << endl;
	Student stu = {2024382021, "xxxstu", MALE};
	insertList(stuList, 3, stu);
	printList(stuList);

	cout << "删除" << endl;
	deleteList(stuList, 4, stu);
	printList(stuList);

	//这里先按性别排序再按学号排序
	cout << "排序" << endl;
	sortList(stuList, compareById);
	sortList(stuList, compareBySex);
	printList(stuList);

	//查找
	stu = locateList(stuList, 2024382002, isEqual);
	if (stu.id != -1) {
		cout << "find: id=" << stu.id << " name=" << stu.name << " sex=";
		if (stu.sex == MALE)cout << "MALE" << endl;
		else cout << "FEMALE" << endl;
	}

	return 0;
}
/*
 测试用例：（网上随机生成的）
 10
 2024382001 贾松玺 MALE
 2024382003 班晋 MALE
 2024382005 夏琼琼 MALE
 2024382007 唐勉勉 MALE
 2024382009 越桦孝 MALE
 2024382002 薛泉泉 FEMALE
 2024382004 宫霏茵 FEMALE
 2024382006 王纯纯 FEMALE
 2024382008 俞雪雪 FEMALE
 2024382010 家鹃杏 FEMALE
 */