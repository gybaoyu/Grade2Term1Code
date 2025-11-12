#include <bits/stdc++.h>
typedef int ElemType;
#define MAX_SIZE 100  // 队列的最大容量

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

int main() {
    SqQueue q;
    InitQueue(&q);

    EnQueue(&q, 1);
    EnQueue(&q, 2);
    EnQueue(&q, 3);

    printf("Dequeued: %d\n", DeQueue(&q));
    printf("Dequeued: %d\n", DeQueue(&q));
    printf("Dequeued: %d\n", DeQueue(&q));

    return 0;
}