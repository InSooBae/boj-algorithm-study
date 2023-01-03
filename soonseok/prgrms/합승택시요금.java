import java.util.*;

/*
    n : 지점 개수, 1~n 지점 번호 ( 3 ≤ n ≤ 200)
    s : 출발 지점
    a : A의 도착 지점
    b : B의 도착 지점
    fares : 지점 사이의 택시 요금
    
    1 ≤ 요금 ≤ 100,000
    
    플로이드와샬
*/

class Solution {
    
    static int[][] map;
    static int MAX = 90000000;
    
    public int solution(int n, int s, int a, int b, int[][] fares) {
        
        init(n, fares); // 배열 초기화
        
        // 플로이드 와샬
        for(int k = 1; k <= n; k++)
            for(int u = 1; u <= n; u++)
                for(int v = 1; v <= n; v++)
                    // u -> v 로 가는 비용과
                    // u -> k -> v 로 가는 비용을 비교하여, 최소 비용으로 갱신
                    map[u][v] = Math.min(map[u][v], map[u][k] + map[k][v]);
        
        
        int answer = MAX;
        
        for(int i = 1; i <= n; i++) {
            // 시작지점 s -> i 까지의 비용과
            // i -> a, i -> b 까지 비용을 모두 합계한 최솟값으로 갱신
            answer = Math.min(answer, map[s][i] + map[i][a] + map[i][b]);
        }
        
        return answer;
        
    } // solution()
    
    // 배열 초기화
    public void init(int n, int[][] fares) {
        map = new int[n + 1][n + 1];
        
        for(int i = 1; i <= n; i++) { // 배열을 최댓값으로 초기화
            for(int j = 1; j <= n; j++) {
                if(i == j) continue; // 자기 자신으로 가는 비용은 0
                
                map[i][j] = MAX;
            }
        }
        
        for(int[] r : fares) { // 주어진 비용을 배열에 적용
            int nodeA = r[0];
            int nodeB = r[1];
            int fare = r[2];            
            
            map[nodeA][nodeB] = map[nodeB][nodeA] = fare;
        }
    } // init()
    
} // class Solution