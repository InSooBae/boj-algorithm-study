import java.util.*;

class Solution {
    public int solution(int[] order) {
        
        Stack<Integer> stack = new Stack<Integer>();
        
        int total = order.length; // 총 상자 개수
        
        int box = 1; // 컨테이너 벨트에 들어갈 순서의 박스
        int idx = 0; // 현재 적재할 상자의 인덱스
        
        int answer = 0; // 지금까지 적재한 상자의 개수
      
        
        while(idx < total) { 
            
            int cur = order[idx];
            
            if(cur == box) { // 메인 벨트에서 맞춘 경우
                answer ++;
                box ++;
                idx ++;
                
                continue;
            }
            
            if(!stack.isEmpty() && cur == stack.peek()) { // 서브 벨트에서 맞춘 경우
                stack.pop();
                answer ++;
                idx ++;
                
                continue;
            }
            
            stack.push(box); // 이번 박스는 서브벨트에 저장
            box ++;
            
            if(box > total) {
                break;
            }
            
            
        } // while
        
        
        return answer;
        
    } // solution()
}
