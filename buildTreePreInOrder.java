import com.TreeNode;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class buildTreePreInOrder {
    public static class Solution {
        /*
        // stackoverflowerror
        private TreeNode buildTreeRecursion(int [] pre, int i, int j, int [] in, int m, int n) {
            TreeNode root = null;
            if (i >= pre.length) return root;
            else if (m >= in.length) return root;
            else if (pre == null || pre.length == 0) return root;
            else if (pre.length == 1 && in.length == 1) {
                root =  new TreeNode(pre[0]);
                return root;
            } else if (pre.length == 2 && in.length == 2) {
                root =  new TreeNode(pre[0]);
                if (pre[0] == in[0])
                    root.right = new TreeNode(pre[1]);
                else root.left = new TreeNode(pre[1]);
                return root;
            }
System.out.println("i: " + i);

            root = new TreeNode(pre[i]);
            int x = m; 
            while (x < n && in[x] != pre[i]) {
                ++x;
            }
            System.out.println("x: " + x);

            root.left = buildTreeRecursion(pre, i + 1, x, in, m, x - 1);
            root.right = buildTreeRecursion(pre, x + 1, pre.length, in, x, in.length);
            return root;
            } */
        
        public TreeNode buildTree(int[] preorder, int[] inorder) {
            TreeNode root = null;
            if (preorder == null || preorder.length == 0) return root;
            else if (preorder.length == 1 && inorder.length == 1) {
                root =  new TreeNode(preorder[0]);
                return root;
            } else if (preorder.length == 2 && inorder.length == 2) {
                root =  new TreeNode(preorder[0]);
                if (preorder[0] == inorder[0])
                    root.right = new TreeNode(preorder[1]);
                else root.left = new TreeNode(preorder[1]);
                return root;
            }
            
            Stack<TreeNode> s = new Stack<TreeNode>();
            root = new TreeNode(preorder[0]);
            TreeNode curr = root;
            int i = 1;
            int j = 0;
            if (inorder[0] != preorder[0]) {
                while (preorder[i] != inorder[j]) {
                    curr.left = new TreeNode(preorder[i++]);
                    curr = curr.left;
                    s.push(curr);
                }
                curr.left = new TreeNode(inorder[j++]);
                if (i + 1 == inorder.length)
                    return root;
            
                curr.right = new TreeNode(inorder[++j]);
                if (!s.isEmpty())
                    s.pop();
                while (!s.isEmpty()) {
                    curr = s.pop();
                    curr.right = new TreeNode(inorder[++j]);
                }
            } else { // ==
                j = i;
                if (preorder[i] != inorder[j])
                    curr.right = new TreeNode(preorder[i]++);
                
                while (i < preorder.length && preorder[i] == inorder[j]) {
                    curr.right = new TreeNode(preorder[i++]);
                    curr = curr.right;
                    ++j;
                    s.push(curr);
                }
                if (preorder.length == i)
                    return root;
                else 
                    curr.left = new TreeNode(inorder[j++]);
                if (i + 1 == inorder.length)
                    return root;
            
                curr.right = new TreeNode(inorder[++j]);
                if (!s.isEmpty())
                    s.pop();
                while (!s.isEmpty()) {
                    curr = s.pop();
                    curr.right = new TreeNode(inorder[++j]);
                }
            }
            if (inorder.length > ++j + 1) {
                root.right = new TreeNode(preorder[++j]);
                curr = root.right;
            }

            
            i = j + 1;
            while (i < preorder.length && preorder[i] != inorder[j]) {
                curr.left = new TreeNode(preorder[i++]);
                curr = curr.left;
                s.push(curr);
            }

            if (i < preorder.length && preorder[i] == inorder[j]) {
            curr.left = new TreeNode(inorder[j++]);
            curr.right = new TreeNode(inorder[++j]);
            if (!s.isEmpty())
                s.pop();
            }
            while (!s.isEmpty()) {
                curr = s.pop();
                curr.right = new TreeNode(inorder[++j]);
            }
            return root;
        }
    }

    public static void main(String[] args){
        Solution result = new Solution();
        int [] a = {1, 2, 3};
        int [] b = {1, 3, 2};
        TreeNode root = result.buildTree(a, b);
        root.levelPrintTree(root);
    }
}

