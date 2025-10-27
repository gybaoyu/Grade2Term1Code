#include <bits/stdc++.h>
typedef int ElemType;
struct QNode {
    ElemType data;
    QNode *next;
};
struct  LinkQueue{
    QNode *front;
    QNode *rear;
};

void InitQueue(LinkQueue *q) {
    q->front = q->rear = NULL;
}

void EnQueue(LinkQueue *q, ElemType e) {
    QNode *newNode = (QNode *)malloc(sizeof(QNode));
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

int main() {
    LinkQueue q;
    InitQueue(&q);

    EnQueue(&q, 1);
    EnQueue(&q, 2);
    EnQueue(&q, 3);

    printf("Dequeued: %d\n", DeQueue(&q));
    printf("Dequeued: %d\n", DeQueue(&q));
    printf("Dequeued: %d\n", DeQueue(&q));

    return 0;
}