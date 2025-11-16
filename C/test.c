#include <stdio.h>
#include <malloc.h>
#include <process.h>
#include "string.h"

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
} HTNode, * HuffmanTree;

void HuffmanCoding(HuffmanTree HT, HuffmanCode HC, unsigned int* w, int n);

void Select(HuffmanTree HT, int n, int* s1, int* s2);

int main(void) {
	HuffmanTree T = NULL;
	HuffmanCode C = NULL;
	unsigned int w[] = { 5, 29, 7, 8, 14, 23, 3, 11 };
	int n = 8;
	HuffmanCoding(T, C, w, n);
	return OK;
}

void HuffmanCoding(HuffmanTree HT, HuffmanCode HC, unsigned int* w, int n) {
	if (n <= 1) return;
	int m = 2 * n - 1;
	HT = (HuffmanTree)malloc((m + 1) * sizeof(HTNode));
	int i, c, f;
	for (i = 1; i <= n; ++i, ++w) {
		HT[i].weight = *w;
		HT[i].parent = 0;
		HT[i].lchild = 0;
		HT[i].rchild = 0;
	}
	for (; i <=m; ++i, ++w) {
		HT[i].weight = 0;
		HT[i].parent = 0;
		HT[i].lchild = 0;
		HT[i].rchild = 0;
	}
	int s1, s2;
//	for (i = 1; i <=m; ++i) {
//		printf("aaaHT[%d].weight = %d\n", i, HT[i].weight);
//	}
	for (i = n+1; i <= m; ++i) {
		Select(HT, i-1, &s1, &s2);
		for (int q = 1; q <=m; ++q) {
			printf("aaaHT[%d].weight = %d\n", q, HT[q].weight);
		}
		printf("s1 = %d,s2 = %d\n",s1,s2);
		HT[s1].parent = i;
		HT[s2].parent = i;
		HT[i].lchild = s1;
		HT[i].rchild = s2;
		HT[i].weight = HT[s1].weight + HT[s2].weight;
	}
	HC = (HuffmanCode)malloc((n + 1) * sizeof(char*));
	char* cd = (char*)malloc(n * sizeof(char));
	cd[n - 1] = '\0';
	for (i = 1; i <= n; i++) {
		int start = n - 1;
		for (c = i, f = HT[i].parent; f != 0; c = f, f = HT[f].parent) {
			if (HT[f].lchild == c)
				cd[--start] = '0';
			else
				cd[--start] = '1';
		}
		HC[i] = (char*)malloc((n - start) * sizeof(char));
		strcpy_s(HC[i],sizeof(HC[i]), &cd[start]);
	}
	for (i = 1; i <= n; i++) {
		printf("字符%d的哈夫曼编码: %s\n", i, HC[i]);
	}
	free(cd);
}

void Select(HuffmanTree HT, int n, int* s1, int* s2) {
	int i;
	int max= 1000000;
	int maxS=0;
	for (i = 1; i <= n;i++) {
		if (HT[i].weight < max && HT[i].parent == 0)max = HT[i].weight, maxS = i;
	}
	*s1 = maxS;
	max = 100000;
	for (i = 1; i <= n; i++) {
		if (HT[i].weight < max && HT[i].parent == 0 && i!=*s1)max = HT[i].weight, maxS = i;
	}
	*s2 = maxS;
}
