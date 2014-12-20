#include <iostream>
#include <vector>
#include <queue>
#include <stack>
using namespace std;

typedef vector<int> vi;
typedef vector< vector<int> > vvi;

struct TreeNode {
    int val;
    TreeNode* left;
    TreeNode* right;
    TreeNode(int x) : val(x), left(NULL), right(NULL) {}
};

struct intNode {
    int dep;
    TreeNode* ptr;
};

void preOrder(TreeNode* root);
TreeNode* buildTree(int a[], int len);
void printVVI(vvi tmp);
void printVI(vi tmp);
void levelOrderPrint(TreeNode* root);

TreeNode* preinbuildTree(vector<int> &preorder, vector<int> &inorder) {
    int size = preorder.size();
    if (!size) return NULL;
    
    stack<TreeNode*> s;
    stack<int> si;

    int cnt = 0, icnt = 0, f = 0;
    TreeNode* root = new TreeNode(preorder[cnt]);
    si.push(preorder[cnt]);
    s.push(root);
    TreeNode* curr = root;
    cnt++;

    while (cnt < size) {
        if (!s.empty() && (s.top())->val == inorder[icnt]) {
            curr = s.top();
            s.pop();
            si.pop();
            f = 1; // right node insertion mark
            ++icnt;
        } else {
            if (f == 0) {
                si.push(preorder[cnt]);
                curr->left = new TreeNode(preorder[cnt]);
                curr = curr->left;
                s.push(curr);
                cnt++;
            } else {
                f = 0; // not right any more
                si.push(preorder[cnt]);
                curr->right = new TreeNode(preorder[cnt]);
                curr = curr->right;
                s.push(curr);
                cnt++;
            }
        }
    }
    return root;
}

int main() {
    const int size = 4;
    int a[size] = {2, 1, 3, 4};
    int b[size] = {1, 2, 3, 4};
    vi va, vb;
    for (int i = 0; i < size; ++i) {    
        va.push_back(a[i]);
        vb.push_back(b[i]);
    }
    
    TreeNode* root;
    root = preinbuildTree(va, vb);
    levelOrderPrint(root);
    /*    
          TreeNode* tmp;
          TreeNode* curr;
          int cnt = 0;
          root = buildTree(a, size);
          cout << endl;
          levelOrderPrint(root);
          cout << endl;

          vector<vector<int> > vvi = levelOrder(root);
          printVVI(vvi);
    */
    return 0;
}

TreeNode* buildTree(int a[], int len) {
    TreeNode* root;
    TreeNode* curr;
    queue<TreeNode*> q;
    int idx = 0;
    if (idx == 0) {        
        root = new TreeNode(a[idx++]);
        curr = root;
        q.push(curr);
    }
    while(!q.empty() && idx < len) {
        curr = q.front();
        if (idx % 2 == 1) {            
            if (a[idx] != -1) {                
                curr->left = new TreeNode(a[idx]);
                q.push(curr->left);
            } else 
                curr->left = NULL;
            idx++;
        }
        if (idx % 2 == 0) {        
            if (a[idx] != -1) {                
                curr->right = new TreeNode(a[idx]);
                q.push(curr->right);
            } else 
                curr->right = NULL;
            idx++;
        }
        q.pop();
    }
    return root;
}
 
void levelOrderPrint(TreeNode* root) {
    queue<TreeNode*> q;
    if (!root) return;
    TreeNode* curr;
    
    q.push(root);
    while ( !q.empty() ) {    
        curr = q.front();
        if (curr) {
            if (curr->left && curr->right) {        
                q.push(curr->left);
                q.push(curr->right);
            } else if (curr->left && !curr->right) {
                q.push(curr->left);
                q.push(NULL);
            } else if (!curr->left && curr->right) {
                q.push(NULL);
                q.push(curr->right);
            } 
            cout << curr->val << ", ";                
        } else 
            cout << "#, ";
        q.pop();
    }
}

void preOrder(TreeNode* root) {
    if (!root) return;
    cout << root->val << ", ";
    preOrder(root->left);
    preOrder(root->right);
}

void printVI(vi tmp) {
    for (int i = 0; i < tmp.size(); ++i)
        cout << tmp[i] << ", ";
    cout << endl;
}

void printVVI(vvi tmp) {
    cout << endl << "vvi print: " << endl;
    for (int i = 0; i < tmp.size(); ++i)
        printVI(tmp[i]);
    cout << endl;
}

