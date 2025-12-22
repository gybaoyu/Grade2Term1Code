#include <bits/stdc++.h>

using namespace std;
template<typename T>
class TNode{
public:
	T data;
	TNode *l= nullptr, *r= nullptr;
	int height;
	TNode(T data){
		this->data = data;
		height=1;
	}
	TNode(){}
	void preTraverse(TNode *tNode) {
		if (tNode != nullptr) {
			cout << tNode->data << " ";
			preTraverse(tNode->l);
			preTraverse(tNode->r);
		}
	}

	void inTraverse(TNode *tNode) {
		if (tNode != nullptr) {
			inTraverse(tNode->l);
			cout << tNode->data << " ";
			inTraverse(tNode->r);
		}
	}
};