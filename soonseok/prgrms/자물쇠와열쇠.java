import java.util.*;

// https://school.programmers.co.kr/learn/courses/30/lessons/60059#

class Solution {
    
    public boolean solution(int[][] key, int[][] lock) {
        
        int N = lock.length;
        int M = key.length;
        
        int totalHom = 0; // 총 홈의 개수
        
        int K = N + (M - 1) * 2;
        
        int[][] map = new int[K][K]; // Lock을 가운데 둘 패딩 배열
        for(int r = 0; r < K; r++) {
            Arrays.fill(map[r], -1); // 빈 공간 -1로 채움
        }
        
        for(int r = 0; r < N; r ++) {
            for(int c = 0; c < N; c ++) {
                if(lock[r][c] == 0) totalHom++;
                map[r + (M-1)][c + (M-1)] = lock[r][c];
            }
        }
        
        
        // 배열 준비 완료
        
        int checkSize = N + (M - 1);
        
        for(int i = 0; i < 4; i++) { // key를 회전하는 반복문
            
            
            for(int r = 0; r < checkSize; r++) { // key 위치 옮기기
                for(int c = 0; c < checkSize; c++) {
                    
                    boolean answer = true; // 참이라고 가정
                    int hom = 0; // 채운 홈 개수
                    
                    for(int rr = 0; rr < M; rr++) { // 한 key 조건 체크
                        for(int cc = 0; cc < M; cc++) {
                            if(map[r + rr][c + cc] == -1) continue; // -1이면 체크 안함
                            
                            if(map[r + rr][c + cc] == 1 && key[rr][cc] == 1) { // 돌기에 돌기가 만나면
                                answer = false;
                                break;
                            }
                            
                            if(map[r + rr][c + cc] == 0) { // 홈을 발견
                                if(key[rr][cc] == 0) { // 못 채우면
                                    answer = false;
                                    break;
                                }
                                else {
                                    hom++; // 채운 홈 개수 증가
                                }
                            }
                            
                            
                        }
                        
                        if(!answer) break; // 중간에 실패하면 나간다
                    }
                    
                    if(answer && (hom == totalHom)) return true;
                    
                }
            }
            
            
            if(i == 3) break; // 종료할때는 배열 회전 필요 x
            key = rotateClockwise(key);
        } // for
        
        return false;
        
    } // solution()
    
    // 배열 시계방향으로 회전시키는 함수
    public int[][] rotateClockwise(int[][] map) {
        int size = map.length;
        int[][] newMap = new int[size][size];
        
        // 배열을 우측으로 회전하면, 왼쪽 열의 바닥부터 시작하는 상단 행으로 변경
        for(int c = 0; c < size; c++) {
            for(int r = size - 1; r >= 0; r--) {
                newMap[c][size - (r+1)] = map[r][c];
            }
        }
        
        return newMap;
        
    } // rotateClockwise()
    
}