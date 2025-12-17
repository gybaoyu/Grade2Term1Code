//
// Created by Abalone on 2025/12/17.
//


#include <bits/stdc++.h>
#include "ex2.h"
#include "ex6Tree.h"

using namespace std;

bool cmp(ElemType x, ElemType y);
int search(List l, ElemType key);
void initBinarySortTree(TNode *&root, int n);
TNode *binarySearch(TNode *&node, int key);
void deleteNode(TNode *node);

/*
13 6 7 23 53 32 1 8 9 3
 */
int main() {
	//T1 利用实验一建立有序表，采用折半查找实现某一已知的关键字的查找。
	List myList;
	initList(myList);
	autoInsert(myList, 10);
	sortList(myList, cmp);
	search(myList, 5);
	search(myList, 53);
	//T2 随机产生一组关键字，利用二叉排序树的插入算法建立二叉排序树，然后删除某一指定关键字元素。
	TNode *root;
	initBinarySortTree(root, 10);
	inTraverse(root);
	deleteNode(root);
	inTraverse(root);
	return 0;
}

bool cmp(ElemType x, ElemType y) { return x > y; }

int search(List l, ElemType key) {
	int low = 0, high = l.length, mid;
	while (low <= high) {
		mid = (low + high) / 2;
		if (key == l.elem[mid]) {
			cout << "key " << key << " is at " << mid << endl;
			return mid;
		} else if (key > l.elem[mid]) low = mid + 1;
		else high = mid - 1;
	}
	cout << "didn't find key " << key << endl;
	return -1;
}

void addTNode(TNode *&root, TNodeElem data) {
	TNode *node = binarySearch(root, data);
	if (node->data < data) node->r = new TNode(data);
	else if (node->data > data) node->l = new TNode(data);
}

void initBinarySortTree(TNode *&root, int n) {
	srand((unsigned int) time(nullptr));
	root = new TNode(1 + rand() % 1000);
	cout << root->data << endl;
	for (int i = 0; i < n - 1; ++i) addTNode(root, 1 + rand() % 1000);
}

TNode *binarySearch(TNode *&node, int key) {
	if (key == node->data)return node;
	if (key > node->data) {
		if (node->r == nullptr)return node;
		return binarySearch(node->r, key);
	} else {
		if (node->l == nullptr)return node;
		return binarySearch(node->l, key);
	}
}

TNode *deleteTreeNode(TNode *node, TNodeElem data) {
	if (data < node->data) node->l = deleteTreeNode(node->l, data);
	else if (data > node->data) node->r = deleteTreeNode(node->r, data);
	else {
		if (node->l == nullptr) return node->r;
		else if (node->r == nullptr) return node->l;

		TNode *minNode = node->r;
		while (minNode->l != nullptr) {
			minNode = minNode->l;
		}
		node->data = minNode->data;
		node->r = deleteTreeNode(node->r, minNode->data);
	}
	return node;
}

void deleteNode(TNode *node) {
	int data;
	cin >> data;
	deleteTreeNode(node, data);
}