import java.util.*;

/*
info가 최소 2, 최대 17이므로 실제 tree 형태로 구현하여 dfs 완탐 가능

이진트리 형태 고정

https://blog.encrypted.gg/1029 -> 상태의 중복 처리 필요
*/

class Solution {
    
    int N;
    int answer = 1; // 양은 최소 1개부터 시작
    int[] left = new int[20];
    int[] right = new int[20];
    int[] animal = new int[20];
    boolean[] visit = new boolean[1<<17]; // 비트마스킹으로 2^17개의 방문 상태 체크
    
    public int solution(int[] info, int[][] edges) {
        
        init(info, edges); // 입력값 전역으로 초기화
        
        dfs(1); // 0번 노드(root)를 방문하면서 시작
        
        return answer; 
        
    } // solution()
    
    public void dfs(int state) {
        if(visit[state]) return; // 이미 해당 비트마스킹 상태롤 방문했다면 종료
        visit[state] = true; // 방문처리
        
        int total = 0;
        int wolf = 0;
        
        for(int i = 0; i < N; i++) {
            if((state & (1<<i)) != 0) { // i번째 노드를 방문했다면
                total++; // 현재 방문 상황에서 (늑대 + 양) 값을 누적
                wolf += animal[i]; // 늑대인 경우 1이므로, 늑대 값 누적
            }
        }
        
        if(wolf * 2 >= total) return; // 늑대가 총 동물의 절반 이상을 차지하면, 종료
        
        answer = Math.max(answer, total - wolf); // 현재 양의 마리수가 최대면 갱신한다
        
        // 다음 노드로 방문한다
        for(int i = 0; i < N; i++) {
            // 현재 상태에서 방문한 정점의 자식들로만 진행이 가능하다.
            // 따라서 state에 0인 정점은 방문하지 않았으니 자식으로 진행할 수 없다.
            if((state & (1<<i)) == 0) continue;
            
            if(left[i] != -1) // 왼쪽 자식이 있다면, 왼쪽 자식을 방문한 state로 마스킹
                dfs(state | (1<<left[i]));
            
            if(right[i] != -1) // 오른쪽 자식이 있다면, 오른쪽 자식을 방문한 state로 마스킹
                dfs(state | (1<<right[i]));
        }
    } // dfs()
    
    public void init(int[] info, int[][] edges) {
        N = info.length;
        
        Arrays.fill(left, -1);
        Arrays.fill(right, -1);
        
        for(int i = 0; i < info.length; i++)
            animal[i] = info[i]; // 양, 늑대 정보 초기화
        
        for(int i = 0; i < edges.length; i++) {
            int parent = edges[i][0];
            int child = edges[i][1];
            
            if(left[parent] == -1) { // 이진 트리
                left[parent] = child;
                continue;
            }
            
            right[parent] = child;
        }
    } // init()
    
} // class Solution