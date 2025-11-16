#include <stdio.h>
#include <malloc.h>
#include <process.h>
#define TRUE 1
#define FALSE 0
#define OK 1
#define ERROR 0
#define OVERFLOW -2
#define STACK_INIT_SIZE 100
#define STACKINCREMENT 20
#define MAXQSIZE 100
typedef int Status;
typedef char TElemType;
typedef char** HuffmanCode;
typedef struct {
	unsigned int weight;
	unsigned int parent, lchild, rchild;
}HTNode, * HuffmanTree;
void HuffmanCoding(HuffmanTree& HT, HuffmanCode& HC, unsigned int* w, int n);
void Select(HuffmanTree HT, int  n, int s1, int s2);
void strcpy(char* A, char* B);

int main(void) {
	HuffmanTree T;
	HuffmanCode C;
	unsigned int w[] = {5, 29, 7, 8, 14, 23, 3, 11};
	int n = 8;
	HuffmanCoding(T, C, w, n);
	for (int i = 1; i <= n; i++) {
		printf("字符%d的哈夫曼编码: %s\n", i, C[i]);
	}
	for (int i = 1; i <= n; i++) {
		free(C[i]);
	}
	free(C);
	free(T);
}

void HuffmanCoding(HuffmanTree& HT, HuffmanCode& HC, unsigned int* w, int n) {
	if (n <= 1)  return;
	int m = 2 * n - 1;
	HT = (HuffmanTree)malloc((m + 1) * sizeof(HTNode));
	HuffmanTree p0 = HT + 1;
	for (int i = 1; i <= n;++i,++p0,++w)
	*p0 = {*w,0,0,0 };
	HuffmanTree p;
	for (int i; i <= m; ++i, ++p)
	*p = { 0,0,0,0 };
	for (int i = n + 1; i <= m; ++i) {
		int s1, s2;
		Select(HT, i - 1,s1,s2);
		HT[s1].parent = i;
		HT[s2].parent = i;
		HT[i].lchild = s1;
		HT[i].rchild = s2;
		HT[i].weight = HT[s1].weight + HT[s2].weight;
	}
	HC = (HuffmanCode)malloc((n + 1) * sizeof(char*));
	char* cd = (char*)malloc(n * sizeof(char));
	cd[n - 1] = '\0';
	for (int i = 1; i <= n; i++) {
		int start = n - 1;
		for (int c = i, f = HT[i].parent; f != 0; c = f, f = HT[f].parent)
		if (HT[f].lchild == c)
			cd[--start] = '0';
		else
			cd[--start] = '1';
		HC[i] = (char*)malloc((n - start) * sizeof(char)) ;
		strcpy(HC[i], &cd[start]);
	}
	free(cd);
}

void Select(HuffmanTree HT,int  n, int s1, int s2) {
	s1 = 1;
	s2 = 2;
	for (int i = 2; i < n; i++) {
		if (HT[i].parent = 0 and HT[i].weight < s1 and HT[i].weight < s2)
			s1, s2 = i, s1;
	}
}

void strcpy(char* A, char* B) {
	int i = 0;
	while (B[i] != '\0') {
		A[i] = B[i];
		i++;
	}
	A[i] = '\0';
}