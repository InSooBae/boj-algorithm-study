import java.util.*;

/*
    https://school.programmers.co.kr/learn/courses/30/lessons/1832
    
    배열의 크기가 최대 500*500 이므로, 완탐은 불가능, bfs도 비효율
    dp를 사용해야 함

*/

class Solution {
    static int MOD = 20170805;
    
    public int solution(int m, int n, int[][] cityMap) {
        
        int[][][] answer = new int[m][n][2]; // 왼쪽0에서 왔는지 위1에서 왔는지 기록
        
        // 맨 위 행과 왼쪽 열은 항상 1이거나, 0이다
        for(int c = 0; c < n; c++) { // 맨 위 행 ( r == 0 )
            if(cityMap[0][c] == 1) break; // 통행이 금지됐으면 갈 수 없다
            
            answer[0][c][0] = 1;
        }
        
        for(int r = 0; r < m; r++) { // 맨 왼쪽 열 ( c == 0 )
            if(cityMap[r][0] == 1) break;
            
            answer[r][0][1] = 1; 
        }
        
        // dp를 돌린다
        for(int r = 1; r < m; r++) {
            for(int c = 1; c < n ; c++) {
                
                if(cityMap[r][c] == 1) continue; // 지나갈 수 없는 경우
                
                // 나머지 경우에는 이전 표지판 상태에 따라 달라짐
                if(cityMap[r-1][c] == 2) { // 위쪽에서 일방통행으로 온 경우
                    answer[r][c][1] += answer[r-1][c][1] % MOD; // 이전에도 위에서 온 것 만
                }
                else { // 위쪽에서 일방통행이 아니라면, 둘 다 더해줄 수 있다
                    answer[r][c][1] += (answer[r-1][c][0] + answer[r-1][c][1]) % MOD;
                }
                
                if(cityMap[r][c-1] == 2) { // 왼쪽에서 일방통행으로 온 경우
                    answer[r][c][0] += answer[r][c-1][0] % MOD; // 이전에도 왼쪽에서 온 것 만
                }
                else {
                    answer[r][c][0] += (answer[r][c-1][0] + answer[r][c-1][1]) % MOD;
                }
                
            }
        }
        
        // for(int r = 0; r < m; r++) {
        //     for(int c = 0; c < n; c++) {
        //         System.out.print(Arrays.toString(answer[r][c]));
        //     }
        //     System.out.println();
        // }
        

        return (answer[m-1][n-1][0] + answer[m-1][n-1][1]) % MOD; /// 정답 반환
        
    } // solution()
} // class Solution