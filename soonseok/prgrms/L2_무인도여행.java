import java.util.*;

class Solution {
    
    static int R;
    static int C;
    static int[][] map;
    static boolean[][] visit;
    
    static int[] dr = {-1,0,0,1};
    static int[] dc = {0,-1,1,0};
    
    public int[] solution(String[] maps) {
        
        R = maps.length;
        C = maps[0].length();
        
        map = new int[R][C];
        visit = new boolean[R][C];
        
        for(int r = 0; r < R; r++) { // 맵 생성
            for(int c = 0; c < C; c++) {
                if(maps[r].charAt(c) == 'X') continue;
                
                map[r][c] = maps[r].charAt(c) - '0';
            }
        }
        
        
        List<Integer> island = new ArrayList<>();
        
        for(int r = 0; r < R; r++) { // 맵 탐색
            for(int c = 0; c < C; c++) {
                if(visit[r][c] || map[r][c] == 0) continue; // 이미 방문했거나, 섬이 없다면 넘어간다
                
                island.add(bfs(r, c));
                
            }
        }
        
        if(island.isEmpty()) return new int[]{-1}; // 섬이 없는 경우 [-1] 반환
        
        int size = island.size();
        int[] answer = new int[size];
        for(int i = 0; i < size; i++) {
            answer[i] = island.get(i);
        }
        
        Arrays.sort(answer);
        return answer;
    } // solution()
    
    public int bfs(int startR, int startC) {
        Queue<int[]> queue = new LinkedList<>();
        int food = 0;
        
        // 시작점 방문
        visit[startR][startC] = true;
        food += map[startR][startC];
        queue.add(new int[]{startR, startC});
        
        while(!queue.isEmpty()) {
            
            int size = queue.size();
            
            while(--size >= 0) {
                
                int[] cur = queue.poll();
                
                for(int i = 0; i < 4; i ++) {
                    int newR = cur[0] + dr[i];
                    int newC = cur[1] + dc[i];
                    
                    if(newR < 0 || newC < 0 || newR >= R || newC >= C) continue; // 지도 벗어남
                    
                    if(visit[newR][newC] || map[newR][newC] == 0) continue; // 이미 방문했거나, 섬이 연결되지 않은 경우
                    
                    // 방문 처리
                    visit[newR][newC] = true;
                    food += map[newR][newC];
                    queue.add(new int[]{newR, newC});
                }
                
            } // while for one bfs depth
            
        } // while for total bfs
        
        return food;
        
    } // bfs()
    
}
