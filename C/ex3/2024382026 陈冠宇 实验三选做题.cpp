#include <bits/stdc++.h>

using namespace std;

struct TermSet {
	double x;//系数
	int e;//指数
};

struct Polynomial {
	TermSet data;
	Polynomial *next;
};

void addNode(Polynomial *&n, TermSet value) {
	Polynomial *newNode = (Polynomial *) malloc(sizeof(Polynomial));
	newNode->data = value;
	Polynomial *tmp = n->next;
	n->next = newNode;
	newNode->next = tmp;
}

void initHeadNode(Polynomial *&list) {
	list = (Polynomial *) malloc(sizeof(Polynomial)); // 创建头结点
	if (list == NULL) exit(-1);//内存分配失败
	list->data.x = 0;
	list->data.e = 0;
	list->next = NULL;
}

void createNodeList(Polynomial *&headNode) {
	int n;
	TermSet value;
	cin >> n;
	Polynomial *node = headNode;
	for (int i = 0; i < n; ++i) {
		cin >> value.x >> value.e;
		addNode(node, value);
		node = node->next;
	}
}

void printList(Polynomial *list) {
	Polynomial *n = list;
	bool isFirst = true;
	while (true) {
		n = n->next;
		if (!isFirst) {
			if (n->data.x > 0)cout << "+";
			else if (n->data.x < 0)cout << "-";
		}
		cout << n->data.x << "x^" << n->data.e;
		isFirst = false;
		if (n->next == NULL) {
			cout << endl;
			break;
		}
	}
	cout << endl;
}

Polynomial *plusPolynomial(Polynomial *p1, Polynomial *p2) {
	Polynomial *head;
	if (p1->next->data.e > p2->next->data.e)swap(p1, p2);
	head = p1;
	p1 = p1->next;
	p2 = p2->next;
	while (true) {
		if (p2==NULL)break;
		if(p1->next==NULL){
			if (p1->data.e==p2->data.e) {
				p1->data.x += p2->data.x;
				p2 = p2->next;
			}
			p1->next=p2;
			break;
		}
		if(p1->next->data.e <= p2->data.e) {
			p1 = p1->next;
			continue;
		}
		if (p1->data.e == p2->data.e){
			p1->data.x+=p2->data.x;
		}else{
			addNode(p1,p2->data);
		}
		p2=p2->next;
//		cout<<"p1: "<<p1->data.x<<p1->data.e<<" ";
//		cout<<"p2: "<<p2->data.x<<p2->data.e<<" ";
//		printList(head);
	}
	return head;
}
/**
5 1 3 2 4 3 6 4 7 5.1 8
5 2 3 3 4 4 5 5 8 8 10
 */
int main() {
	Polynomial *p1,*p2;
	initHeadNode(p1);
	initHeadNode(p2);
	createNodeList(p1);
	createNodeList(p2);
	printList(p1);
	printList(p2);

	printList(plusPolynomial(p1,p2));
	return 0;
}