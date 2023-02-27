import java.util.*;

class Solution {
    
    static char[][] map = new char[3][3];
    static int o = 0;
    static int x = 0;
    
    static int[] tic = new int[100];
    
    public int solution(String[] board) {
        
        int answer = 1;
        
        for(int r = 0; r < 3; r++) {
            for(int c = 0; c < 3; c++) {
                map[r][c] = board[r].charAt(c);
                if(board[r].charAt(c) == '.') continue;
                
                if(map[r][c] == 'O') o++;
                if(map[r][c] == 'X') x++;
            }
        } // 값 입력 끝
        
        // 1. O, X 개수 체크
        if(o < x) answer = 0;
        if((o - x) > 1) answer = 0; 
        
        // 2. O, X 둘 다 이긴 경우
        checkTictacto('O');
        checkTictacto('X');
        if(tic['O'] > 1 &&tic['X'] > 1) answer = 0;
        
        // 3. 만약 X 틱택토가 있다면, O는 X와 개수가 같아야 함
        if(tic['X'] == 1 && (o-x) != 0) answer = 0; 
        
        // 4. 만약 O 틱택토가 있다면, O는 X보다 1개 많아야 함
        if(tic['O'] == 1 && (o-x) != 1) answer = 0;
        
        return answer;
        
    } // solution()
    
    static void checkTictacto(char c) {
        if(map[0][0] == c && map[0][1] == c && map[0][2] == c) tic[c]++;
        if(map[1][0] == c && map[1][1] == c && map[1][2] == c) tic[c]++;
        if(map[2][0] == c && map[2][1] == c && map[2][2] == c) tic[c]++;
        
        if(map[0][0] == c && map[1][0] == c && map[2][0] == c) tic[c]++;
        if(map[0][1] == c && map[1][1] == c && map[2][1] == c) tic[c]++;
        if(map[0][2] == c && map[1][2] == c && map[2][2] == c) tic[c]++;
        
        if(map[0][0] == c && map[1][1] == c && map[2][2] == c) tic[c]++;
        if(map[0][2] == c && map[1][1] == c && map[2][0] == c) tic[c]++;
    } // checkTictacto()
    
} // class Solution
