import java.util.*;

class Solution {
    
    public static int[] solution(String[] keymap, String[] targets) {
        List<Integer> list = new ArrayList<>();
        
        // 모든 target에 대해 반복
        for(int i = 0; i < targets.length; i++) {
            list.add(makeTarget(keymap, targets[i]));
        }
     
        
        // 결과 반환
        return list.stream().mapToInt(Integer::intValue).toArray();
        
    } // solution()
    
    public static int makeTarget(String[] keymap, String target) {
        int count = 0;
        
        for(int i = 0; i < target.length(); i++) {
            char cur = target.charAt(i);
            int minCount = Integer.MAX_VALUE;
            
            for(int j = 0; j < keymap.length; j++) {
                
                int curCount = keymap[j].indexOf(cur);
                
                if(curCount == -1) continue; // -1이면 없음
                
                curCount++;
                
                minCount = Math.min(minCount, curCount);
            }
            
            if(minCount == Integer.MAX_VALUE) return -1; // 불가능
            
            count += minCount;
        }
        
        return count;
        
    } // makeTarget()

   
    
} // class Solution
