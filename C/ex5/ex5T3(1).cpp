#include <bits/stdc++.h>

using namespace std;
typedef int ElemType;
struct QNode {
	ElemType data;
	QNode *next;
};
struct LinkQueue {
	QNode *front;
	QNode *rear;
};

void InitQueue(LinkQueue *q) {
	q->front = q->rear = NULL;
}

void EnQueue(LinkQueue *q, ElemType e) {
	QNode *newNode = (QNode *) malloc(sizeof(QNode));
	if (!newNode) {
		printf("Memory allocation failed.\n");
		exit(1);
	}
	newNode->data = e;
	newNode->next = NULL;

	if (q->rear == NULL) {
		q->front = q->rear = newNode;
	} else {
		q->rear->next = newNode;
		q->rear = newNode;
	}
}

ElemType DeQueue(LinkQueue *q) {
	if (q->front == NULL) {
		printf("Queue is empty.\n");
		exit(1);
	}
	QNode *temp = q->front;
	ElemType e = temp->data;

	q->front = q->front->next;
	if (q->front == NULL) {
		q->rear = NULL;
	}
	free(temp);
	return e;
}

void solve(int n) {
	LinkQueue Q;
	InitQueue(&Q);
	EnQueue(&Q, 1);
	EnQueue(&Q, 1);
	int s = 0;
	cout<<1;
	for (int i = 1; i < n; i++) {
		cout << endl;
		EnQueue(&Q, 0);
		for (int j = 1; j <= i + 2; j++) {
			int t = DeQueue(&Q);
			EnQueue(&Q, s + t);
			s = t;
			if (j != i + 2)
				cout << s << ' ';
		}
	}
}

int main() {
	solve(10);
	return 0;
}