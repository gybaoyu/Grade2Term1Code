#include <bits/stdc++.h>

using namespace std;

typedef int TNodeElem;
struct TNode {
	TNodeElem data;
	TNode *l= nullptr, *r= nullptr;
	TNode(TNodeElem data){
		this->data = data;
	}
	TNode(){}
};
//typedef TNode *ElemType;
struct Node {
	TNode *data;
	Node *next;
};
//struct Stack {
//	Node *top;
//	Node *base;
//	Stack() : top(nullptr), base(nullptr) {}
//};
//struct Queue {
//	Node *front;
//	Node *rear;
//	Queue() : front(nullptr), rear(nullptr) {}
//};
//
//void createTree(TNode *&T);
//void preTraverse(TNode *T);
//void inTraverse(TNode *T);
//void postTraverse(TNode *T);
//void inTraverse2(TNode *T);
//void layerTraverse(TNode *T);
//int getDepth(TNode *T);
//int count(TNode *T);
//
////int main() {
////	system("chcp 65001");
////	TNode *tree = nullptr;
////	createTree(tree);
////	cout << "先序遍历: ";
////	preTraverse(tree);
////	cout << endl << "中序遍历: ";
////	inTraverse(tree);
////	cout << endl << "后序遍历: ";
////	postTraverse(tree);
////	cout << endl << "无递归中序遍历: ";
////	inTraverse2(tree);
////	cout << endl << "层次遍历: "<<endl;
////	layerTraverse(tree);
////	cout << "depth: " << getDepth(tree) << endl;
////	cout << "count: " << count(tree) << endl;
////	return 0;
////}
//
//void createTree(TNode *&T) {
//	char c;
//	cin >> c;
//	if (c != '@') {
//		if (T == nullptr)T = new TNode();
//		T->data = c;
//		createTree(T->l);
//		createTree(T->r);
//	}
//}
//
void preTraverse(TNode *T) {
	if (T != nullptr) {
		cout << T->data << " ";
		preTraverse(T->l);
		preTraverse(T->r);
	}
}

void inTraverse(TNode *T) {
	if (T != nullptr) {
		inTraverse(T->l);
		cout << T->data << " ";
		inTraverse(T->r);
	}
}

//void postTraverse(TNode *T) {
//	if (T != nullptr) {
//		postTraverse(T->l);
//		postTraverse(T->r);
//		cout << T->data;
//	}
//}
//
//void push(Stack *s, TNode* e) {
//	Node *node = new Node();
//	node->data = e;
//	if (s->top == nullptr || s->base == nullptr) {
//		s->top = node;
//		s->base = node;
//	} else {
//		Node *tmp = s->top;
//		s->top = node;
//		node->next = tmp;
//	}
//}
//
//TNode* pop(Stack *s) {
//	if (s == nullptr || s->top == nullptr)return nullptr;
//	Node *result = s->top;
//	s->top = s->top->next;
//	if (s->top == nullptr)s->base = nullptr;
//	return result->data;
//}
//
//void inTraverse2(TNode *T) {
//	Stack s;
//	TNode *p = T;
//	while (s.top != s.base || T != nullptr) {
//		if (p) {
//			push(&s, p);
//			p = p->l;
//		} else {
//			p = pop(&s);
//			if (p != nullptr) {
//				cout << p->data << " ";
//				p = p->r;
//			} else break;
//		}
//	}
//}
//
//void enQueue(Queue *q, TNode* e) {
//	Node *node = new Node();
//	node->data = e;
//	if (q->rear == nullptr || q->front == nullptr) {
//		q->front = node;
//		q->rear = node;
//	} else {
//		q->rear->next = node;
//		q->rear = node;
//	}
//}
//
//TNode* deQueue(Queue *q) {
//	if (q == nullptr || q->front == nullptr) return nullptr;
//	Node *result = q->front;
//	q->front = q->front->next;
//	if (q->front == nullptr)q->rear = nullptr;
//	return result->data;
//}
//
//void layerTraverse(TNode *T) {
//	Queue q;
//	enQueue(&q, T);
//	while (q.front != nullptr) {
//		auto p = deQueue(&q);
//		cout << p->data;
//		if (p->l != nullptr)enQueue(&q, p->l);
//		if (p->r != nullptr)enQueue(&q, p->r);
//	}
//}
//
//int getDepth(TNode *T) {
//	int dl, dr;
//	if (!T)return 0;
//	if (T->l)dl = getDepth(T->l);
//	else dl = 0;
//	if (T->r)dr = getDepth(T->r);
//	else dr = 0;
//	return max(dl, dr) + 1;
//}
//
//int count(TNode *T) {
//	if (T == nullptr)return 0;
//	return count(T->l) + count(T->r) + 1;
//}