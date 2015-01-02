import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

public class findMinimumInRotatedSortedArrayDup {
    public static class Solution {
        public  int helper(int[] num, int start, int end) {
            if (start == end)  {
                return num[start];
            } else if (start == end -1) {
                return num[start] < num[end] ? num[start] : num[end];
            }
            int mid = start + (end - start) / 2;
            if (num[mid] > num[end]) { // stands
                return helper(num, mid+1, end);
            } else if (num[mid] < num[end]){ // I think it stands
                return helper(num, start, mid);
            } else { // when equals, don't know
                if (num[start] != num[end]) {  // left
                    return helper(num, start, mid);  // if start+1, skipped case like [1,3,3]
                } else {
                    return Math.min(helper(num, start, mid), helper(num, mid, end));
                }
            }
        }
        public int findMin(int[] num) {
            if (num.length == 1) {
                return num[0];
            }
            return helper(num, 0, num.length-1);
        }
    }

    public static void main(String[] args){
        Solution result = new Solution();
        int [] a = {2, 0, 1, 2, 2, 2, 2, 2, 2};
        int res = result.findMin(a);
        
        System.out.println(res);
    }
}
