#include <bits/stdc++.h>
typedef int ElemType;
#define MAX_SIZE 100  // 队列的最大容量

using namespace std;

typedef int ElemType;
struct SqQueue{
	ElemType data[MAX_SIZE];
	int front;  // 队头指针
	int rear;   // 队尾指针
};
void InitQueue(SqQueue *q) {
	q->front = q->rear = 0;
}
int IsEmpty(SqQueue *q) {
	return q->front == q->rear;
}

int IsFull(SqQueue *q) {
	return (q->rear + 1) % MAX_SIZE == q->front;
}

void EnQueue(SqQueue *q, ElemType e) {
	if (IsFull(q)) {
		printf("Queue is full.\n");
		return;
	}
	q->data[q->rear] = e;
	q->rear = (q->rear + 1) % MAX_SIZE;
}

ElemType DeQueue(SqQueue *q) {
	if (IsEmpty(q)) {
		printf("Queue is empty.\n");
		exit(1);
	}
	ElemType e = q->data[q->front];
	q->front = (q->front + 1) % MAX_SIZE;
	return e;
}

void SeeDoctor() {
	SqQueue Q;
	InitQueue(&Q);
	bool flag=true;
	while (flag) {
		char ch;
		int n;
		cout << "请输入命令:" ; cin>>ch;
		switch (ch) {
			case 'A' : cout << "病历号:" ; cin>>n; cout<<endl;
				EnQueue(&Q, n);
				break;
			case 'N' :
				if (!IsEmpty(&Q)) {
					n = DeQueue(&Q);
					cout << "病历号为" << n << "的病人就诊" << endl; }
				else cout << "无病人等候就诊" << endl;
				break;
			case 'Q' : cout << "下列排队的病人依次就诊:" ;
				while (!IsEmpty(&Q)) {
					n = DeQueue(&Q); cout << n << " " ;
				}
				cout << endl << "今天不再接收病人排队" << endl;
				flag= false;
				break;
			default : cout << "输入的命令不合法!" << endl;
		}
	}
}

int main() {
	SeeDoctor();
	return 0;
}