#include <bits/stdc++.h>
#define STACK_INIT_SIZE 100
#define STACK_INCREMENT 10

typedef char ElemType;
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

bool isLeft(char s){
	if (s=='('||s=='['||s=='{')return true;
	else return false;
}

bool isLegal(char a,char b){
	if (a=='('){
		if (b==')')return true;
		return false;
	}
	if (a=='['){
		if (b==']')return true;
		return false;
	}
	if (a=='{'){
		if (b=='}')return true;
		return false;
	}
	return false;
}

int main(){
	Stack s;
	initStack(s);
	string str;
	cin>>str;
	for (auto &item: str){
		if (isLeft(item))push(s,item);
		else{
			if (isLegal(getTop(s),item)){
				char c;
				pop(s,c);
			}else{
				cout<<"The combination is illegal";
				return 0;
			}
		}
	}
	cout<<"The combination is legal";
	return 0;
}
