import java.util.*;

/*
    이모티콘의 개수가 최대 7개, 할인율은 4 종류이므로
    
    4^7 가지 경우의 수 완탐 가능

*/

class Solution {
    
    int N; // 유저 수
    int M; // 이모티콘 수
    int[] p;
    int[][] u;
    
    int[] sales;
    
    int maxPlus = 0;
    int maxEmoticon = 0;
    
    public int[] solution(int[][] users, int[] emoticons) {
        
        init(users, emoticons); // 데이터 전역 처리
        
        go(0); // 완전탐색
        
        int[] answer = new int[]{maxPlus, maxEmoticon}; // 정답 반환
        return answer;
        
    } // solution()
    
    public void go(int cnt) {
        if(cnt == M) { // 모든 이모티콘의 할인율을 정했다
            
            calculate(); // 계산하여 반영한다
            
            return;
        }
        
        for(int i = 10; i <= 40; i+=10) { // 10, 20, 30, 40
            sales[cnt] = i; // cnt번 이모티콘의 할인율을 정하고 
            go(cnt + 1); // 다음 이모티콘의 할인율을 정한다
        }
        
    } // go()
    
    public void calculate() {
        
        int plus = 0; // 플러스 가입자
        int emoticon = 0; // 이모티콘 매출액
        
        for(int i = 0; i < N; i++) { // 각 유저별로 계산
            int rule = u[i][0]; // 유저는 rule 이상의 할인율 이모티콘은 모두 구매한다
            int threshold = u[i][1]; // 유저는 threshold 이상의 구매액이면, 플러스에 가입한다
            
            int sum = 0; // 유저의 이모티콘 총 구매액
            
            for(int j = 0; j < M; j++) { // 모든 이모티콘에 대해 계산
                if(sales[j] < rule) continue; // 기준 미만의 할인율이라면, 구매하지 않는다
                
                // 구매하는 경우
                int salePrice = p[j] * (100 - sales[j]) / 100; // 할인 판매액 계산
                sum += salePrice;
            }
            
            if(sum >= threshold) plus++; // 기준액 이상이면 플러스 가입
            else emoticon += sum; // 아니면 이모티콘 구매액만 더해준다    
        }
        
        // 이번 할인 경우의 수에서 계산이 끝난 후
        if(plus > maxPlus) { // 가입자를 더 많이 확보했다면
            maxPlus = plus; // 가입자와 판매액 갱신
            maxEmoticon = emoticon;
        }
        else if(plus == maxPlus) { // 가입자가 동률이라면, 판매액만 갱신
            maxEmoticon = Math.max(maxEmoticon, emoticon);
        }
        
        return;
        
    } // calculate()
    
    public void init(int[][] users, int[] emoticons) {
        N = users.length;
        M = emoticons.length;
        
        u = new int[N][2];
        for(int i = 0; i < N; i++) {
            u[i][0] = users[i][0];
            u[i][1] = users[i][1];
        }
        
        p = new int[M];
        for(int i = 0; i < M; i++) {
            p[i] = emoticons[i];
        }
        
        sales = new int[M];
        
    } // init()
    
} // class Solution