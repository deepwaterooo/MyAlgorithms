import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

public class findSubstring {
    public static class Solution {
        public List<Integer> findSubstring(String s, String[] l) {
            List<Integer> res = new ArrayList<Integer>();
            if (s == null || l == null || (s.length() == 0 && l.length == 0)) return null;
            int n = l.length;
            int m = l[0].length();
            if (s.length() < n * m) return res;

            // hashmap to store the strings
            Map<String, Integer> hash = new HashMap<String, Integer>();
            Map<String, Integer> fnd = new HashMap<String, Integer>();
            for(String i : l)
                if (hash.containsKey(i))
                    hash.put(i, hash.get(i) + 1);
                else 
                    hash.put(i, 1);

            int slow = 0;
            int fast = m;
            int one = slow;
            while (fast < s.length()) {
                while (fast < s.length() && !hash.containsKey(s.substring(slow, fast))) {
                    slow++;
                    fast++;
                }
                one = slow;
                //System.out.println("slow 0: " + slow);
                while (fast < s.length() && hash.containsKey(s.substring(slow, fast))) {
                    if (!fnd.containsKey(s.substring(slow, fast)) ||
                        (fnd.containsKey(s.substring(slow, fast)) && hash.get(s.substring(slow, fast)) > 1) ) {
                        if (!fnd.containsKey(s.substring(slow, fast)))
                            fnd.put(s.substring(slow, fast), 1);
                        else
                            fnd.put(s.substring(slow, fast), fnd.get(s.substring(slow, fast)) + 1);
                        int cnt = 0;
                        for(String i : fnd.keySet()) 
                            cnt += fnd.get(i);
                        if (cnt == n) {
                            //System.out.println("one: " + one);
                            //System.out.println("");
                            res.add(one);
                            fnd.remove(s.substring(one, one+m));
                            slow = one + 1;
                            fast = slow + m;
                        } else {
                            slow += m;
                            fast += m;
                        }
                    } else {
                        fnd.clear();
                        //slow = fast;
                        //one = slow;
                        //fast += m;
                        slow = one + 1;
                        fast = slow + m;
                    }
                    //System.out.println("slow 1: " + slow);
                    //System.out.println("fast 0: " + fast);
                }
                
                if (fnd.size() < n) 
                    fnd.clear(); // clear fnd map
                //slow = fast;
                //fast += m;
                slow = one + 1;
                fast = slow + m;
                //System.out.println("fast 1: " + fast);
            }
            return res;
        }
    }

    public static void main(String[] args){
        Solution result = new Solution();
        String [] a = {"aa","aa","aa"};
        String s = "aaaaaaaa";
        List<Integer> res = new ArrayList<Integer>();
        res = result.findSubstring(s, a);

        System.out.println(res);
    }
}

/*
        String [] a = {"fooo","barr","wing","ding","wing"};
        String s = "lingmindraboofooowingdingbarrwingmonkeypoundcake";
  Last executed input:	"aaaaaaaa", ["aa","aa","aa"]
 */
