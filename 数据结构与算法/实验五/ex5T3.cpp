#include <bits/stdc++.h>
using namespace std;
typedef int ElemType;
struct QNode {
	ElemType data;
	QNode *next;
};
struct LinkQueue {
	QNode *front;  // 队头指针
	QNode *rear;   // 队尾指针
};

// 初始化队列
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

	if (q->rear == NULL) {  // 队列为空
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
	if (q->front == NULL) {  // 如果队列只有一个元素
		q->rear = NULL;
	}
	free(temp);
	return e;
}

void solve(int n) {
	LinkQueue q;
	InitQueue(&q);
	EnQueue(&q, 1);
	for (int i = 1; i <=n-1; ++i) cout<<" ";
	cout<<1<<endl;
	for (int i = 2; i <= n; i++) {
		for (int j = 0; j < n-i; ++j) cout<<" ";
		int prev = 0;
		int curr;
		for (int j = 1; j <= i; j++) {
			if (j == 1 || j == i) {
				curr = 1;
			} else {
				int top_right = q.front->data;
				curr = prev + top_right;
			}
			cout << curr << " ";
			EnQueue(&q, curr);
			if (j < i) prev = DeQueue(&q);
		}
		cout<<endl;
	}
}

int main() {
	solve(10);
	return 0;
}