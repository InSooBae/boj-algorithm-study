import java.util.*;

class Solution {
    public int solution(int x, int y, int n) {
        return bfs(x,y,n);
    } // solution()
    
    int bfs(int start, int target, int n) {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visit = new boolean[1_000_001];
        
        // 시작지점 방문
        visit[start] = true;
        queue.add(start);
        
        int count = 0;
        
        while(!queue.isEmpty()) {
            int size = queue.size();
            
            while(--size >= 0) {
                int cur = queue.poll();
                
                if(cur == target) return count; // 정답 발견
                
                int plus = cur + n;
                int twice = cur * 2;
                int triple = cur * 3;
                
                // x + n 처리
                if(plus <= 1_000_000 && !visit[plus]) {
                    visit[plus] = true;
                    queue.add(plus);
                }
                
                // x + 2 처리
                if(twice <= 1_000_000 && !visit[twice]) {
                    visit[twice] = true;
                    queue.add(twice);
                }
                
                // x + 3 처리
                if(triple <= 1_000_000 && !visit[triple]) {
                    visit[triple] = true;
                    queue.add(triple);
                }
            }
            
            count ++;
            
        } // outer while
        
        return -1;
        
    } // bfs()
} // class Solution
