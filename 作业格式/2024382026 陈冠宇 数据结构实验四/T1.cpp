#include <bits/stdc++.h>
#define STACK_INIT_SIZE 100
#define STACK_INCREMENT 10

typedef int ElemType;
using namespace std;
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
	if (s.top==s.base)return -1;
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

int main(){
	Stack s;
	initStack(s);
	push(s,1);
	push(s,2);
	push(s,3);
	cout<<"top: "<<getTop(s)<<endl;
	ElemType popE;
	pop(s,popE);
	cout<<"top: "<<getTop(s)<<endl;
	pop(s,popE);
	cout<<"top: "<<getTop(s)<<endl;
	pop(s,popE);
	cout<<"top: "<<getTop(s)<<endl;
	return 0;
}