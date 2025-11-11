#include <bits/stdc++.h>

using namespace std;
#define INF 535353
struct HTNode {
	double weight;
	int parent, l, r;
};

void select(HTNode* HTree,int m,int &s1,int &s2);
void huffmanCoding(HTNode *&HTree, char **&HC, double *w, int n);

int main() {
	double weights[8] = {0.05, 0.29, 0.07, 0.08, 0.14, 0.23, 0.03, 0.11};
	HTNode* HTree;
	char **HC;
	huffmanCoding(HTree,HC,weights,8);
	for (int i = 1; i <= 8; ++i) {
		cout << "Weight: " << weights[i-1] << " -> Huffman Code: " << HC[i] << endl;
	}
	return 0;
}

void select(HTNode *HTree, int m, int &s1, int &s2) {
	double min1 = INF, min2 = INF;
	for (int i = 1; i <= m; ++i) {
		if (HTree[i].parent == 0) {
			if (HTree[i].weight < min1) {
				min2 = min1,s2 = s1;
				min1 = HTree[i].weight,s1 = i;
			} else if (HTree[i].weight < min2) min2 = HTree[i].weight,s2 = i;
		}
	}
}

void huffmanCoding(HTNode *&HTree, char **&HC, double *w, int n) {
	if (n <= 1)return;
	int m = 2 * n - 1, s1, s2;
	HTree = (HTNode *) malloc((m + 1) * sizeof(HTNode));
	HTNode *p = HTree;
	for (int i = 1; i <= n; ++i, ++p, ++w) *p = {*w, 0, 0, 0};
	for (int i = n + 1; i <= m; ++i, ++p) *p = {0, 0, 0, 0};
	for (int i = n + 1; i <= m; ++i) {
		select(HTree, i - 1, s1, s2);
		HTree[s1].parent = i, HTree[s2].parent = i;
		HTree[i].l = s1, HTree[i].r = s2;
		HTree[i].weight = HTree[s1].weight + HTree[s2].weight;
	}
	HC = (char **) malloc((n + 1) * sizeof(char *));
	auto cd = (char *) malloc(n * sizeof(char));
	cd[n - 1] = '\0';
	for (int i = 1; i <= n; ++i) {
		int start = n - 1;
		for (int c = i, f = HTree[i].parent; f != 0; c = f, f = HTree[f].parent) {
			if (HTree[f].l==c)cd[--start] = '0';
			else cd[--start] = '1';
		}
		HC[i] = (char *) malloc((n-start)*sizeof(char));
		strcpy(HC[i],&cd[start]);
	}
	free(cd);
}