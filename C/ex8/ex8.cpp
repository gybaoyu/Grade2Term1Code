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

void binarySearch(TNode *&node, int key, TNode *&res);

void deleteTreeNode(TNode *&node);

/*
13 6 7 23 53 32 1 8 9 3
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
	initBinarySortTree(root, 2);
	inTraverse(root);
	deleteTreeNode(root);
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
	TNode *node;
	binarySearch(root, data, node);
	if (node->data < data) node->r = new TNode(data);
	else if (node->data > data) node->l = new TNode(data);
}

void initBinarySortTree(TNode *&root, int n) {
	srand((unsigned int) time(nullptr));
	root = new TNode(1 + rand() % 1000);
	cout << root->data << endl;
	for (int i = 0; i < n - 1; ++i) addTNode(root, 1 + rand() % 1000);
}

void binarySearch(TNode *&node, int key, TNode *&res) {
	res = node;
	if (key == node->data)return;
	if (key > node->data){
		if (node->r != nullptr) binarySearch(node->r, key, res);
	}else
		if (node->l != nullptr) binarySearch(node->l, key, res);
}

void deleteTreeNode(TNode *&node) {
	TNodeElem data;
	cin >> data;
	TNode *find;
	binarySearch(node, data, find);
	TNode *q = find;
	if (find->l == nullptr && find->r == nullptr) {
		find = nullptr;
		free(q);
	} else if (find->l == nullptr) {
		find = find->l;
		q = nullptr;
		free(q);
	} else if (find->r == nullptr) {
		find = find->r;
		q = nullptr;
		free(q);
	} else {

	}

	inTraverse(node);
}