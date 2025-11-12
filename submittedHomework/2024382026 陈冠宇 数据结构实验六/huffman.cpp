#include <bits/stdc++.h>

using namespace std;
struct HTNode {
	double weight;
	HTNode *l=nullptr, *r= nullptr;
	bool isIn = false,isLeaf = false;
	string code = "";
	HTNode() {}
	HTNode(double w,bool isLeaf) {
		weight = w;
		this->isLeaf = isLeaf;
	}
	HTNode(double w,HTNode *l,HTNode *r,bool isIn){
		weight = w;
		this->l = l;
		this->r = r;
		this->isIn = isIn;
	}
};

const int n = 8;
double weights[n] = {0.05, 0.29, 0.07, 0.08, 0.14, 0.23, 0.03, 0.11};
vector<HTNode*> nodes;

HTNode* solve();
void preTraverse(HTNode *T);
void setCode(HTNode* T, const string& parentCode);

int main() {
	for (int i = 0; i < n; ++i) nodes.push_back(new HTNode(weights[i], true));
	HTNode* root = solve();
	setCode(root,"");
	preTraverse(root);
}

HTNode* solve(){
	while(true){
		sort(nodes.begin(), nodes.end(),[](HTNode* n1,HTNode* n2){
			if (n1->isIn==n2->isIn) return n1->weight<n2->weight;
			else return n1->isIn<n2->isIn;
		});
		if (nodes[1]->isIn)break;
		nodes[0]->isIn = true,nodes[1]->isIn = true;
		nodes.push_back(new HTNode(nodes[0]->weight+nodes[1]->weight,nodes[0],nodes[1], false));
	}
	return nodes[0];
}

void preTraverse(HTNode *T) {
	if (T != nullptr) {
		if (T->isLeaf) cout <<"weight: "<<T->weight<<" code: "<<T->code<<endl;
		preTraverse(T->l);
		preTraverse(T->r);
	}
}

void setCode(HTNode* T, const string& parentCode) {
	T->code = parentCode;
	if (T->l != nullptr) setCode(T->l, T->code + "0");
	if (T->r != nullptr) setCode(T->r, T->code + "1");
}
