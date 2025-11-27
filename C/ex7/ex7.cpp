#include <bits/stdc++.h>

using namespace std;
/*
 @ 5 @ 7 @ @
 @ @ 4 @ @ @
 8 @ @ @ @ 9
 @ @ 5 @ @ 6
 @ @ @ 5 @ @
 3 @ @ @ 1 @
*/

#define INF INT_MAX
#define MAX_N 20
#define NType int
#define ElemType int
typedef enum {DG,DN,UDG,UDN} GraphKind;//有向图，有向网，无向图，无向网

struct Node{
	NType adj;//权值
	ElemType elem;//该节点具体信息
	Node(){}
};

struct MGraph{//邻接矩阵
	Node matrix [MAX_N][MAX_N];
	int vexN,arcN;//顶点数和弧数
	GraphKind kind;
};

struct LGraph{//邻接表
	vector<Node>
};

MGraph* MGraphInit(GraphKind type,int vexN,int arcN);
void printMGraph(MGraph* g,int vexN);

int main() {
	MGraph* g = MGraphInit(DG,6,6);
	printMGraph(g,6);
	return 0;
}

MGraph* MGraphInit(GraphKind type,int vexN,int arcN) {
	MGraph *g = new MGraph();
	g->vexN = vexN;
	g->arcN = 0;
	char c;
	for (int i = 1; i <= vexN; ++i) {
		for (int j = 1; j <= vexN; ++j) {
			cin >> c;
			if (c != '@') g->matrix[i][j].elem = c - '0',g->arcN++;
			else g->matrix[i][j].elem = -1;
		}
	}
	return g;
}
void printMGraph(MGraph* g,int vexN){
	for (int i = 1; i <= vexN; ++i) {
		for (int j = 1; j <= vexN; ++j) {
			cout<<g->matrix[i][j].elem<<" ";
		}cout<<endl;
	}
}
/*
化 4+9+1
 法 2
 外 5
 政 1
 材 1+3
 医 20
 管理 4
 海 4
 54

 */