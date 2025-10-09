#include <bits/stdc++.h>
#define STACK_INIT_SIZE 100
#define STACK_INCREMENT 10
using namespace std;
struct Stack{
	int *base;
	int *top;
	int stackSize;
};

void initStack(Stack &s){
	s.base = (int*) malloc(STACK_INIT_SIZE*sizeof (int));
	if (!s.base)exit(OVERFLOW);
	s.top = s.base;
	s. stackSize = STACK_INIT_SIZE;
}

int getTop(Stack s){
	if (s.top==s.base)return -1;
	return *(s.top-1);
}

void push(Stack &s,int e){
	if (s.top-s.base>=s.stackSize){
		s.base = (int*) realloc(s.base,(s.stackSize+STACK_INCREMENT)*sizeof (int));
		if (!s.base)exit(OVERFLOW);
		s.top = s.base +s.stackSize;
		s.stackSize += STACK_INCREMENT;
	}
	*s.top = e;
	s.top++;
}

void pop(Stack &s,int &e){
	if (s.top == s.base)return;
	--s.top;
	e = *s.top;
}

bool StackEmpty(Stack &s){
	if (s.top==s.base)return true;
	return false;
}

/*十进制转T进制*/
void conversion(int T){
	Stack S;
	initStack(S);
	int N;
	cin>>N;
	while(N){
		push(S,N%T);
		N = N/T;
	}
	int e;
	while (!StackEmpty(S)){
		pop(S,e);
		cout<<e;
	}
	cout<<endl;
}

int main(){
	conversion(2);//十进制转二进制
	conversion(8);//十进制转八进制
}