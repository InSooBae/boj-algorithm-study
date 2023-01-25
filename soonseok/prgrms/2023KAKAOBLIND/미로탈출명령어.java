import java.util.*;

/*
    bfs로 조건에 따라 미로 탈출 진행?
        -> 같은 칸을 두번 이상 방문할 수 있으므로, 방문체크 주의
        -> 모든 경로가 다르므로, 상태의 중복이 없음
        -> 그럼 한칸 이동할 때 마다 4씩 곱해야하므로 문제 발생

    사전 순서 : d < l < r < u
    
    가능한 만큼 d로 움직이고, l로 움직인 이후에
    
    나머지 움직임을 계산한다(공회전?)
    
    현재 지점에서 골인 지점까지 필요한 거리 -> 계산
    움직일 수 있는 남은 이동 거리 -> 계산
    
    이제 이동 거리가 안남아서 최단거리로 골인지점에 가야하는 경우 boolean shouldGo = true
    
    아닌 경우에는 r->l을 몇번 할수있는지 계산하여 추가
    
    마지막에 최단거리 이동을 사전순 고려하여 추가
    
    
*/


class Solution {
    
    int R, C; // map의 행, 열
    int startR, startC; // 시작 위치
    int goalR, goalC; // 골인 지점
    int totalMove; // 움직일수 있는 횟수
    
    StringBuilder sb = new StringBuilder();
    
    int[] dr = {1, 0, 0, -1}; // d < l < r < u
    int[] dc = {0, -1, 1, 0};
    
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        
        init(n, m, x, y, r, c, k); // 변수 전역화
        
        boolean canGo = go();
        
        if(!canGo) return "impossible";
        
        // 아닌 경우 필요한 움직임 더해주고 반환
        
        return sb.toString();
        
        
    } // solution()
    
    public boolean go() {
        int curR = startR; // 현재 위치
        int curC = startC;
        int remainMove = totalMove; // 남은 움직임 횟수
        
        boolean flag = false; // 가야하는 경우
        
        int dCnt = 0;
        while(curR < R && !flag) { // 우선 아래로 움직인다
            dCnt ++;
            curR ++;
            remainMove --;
            flag = shouldGo(curR, curC, remainMove);
        }
        for(int i = 0; i < dCnt; i++) sb.append('d');
        
        int lCnt = 0;
        while(curC > 1 && !flag) { // 왼쪽으로 움직인다
            lCnt ++;
            curC --;
            remainMove --;    
            flag = shouldGo(curR, curC, remainMove);
        }
        for(int i = 0; i < lCnt; i++) sb.append('l');
        
        int needMove = Math.abs(goalR - curR) + Math.abs(goalC - curC); // 골 지점까지 필요한 남은 거리를 계산
        int freeMove = remainMove - needMove; // 자유롭게 움직일 수 있는 거리
            
        if(freeMove < 0 || freeMove % 2 != 0) return false; // 골인 불가능
        
        // freeMove를 2로 나눈만큼 rl을 추가
        for(int i = 0; i < freeMove/2; i++) sb.append("rl");

        remainMove -= freeMove; // 남은 움직임 빼주고
        freeMove -= freeMove; // 자유 움직임 빼주기
        // 자유 움직임 끝
        
        
        // 남은 움직임으로 골대까지 가기
        int remainR = goalR - curR;
        int remainC = goalC - curC;
        
        // dlru
        if(remainR > 0) { // 아래로 더 가야하는 경우
            for(int i = 0; i < Math.abs(remainR); i++) sb.append('d');
        }
        if(remainC < 0) { // 왼쪽으로 더 가야하는 경우
            for(int i = 0; i < Math.abs(remainC); i++) sb.append('l');
        }
        if(remainC > 0) { // 오른쪽으로 더 가야하는 경우
            for(int i = 0; i < Math.abs(remainC); i++) sb.append('r');
        }
        if(remainR < 0) { // 위로 더 가야하는 경우
            for(int i = 0; i < Math.abs(remainR); i++) sb.append('u');
        }
        
        
        return true;
        
    } // go()
    
    public boolean shouldGo(int curR, int curC, int remainMove) {
        int r = Math.abs(curR - goalR);
        int c = Math.abs(curC - goalC);
        
        if(remainMove == r + c) return true;
        
        return false;
    } // shouldGo()
    
    public void init(int n, int m, int x, int y, int r, int c, int k) {
        R = n;
        C = m;
        startR = x;
        startC = y;
        goalR = r;
        goalC = c;
        totalMove = k;
    } // init()
    
} // class Solution