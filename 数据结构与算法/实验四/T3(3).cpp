#include <bits/stdc++.h>
#define STACK_INIT_SIZE 100
#define STACK_INCREMENT 10

using namespace std;

struct ElemType{
	int ord;//路径编号
	pair<int,int> pos;//坐标位置
	int faceTo;//1右2下3左4上
};
struct Stack{
	ElemType *base;
	ElemType *top;
	int stackSize;
};

void initStack(Stack &s){
	s.base = (ElemType*) malloc(STACK_INIT_SIZE*sizeof (ElemType));
	if (!s.base)exit(OVERFLOW);
	s.top = s.base;
	s. stackSize = STACK_INIT_SIZE;
}

ElemType getTop(Stack s){
	return *(s.top-1);
}

void push(Stack &s,ElemType e){
	if (s.top-s.base>=s.stackSize){
		s.base = (ElemType*) realloc(s.base,(s.stackSize+STACK_INCREMENT)*sizeof (ElemType));
		if (!s.base)exit(OVERFLOW);
		s.top = s.base +s.stackSize;
		s.stackSize += STACK_INCREMENT;
	}
	*s.top = e;
	s.top++;
}

void pop(Stack &s,ElemType &e){
	if (s.top == s.base)return;
	--s.top;
	e = *s.top;
}

bool StackEmpty(Stack &s){
	if (s.top==s.base)return true;
	return false;
}
Stack s;
int _map[100][100];
int width,length;//width表示最大宽度，即最大列坐标,最小列坐标和最小行坐标为1
int N,x,y;//x行坐标，y列坐标
int sx,sy,fx,fy;
pair<int,int>ans[100];
int mx=0;

bool canPass(ElemType e){
	if (e.pos.first < 1 || e.pos.first > width || e.pos.second < 1 || e.pos.second > length) return false;
	return _map[e.pos.first][e.pos.second]==0;
}

ElemType nextPos(ElemType e,int faceTo){
	ElemType res = e;
	if (faceTo==1)++res.pos.second;
	if (faceTo==2)++res.pos.first;
	if (faceTo==3)--res.pos.second;
	if (faceTo==4)--res.pos.first;
	return res;
}

bool solve(){
	initStack(s);
	ElemType curPos;
	curPos.pos.first = sx;
	curPos.pos.second = sy;
	int curStep = 1;
	curPos.ord = curStep;
	curPos.faceTo = 1; // 初始方向
	push(s, curPos);
	_map[sx][sy] = curStep;

	while (!StackEmpty(s)) {
		ElemType e = getTop(s);
		ans[curStep] = e.pos;
		mx = max(mx,curStep);
		bool foundNext = false;
		// 尝试当前点的下一个方向
		for (int dir = e.faceTo; dir <= 4; ++dir) {
			ElemType next = nextPos(e, dir);
			if (canPass(next)) {
				// 找到可行方向
				_map[next.pos.first][next.pos.second] = curStep + 1; // 标记新点
				ElemType toPush = next;
				toPush.ord = curStep + 1;
				toPush.faceTo = 1; // 新点从方向1开始试
				push(s, toPush);
				curStep++;
				foundNext = true;
				if (next.pos.first == fx && next.pos.second == fy) return true;
				break; // 跳出方向尝试循环
			}
		}
		if (!foundNext) {
			// 当前点所有方向都走不通，回溯
			ElemType toPop;
			pop(s, toPop);
			_map[toPop.pos.first][toPop.pos.second] = -1; // 撤销标记
			curStep = toPop.ord-1; // 回溯步数
		}
	}
	return false;
}

int main(){
	cin>>width>>length>>N>>sx>>sy>>fx>>fy;
	while (N--){
		cin>>x>>y;
		_map[x][y] = -1;//-1表示障碍
	}

	if (solve()){
		cout<<"YES"<<endl;
		for (int i = 1; i <= mx; ++i) cout<<"("<<ans[i].first<<","<<ans[i].second<<")->";
		cout<<"("<<fx<<","<<fy<<")";
	}else cout<<"NO";
	return 0;
}

/*
 样例输入（以书中图片为例子）
 8 8 18 1 1 8 8
 1 3
 1 7
 2 3
 2 7
 3 5
 3 6
 4 2
 4 3
 4 4
 5 4
 6 2
 6 6
 7 2
 7 3
 7 4
 7 6
 7 7
 8 1
 */