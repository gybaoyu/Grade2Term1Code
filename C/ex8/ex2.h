#include <iostream>
#include <cmath>

using namespace std;
#define LIST_INIT_SIZE 100 //线性表存储空间的初始分配量
#define LISTINCREMENT 10 //线性表存储空间的分配增量
#define OK 1
#define ERROR 0
#define YES 1
#define NO 0

typedef int ElemType; //数据元素类型
typedef int Status; //数据元素类型

struct List {
	ElemType *elem;  //存储空间基址 elem[0]是第一个
	int length;//当前长度  如果只有elem[0]有值，则length=1
	int listsize;//当前分配的存储容量（以sizeof（ElemType）为单位），顺序表中可存放元素的最大个数
};

Status initList(List &l) {
	l.elem = (ElemType *) malloc(LIST_INIT_SIZE * sizeof(ElemType));
	if (!l.elem)exit(OVERFLOW);//InitList_Sq//存储分配失败
	l.length = 0;
	l.listsize = LIST_INIT_SIZE;
	return OK;
}

Status insertList(List &l, int pos, ElemType e) {
	if (pos < 0 || pos > l.length)return ERROR;
	if (l.length >= l.listsize) {
		auto *newBase = (ElemType *) realloc(l.elem, (l.listsize + LISTINCREMENT) * sizeof(ElemType));
		if (!newBase)return OVERFLOW;
		l.elem = newBase;
		l.listsize += LISTINCREMENT;
	}
	ElemType *insertPos = &(l.elem[pos]);
	for (ElemType *p = &(l.elem[l.length]); p > insertPos; --p) {
		*p = *(p - 1);
	}
	*insertPos = e;
	l.length++;
	return OK;
}

Status deleteList(List &l, int i, ElemType &e) {
	if (i < 0 || i >= l.length)return ERROR;
	ElemType *p = &(l.elem[i]);
	e = *p;//记录被删除的元素的值
	ElemType *lastElem = l.elem + l.length;
	for (p++; p <= lastElem; p++) {
		*(p - 1) = *p;
	}
	--l.length;
	return OK;
}

void printList(List &list) {
	for (int i = 0; i < list.length; ++i) {
		cout << "No." << i + 1 << " is " << list.elem[i] << endl;
	}
	cout << endl;
}

Status isEqual(ElemType A, ElemType B) {
	if (A == B)return YES;
	else return NO;
}

int locateListAndPrint(List l, ElemType e, Status(*compare)(ElemType, ElemType)) {
	for (int i = 0; i < l.length; ++i) {
		if (compare(l.elem[i], e) == YES) {
			cout << "The element with value " << e << " was at No." << i + 1 << endl;
			return i;
		}
	}
	cout << "No element with value " << e << " was found" << endl;
	return -1;//由于我这里从下标0开始储存数组，所以没查到时不返回0
}

int locateList(List l, ElemType e, Status(*compare)(ElemType, ElemType)) {
	for (int i = 0; i < l.length; ++i) {
		if (compare(l.elem[i], e) == YES) {
			return i;
		}
	}
	return -1;//由于我这里从下标0开始储存数组，所以没查到时不返回0
}

void unionList(List &LA,List &LB){//将所有在LB中但不在LA中的数据元素插入到LA
	for (int i = 0; i < LB.length; ++i) {
		if (locateList(LA,LB.elem[i],isEqual)==-1){
			insertList(LA,LA.length,LB.elem[i]);
		}
	}
}

void autoInsert(List &myList,int n){
	int elem;
	for (int i = 0; i < n; ++i) {
		cin>>elem;
		insertList(myList, i, elem);
	}
}

void sortList(List &l, bool(*compare)(ElemType, ElemType)) {
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

//int main() {
//	List myList{},myList2{};
//	initList(myList);//实验内容1
//
//	//实验内容2
//	int n;
//	cin>>n;
//	autoInsert(myList,n);
//
//	//实验内容3
//	printList(myList);
//
//	//实验内容4
//	ElemType delElem;
//	cout << "before delete:" << endl;
//	printList(myList);
//	deleteList(myList, 3, delElem);
//	cout << "after delete:" << endl;
//	printList(myList);
//	cout << "the value of deleted element is " << delElem << endl;
//
//	//实验内容5
//	cout<<endl;
//	locateListAndPrint(myList, 66, isEqual);
//	locateListAndPrint(myList, 100, isEqual);
//
//	//实验内容6
//	cin>>n;
//	autoInsert(myList2,n);
//	cout<<"before union: "<<endl<<"list1: "<<endl;
//	printList(myList);
//	cout<<endl<<"list2:"<<endl;
//	printList(myList2);
//
//	cout<<endl<<"after union: "<<endl<<"list1: "<<endl;
//	unionList(myList,myList2);
//	printList(myList);
//	cout<<endl<<"list2: "<<endl;
//	printList(myList2);
//	return 0;
//}
/*
6
25 12 78 34 100 88
4
13 25 79 100
 */