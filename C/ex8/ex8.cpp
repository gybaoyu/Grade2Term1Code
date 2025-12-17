//
// Created by Abalone on 2025/12/17.
//


#include <bits/stdc++.h>
#include "ex2.h"

using namespace std;

bool cmp(ElemType x,ElemType y);
int search(List l,ElemType key);

/*
13 6 7 23 53 32 1 8 9 3
 */
int main() {
	//利用实验一建立有序表，采用折半查找实现某一已知的关键字的查找。
	List myList;
	initList(myList);
	autoInsert(myList,10);
	sortList(myList,cmp);
	search(myList,5);
	search(myList,53);

	return 0;
}

bool cmp(ElemType x,ElemType y){return x>y;}
int search(List l,ElemType key){
	int low = 0,high = l.length,mid;
	while(low<=high){
		mid = (low+high)/2;
		if (key==l.elem[mid]) {
			cout<<"key "<<key<<" is at "<<mid<<endl;
			return mid;
		}
		else if (key>l.elem[mid]) low = mid+1;
		else high = mid-1;
	}
	cout<<"didn't find key "<<key<<endl;
	return -1;
}
