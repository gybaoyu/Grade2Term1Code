
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
	for (int i = 0; i < n; ++i) {
		cin >> value;
		addNode(node, value);
		node = node->next;
	}
}

void printList(LNode *list) {
	LNode *n = list;
//	cout << "head node: " << n->data << endl << "elements:";
	while (n->next != NULL) {
		n = n->next;
		cout << n->data << " ";
	}
	cout << endl;
}

void insertNode(LNode *&headNode, int pos, ElemType value) {//在第pos个元素（头节点为第0个元素，pos若大于链表长度则在末尾添加元素）后增加一个值为value的节点
	LNode *n = headNode;
	while (true) {
		if (pos == 0 || n->next == NULL) {
			addNode(n, value);
			return;
		}
		n = n->next;
		pos--;
	}
}

void deleteNode(LNode *&headNode, int pos) {//删除第pos个节点，头节点pos=0,pos大于链表长度时删除最后一个节点
	LNode *L = headNode;
	LNode *prev;
	while (L->next != NULL && pos > 1) {
		pos--;
		prev = L;
		L = L->next;
	}
	if (L->next == NULL) {
		prev->next = NULL;
		free(L);
	} else {
		LNode *tmp = L->next;
		L->next = tmp->next;
		free(tmp);
	}
}

LNode *findElem(LNode *&headNode, ElemType value) {
	LNode *l = headNode;
	int pos = 0;
	while (l->next != NULL) {
		l = l->next;
		pos++;
		if (l->data == value) {
			cout << "The value was found at No." << pos << endl;
			return l;
		}
	}
	cout << "didn't find the value" << endl;
	return NULL;
}

// 合并两个有序链表
LNode *merge(LNode *l1, LNode *l2) {
	LNode dummy;
	LNode *tail = &dummy;

	while (l1 != NULL && l2 != NULL) {
		if (l1->data < l2->data) {
			tail->next = l1;
			l1 = l1->next;
		} else if (l1->data > l2->data) {
			tail->next = l2;
			l2 = l2->next;
		} else {
			tail->next = l1;
			l1 = l1->next;
			l2 = l2->next;
		}
		tail = tail->next;
	}
	tail->next = (l1 != NULL) ? l1 : l2;
	return dummy.next;
}

LNode *findMid(LNode *head) {
	if (head == NULL) return head;
	LNode *slow = head;
	LNode *fast = head->next;

	while (fast != NULL && fast->next != NULL) {
		slow = slow->next;
		fast = fast->next->next;
	}
	return slow;
}

// 归并排序
LNode *mergeSort(LNode *head) {
	if (head == NULL || head->next == NULL) {
		return head;
	}

	LNode *mid = findMid(head);
	LNode *rightHead = mid->next;
	mid->next = NULL;

	LNode *leftSorted = mergeSort(head);
	LNode *rightSorted = mergeSort(rightHead);

	return merge(leftSorted, rightSorted);
}

void reverse(LNode *&nodeList) {
	LNode *prev= NULL,*curr = nodeList->next,*next= NULL;
	while(curr!=NULL){
		next = curr->next;
		curr->next = prev;
		prev = curr;
		curr = next;
	}
	nodeList->next = prev;
}

/*
示例输入：
 (分别为list list1 list2的数据)
 3
 1 2 3
 5
 5 2 1 3 4
 6
 3 4 12 32 26 13
 */
int main() {
	LNode *nodeList;
	initHeadNode(nodeList);//T1 nodeList即为一个空单链表的头节点

	createNodeList(nodeList);//T2 键盘输入 第一个数字为加入的元素个数n 后续n个数字为元素的值
	printList(nodeList);//T3 遍历单链表
	cout << endl;

	//T4 插入元素
	insertNode(nodeList, 2, 666);
	printList(nodeList);
	insertNode(nodeList, 3, 333);
	printList(nodeList);
	cout << endl;

	//T5
	deleteNode(nodeList, 2);
	printList(nodeList);
	deleteNode(nodeList, 10);
	printList(nodeList);
	cout << endl;

	//T6
	findElem(nodeList, 666);
	findElem(nodeList, 111);

	//T7
	LNode *nodeList1, *nodeList2, *nodeList3;
	initHeadNode(nodeList1);
	initHeadNode(nodeList2);
	createNodeList(nodeList1);
	createNodeList(nodeList2);
	mergeSort(nodeList1);
	mergeSort(nodeList2);
	printList(nodeList1);
	nodeList3 = merge(nodeList1, nodeList2);
	printList(nodeList3);
	cout << endl;

	//T8
	printList(nodeList3);
	cout<<"reverse: ";
	reverse(nodeList3);
	printList(nodeList3);
	cout<<endl;
	return 0;
}