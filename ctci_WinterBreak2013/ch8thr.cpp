#include <iostream>
#include <vector>
using namespace std;

typedef vector< vector<int> > vvi;
typedef vector<int>  vi;

vvi get_subsets(int a[], int n) {  // O(n*2^n)  
    vvi subsets;
    int max = 1 << n;
    for (int i = 0; i < max; ++i) {
	vi subset;
	int idx = 0;
	int j = i;
	while (j > 0) {
	 // if (j & 1) subset.push_back(a[idx++]); WRONG, idx++, considered only one condition
	    if (j & 1)
		subset.push_back(a[idx]);
	    ++idx;        // attention
	    j >>= 1;
	}
	subsets.push_back(subset);
    }
    return subsets;
}

vvi get_subsets1(int a[], int idx, int n) {  // not understanding, difficult !!!
    vvi subsets;
    if (idx == n) {
	vi subset;
	subsets.push_back(subset);  // empty set
    } else {
	vvi rsubsets = get_subsets1(a, idx+1, n);
	int v = a[idx];
	for (int i = 0; i < rsubsets.size(); ++i) {
	    vi subset = rsubsets.at(i);
	    subsets.push_back(subset);
	    subset.push_back(v);
	    subsets.push_back(subset);
	}
    }
    return subsets;
}

void print_subsets(vvi subsets) {
    cout << "Subsets: " << endl;
    for (int i = 0; i < subsets.size(); ++i) {
	vi subset = subsets.at(i);
	cout << "Subset Vector #" << i << ": ";
	for (int j = 0; j < subset.size(); ++j) 
	 // cout << subset.at(j) << " ";
	    cout << subset[j] << " ";    // both works just fine!!!
	cout << endl;
    }
    cout << "End of Subsets!" << endl;
}

int main() {
    int a[] = {1, 2, 3, 4};
    vvi sub = get_subsets(a, 4);
    vvi sub1 = get_subsets1(a, 0, 4);
    print_subsets(sub);
    print_subsets(sub1);
    return 0;
}
