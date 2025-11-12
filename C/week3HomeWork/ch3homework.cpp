// Created by Abalone on 2025/10/27.

#include <bits/stdc++.h>

using namespace std;

typedef char ElemType;//数据元素类型
struct Node {
	ElemType data;
	Node *next;
	Node() : data(), next(nullptr) {}
	Node(ElemType x) : data(x), next(nullptr) {}
};

struct Queue {
	Node *front;
	Node *rear;
	Queue() : front(nullptr), rear(nullptr) {}
};

struct Stack {
	Node *top;
	Node *base;
	Stack() : top(nullptr), base(nullptr) {}
};

void enQueue(Queue *q, ElemType e) {
	Node *node = new Node(e);
	if (q->rear == nullptr || q->front == nullptr) {
		q->front = node;
		q->rear = node;
	} else {
		q->rear->next = node;
		q->rear = node;
	}
}

Node *deQueue(Queue *q) {
	if (q== nullptr || q->front == nullptr) return nullptr;
	Node *result = q->front;
	q->front = q->front->next;
	if (q->front== nullptr)q->rear= nullptr;
	return result;
}

void push(Stack *s, ElemType e) {
	Node *node = new Node(e);
	if (s->top == nullptr || s->base == nullptr) {
		s->top = node;
		s->base = node;
	} else {
		Node *tmp = s->top;
		s->top = node;
		node->next = tmp;
	}
}

Node *pop(Stack *s) {
	if (s == nullptr||s->top== nullptr)return nullptr;
	Node *result = s->top;
	s->top = s->top->next;
	if (s->top== nullptr)s->base= nullptr;
	return result;
}

void printList(Node *head) {
	while (head->next != nullptr) {
		cout << head->next->data;
		head = head->next;
	}
}

void quickInsertList(Node *head, Stack *stack, Queue *queue) {
	int n;
	cin>>n;
	while (n--) {
		char c;
		cin >> c;
		head->next = new Node();
		head->next->data = c;
		head = head->next;
		push(stack, c);
		enQueue(queue, c);
	}
}

void solve(Node *head, Stack *stack, Queue *queue) {
	while (true) {
		Node *s = pop(stack);
		Node *q = deQueue(queue);
		if (s == nullptr || q == nullptr) break;
		if (s->data != q->data){
			printList(head);
			cout<<"不中心对称"<<endl;
			return;
		}
	}
	printList(head);
	cout<<"中心对称"<<endl;
}

/*
 7 abcdcba
 6 abccba
 6 akcdbw
*/

int main() {
	system("chcp 65001");
	Node str1, str2, str3;//三个链表的头结点
	Queue q1, q2, q3;
	Stack s1, s2, s3;
	quickInsertList(&str1, &s1, &q1);
	quickInsertList(&str2, &s2, &q2);
	quickInsertList(&str3, &s3, &q3);
	solve(&str1,&s1,&q1);
	solve(&str2,&s2,&q2);
	solve(&str3,&s3,&q3);
	return 0;
}