/*
Runtime Error Message:	Line 28: java.lang.IndexOutOfBoundsException: Index: 215, Size: 215
Last executed input:	[100,-69,-138,118,0,-121,248,-88,224,-2,-135,-60,1,7,293,40,-29,46,148,-62,-177,36,104,282,212,-200,288,130,272,113,-166,258,-187,266,206,-75,42,-96,-157,182,-43,-26,242,-50,66,-87,48,-86,52,71,3,208,-94,-85,-140,255,43,-122,-105,-144,264,205,222,230,111,199,-27,68,-145,225,268,149,228,-113,263,-24,15,56,275,23,-165,164,-22,78,-13,-170,-91,137,-6,160,162,262,-45,69,39,-77,-81,-33,-118,-4,-84,-51,296,172,276,54,-104,-189,-82,-74,-58,129,-66,90,-193,128,21,105,291,81,-90,-151,143,-156,45,259,-155,-107,-16,-179,147,80,184,117,58,-174,29,155,200,297,207,-171,-52,-71,-176,-114,234,107,82,278,175,298,77,196,209,-185,-32,-41,-103,-186,-117,204,265,-188,185,289,157,195,91,2,-162,-79,283,61,44,-192,-30,177,-10,-164,202,269,26,125,-70,-89,114,121,-139,246,-125,229,-167,-158,-169,-159,60,217,-93,96,189,-168,-150,159,193,-31,237,-130,99,-152,251,169,134,72,-142,-111,102,-14,231,-131,171,-198,20,-28,-163,-21,-100,103,-110,252,101,16,92,-124,140,-126,219,6,-101,-42,32,64,14,214,-132,166,-35,109,187,-8,-11,70,33,239,138,-44,25,-108,9,271,-116,106,-57,-67,299,-15,218,213,168,211,161,167,-133,238,95,97,-49,232,-183,49,-40,-3,11,-76,136,-97,-65,227,-68,79,93,-137,-46,76,158,108,126,-38,120,173,223,-1,-178,47,178,233,94,30,191,-112,197,5,-136,87,153,22,-37,176,201,-12,192,12,-36,142,41,-5,181,17,270,279,-17,-161,-73,51,-175,-18,74,-180,216,-95,-147,257,260,-127,-80,-172,194,295,13,150,122,-146,-181,247,53,-78,-98,119,-154,-128,144,-134,-106,-9,152,203,-195,-55,116,215,75,-92,-190,-25,-47,281,86,190,188,-72,285,-39,292,127,-194,-153,-7,156,57,284,-48,-141,274,38,18,110,84,37,261,88,154,-197,115,4,73,-148,-109,19,236,290,294,85,-53,-99,-19,250,63,50,-34,245,27,220,139,-63,83,151,-184,179,-64,253,67,34,-160,210,59,-115,-196,174,-102,28,186,-23,241,62,221,267,124,146,-199,286,112,256,135,183,243,65,254,31,8,-182,-119,145,-61,-173,10,131,89,-83,277,24,55,98,280,-20,35,249,-149,244,-59,165,-56,-143,-129,273,-123,-191,226,-120,133,198,235,180,141,132,170,-54,287,123,240,163],
[-121,-88,248,0,224,118,293,40,7,1,148,46,-29,36,104,-177,-62,-60,-166,113,272,130,258,288,-187,206,-75,266,42,-200,182,-157,-43,242,-26,-96,-87,66,-50,48,-86,212,52,71,282,208,-94,-85,3,-140,-122,-105,-144,43,255,230,222,111,205,199,-27,264,-145,225,68,149,268,-135,263,56,275,15,23,-165,-24,-22,164,78,-113,-170,-6,137,160,262,162,-91,-13,69,-45,39,228,-33,-81,-118,-77,-2,-84,296,172,-51,276,-4,-104,-82,-58,-74,-189,129,-66,54,90,-138,128,105,21,81,291,-151,-90,-193,-156,-107,-155,147,184,117,58,80,-179,-174,-171,207,-176,-71,-52,-114,297,200,155,234,29,-16,107,278,82,259,175,45,77,196,209,298,-32,-103,-41,-185,-117,204,-186,265,185,-188,143,2,91,195,283,61,-79,44,-162,157,177,-164,-10,202,-30,269,26,-70,125,114,-89,-192,-125,246,229,-139,-167,121,-169,-159,60,-158,217,289,189,96,193,159,-31,-150,-130,99,237,-168,-152,-93,134,72,169,251,-111,102,-142,-69,20,-198,-28,171,-110,101,252,16,-124,92,103,-100,-21,-126,219,140,6,-163,-42,-101,32,-131,214,14,-132,64,109,187,-35,166,33,70,138,-44,239,25,9,-108,271,-11,-67,-57,106,299,-15,213,218,-116,-8,211,161,168,167,-133,231,-183,232,-49,49,97,-3,-76,136,11,-97,-40,-65,-68,227,79,95,93,238,-46,-137,126,108,158,76,-38,-14,-178,-1,178,47,94,233,223,30,197,-112,-136,5,191,87,22,153,173,-37,176,120,12,192,-12,142,41,-5,181,-36,201,270,279,17,-161,-17,100,-180,74,260,257,-147,-95,216,-80,-127,-18,295,122,-146,150,-181,247,53,13,-98,-78,194,-154,119,-128,-172,-9,152,203,-106,-55,-195,215,75,116,-25,-190,281,-47,-92,188,190,-72,-39,285,292,86,-134,127,144,-194,-175,156,57,284,-7,38,274,-141,-48,-153,110,84,18,4,115,-148,73,-197,19,-109,154,88,290,236,261,294,37,63,250,50,-19,245,27,-34,220,-99,139,-53,83,151,-184,179,-63,85,-64,67,253,51,210,-196,-115,59,-160,-102,174,28,186,-23,34,62,221,241,-199,146,124,267,286,112,-73,31,254,65,-182,8,243,-119,183,145,-61,135,89,-83,131,277,10,-173,98,55,-20,280,35,24,249,-149,244,256,-143,-56,165,226,-191,-123,133,-120,235,198,273,141,170,132,180,-54,-129,123,287,-59,240,163]
 */
