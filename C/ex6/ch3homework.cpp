// Created by Abalone on 2025/10/27.

#include <bits/stdc++.h>
using namespace std;

typedef char ElemType;//数据元素类型
struct LNode {
	ElemType data;
	LNode *next;
	LNode(): data(),next(nullptr){}
};
struct QNode {
	ElemType data;
	QNode *next;
};
struct Queue {
	QNode *front;
	QNode *rear;
	Queue(): front(nullptr),rear(nullptr){}
};

void enQueue(Queue *q,ElemType e){
	if (q->rear== nullptr&&q->front== nullptr){

	}
}
void printList(LNode* head){
	while(head->next!= nullptr) {
		cout << head->next->data;
		head = head->next;
	}
	cout<<endl;
}
void quickInsertList(LNode* head,int n){
	while(n--){
		head->next = new LNode();
		cin>>head->next->data;
		head = head->next;
	}
}
/*
 abcdcba
 abccba
 akcdbw
*/

int main() {
	system("chcp 65001");
	LNode str1,str2,str3;
	Queue q1,q2,q3;
	{
	quickInsertList(&str1,7);
	quickInsertList(&str2,6);
	quickInsertList(&str3,6);
	}

	return 0;
}