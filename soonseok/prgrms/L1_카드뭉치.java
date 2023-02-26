import java.util.*;

class Solution {
    
    public String solution(String[] cards1, String[] cards2, String[] goal) {
        
        int idx1 = 0;
        int idx2 = 0;
        
        for(int i = 0; i < goal.length; i++) {
            
            if(idx1 < cards1.length && goal[i].equals(cards1[idx1])) { // cards1 매칭
                idx1++;
            }
            else if(idx2 < cards2.length && goal[i].equals(cards2[idx2])) { // cards2 매칭
                idx2++;
            }
            else { // 둘다 매칭
                return "No";
            }
        }
        
        return "Yes";
        
    } // solution()
    
} // class Solution
