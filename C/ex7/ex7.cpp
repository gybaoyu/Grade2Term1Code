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
	NType adj;//权值类型
	ElemType *elem;//该节点具体信息
};

struct Graph{
	Node adjM [MAX_N][MAX_N];//邻接矩阵
	int vexN,arcN;//顶点数和弧数
	GraphKind kind;
};

int main() {

	return 0;
}