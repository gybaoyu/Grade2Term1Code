#include <bits/stdc++.h>

using namespace std;
/*
 弧：有向的边
 网：边带权
 图：边不带权
*/

#define N 20
#define NodeData char
#define EdgeData int
typedef enum {DG,DN,UDG,UDN} GraphKind;//有向图 有向网 无向图 无向网

NodeData nodes[N];//顶点数据,下标为顶点索引
bool isVisited[N];
struct MGraph{//邻接矩阵
	int nodeNum,edgeNum;
	EdgeData matrix[N][N];
};
struct LGraph{//邻接表
	int nodeNum;
	vector<pair<int,EdgeData>> list[N];//下标表示顶点索引
};

MGraph* initMGraph(int nodeNum,int edgeNum,GraphKind kind);
LGraph* initLGraph(int nodeNum,int edgeNum,GraphKind kind);
void printMGraph(MGraph* g);
void printLGraph(LGraph* g);
void dfs_MGraph(MGraph* g,int v);
void dfs_LGraph(LGraph* g,int v);
void bfs_MGraph(MGraph* g);
void bfs_LGraph(LGraph* g);


int main(){
	for (int i = 1; i <=6; ++i) nodes[i] = 'A'+(i-1);
	MGraph* mG = initMGraph(6,9,UDN);
	LGraph* lG = initLGraph(6,9,UDN);
	printMGraph(mG);
	printLGraph(lG);
	memset(isVisited,0,sizeof isVisited);
	dfs_MGraph(mG,1);
	cout<<endl;
	memset(isVisited,0,sizeof isVisited);
	dfs_LGraph(lG,1);
	cout<<endl;
	bfs_MGraph(mG);
	bfs_LGraph(lG);
	return 0;
}

void bfs_MGraph(MGraph* g){
	bool visited[N];
	queue<int> q;
	visited[1] = true;
	q.push(1);
	while (!q.empty()) {
		int u = q.front();
		q.pop();
		cout << nodes[u];
		for (int v = 1; v <= g->nodeNum; v++) {
			if (g->matrix[u][v] != 0 && !visited[v]) {
				visited[v] = true;
				q.push(v);
			}
		}
	}
	cout<<endl;
}

void bfs_LGraph(LGraph* g){
	bool visited[N];
	queue<int> q;
	visited[1] = true;
	q.push(1);
	while (!q.empty()) {
		int u = q.front();
		q.pop();
		cout << nodes[u];
		for (const auto &item: g->list[u]){
			if (!visited[item.first]){
				visited[item.first] = true;
				q.push(item.first);
			}
		}
	}
	cout<<endl;
}

void dfs_MGraph(MGraph* g, int v) {
	isVisited[v] = true;
	cout<<nodes[v];
	for (int w = 1; w <= g->nodeNum; w++) {
		if (g->matrix[v][w] != 0 && !isVisited[w]) dfs_MGraph(g,w);
	}
}
void dfs_LGraph(LGraph* g,int v){
	isVisited[v] = true;
	cout<<nodes[v];
	for (auto &item: g->list[v]){
		if (!isVisited[item.first]){
			isVisited[item.first] = true;
			dfs_LGraph(g,item.first);
		}
	}
}

void printMGraph(MGraph* g){
	for (int i = 1; i <= g->nodeNum; ++i){
		for (int j = 1; j <= g->nodeNum; ++j){
			if (g->matrix[i][j]!=0)cout<<g->matrix[i][j]<<" ";
			else cout<<"@ ";
		}cout<<endl;
	}
}

void printLGraph(LGraph* g){
	for (int i = 1; i<=g->nodeNum; ++i){
		cout<<i<<" ";
		for (const auto &item: g->list[i]){
			cout<<"(to: "<<item.first<<" value: "<<item.second<<") ";
		}cout<<endl;
	}
}


MGraph* initMGraph(int nodeNum,int edgeNum,GraphKind kind){
	MGraph *g = new MGraph();
	g->nodeNum = nodeNum;
	int from,to,value;
	for (int i = 0; i < edgeNum; ++i){
		cin>>from>>to;
		if (kind == DN||kind == UDN){
			cin>>value;
			g->matrix[from][to] = value;
			if (kind == UDN) g->matrix[to][from] = value;
		}else{
			g->matrix[from][to] = 1;
			if (kind == UDG) g->matrix[to][from] = 1;
		}
	}
	return g;
}

LGraph* initLGraph(int nodeNum,int edgeNum,GraphKind kind){
	LGraph *g = new LGraph();
	g->nodeNum = nodeNum;
	int from,to,value;
	for (int i = 0; i < edgeNum; ++i){
		cin>>from>>to;
		if (kind==DN || kind==UDN){
			cin>>value;
			g->list[from].push_back({to,value});
			if (kind == UDN)g->list[to].push_back({from,value});
		}else{
			g->list[from].push_back({to,1});
			if (kind == UDG)g->list[to].push_back({from,1});
		}
	}
	return g;
}

/*
 in:
 1 4 7
 2 3 5
 3 1 8
 3 6 9
 4 3 6
 4 6 6
 5 4 5
 6 1 3
 6 5 1
 1 4 7
 2 3 5
 3 1 8
 3 6 9
 4 3 6
 4 6 6
 5 4 5
 6 1 3
 6 5 1

 out:
 @ @ 8 7 @ 3
 @ @ 5 @ @ @
 8 5 @ 6 @ 9
 7 @ 6 @ 5 6
 @ @ @ 5 @ 1
 3 @ 9 6 1 @
 1 (to: 4 value: 7) (to: 3 value: 8) (to: 6 value: 3)
 2 (to: 3 value: 5)
 3 (to: 2 value: 5) (to: 1 value: 8) (to: 6 value: 9) (to: 4 value: 6)
 4 (to: 1 value: 7) (to: 3 value: 6) (to: 6 value: 6) (to: 5 value: 5)
 5 (to: 4 value: 5) (to: 6 value: 1)
 6 (to: 3 value: 9) (to: 4 value: 6) (to: 1 value: 3) (to: 5 value: 1)
 ACBDEF
 ADCBFE
 ACDFBE
 ADCFEB
*/