#include <bits/stdc++.h>

using namespace std;
typedef int ElemType;//数据元素类型
struct LNode {
    ElemType data;
    LNode *next;
};

void initHeadNode(LNode *&list) {
    list = (LNode *) malloc(sizeof(LNode)); // 创建头结点
    if (list == NULL) exit(-1);//内存分配失败
    list->data = 0;
    list->next = NULL;
}

void addNode(LNode *n, ElemType value) {
    LNode *newNode = (LNode *) malloc(sizeof(LNode));
    newNode->data = value;
    LNode *tmp = n->next;
    n->next = newNode;
    newNode->next = tmp;
}

void createNodeList(LNode *&headNode) {
    int n;
    ElemType value;
    cin >> n;
    LNode *node = headNode;
	if (n>=1){
		cin>>value;
		headNode->data = value;
	}
    for (int i = 1; i < n; ++i) {
        cin >> value;
        addNode(node, value);
        node = node->next;
    }
}

void printList(LNode *list) {
    LNode *n = list;
    //	cout << "head node: " << n->data << endl << "elements:";
    while (n) {
        cout << n->data << " ";
		n = n->next;
	}
    cout << endl;
}

void MoveMaxToEnd(LNode* &L) {
    if (!L || !L->next) {
        cout<<"number of nodes < 2 "<<endl;
        return;
    }
    LNode* maxPrev = NULL,*maxCurr = L,*prev = NULL,*curr = L;
    while (curr->next) {
        if (curr->next->data > maxCurr->data) {
            maxPrev = curr;
            maxCurr = curr->next;
        }
        curr = curr->next;
    }
    if (maxCurr == curr) return;
    if (!maxPrev) L = L->next;
    else maxPrev->next = maxCurr->next;
    curr->next = maxCurr;
    maxCurr->next = NULL;
}
/*
示例输入：
1 2
3 5 3 2
3 1 5 3
3 1 2 4
 */
int main(){
    LNode *nodeList1,*nodeList2,*nodeList3,*nodeList4;
    initHeadNode(nodeList1);
    initHeadNode(nodeList2);
    initHeadNode(nodeList3);
    initHeadNode(nodeList4);
    createNodeList(nodeList1);
    createNodeList(nodeList2);
    createNodeList(nodeList3);
    createNodeList(nodeList4);
    printList(nodeList1);
    printList(nodeList2);
    printList(nodeList3);
    printList(nodeList4);
    MoveMaxToEnd(nodeList1);
    MoveMaxToEnd(nodeList2);
    MoveMaxToEnd(nodeList3);
    MoveMaxToEnd(nodeList4);
    printList(nodeList1);
    printList(nodeList2);
    printList(nodeList3);
    printList(nodeList4);
    return 0;
}