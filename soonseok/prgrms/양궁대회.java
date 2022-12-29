package prgrms;

import java.util.*;

class Solution {
    
    static int N;
    static int[] apeach;
    static int maxscore;
    static int[] lion;
    
    public int[] solution(int n, int[] info) {
        
        N = n;
        apeach = info.clone();
        maxscore = 0;
        lion = new int[11];
        
        dfs(0, n, 0, new int[11]);
        
        int apeachScore = 0;
        for(int i = 0; i < 11; i++) {
            if(info[i] == 0) continue;
            
            apeachScore += 10 - i;
        }
        
        // 어피치랑 라이언이 비기는 경우에도 우승이 불가능!!!
        if(maxscore <= apeachScore) return new int[]{-1};
        
        return lion;
        
    } // solution()
    
    
    /**
        idx : 과녁 점수 (10-idx) (0~10)
        arrow : 나에게 남은 화살 수
        myscore : 현재까지 얻은 점수
        shot[] : 쏜 화살 기록
    */
    public void dfs(int idx, int arrow, int myscore, int[] shot) {
        // 기저조건 :
        //  화살을 다 쓴 경우
        //  idx가 10인 경우(더이상 점수를 얻을 수 없음)
        if(arrow == 0 || idx == 10) {
            if(myscore < maxscore) return;
            
            if(myscore > maxscore) {
                
                maxscore = myscore;
                
                lion = shot.clone();
                lion[10] += arrow; // 남은 화살 0점에 몰아주기
                
                return;
            }
            
            if(myscore == maxscore) {
                // 동점인 경우 낮은 점수부터 비교
                if(arrow > lion[10]) {
                    lion = shot.clone();
                    lion[10] += arrow; // 남은 화살 0점에 몰아주기
                    return;
                }
                
                for(int i = 9; i >= 0; i--) {                    
                    if(shot[i] == lion[i]) {
                        continue;
                    }
                    
                    if(shot[i] > lion[i]) {
                        
                        lion = shot.clone();
                        
                        return;
                    }
                    else return;
                }
            }
            
            return;
        }
        
        
        // 이번 점수의 화살을 선택하고 진행
        if(arrow > apeach[idx]) {
            // 어피치가 맞춘 경우 얻는 점수 기댓값은 2배다
            int tempscore = (apeach[idx] == 0) ? 10-idx : (10-idx) * 2;
            
            // 어피치보다 딱 한발만 더 맞추면 점수를 얻는다
            shot[idx] = apeach[idx] + 1;
            
            dfs(idx + 1, arrow - (apeach[idx] + 1), myscore + tempscore, shot);
            
            shot[idx] = 0; // 다시 안 맞춘것처럼 세팅
        }
        
        // 이번 점수의 화살을 미선택하고 진행
        dfs(idx + 1, arrow, myscore, shot);
        
        
    } // dfs()
        
        
}
