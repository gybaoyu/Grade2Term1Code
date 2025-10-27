#include <bits/stdc++.h>

using namespace std;

int kmp(string &str0,string &str1){
	string str = str0+'#'+str1;
	vector<int> pi(str.size());
	for (int i = 1; i < str.size(); ++i) {
		int len = pi[i-1];
		while(len!=0&&str[i]!=str[len]){
			len = pi[len-1];
		}
		if (str[i] == str[len]){
			pi[i] = len + 1;
		}
		if (pi[i]==str0.size()){
			return i-str0.size()*2;
		}
	}
	return -1;
}

int main() {
	string s1 = "ATAATA";
	string s2 = "AGCATAATAATTAA";
	cout<<kmp(s1,s2);
	return 0;
}